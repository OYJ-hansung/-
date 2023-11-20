package com.ssafy.plan.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FavoriteDto : 좋아요 정보", description = "유저의 게시물에 좋아요 정보를 나타낸다.")
public class FavoriteDto {

	@ApiModelProperty(value = "좋아요 아이디")
	private int favoriteId;
	
	@ApiModelProperty(value = "여행계획 아이디")
	private int planId;
	
	@ApiModelProperty(value = "사용자 아이디")
	private int userId;

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public FavoriteDto(int favoriteId, int planId, int userId) {
		super();
		this.favoriteId = favoriteId;
		this.planId = planId;
		this.userId = userId;
	}

	public FavoriteDto() {
		super();
	}
}
