package com.tainy.console.vo.console;

import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/14
 */
public class AddConsumerInfoRequest implements Serializable{

    @NotNull(message = "省份不能为空")
    @ApiModelProperty(value = "省份", required = true)
    private String province;

    @NotNull(message = "城市不能为空")
    @ApiModelProperty(value = "城市", required = true)
    private String city;

    @NotNull(message = "消费父类型编码不能为空")
    @ApiModelProperty(value = "消费父类型编码", required = true)
    private String parentCode;

    @NotNull(message = "消费父类型名称不能为空")
    @ApiModelProperty(value = "消费父类型名称", required = true)
    private String parentCodeName;

    @NotNull(message = "消费类型编码不能为空")
    @ApiModelProperty(value = "消费类型编码", required = true)
    private String code;

    @NotNull(message = "消费类型名称不能为空")
    @ApiModelProperty(value = "消费类型名称", required = true)
    private String codeName;

    @NotNull(message = "消费金额不能为空")
    @ApiModelProperty(value = "消费金额", required = true)
    private Integer amount;

    @NotNull(message = "摘要不能为空")
    @ApiModelProperty(value = "摘要", required = true)
    private Integer digest;

    @NotNull(message = "消费者不能为空")
    @ApiModelProperty(value = "消费者", required = true)
    private String consumer;

    @NotNull(message = "消费时间不能为空")
    @ApiModelProperty(value = "消费时间", required = true)
    private String consumerTime;

    @NotNull(message = "描述不能为空")
    @ApiModelProperty(value = "描述", required = true)
    private String description;

    @NotNull(message = "消费记录状态不能为空")
    @ApiModelProperty(value = "消费记录状态", required = true)
    private Integer status;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCodeName() {
        return parentCodeName;
    }

    public void setParentCodeName(String parentCodeName) {
        this.parentCodeName = parentCodeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDigest() {
        return digest;
    }

    public void setDigest(Integer digest) {
        this.digest = digest;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getConsumerTime() {
        return consumerTime;
    }

    public void setConsumerTime(String consumerTime) {
        this.consumerTime = consumerTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
