package com.ssafy.member.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberDto (회원정보)", description = "아이디, 비번, 이름을 가진   Domain Class")
public class MemberDto {
    
    @ApiModelProperty(value = "회원아이디")
    private String userId;
    @ApiModelProperty(value = "회원이름")
    private String userName;
    @ApiModelProperty(value = "회원비밀번호")
    private String userPassword;
    @ApiModelProperty(value = "이메일아이디")
    private String emailId;
    @ApiModelProperty(value = "이메일도메인")
    private String emailDomain;
    @ApiModelProperty(value = "가입일")
    private String joinDate;
    @ApiModelProperty(value = "삭제여부")
    private Boolean idDel;

    public Boolean getIdDel() {
		return idDel;
	}

	public MemberDto(String userId, String userName, String userPassword, String emailId, String emailDomain,
			String joinDate, Boolean idDel) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.joinDate = joinDate;
		this.idDel = idDel;
	}

	public void setIdDel(Boolean idDel) {
		this.idDel = idDel;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "MemberDto [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", emailId="
                + emailId + ", emailDomain=" + emailDomain + ", joinDate=" + joinDate + "]";
    }

}
