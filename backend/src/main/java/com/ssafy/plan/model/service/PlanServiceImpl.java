package com.ssafy.plan.model.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ssafy.plan.model.TagDto;
import com.ssafy.plan.model.TagToPlanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.plan.model.FavoriteDto;
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
		System.out.println("key=" + planParamDto.getKey());
		System.out.println("word=" + planParamDto.getWord());

		return planMapper.planList(planParamDto);
	}

	@Override
	public PlanDto planDetail(int planId) throws Exception {
		return planMapper.planDetail(planId);
	}

	@Override
	@Transactional
	public void writePlan(PlanDto planDto) throws Exception {
		List<TagDto> tagList = planDto.getTagList();
		System.out.println("태그리스트 :" + tagList);
		planMapper.writePlan(planDto);

		Map<String, Object> map = new HashMap<>();
		map.put("planId", planDto.getPlanId());
		map.put("attrInfoList", planDto.getAttrInfoList());
		planMapper.insertPlanToAttr(map);

		System.out.println("태그리스트 :" + tagList);

		for (TagDto tagDto : tagList) {
			TagDto tag = planMapper.getTag(tagDto.getTagName());
			System.out.println("태그를 찾았습니까? :");

			if (tag == null) { // 태그가 기존에 없다면 새로운 태그를 추가
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
	@Transactional
	public void modifyPlan(PlanDto planDto) throws Exception {

		planMapper.modifyPlan(planDto);

		Map<String, Object> map = new HashMap<>();
		map.put("planId", planDto.getPlanId());
		map.put("attrInfoList", planDto.getAttrInfoList());

		planMapper.deletePlanToAttr(planDto.getPlanId());
		planMapper.insertPlanToAttr(map);

		int planId = planDto.getPlanId();

		List<TagDto> newTagList = planDto.getTagList();
		Set<Integer> newTagSet = new HashSet();
		for (TagDto tagDto : newTagList) {
			newTagSet.add(tagDto.getTagId());
		}

		List<Integer> originalTagList = planMapper.getCurrentTagList(planId);

		// 삭제된 태그 처리
		for (int tagId : originalTagList) {
			if (!newTagSet.contains(tagId)) {
				TagToPlanDto tagToPlanDto = new TagToPlanDto();
				tagToPlanDto.setTagId(tagId);
				tagToPlanDto.setPlanId(planId);
				planMapper.deleteTagToPlan(tagToPlanDto); //매핑테이블에서 제거
				planMapper.decreaseTagCount(tagId); //카운트 감소
			}
		}

		//추가된 태그 처리
		for (TagDto tagDto : newTagList) {
			TagDto tag = planMapper.getTag(tagDto.getTagName());
			System.out.println("태그:" + tagDto.getTagName());
			if (tag == null) { // 태그리스트에 없는 태그라면 태그 추가
				System.out.println("하이");
				System.out.println("기존에 없는 태그입니다");
				planMapper.addTag(tagDto);
				TagToPlanDto tagToPlanDto = new TagToPlanDto();
				tagToPlanDto.setTagId(tagDto.getTagId());
				tagToPlanDto.setPlanId(planDto.getPlanId());
				planMapper.addTagToPlan(tagToPlanDto);
			} else if (!originalTagList.contains(tag.getTagId())) { // 태그리스트에 있고, 추가된 태그라면 카운트 증가
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
	@Transactional
	public void deletePlan(int planId) throws Exception {
		planMapper.deletePlan(planId);
	}

	@Override
	public List<TagDto> hotTagList() throws Exception {
		return planMapper.hotTagList();
	}

	@Override
	public List<TagDto> searchTag(String tagName) throws Exception {
		return planMapper.searchTag(tagName);
	}

	@Override
	public void addFavorite(FavoriteDto favoriteDto) throws Exception {
		System.out.println(favoriteDto);
		planMapper.addFavorite(favoriteDto);
	}

	@Override
	public void cancelFavorite(FavoriteDto favoriteDto) throws Exception {
		planMapper.cancelFavorite(favoriteDto);
	}

	@Override
	public int shareMyPlan(int planId) throws Exception {
		planMapper.shareMyPlan(planId);
		return planMapper.getShared(planId);

	}

	@Override
	public List<PlanDto> hotPlanList() throws Exception {
		return planMapper.hotPlanList();
	}
}
