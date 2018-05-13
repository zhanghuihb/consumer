package com.tainy.webmagic.processor.爬取歌曲;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/5/11
 */
@Data
public class Music implements Serializable{

    private Integer musicId;

    private String song;

    private String album;

    private String artist;

    private String getUrl;

    private String highDownUrl;

    private String lowDownUrl;// 下载地址

    private String msg;

    private Integer type;
}
