package com.metanet.domain;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityVO extends User{
	
	private static final long serialVersionUID = 1L;
	
	public SecurityVO(EmployeeVO emp) {
	
		super(Integer.toString(emp.getEmpNo()), "{noop}" + emp.getEmpPwd(), 
				AuthorityUtils.createAuthorityList(Integer.toString(emp.getDeptNo())));
	}
}
