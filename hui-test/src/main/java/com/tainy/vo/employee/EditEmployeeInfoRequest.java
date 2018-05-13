package com.tainy.vo.employee;


import com.wordnik.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

public class EditEmployeeInfoRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "员工ID不能为空")
	@ApiModelProperty(value = "员工ID", required = true)
	private Long id;

	@NotNull(message = "部门ID不能为空")
	@ApiModelProperty(value = "部门ID", required = true)
	private Long deptId;

	@NotNull(message = "职位ID不能为空")
	@ApiModelProperty(value = "职位ID", required = true)
	private Long jobId;

	@NotNull(message = "员工名称不能为空")
	@ApiModelProperty(value = "员工名称", required = true)
	private String name;

	@NotNull(message = "身份证号不能为空")
	@ApiModelProperty(value = "身份证号", required = true)
	private String cardId;

	@NotNull(message = "联系地址不能为空")
	@ApiModelProperty(value = "联系地址", required = true)
	private String address;

	@ApiModelProperty(value = "邮政编码", required = true)
	private String postCode;

	@NotNull(message = "手机号码不能为空")
	@ApiModelProperty(value = "手机号码", required = true)
	private String tel;

	@ApiModelProperty(value = "QQ号", required = true)
	private String qqNum;

	@ApiModelProperty(value = "邮箱", required = true)
	private String email;

	@NotNull(message = "性别不能为空")
	@ApiModelProperty(value = "性别", required = true)
	private Short sex;

	@ApiModelProperty(value = "党派", required = true)
	private String party;

	@ApiModelProperty(value = "出生日期", required = true)
	private String birthday;

	@ApiModelProperty(value = "民族", required = true)
	private String race;

	@ApiModelProperty(value = "学历", required = true)
	private String education;

	@ApiModelProperty(value = "所学专业", required = true)
	private String speciality;

	@ApiModelProperty(value = "爱好", required = true)
	private String hobby;

	@ApiModelProperty(value = "备注", required = true)
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
