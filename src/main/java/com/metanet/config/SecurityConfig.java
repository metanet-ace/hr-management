package com.metanet.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.metanet.service.SecurityService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// @EnableWebSecurity 어노테이션은 이 클래스로부터 생성된 객체가 시큐리티 
	// 설정 파일임을 의미하면서, 동시에 시큐리티를 사용하는 데 필요한 수많은 객체를 생성한다. 
	// WebSecurityConfigurerAdapter 클래스를 상속한 시큐리티 설정 클래스가 빈으로
	// 등록되어 있기만 해도 애플리케이션에서는 시큐리티 로그인을 강제하지 않는다.
	
	@Autowired
	private SecurityService service;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception{
		security.authorizeRequests()
				.antMatchers("/signin").permitAll()
				// hasRole은 접두어 "ROLE_"이 디폴트로 붙어 있다.DB에 ROLE_ADMIN, ROLE_USER 등으로 저장했다면 사용가능
				// 현재는 부서번호로 권한을 주기 때문에 ROLE_이 붙으면 안되므로 hasAuthority 사용 
				.antMatchers("/admin/**").hasAuthority("인사팀")
				.antMatchers("/edu/log", "/edu/detail", "/edu/update", "/edu/delete", 
						"/edu/add", "/updateNotice",  "/noticeAdd", "/deleteNotice",
						"/edu/list/**", "/edu/allocation/**", "/edu/score", "/edu/calendar").hasAuthority("인사팀")
				.antMatchers("/main", "/notice", "/edu/eduEmpDetail","/edu/history", "/edu/download", 
						"/edu/empEduCalendar", "/noticeDetail").authenticated();
				
		
		security.csrf().disable();
		// 시큐리티 로그인 페이지를 대체할 커스템 페이지 지정
		security.formLogin().loginPage("/signin").defaultSuccessUrl("/loginSuccess");
		// security.logout().invalidateHttpSession(true).logoutSuccessUrl("/signin");
		security.exceptionHandling().accessDeniedPage("/accessDenied");
	
		
		// 시큐리티 로그인 비즈니스 로직 수행
		security.userDetailsService(service);
	}
	
	// 패스워드 암호화 적용
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	// AuthenticationManagerBuilder 의존성 주입을 위한 @Autowired
	//	@Autowired
	//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
	//		// 사용자가 입력한 아이디로 사용자 정보를 조회
	//		// 시큐리티 내부적으로 사용하는 org.springframework.security.core.userdatails.User
	//		// 객체에 자동으로 매핑한다. 이 때 emp_no는 "username"에 emp_pwd는 "password" 변수에
	//		// 각각 저장되는데 칼럼 이름이 일치해야 매핑이 되므로 Alias를 적용한 것이다.
	//		String query1 = "SELECT emp_no AS username, CONCAT('{noop}',emp_pwd) "
	//				+ "AS password FROM employee WHERE emp_no = ? ";
	//		// 사용자의 권한 정보 조회
	//		String query2 = "SELECT emp_no, TO_CHAR(dept_no) FROM employee WHERE emp_no = ?";
	//		
	//		auth.jdbcAuthentication()
	//			.dataSource(dataSource)
	//			.usersByUsernameQuery(query1) // 사용자 인증  
	//			.authoritiesByUsernameQuery(query2); // 사용자 권한 체크 
	//	}
}
