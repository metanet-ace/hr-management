package com.metanet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metanet.domain.EmployeeVO;
import com.metanet.domain.SecurityVO;
import com.metanet.persistence.EmployeeRepository;

@Service
public class SecurityService implements UserDetailsService{
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		int parsingUser = Integer.parseInt(username); 
		EmployeeVO vo = empRepo.findByEmpNoWithDeptNo(parsingUser); 
		if(vo == null) {
			throw new UsernameNotFoundException(username + " 사용자 없음");
		} else {
			return new SecurityVO(vo);
		}
	}
}
