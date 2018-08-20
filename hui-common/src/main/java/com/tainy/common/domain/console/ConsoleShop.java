package com.tainy.common.domain.console;

import lombok.Data;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.util.Date;

@SolrDocument(solrCoreName = "shop")
@Data
public class ConsoleShop implements Serializable{

    private Integer id;

    private Integer userId;

    private String unionId;

    private String shopName;

    private String province;

    private String address;

    private String head;

    private String mobile;

    private String logo;

    private String avatar;

    private Integer sampleClassifyId;

    private Integer classifyId;

    private String classify;

    private Double longitude;

    private Double latitude;

    private Byte status;

    private Byte delFlag;

    private Date createTime;

    private Date updateTime;

    private String provinceCode;

    private String city;

    private String cityCode;

    private String country;

    private String counrtyCode;

    /**
     * 店铺状态
     */
    public enum STATUS {

        FINISHED ((byte) 1, "已完成"),
        PUBLISHED((byte) 2, "已发布"),
        OVERDUE  ((byte) 3, "已过期"),
        CANCELED ((byte) 4, "已废弃");

        private Byte type;

        private String desc;

        STATUS(Byte type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public Byte getType() {
            return type;
        }

        public void setType(Byte type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }
}