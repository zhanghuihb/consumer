package com.tainy.common.domain.console;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.util.Date;

@SolrDocument(solrCoreName = "shop")
public class ConsoleShop implements Serializable{

    @Field
    private Integer id;

    @Field
    private Integer userId;

    @Field
    private String unionId;

    @Field
    private String shopName;

    @Field
    private String province;

    @Field
    private String address;

    @Field
    private String head;

    @Field
    private String mobile;

    @Field
    private String logo;

    @Field
    private String avatar;

    @Field
    private Integer sampleClassifyId;

    @Field
    private Integer classifyId;

    @Field
    private String classify;

    @Field
    private Double longitude;

    @Field
    private Double latitude;

    @Field
    private Byte status;

    @Field
    private Byte delFlag;

    @Field
    private Date createTime;

    @Field
    private Date updateTime;

    @Field
    private String provinceCode;

    @Field
    private String city;

    @Field
    private String cityCode;

    @Field
    private String country;

    @Field
    private String counrtyCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Integer getSampleClassifyId() {
        return sampleClassifyId;
    }

    public void setSampleClassifyId(Integer sampleClassifyId) {
        this.sampleClassifyId = sampleClassifyId;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify == null ? null : classify.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getCounrtyCode() {
        return counrtyCode;
    }

    public void setCounrtyCode(String counrtyCode) {
        this.counrtyCode = counrtyCode == null ? null : counrtyCode.trim();
    }

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