package com.ssafy.plan.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FavoriteDto : 좋아요 정보", description = "유저의 게시물에 좋아요 정보를 나타낸다.")
public class FavoriteDto {

	@Override
	public String toString() {
		return "FavoriteDto [favoriteId=" + favoriteId + ", planId=" + planId + ", userId=" + userId + "]";
	}

	@ApiModelProperty(value = "좋아요 아이디")
	private int favoriteId;
	
	@ApiModelProperty(value = "여행계획 아이디")
	private int planId;
	
	@ApiModelProperty(value = "사용자 아이디")
	private String userId;

	public int getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}


	public FavoriteDto(int favoriteId, int planId, String userId) {
		super();
		this.favoriteId = favoriteId;
		this.planId = planId;
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public FavoriteDto() {
		super();
	}
}
