package com.ssafy.attraction.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.util.ResultDto;
import com.ssafy.attraction.model.AttractionSearchDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.service.AttractionService;
import com.ssafy.plan.model.TagDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/attraction")
@CrossOrigin("*")
@Api(tags = { "Attraction 컨트롤러  API V1" })
public class AttractionController {

	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);

	private AttractionService attractionService;

	public AttractionController(AttractionService aattractionService) {
		super();
		this.attractionService = aattractionService;
	}

	@ApiOperation(value = "관광지 리스트", notes = "관광지의 <big> 목록</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "관광지 목록 OK!!"), @ApiResponse(code = 404, message = "페이지 없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@PostMapping(value = "/list")
	public ResponseEntity<?> attractionlist(@RequestBody AttractionSearchDto attractionSearchDto) {
		logger.debug("list call");
		try {
			List<AttractionInfoDto> list = attractionService.attractionList(attractionSearchDto);
			list = attractionSort(list, attractionSearchDto.getSortType());
			// System.out.println("list: "+list);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<AttractionInfoDto>>(list, HttpStatus.OK);
			} else {

				return new ResponseEntity<ResultDto>(new ResultDto("fail", "NO RESULT"), HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "NO LIST"), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "구군 검색", notes = "선택한 시도의 구군 목록을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "구군 목록 OK!!"), @ApiResponse(code = 404, message = "페이지 없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping(value = "/search/{sidoCode}")
	public ResponseEntity<?> getGugunList(@PathVariable("sidoCode") int sidoCode) {
		logger.debug("getGugunList call");
		try {
			List<GugunDto> gugunList = attractionService.getGugunList(sidoCode);
			return new ResponseEntity<List<GugunDto>>(gugunList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "구군 목록 조회 실패"), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "관광지 검색", notes = "관광지의 <big> 검색결과</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "관광지 목록 OK!!"), @ApiResponse(code = 404, message = "페이지 없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@PostMapping(value = "/search")
	public ResponseEntity<?> attractionSearch(@RequestBody String word) {
		try {
			System.out.println("검색어: " + word);
			List<AttractionInfoDto> list = attractionService.attractionSearch(word);
			System.out.println(list);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<AttractionInfoDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<ResultDto>(new ResultDto("fail", "NO RESULT"), HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "NO LIST"), HttpStatus.OK);
		}
	}

	private List<AttractionInfoDto> attractionSort(List<AttractionInfoDto> attractions, int search_sort) {

		if (search_sort == 0) {
			return attractions;
		} else if (search_sort == 1) { // 거리순

			// 기준이 멀티캠퍼스
			double pointX = 37.501328668708;
			double pointY = 127.03953821497;
			for (int i = 0; i < attractions.size() - 1; i++) {
				for (int j = 0; j < attractions.size() - 1 - i; j++) {

					double x1 = attractions.get(j).getLatitude();
					double y1 = attractions.get(j).getLongitude();
					double x2 = attractions.get(j + 1).getLatitude();
					double y2 = attractions.get(j + 1).getLongitude();
					double distancej = distanceHaversine(pointX, pointY, x1, y1);
					double distancej1 = distanceHaversine(pointX, pointY, x2, y2);
					// 거리가 j가 j+1보다 클 때

					if (distancej > distancej1) {
						attractions.add(j, attractions.get(j + 1));
						attractions.remove(j + 2);
					}
				}
			}

		} else if (search_sort == 2) { // 이름순
			for (int i = 0; i < attractions.size() - 1; i++) {
				for (int j = 0; j < attractions.size() - 1 - i; j++) {
					String stringj = attractions.get(j).getTitle();
					String stringj1 = attractions.get(j + 1).getTitle();

					if (stringj.compareTo(stringj1) > 0) {
						attractions.add(j, attractions.get(j + 1));
						attractions.remove(j + 2);
					}
				}
			}
		}

		return attractions;
	}

	public static double distanceHaversine(double x1, double y1, double x2, double y2) {
		double distance;
		double radius = 6371; // 지구 반지름(km)
		double toRadian = Math.PI / 180;

		double deltaLatitude = Math.abs(x1 - x2) * toRadian;
		double deltaLongitude = Math.abs(y1 - y2) * toRadian;

		double sinDeltaLat = Math.sin(deltaLatitude / 2);
		double sinDeltaLng = Math.sin(deltaLongitude / 2);
		double squareRoot = Math.sqrt(sinDeltaLat * sinDeltaLat
				+ Math.cos(x1 * toRadian) * Math.cos(x2 * toRadian) * sinDeltaLng * sinDeltaLng);

		distance = 2 * radius * Math.asin(squareRoot);

		return distance;
	}

}
