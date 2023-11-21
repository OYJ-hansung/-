package com.ssafy.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.plan.model.TagDto;
import com.ssafy.plan.model.TagToPlanDto;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.plan.model.FavoriteDto;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanParamDto;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface PlanMapper {
	
	List<PlanDto> planList(PlanParamDto planParamDto) throws SQLException;
	PlanDto planDetail(int planId) throws SQLException;
	void writePlan(PlanDto planDto) throws SQLException;
	void modifyPlan(PlanDto planDto) throws SQLException;
	void deletePlan(int planId) throws SQLException;

	TagDto getTag(String tagName) throws SQLException;
	void addTag(TagDto tagDto) throws SQLException;
	void addTagToPlan(TagToPlanDto tagToPlanDto) throws SQLException;
	void raiseTagCount(int tagId) throws SQLException;
	List<TagDto> hotTagList() throws SQLException;

	List<TagDto> searchTag(String tagName) throws SQLException;
	void insertPlanToAttr(Map<String, Object> map) throws SQLException;
	void addFavorite(FavoriteDto favoriteDto) throws SQLException;
	void cancelFavorite(FavoriteDto favoriteDto) throws SQLException;
	void shareMyPlan(int planId) throws SQLException;
	int getShared(int planId) throws SQLException;
}
