package com.tainy.webmagic.util;

import com.tainy.util.httpclient.DownloadFile;
import com.tainy.webmagic.processor.爬取歌曲.Constants;
import com.tainy.webmagic.processor.爬取歌曲.Music;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.CollectionUtils;

import java.sql.*;
import java.util.List;

/**
 * @author Tainy
 * @date 2017/12/8
 */
public class JdbcUtil {
    // 创建静态全局变量
    public static Connection conn;

    public static Statement st;

    static {
        if(conn == null){
            conn = getConnection(); // 首先要获取连接，即连接到数据库
        }
    }

    /** 批量插入 */
    public static void batchInsert(List<Music> musicList) throws Exception {
        if(!CollectionUtils.isEmpty(musicList)){
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into music (song,album,artist,getUrl,highDownUrl,lowDownUrl,msg,type) values (?,?,?,?,?,?,?,?)");
            // 批量插入一页
            for(int i = 0 ; i < musicList.size() ; i++){
                ps.setString(1,musicList.get(i).getSong());
                ps.setString(2,musicList.get(i).getAlbum());
                ps.setString(3,musicList.get(i).getArtist());
                ps.setString(4,musicList.get(i).getGetUrl());
                ps.setString(5,musicList.get(i).getHighDownUrl());
                ps.setString(6,musicList.get(i).getLowDownUrl());
                ps.setString(7,"masg");
                ps.setInt(8,1);

                ps.executeUpdate();
            }

            conn.commit();
            ps.close();
            System.out.println("向Mysql数据库中插入 " + musicList.size() + " 条数据");
            // 插入完成后，开始下载
            for(int j = 0 ; j < musicList.size() ; j++){
                DownloadFile.downloadFile(musicList.get(j).getHighDownUrl(), Constants.FILE_LOCAL + "/" + musicList.get(j).getSong() + ".mp3");
            }
        }
    }

    /* 插入数据记录，并输出插入的数据记录数*/
    public static void insert(String sql, String name) throws SQLException {

        try {
            st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象

            int count = st.executeUpdate(sql);  // 执行插入操作的sql语句，并返回插入数据的个数

            System.out.println("向Mysql数据库表 " + name + " 中插入 " + count + " 条数据"); //输出插入操作的处理结果

        } catch (SQLException e) {
            System.out.println("插入数据失败" + e.getMessage());
        }finally {
            st.close();
        }
    }

    /* 更新符合要求的记录，并返回更新的记录数目*/
    public static void update() {
        try {
            String sql = "update staff set wage='2200' where name = 'lucy'";// 更新数据的sql语句

            st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量

            int count = st.executeUpdate(sql);// 执行更新操作的sql语句，返回更新数据的个数

            System.out.println("staff表中更新 " + count + " 条数据");      //输出更新操作的处理结果

            conn.close();   //关闭数据库连接

        } catch (SQLException e) {
            System.out.println("更新数据失败");
        }
    }

    /* 查询数据库，输出符合要求的记录的情况*/
    public static void query() {
        try {
            String sql = "select * from staff";     // 查询数据的sql语句
            st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量

            ResultSet rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集
            System.out.println("最后的查询结果为：");
            while (rs.next()) { // 判断是否还有下一个数据

                // 根据字段名获取相应的值
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String address = rs.getString("address");
                String depart = rs.getString("depart");
                String worklen = rs.getString("worklen");
                String wage = rs.getString("wage");

                //输出查到的记录的各个字段的值
                System.out.println(name + " " + age + " " + sex + " " + address
                        + " " + depart + " " + worklen + " " + wage);

            }
            conn.close();   //关闭数据库连接

        } catch (SQLException e) {
            System.out.println("查询数据失败");
        }
    }

    /* 删除符合要求的记录，输出情况*/
    public static void delete() {
        try {
            String sql = "delete from staff  where name = 'lili'";// 删除数据的sql语句
            st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量

            int count = st.executeUpdate(sql);// 执行sql删除语句，返回删除数据的数量

            System.out.println("staff表中删除 " + count + " 条数据\n");    //输出删除操作的处理结果

            conn.close();   //关闭数据库连接

        } catch (SQLException e) {
            System.out.println("删除数据失败");
        }

    }

    /* 获取数据库连接的函数*/
    public static Connection getConnection() {
        Connection con = null;  //创建用于连接数据库的Connection对象
        try {
            Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hui_project", "root", "root");// 创建数据连接

        } catch (Exception e) {
            System.out.println("数据库连接失败" + e.getMessage());
        }
        return con; //返回所建立的数据库连接
    }
}
