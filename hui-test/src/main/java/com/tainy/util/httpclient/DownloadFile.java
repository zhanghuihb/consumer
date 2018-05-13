package com.tainy.util.httpclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Tainy
 * @date 2018/5/12
 */
public class DownloadFile {


    /**
     * 2      * TODO 下载文件到本地
     * 3      * @author nadim
     * 4      * @date Sep 11, 2015 11:45:31 AM
     * 5      * @param fileUrl 远程地址
     * 6      * @param fileLocal 本地路径
     * 7      * @throws Exception
     * 8
     */
    public static void downloadFile(String fileUrl, String fileLocal) throws Exception {
        URL url = new URL(fileUrl);
        HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
        urlCon.setConnectTimeout(6000);
        urlCon.setReadTimeout(6000);
        int code = urlCon.getResponseCode();
        if (code != HttpURLConnection.HTTP_OK) {
            throw new Exception("文件读取失败");
        }
        //读文件流
        DataInputStream in = new DataInputStream(urlCon.getInputStream());
        DataOutputStream out = new DataOutputStream(new FileOutputStream(fileLocal));
        byte[] buffer = new byte[2048];
        int count = 0;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        out.close();
        in.close();
    }
}
