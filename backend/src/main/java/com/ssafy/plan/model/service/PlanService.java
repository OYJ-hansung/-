package com.ssafy.plan.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.plan.model.FavoriteDto;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanParamDto;
import com.ssafy.plan.model.TagDto;
import com.ssafy.util.PageNavigation;

public interface PlanService {
	
	List<PlanDto> planList(PlanParamDto planParamDto) throws Exception;
	PlanDto planDetail(int planId) throws Exception;
	void writePlan(PlanDto planDto) throws Exception;
	void modifyPlan(PlanDto planDto) throws Exception;
	void deletePlan(int planId) throws Exception;
	List<TagDto> hotTagList() throws Exception;


	List<TagDto> searchTag(String tagName) throws Exception;
	void addFavorite(FavoriteDto favoriteDto) throws Exception;
	void cancelFavorite(FavoriteDto favoriteDto) throws Exception;
	int shareMyPlan(int planId) throws Exception;
	List<PlanDto> hotPlanList() throws Exception;
}
