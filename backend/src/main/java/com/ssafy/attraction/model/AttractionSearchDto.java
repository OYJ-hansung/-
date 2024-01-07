package com.ssafy.attraction.model;

import lombok.Data;

@Data
public class AttractionSearchDto {
	private int sortType;
	private int contentTypeId;
	private int sidoCode;
	private int gugunCode;
	private String title;
	private int pageSize;
	private int startIndex;
}
