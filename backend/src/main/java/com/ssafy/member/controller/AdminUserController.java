package com.ssafy.member.controller;

import java.util.List;

import com.ssafy.util.ResultDto;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
//@Controller
@RequestMapping("/members")
@CrossOrigin("*")
@Api(tags = {"어드민 User 컨트롤러  API V1"})
@Slf4j
public class AdminUserController {

//	private static final Logger log = LoggerFactory.getLogger(AdminUserController.class);

	private MemberService memberService;

	@Autowired
	public AdminUserController(MemberService memberService) {
		this.memberService = memberService;
	}

	@ApiOperation(value = "회원목록", notes = "회원의 <big>전체 목록</big>을 반환해 줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping(value = "/user")
	public ResponseEntity<?> userList() {
		log.debug("userList call");
		try {
			List<MemberDto> list = memberService.listMember(null);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "회원등록", notes = "회원의 정보를 받아 처리.") //조건문 추가하기 (빈칸, 중복 등)
	@PostMapping(value = "/")
	public ResponseEntity<?> userRegister(@RequestBody MemberDto memberDto) {
		log.debug("userRegister memberDto : {}", memberDto);
		try {
			memberService.joinMember(memberDto);
			return new ResponseEntity<ResultDto>(new ResultDto("success", "회원등록 성공"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "회원등록 실패"), HttpStatus.OK);
		}
		
	}

	@ApiOperation(value = "회원조회", notes = "회원의 정보를 받아 처리.") //조건문 추가하기 (빈칸, 중복 등)
	@GetMapping(value = "/{userId}")
	public ResponseEntity<?> userInfo(@PathVariable String userId) {
		try {
			MemberDto memberDto = memberService.getMember(userId);
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "회원조회 실패"), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "로그인", notes = "입력된 정보로 로그인을 시도합니다.") //조건문 추가하기 (빈칸, 중복 등)
	@PostMapping(value = "/login")
	public ResponseEntity<?> userIogin(@RequestBody MemberDto memberDto) {
		try {
			MemberDto info = memberService.loginMember(memberDto);
			if(info != null)
				return new ResponseEntity<ResultDto>(new ResultDto("success", "로그인 성공"), HttpStatus.OK);
			else
				return new ResponseEntity<ResultDto>(new ResultDto("fail", "로그인 실패"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "로그인 실패"), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "비밀번호 찾기", notes = "회원의 비밀번호를 찾습니다.") //조건문 추가하기 (빈칸, 중복 등)
	@GetMapping(value = "/login/{userId}")
	public ResponseEntity<?> userFindPassword(@PathVariable String userId) {
		try {
			String password = memberService.findPassword(userId);
			System.out.println(password);
			if(password != null)
				return new ResponseEntity<String>(password, HttpStatus.OK);
			else
				return new ResponseEntity<ResultDto>(new ResultDto("fail", "비밀번호찾기 실패"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "비밀번호찾기 실패!"), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "회원정보수정", notes = "회원정보를 수정합니다.")
	@PutMapping(value = "/")
	public ResponseEntity<?> userModify(@RequestBody MemberDto memberDto) {
		log.debug("userModify memberDto : {}", memberDto);
		try {
			memberService.updateMember(memberDto);
			return new ResponseEntity<ResultDto>(new ResultDto("success", "회원정보수정 성공"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "회원정보수정 실패"), HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "회원정보삭제", notes = "회원정보를 삭제합니다.")
	@DeleteMapping(value = "/{userid}")
	public ResponseEntity<?> userDelete(@PathVariable("userid") String userId) { //조건문 추가하기 (회원 존재 여부)
		log.debug("userDelete userid : {}", userId);
		try {
			memberService.deleteMember(userId);
			return new ResponseEntity<ResultDto>(new ResultDto("success", "회원정보삭제 완료"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResultDto>(new ResultDto("fail", "회원정보삭제 실패"), HttpStatus.OK);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
