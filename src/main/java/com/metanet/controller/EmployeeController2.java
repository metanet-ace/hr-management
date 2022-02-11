package com.metanet.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.metanet.domain.EmployeeVO2;
import com.metanet.service.EmployeeService2;

@Controller
public class EmployeeController2 {
	
	@Autowired
	EmployeeService2 service;

	
	//사원등록페이지 이동(인사팀)
	@GetMapping("/admin/emp/insertEmpPage")
	public String insertEmployeePage() {
		return "/admin/insertEmp";
	}
	
	//사원등록하기(인사팀)
	@PostMapping("/admin/emp/insertEmp")
	public String insertEmployee(EmployeeVO2 emp, HttpServletRequest request, Model model, 
					@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		// parameter 값이 없어도 오류가 나지 않게 하기 위해서 required = false
		
		// 파일 저장 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/employeeImages");
		
		if(!mfile.isEmpty()) {
			String fileName = mfile.getOriginalFilename();
			if(fileName != null && fileName.length() > 0) {
				try {
					mfile.transferTo(new File(savePath + "\\" + fileName));
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					
					String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
					
					renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);
					
					File originFile = new File(savePath + "\\" + fileName);
					File renameFile = new File(savePath + "\\" + renameFileName);
					
					if(!originFile.renameTo(renameFile)) {
						FileInputStream fin = new FileInputStream(originFile);
						FileOutputStream fout = new FileOutputStream(renameFile);

						int data = -1;
						byte[] buffer = new byte[1024];

						while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
							fout.write(buffer, 0, buffer.length);
						}
						
						fin.close();
						fout.close();
						originFile.delete();
					}
					
					emp.setEmpRePhoto(renameFileName);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "파일 업로드 실패");
				}
			}
			
			emp.setEmpPhoto(mfile.getOriginalFilename());
		}
		
		if (service.insertEmp(emp) > 0) {
			return "redirect:/admin/emp";
		}else {
			model.addAttribute("message", "사원 등록하기 실패");
			return "redirect:/eduList";
		}
	}
	
	// 조직관리 메뉴를 통한 사원상세보기(인사팀)
	@GetMapping("/admin/emp/detail")
	public String adminEmpDetail(@RequestParam("empNo") int empNo, Model model) {
		EmployeeVO2 emp = service.selectOne(empNo);
		model.addAttribute("empDetail", emp);
		
		return "/admin/empDetail";
	}
	
	// 사원수정페이지(인사팀) : 비밀번호 변경은 x
	@GetMapping("/admin/emp/updateEmpPage")
	public String updateEmployeePage(@RequestParam("empNo") int empNo, Model model) {
		EmployeeVO2 emp = service.selectOne(empNo);
		model.addAttribute("emp", emp);
		
		return "/admin/updateEmp";
	}
	
	// 사원수정하기(인사팀) : 비밀번호변경X
	@PostMapping("/admin/emp/updateEmp")
	public String updateEmployee(EmployeeVO2 emp, HttpServletRequest request, Model model, 
								@RequestParam(name="upfile", required=false) MultipartFile mfile) {
		// 파일 저장 경로
		String savePath = request.getSession().getServletContext().getRealPath("resources/employeeImages");
		
		// 새로운 파일
		if (!mfile.isEmpty()) {
			// 이전 파일 삭제
			if(emp.getEmpPhoto() != null) {
				new File(savePath + "\\" + emp.getEmpRePhoto()).delete();
				emp.setEmpPhoto(null);
				emp.setEmpRePhoto(null);
			}
			
			String fileName = mfile.getOriginalFilename();
			if(fileName != null && fileName.length() > 0) {
				try {
					mfile.transferTo(new File(savePath + "\\" + fileName));
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					
					String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
					
					renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);
					
					File originFile = new File(savePath + "\\" + fileName);
					File renameFile = new File(savePath + "\\" + renameFileName);
					
					if (!originFile.renameTo(renameFile)) {
						FileInputStream fin = new FileInputStream(originFile);
						FileOutputStream fout = new FileOutputStream(renameFile);

						int data = -1;
						byte[] buffer = new byte[1024];
						
						while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
							fout.write(buffer, 0, buffer.length);
						}

						fin.close();
						fout.close();
						originFile.delete();
					}
					
					emp.setEmpRePhoto(renameFileName);
					
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "파일 업로드 실패");
				}
			}
			
			emp.setEmpPhoto(mfile.getOriginalFilename());
		}
		System.out.println("empNo확인" + emp.getEmpNo());
		if (service.updateEmp(emp) > 0) {
			return "redirect:/admin/emp/detail?empNo="+emp.getEmpNo();
		}else {
			model.addAttribute("message", "수정 실패");
			return "/admin/updateEmp";
		}
	}
	// 사원상세보기(인사팀x)
//	@GetMapping("/emp/detail")
//	public String empDetail(@RequestParam("empNo") int empNo, Model model) {
//		EmployeeVO2 emp = service.selectOne(empNo);
//		model.addAttribute("emp", emp);
//		
//		return "/empDetail";
//	}
	
	// 비밀번호 변경(공통)
	
	
	
	
	
	
	
	
}
