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
	public void writePlan(PlanDto planDto) throws Exception {
		List<TagDto> tagList = planDto.getTagList();
		System.out.println("태그리스트 :"+tagList);
		planMapper.writePlan(planDto);
		System.out.println("태그리스트 :"+tagList);
		
		for(TagDto tagDto: tagList){
			TagDto tag = planMapper.getTag(tagDto.getTagName());
			System.out.println("태그를 찾았습니까? :");
			
			
			if(tag==null) { // 태그가 기존에 없다면 새로운 태그를 추가
				System.out.println("하이");
				System.out.println("기존에 없는 태그입니다");
				planMapper.addTag(tagDto);
				TagToPlanDto tagToPlanDto = new TagToPlanDto();
				tagToPlanDto.setTagId(tagDto.getTagId());
				tagToPlanDto.setPlanId(planDto.getPlanId());
				planMapper.addTagToPlan(tagToPlanDto);
			} else { // 태그가 기존에 있다면 카운트를 하나 증가
				System.out.println("하이2");
				System.out.println("기존에 있는 태그입니다: ");
				planMapper.raiseTagCount(tag.getTagId());
				
				TagToPlanDto tagToPlanDto = new TagToPlanDto();
				tagToPlanDto.setTagId(tag.getTagId());
				tagToPlanDto.setPlanId(planDto.getPlanId());
				planMapper.addTagToPlan(tagToPlanDto);
			}
			
			
			
			
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

	@Override
	public List<TagDto> hotTagList() throws Exception {
		return planMapper.hotTagList();
	}

}
