package com.ssafy.plan.model;

import java.util.List;

import com.ssafy.attraction.model.AttractionInfoDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PlanDto : 여행계획 정보", description = "여행계획 정보를 나타낸다.")
public class PlanDto {

	@ApiModelProperty(value = "여행계획 아이디")
	private int planId;
	@ApiModelProperty(value = "작성자 아이디")
	private String userId;
	@ApiModelProperty(value = "여행계획 이름")
	private String planName;
	@ApiModelProperty(value = "여행 시작일")
	private String startDate;
	@ApiModelProperty(value = "여행 종료일")
	private String endDate;
	@ApiModelProperty(value = "여행계획 등록일")
	private String regDate;
	@ApiModelProperty(value = "여행계획 설명")
	private String planDetail;
	@ApiModelProperty(value = "여행계획 대표 이미지")
	private String img;
	@ApiModelProperty(value = "여행명소 리스트")
	private List<AttractionInfoDto> attrInfoList;
	@ApiModelProperty(value = "여행명소 리스트")
	private List<TagDto> tagList;

	public void setTagList(List<TagDto> tagList) {
		this.tagList = tagList;
	}

	public List<TagDto> getTagList() {
		return tagList;
	}

	public PlanDto() {
		super();
	}

	public PlanDto(int planId, String userId, String planName, String startDate, String endDate, String regDate,
			String planDetail, String img, List<AttractionInfoDto> attrInfoList,List<TagDto> tagList) {
		super();
		this.planId = planId;
		this.userId = userId;
		this.planName = planName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regDate = regDate;
		this.planDetail = planDetail;
		this.img = img;
		this.attrInfoList = attrInfoList;
		this.tagList = tagList;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getPlanDetail() {
		return planDetail;
	}

	public void setPlanDetail(String planDetail) {
		this.planDetail = planDetail;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<AttractionInfoDto> getAttrInfoList() {
		return attrInfoList;
	}

	public void setAttrInfoList(List<AttractionInfoDto> attrInfoList) {
		this.attrInfoList = attrInfoList;
	}

	@Override
	public String toString() {
		return "PlanDto [planId=" + planId + ", userId=" + userId + ", planName=" + planName + ", startDate="
				+ startDate + ", endDate=" + endDate + ", regDate=" + regDate + ", planDetail=" + planDetail + ", img="
				+ img + ", attrInfoList=" + attrInfoList + "]";
	}

}
