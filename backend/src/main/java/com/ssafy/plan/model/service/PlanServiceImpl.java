package com.ssafy.plan.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.plan.model.TagDto;
import com.ssafy.plan.model.TagToPlanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanParamDto;
import com.ssafy.plan.model.mapper.PlanMapper;
import com.ssafy.util.SizeConstant;

@Service
public class PlanServiceImpl implements PlanService {
	
	private PlanMapper planMapper;

	@Autowired
	public PlanServiceImpl(PlanMapper planMapper) {
		super();
		this.planMapper = planMapper;
	}

	@Override
	public List<PlanDto> planList(PlanParamDto planParamDto) throws Exception {
		
		int pgNo = planParamDto.getPgno() == 0 ? 1 : planParamDto.getPgno();
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
	
		planParamDto.setStart(start);
		System.out.println("key="+planParamDto.getKey());
		System.out.println("word="+planParamDto.getWord());
		
		return planMapper.planList(planParamDto);
	}
	
	@Override
	public PlanDto planDetail(int planId) throws Exception {
		return planMapper.planDetail(planId);
	}

	@Override
	@Transactional
	public void writePlan(PlanDto planDto) throws Exception {
		System.out.println("서비스impl로 들어왔다");
		List<TagDto> tagList = planDto.getTagList();
		System.out.println("tagList: "+tagList);
		
		planMapper.writePlan(planDto);
		for(TagDto tagDto: tagList){
			planMapper.addTag(tagDto);
			TagToPlanDto tagToPlanDto = new TagToPlanDto();
			tagToPlanDto.setTagId(tagDto.getTagId());
			tagToPlanDto.setPlanId(planDto.getPlanId());
			planMapper.addTagToPlan(tagToPlanDto);
		}
	}

	@Override
	public void modifyPlan(PlanDto planDto) throws Exception {
		planMapper.modifyPlan(planDto);	
	}
	
	@Override
	@Transactional
	public void deletePlan(int planId) throws Exception {
		planMapper.deletePlan(planId);		
	}

}
