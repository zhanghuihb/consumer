package com.tainy.util.page;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.util.JdbcUtils;
import com.tainy.util.constant.MogoConstant;
import com.tainy.util.vo.CommHisConfigInfo;
import com.tainy.util.vo.CommonContext;
import com.tainy.util.vo.HisTableInfo;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.plugin.Invocation;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @brief 历史数据记录
 * @details 对特定业务数据的操作,记录其历史数据
 * @author szl
 * @date 2016.6.23
 * @note 其相关历史数据记录规则配置在comm_business_record_mapping表中
 */
public class AutoHistoryExecuter {

	private static Logger LOGGER = Logger.getLogger(AutoHistoryExecuter.class);

	/**
	 * 记录历史数据
	 *
	 * @param ivk
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public Object savaHisData(Invocation ivk) throws Exception {
		Object returnObj = null;
		CommonContext commonContext = MogoConstant.currentCommonContext.get();

		if (commonContext != null && commonContext.isNeedSaveHis()) {

			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler,
					"delegate");

			Statement stat = (Statement) ivk.getArgs()[0];
			HistoryOperatorInfo historyOperatorInfo = this.getHisTableInfo(delegate.getBoundSql().getSql(), commonContext,
					stat.getConnection());

			if (historyOperatorInfo.getTableNameHis() != null) {

				if (historyOperatorInfo.isInsert()) {
					returnObj = ivk.proceed();

					historyOperatorInfo.setId(getAutoId(stat));

					upateCommBusinessRecordForOrderId(historyOperatorInfo, stat.getConnection());
				}

				insertHisData(historyOperatorInfo, stat.getConnection());

				if (!historyOperatorInfo.isInsert()) {
					returnObj = ivk.proceed();
				}
			}
			if (commonContext != null) {
				commonContext.setNeedSaveHis(false);
			}
		}
		if (returnObj == null) {
			returnObj = ivk.proceed();
		}
		return returnObj;
	}

	private void upateCommBusinessRecordForOrderId(HistoryOperatorInfo historyOperatorInfo, Connection conn) {
		String sql = "update  comm_business_record set orderId =  ? where id = ?";
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setInt(1, historyOperatorInfo.getId());
			preparedStatement.setInt(2, historyOperatorInfo.getSoDoneCode());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}

	private HistoryOperatorInfo getHisTableInfo(String sql, CommonContext commonContext, Connection conn) {

		HistoryOperatorInfo historyOperatorInfo = new HistoryOperatorInfo();

		// PreparedStatement stat = null;
		// PreparedStatement recordMapStat = null;

		String sqlInfo[] = getTableName(sql);

		String originTableName = sqlInfo[1];

		historyOperatorInfo.setTableName(originTableName);
		historyOperatorInfo.setInsert(sqlInfo[0].equals("insert"));
		historyOperatorInfo.setBizType(commonContext.getBizType());
		historyOperatorInfo.setId(commonContext.getOrderId());
		historyOperatorInfo.setSoDoneCode(commonContext.getSoDoneCode());

		HisTableInfo hisTableInfo = CommHisConfigInfo.get().get(commonContext.getBizType(), originTableName);
		if (hisTableInfo != null) {
			historyOperatorInfo.setTableNameHis(hisTableInfo.getTableHisName());
			historyOperatorInfo.setPrimaryKeyColumnName(hisTableInfo.getHisPrimaryKeyColumnName());
		}
		/*
		 * try {
		 * 
		 * 
		 * String getHisInfoSQL =
		 * "select * from comm_business_record_mapping where busiType = ? and tableName = ? and isCommWrite = ? limit 1"
		 * ; recordMapStat = conn.prepareStatement(getHisInfoSQL);
		 * recordMapStat.setInt(1, commonContext.getBizType());
		 * recordMapStat.setString(2, originTableName); recordMapStat.setInt(3,
		 * 1);
		 * 
		 * ResultSet rs = recordMapStat.executeQuery(); Map<String,Object>
		 * dataRecordMap = convertResultSetToMap(rs); rs.close();
		 * 
		 * if(dataRecordMap !=null){
		 * historyOperatorInfo.setTableNameHis((String)
		 * dataRecordMap.get("tableNameHis"));
		 * historyOperatorInfo.setPrimaryKeyColumnName
		 * ((String)dataRecordMap.get("checkUniColumn")); }
		 * 
		 * } catch (SQLException e) { logger.error(e.getMessage(), e); }finally{
		 * try { //if(stat!=null){ // stat.close(); //}
		 * 
		 * if(recordMapStat!=null){ recordMapStat.close(); }
		 * 
		 * } catch (SQLException e) { logger.error(e.getMessage(), e); } }
		 */

		return historyOperatorInfo;
	}

	private Map<String, Object> convertResultSetToMap(ResultSet rs) throws SQLException {
		if (!rs.next()) {
			return null;
		}

		ResultSetMetaData rsMeta = rs.getMetaData();
		int columnCount = rsMeta.getColumnCount();
		Map<String, Object> dataMap = new HashMap<String, Object>(columnCount);
		for (int i = 1; i <= columnCount; i++) {
			String key = rsMeta.getColumnLabel(i);
			Object value = rs.getObject(key);

			dataMap.put(key, value);
		}

		return dataMap;
	}

	private String[] getTableName(String sql) {
		String[] sqlInfo = new String[2];
		SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcUtils.ORACLE);
		List<SQLStatement> stmtList = parser.parseStatementList(); //
		SQLStatement stmt = stmtList.get(0);

		if (stmt instanceof SQLInsertStatement) {
			sqlInfo[0] = "insert";
			sqlInfo[1] = ((SQLInsertStatement) stmt).getTableName().getSimpleName();

			return sqlInfo;
		} else if (stmt instanceof SQLUpdateStatement) {
			sqlInfo[0] = "update";
			sqlInfo[1] = ((SQLUpdateStatement) stmt).getTableName().getSimpleName();

			return sqlInfo;
		} else if (stmt instanceof SQLDeleteStatement) {

			sqlInfo[0] = "delete";
			sqlInfo[1] = ((SQLDeleteStatement) stmt).getTableName().getSimpleName();

			return sqlInfo;
		}

		return null;
	}

	private void insertHisData(HistoryOperatorInfo historyOperatorInfo, Connection conn) {

		if (historyOperatorInfo.getId() == null) {
			return;
		}

		String sql = "select * from " + historyOperatorInfo.getTableName() + " where id = ?";

		PreparedStatement preparedStatement = null;

		PreparedStatement insertStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setInt(1, historyOperatorInfo.getId());

			ResultSet rs = preparedStatement.executeQuery();

			Map<String, Object> dataMap = this.convertResultSetToMap(rs);

			rs.close();

			Iterator<String> it = dataMap.keySet().iterator();

			String column = "";
			String value = "";
			while (it.hasNext()) {
				String key = it.next();
				if (key.equals("id")) {
					continue;
				}

				column = column + key + ",";
				value = value + "?,";

			}

			column = column + historyOperatorInfo.getPrimaryKeyColumnName() + ",soDoneCode";
			value = value + "?,?";

			String insertSql = "insert into " + historyOperatorInfo.getTableNameHis() + "(" + column + ") values (" + value
					+ ")";

			insertStatement = conn.prepareStatement(insertSql);

			it = dataMap.keySet().iterator();

			int index = 1;
			while (it.hasNext()) {

				String key = it.next();
				if (key.equals("id")) {
					continue;
				}
				insertStatement.setObject(index, dataMap.get(key));
				index++;
			}
			insertStatement.setInt(index, historyOperatorInfo.getId());

			insertStatement.setInt(index + 1, historyOperatorInfo.getSoDoneCode());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {

			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (preparedStatement != null) {
					insertStatement.close();
				}

			} catch (SQLException e) {
				LOGGER.error(e.getMessage(), e);
			}

		}

	}

	private Integer getAutoId(Statement stat) {
		ResultSet rs = null;
		Integer autoId = null;
		try {
			rs = stat.getGeneratedKeys();
			if (!rs.next()) {
				return null;
			}
			autoId = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return autoId;
	}
}
