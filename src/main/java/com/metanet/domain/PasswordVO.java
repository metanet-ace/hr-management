package com.metanet.domain;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordVO {
	
	@Pattern(regexp="(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}", message="비밀번호 형식에 맞지 않습니다. <br>영문, 숫자, 특수기호를 포함한 8자리 이상으로 입력해주세요.")
	private String pw1;
	private String pw2;
	private String empPwd;
}
