package com.metanet.main;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Data
public class UserVO {
	private int userNo;
	private String id;
	private String userName;
	private String password;
	private Date joinDate;
}
