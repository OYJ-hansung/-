package com.ssafy.attraction.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.attraction.model.AttractionSearchDto;
import com.ssafy.attraction.model.GugunDto;

@Mapper
public interface AttractionMapper {

	List<AttractionInfoDto> attractionList(AttractionSearchDto attractionSearchDto) throws SQLException;

    List<AttractionInfoDto> attractionSearch(String word) throws SQLException;

	List<GugunDto> getGugunList(int sidoCode) throws SQLException;
}