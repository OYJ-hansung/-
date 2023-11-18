package com.ssafy.plan.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanParamDto;
import com.ssafy.plan.model.TagDto;
import com.ssafy.plan.model.service.PlanService;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.ResultDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/plan")
@CrossOrigin("*")
@Api(tags = { "여행계획 컨트롤러  API V1" })
public class PlanController {

	private static final Logger logger = LoggerFactory.getLogger(PlanController.class);

	private PlanService planService;

	public PlanController(PlanService planService) {
		super();
		this.planService = planService;
	}

	@ApiOperation(value = "여행계획 게시판", notes = "<big> 여행계획 목록</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "여행계획 목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@PostMapping
	public ResponseEntity<?> planList(@RequestBody PlanParamDto planParamDto) {
		logger.debug("getPlanList call");
		try {
			List<PlanDto> planlist = planService.planList(planParamDto);
			if (planlist != null && !planlist.isEmpty()) {
				return new ResponseEntity<List<PlanDto>>(planlist, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "NO LIST"), HttpStatus.OK);
		}
	}


	@ApiOperation(value = "태그 검색", notes = "<big> 태그검색결과</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "태그검색결과 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping(value = "/tag/{tagName}")
	public ResponseEntity<?> searchTag(@PathVariable("tagName") String tagName) {
		logger.debug("searchTag call");
		try {
			List<TagDto> tagList = planService.searchTag(tagName);
			if (tagList != null && !tagList.isEmpty()) {
				return new ResponseEntity<List<TagDto>>(tagList, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "NO LIST"), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "인기 태그", notes = "<big>인기 태그 5개</big>를 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "인기 태그 목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping(value="/hottag")
	public ResponseEntity<?> hotTagList() {
		logger.debug("hotTagList call");
		try {
			List<TagDto> hotTagList = planService.hotTagList();
			return new ResponseEntity<List<TagDto>>(hotTagList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "NO LIST"), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "여행계획 상세", notes = "여행계획 하나에 대한 정보.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "planId", value = "글 번호", required = true, dataType = "int", paramType = "path") })
	@GetMapping(value = "/{planId}")
	public ResponseEntity<?> planDetail(@PathVariable("planId") int planId) {
		logger.debug("planDetail planId : {}", planId);
		try {
			PlanDto planDto = planService.planDetail(planId);
			if (planDto != null)
				return new ResponseEntity<PlanDto>(planDto, HttpStatus.OK);
			else
				return new ResponseEntity<ResultDto>(new ResultDto("fail", "NO ARTICLE"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "NO ARTICLE"), HttpStatus.OK);
		}
	}
	

	@ApiOperation(value = "여행계획 추가", notes = "여행계획을 추가합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "여행계획 추가 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@PostMapping(value = "/new")
	public ResponseEntity<?> writePlan(@RequestBody PlanDto planDto) {
		logger.debug("writePlan call");
		logger.debug("시작날짜: "+planDto.getStartDate());

		System.out.println(planDto.getStartDate());
		System.out.println(planDto.getEndDate());
		try {
			planService.writePlan(planDto);
			return new ResponseEntity<ResultDto>(new ResultDto("success", "추가 성공"), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "추가 실패"), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "여행계획 수정", notes = "여행계획을 수정합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "여행계획 수정 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@PutMapping
	public ResponseEntity<?> modifyPlan(@RequestBody PlanDto planDto) {
		logger.debug("modifyPlan planDto : {}", planDto);
		try {
			planService.modifyPlan(planDto);
			return new ResponseEntity<ResultDto>(new ResultDto("success", "수정 성공"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "수정 실패"), HttpStatus.OK);
			
		}
	}

	@ApiOperation(value = "여행계획 삭제", notes = "여행계획을 삭제합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "여행계획 삭제 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@DeleteMapping(value = "/{planId}")
	public ResponseEntity<?> deletePlan(@PathVariable("planId") int planId) {
		logger.debug("deletePlan planId : {}", planId);
		try {
			planService.deletePlan(planId);
			return new ResponseEntity<ResultDto>(new ResultDto("success", "삭제 성공"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "삭제 실패"), HttpStatus.OK);
		}
	}
}
