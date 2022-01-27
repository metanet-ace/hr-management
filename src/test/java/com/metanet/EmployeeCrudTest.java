package com.metanet;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.metanet.domain.EmployeeVO;
import com.metanet.persistence.EmployeeRepository;

@SpringBootTest
public class EmployeeCrudTest {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Test
	public void selectTest() {
		List<EmployeeVO> list = empRepo.findByEmpName("홍길동");
		for(EmployeeVO vo : list) {
			System.out.println(vo.toString());
		}
	}
}
