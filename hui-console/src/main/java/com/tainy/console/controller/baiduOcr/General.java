package com.tainy.console.controller.baiduOcr;

import com.tainy.console.controller.baiduOcr.util.Base64Util;
import com.tainy.console.controller.baiduOcr.util.FileUtil;
import com.tainy.console.controller.baiduOcr.util.HttpUtil;

import java.io.File;
import java.net.URLEncoder;

/**
 * OCR通用识别
 *
 * @author Tainy
 * @date 2018/3/30
 */
public class General {

    public static String genreal(String filePath){
        // 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
        // 本地图片路径
        // String filePath = "D:\\Tesseract-OCR\\tessdata\\willbeopreatedimages\\tesseract.jpg";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = "24.cb89cc65acdf5260cf84a2d0db855341.2592000.1529300214.282335-11023485";
            String result = HttpUtil.post(otherHost, accessToken, params);
            System.out.println(result);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static void main(String[] args) {
        // 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
        // 本地图片路径
        String filePath = "D:\\Tesseract-OCR\\tessdata\\willbeopreatedimages\\tesseract.jpg";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = "24.04ff31a25cc1f5ea0eece5656bd11851.2592000.1524990509.282335-11023485";
            String result = HttpUtil.post(otherHost, accessToken, params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
