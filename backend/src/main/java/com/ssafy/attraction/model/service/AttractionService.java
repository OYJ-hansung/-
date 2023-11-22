package com.ssafy.attraction.model.service;

import java.util.List;

import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.attraction.model.AttractionSearchDto;
import com.ssafy.attraction.model.GugunDto;

public interface AttractionService {
	
	List<AttractionInfoDto> attractionList(AttractionSearchDto attractionSearchDto) throws Exception;

	List<AttractionInfoDto> attractionSearch(String word) throws Exception;

	List<GugunDto> getGugunList(int sidoCode) throws Exception;
}
