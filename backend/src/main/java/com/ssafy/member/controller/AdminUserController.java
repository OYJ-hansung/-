package com.ssafy.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ssafy.util.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.jwt.model.service.JwtServiceImpl;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
//import com.ssafy.user.model.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/members")
@CrossOrigin("*")
@Api(tags = {"어드민 User 컨트롤러  API V1"})
@Slf4j
public class AdminUserController {

	private MemberService memberService;
	private JwtServiceImpl jwtService;

	@Autowired
	public AdminUserController(MemberService memberService,JwtServiceImpl jwtService) {
		this.memberService = memberService;
		this.jwtService = jwtService;
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
		public ResponseEntity<?> userInfo(@PathVariable("userId") String userId, HttpServletRequest request) throws Exception {
		
			System.out.println("UserId: "+userId);
			Map<String, Object> resultMap = new HashMap<>();
			HttpStatus status = HttpStatus.ACCEPTED;
			if (jwtService.checkToken(request.getHeader("access-token"))) {
				// 사용가능한 토큰이라면
			try {
				MemberDto memberDto = memberService.getMember(userId);
				resultMap.put("message", "success");
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else {
			// 사용 불가능한 토큰!
			resultMap.put("message", "fail");
			status = HttpStatus.UNAUTHORIZED;
		}
		
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
	
	@ApiOperation(value = "로그인", notes = "입력된 정보로 로그인을 시도합니다.") //조건문 추가하기 (빈칸, 중복 등)
	@PostMapping(value = "/login")
	public ResponseEntity<?> userIogin(@RequestBody MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginMember = memberService.loginMember(memberDto);
			System.out.println("로그인 정보: "+loginMember.getUserId());
			if (loginMember != null) {
				System.out.println("들어왔다");
				String accessToken = jwtService.createAccessToken("userId", loginMember.getUserId());// key, data
				System.out.println("accessToken: "+accessToken);
				String refreshToken = jwtService.createRefreshToken("userId", loginMember.getUserId());// key, data
				System.out.println("refreshToken: "+refreshToken);
				memberService.deleRefreshToken(memberDto.getUserId()); // 토큰이 중복되지 않도록 삭제 후 삽입
				memberService.saveRefreshToken(memberDto.getUserId(), refreshToken);
				
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
