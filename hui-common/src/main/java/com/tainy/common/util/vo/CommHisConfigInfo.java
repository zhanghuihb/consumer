package com.tainy.common.util.vo;

import java.util.HashMap;
import java.util.Map;

public class CommHisConfigInfo {

	private final static CommHisConfigInfo commHisConfigInfo = new CommHisConfigInfo();
	private Map<String, HisTableInfo> hisConfigMap = new HashMap<String, HisTableInfo>();

	public static CommHisConfigInfo get() {
		return commHisConfigInfo;
	}

	public void add(Integer busiType, String tableName, String tableHisName, String hisPrimaryKeyColumnName) {
		HisTableInfo hisTableInfo = new HisTableInfo();

		hisTableInfo.setHisPrimaryKeyColumnName(hisPrimaryKeyColumnName);
		hisTableInfo.setTableHisName(tableHisName);

		hisConfigMap.put(busiType + "-" + tableName.toLowerCase(), hisTableInfo);
	}

	public HisTableInfo get(Integer busiType, String tableName) {
		return hisConfigMap.get(busiType + "-" + tableName.toLowerCase());
	}

}
