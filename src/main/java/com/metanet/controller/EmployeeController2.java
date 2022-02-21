package com.metanet.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.metanet.domain.DepartmentVO;
import com.metanet.domain.DeptVO;
import com.metanet.domain.EmployeeVO;
import com.metanet.domain.EmployeeVO2;
import com.metanet.domain.PasswordVO;
import com.metanet.domain.PositionVO;
import com.metanet.service.EmployeeService2;

@Controller
public class EmployeeController2 {

	@Autowired
	EmployeeService2 service;

	@Autowired
	PasswordEncoder encoder;

	// 사원등록페이지 이동(인사팀)
	@GetMapping("/admin/emp/insertEmpPage")
	public String insertEmployeePage(Model model) {
		model.addAttribute("title", "사원등록 페이지");
		model.addAttribute("dept", service.dept());
		model.addAttribute("pos", service.pos());
		return "/admin/insertEmp";
	}

	// 사원등록하기(인사팀)
	@PostMapping("/admin/emp/insertEmp")
	public String insertEmployee(@Valid EmployeeVO2 emp, Errors errors, HttpServletRequest request, Model model,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		// parameter 값이 없어도 오류가 나지 않게 하기 위해서 required = false

		// 파일 저장 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/employeeImages");

		if (!mfile.isEmpty()) {
			String fileName = mfile.getOriginalFilename();
			if (fileName != null && fileName.length() > 0) {
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
		// 유효성 검사

		// 유효성 통과 못한 필드와 메시지를 핸들링
		if (errors.hasErrors()) {
			// 사원등록 실패시, 입력 데이터를 유지
			model.addAttribute("emp", emp);
			model.addAttribute("dept", service.dept());

			// 유효성 통과 못한 필드와 메시지를 핸들링
			Map<String, String> validatorResult = service.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			return "/admin/insertEmp";
		}

		if (service.insertEmp(emp) > 0) {
			return "redirect:/admin/emp";
		} else {
			model.addAttribute("message", "사원 등록 실패");
			return "/admin/edu/list";
		}

	}

	// 조직관리 메뉴를 통한 사원상세보기(인사팀)
	@GetMapping("/admin/emp/detail")
	public String adminEmpDetail(@RequestParam("empNo") int empNo, Model model) {
		EmployeeVO2 emp = service.selectOne(empNo);
		model.addAttribute("empDetail", emp);
		model.addAttribute("title", emp.getEmpName() + "님의 정보");

		return "/admin/empDetail";
	}

	// 사원수정페이지(인사팀) : 비밀번호 변경은 x
	@GetMapping("/admin/emp/updateEmpPage")
	public String updateEmployeePage(@RequestParam("empNo") int empNo, Model model) {
		EmployeeVO2 emp = service.selectOne(empNo);
		model.addAttribute("emp", emp);
		model.addAttribute("title", emp.getEmpName() + "님의 정보 수정페이지");

		return "/admin/updateEmp";
	}

	// 사원수정하기(인사팀) : 비밀번호변경X
	@PostMapping("/admin/emp/updateEmp")
	public String updateEmployee(@Valid EmployeeVO2 emp, Errors errors, HttpServletRequest request, Model model,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		// 파일 저장 경로
		String savePath = request.getSession().getServletContext().getRealPath("resources/employeeImages");

		// 새로운 파일
		if (!mfile.isEmpty()) {
			// 이전 파일 삭제
			if (emp.getEmpPhoto() != null) {
				new File(savePath + "\\" + emp.getEmpRePhoto()).delete();
				emp.setEmpPhoto(null);
				emp.setEmpRePhoto(null);
			}

			String fileName = mfile.getOriginalFilename();
			if (fileName != null && fileName.length() > 0) {
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

		// 유효성 검사

		// 유효성 통과 못한 필드와 메시지를 핸들링
		if (errors.hasErrors()) {
			// 사원등록 실패시, 입력 데이터를 유지
			model.addAttribute("emp", emp);
			// 유효성 통과 못한 필드와 메시지를 핸들링
			Map<String, String> validatorResult = service.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			return "/admin/updateEmp";
		}

		if (service.updateEmp(emp) > 0) {
			return "redirect:/admin/emp/detail?empNo=" + emp.getEmpNo();
		} else {
			model.addAttribute("message", "수정 실패");
			return "/admin/updateEmp";
		}
	}

	// 사원비밀번호 0000 수정(인사팀)
	@GetMapping("/admin/emp/updatePwd")
	public String updatePwd(EmployeeVO2 emp, HttpServletRequest request) {
		service.updatePwd(emp);
		return "redirect:/admin/emp/detail?empNo=" + Integer.parseInt(request.getParameter("empNo"));
	}

	// 내 정보 보기(공통)
	@GetMapping("/emp/detail")
	public String empDetail(@RequestParam("empNo") int empNo, Model model) {
		EmployeeVO2 emp = service.selectOne(empNo);
		model.addAttribute("empDetail", emp);
		model.addAttribute("title", "내 정보");

		return "/empDetail";
	}

	// 비밀번호 변경 페이지 이동(공통)
	@GetMapping("/emp/upPwdPage")
	public String empUpdatePage(@RequestParam("empNo") int empNo, Model model) {
		EmployeeVO2 emp = service.selectOne(empNo);
		model.addAttribute("title", "비밀번호 수정페이지");

		return "/empUpPwd";
	}

	// 현재비밀번호 일치확인(공통)
	@PostMapping("/emp/pwCheck")
	@ResponseBody
	public int pwCheck(HttpSession session, EmployeeVO2 emp) {
		EmployeeVO empInfo = (EmployeeVO) session.getAttribute("sessionEmp");
		System.out.println(empInfo);
		System.out.println("empNo 확인" + empInfo.getEmpNo());
		String empPwd = service.pwCheck(empInfo.getEmpNo());

		System.out.println("emp.getEmpPwd() 확인" + emp.getEmpPwd());
		System.out.println("empPwd 확인" + empPwd);

		if (!encoder.matches(emp.getEmpPwd(), empPwd)) {
			return 0;
		}
		return 1;
	}

	// 비밀번호 수정(공통)
	@PostMapping("/emp/pwUpdate")
	public String pwUpdate(@Valid PasswordVO pass, Errors errors, Model model, int empNo, String pw1,
			RedirectAttributes rttr, HttpSession session) {
		String hashedPw = "{bcrypt}" + BCrypt.hashpw(pw1, BCrypt.gensalt());
		// 유효성 검사

		// 유효성 통과 못한 필드와 메시지를 핸들링
		if (errors.hasErrors()) {
			// 사원등록 실패시, 입력 데이터를 유지
			model.addAttribute("pass", pass);
			// 유효성 통과 못한 필드와 메시지를 핸들링
			Map<String, String> validatorResult = service.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			return "/empUpPwd";
		}
		System.out.println("hashedPw = " + hashedPw);
		service.pwUpdate(empNo, hashedPw);
		session.invalidate();
		rttr.addFlashAttribute("msg", "비밀번호 수정이 완료되었습니다. 다시 로그인해주세요.");

		return "redirect:/logout";
	}

	// 부서리스트조회(인사팀)
	@GetMapping("/admin/emp/deptList")
	public String deptList(Model model, HttpServletRequest request) {
		model.addAttribute("deptList", service.deptList());
		model.addAttribute("title", "부서 리스트");

		return "/admin/deptList";
	}

	// 부서등록페이지이동(인사팀)
	@GetMapping("/admin/emp/insertDeptPage")
	public String insertDept(Model model) {
		model.addAttribute("title", "부서 등록페이지");
		return "/admin/insertDept";
	}

	// 사원번호 확인
	@PostMapping("/empNoCheck")
	@ResponseBody
	public int empNoCheck(int empNo) {
		int result = service.empNoCheck(empNo);
		return result;
	}

	// 부서등록(인사팀)
	@PostMapping("/admin/emp/insertDept")
	public String insertDept(DeptVO dept, int empNo, Model model) {
		int empNoResult = service.empNoCheck(empNo);

		try {
			if (empNoResult == 0) {
				return "/admin/insertDept";
			} else if (empNoResult == 1) {
				service.insertDept(dept);
				return "redirect:/admin/emp/deptList";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}

	// 부서 상세정보(인사팀)
	@GetMapping("/admin/emp/deptDetail")
	public String selectDept(@RequestParam("deptNo") int deptNo, Model model) {
		List<DepartmentVO> listDept = new ArrayList<DepartmentVO>();
		listDept = service.selectDept(deptNo);
		DepartmentVO dept = service.selectD(deptNo);
		model.addAttribute("dept", dept);
		model.addAttribute("deptDetail", listDept);
		model.addAttribute("title", dept.getDeptName() + " 상세보기");

		return "/admin/deptDetail";
	}

	// 부서 수정페이지이동(인사팀)
	@GetMapping("/admin/emp/updateDeptPage")
	public String updateDeptPage(@RequestParam("deptNo") int deptNo, Model model) {
		DepartmentVO dept = service.selectD(deptNo);
		model.addAttribute("dept", dept);
		model.addAttribute("title", dept.getDeptName() + " 수정페이지");

		return "/admin/updateDept";
	}

	// 부서수정(인사팀)
	@PostMapping("/admin/emp/updateDept")
	public String updateDept(DeptVO dept, int empNo, Model model) {
		int empNoResult = service.empNoCheck(empNo);

		try {
			if (empNoResult == 0) {
				return "/admin/updateDept";
			} else if (empNoResult == 1) {
				service.updateDept(dept);
				return "redirect:/admin/emp/deptList";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}

	// 부서삭제(삭제 전 부서0번이동)
	@GetMapping("/admin/emp/deleteDept")
	public String deptDelete(@RequestParam("deptNo") int deptNo, EmployeeVO2 emp, HttpServletRequest request) {
		service.empDept(emp);
		service.deleteDept(deptNo);

		return "redirect:/admin/emp/deptList";

	}

	// 직급리스트조회(인사팀)
	@GetMapping("/admin/emp/posList")
	public String posList(Model model, HttpServletRequest request) {
		model.addAttribute("posList", service.posList());
		model.addAttribute("title", "직급 리스트");

		return "/admin/posList";
	}

	// 직급등록페이지 이동(인사팀)
	@GetMapping("/admin/emp/insertPosPage")
	public String insertPosPage(Model model) {
		model.addAttribute("title", "직급등록 페이지");

		return "/admin/insertPos";
	}

	// 직급등록(인사팀)
	@PostMapping("/admin/emp/insertPos")
	public String insertPos(PositionVO pos, Model model) {
		service.insertPos(pos);

		return "redirect:/admin/emp/posList";
	}

	// 직급 상세정보(인사팀)
	@GetMapping("/admin/emp/posDetail")
	public String selectPosition(@RequestParam("posNo") int posNo, Model model) {
		List<PositionVO> listPos = new ArrayList<PositionVO>();
		listPos = service.selectPos(posNo);
		PositionVO pos = service.selectSal(posNo);
		model.addAttribute("pos", pos);
		model.addAttribute("posDetail", listPos);
		model.addAttribute("title", pos.getPosName() + " 상세보기");

		return "/admin/posDetail";
	}

	// 직급 수정페이지이동(인사팀)
	@GetMapping("/admin/emp/updatePosPage")
	public String updatePosPage(@RequestParam("posNo") int posNo, Model model) {
		PositionVO pos = service.selectSal(posNo);
		model.addAttribute("pos", pos);
		model.addAttribute("title", pos.getPosName() + " 수정페이지");

		return "/admin/updatePos";
	}

	// 직급 수정(인사팀)
	@PostMapping("/admin/emp/updatePos")
	public String updatePos(PositionVO pos, Model model) {
		service.updatePos(pos);
		return "redirect:/admin/emp/posDetail?posNo=" + pos.getPosNo();
	}

	// 직급삭제(삭제 전 직급0번이동)
	@GetMapping("/admin/emp/deletePos")
	public String posDelete(@RequestParam("posNo") int posNo, EmployeeVO2 emp, HttpServletRequest request) {
		service.empPos(emp);
		service.deletePos(posNo);

		return "redirect:/admin/emp/posList";

	}
}
