package com.ssafy.plan.model;

import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PlanDto : 여행계획 정보", description = "여행계획 정보를 나타낸다.")
public class TagDto {

    @Override
	public String toString() {
		return "TagDto [tagId=" + tagId + ", tagName=" + tagName + "]";
	}

	@ApiModelProperty(value = "태그 아이디")
    private int tagId;
    @ApiModelProperty(value = "태그 이름")
    private String tagName;

    public TagDto(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public int getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public TagDto() {
        super();
    }
}
