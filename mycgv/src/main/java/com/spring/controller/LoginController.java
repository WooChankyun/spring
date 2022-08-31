package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.vo.CgvMemberVO;

@Controller
public class LoginController {
	
	/**
	 * loginCheck.do
	 */
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	public ModelAndView loginCheck(CgvMemberVO vo) {
		//System.out.println("id --> " + vo.getId());
		//System.out.println("pass --> " + vo.getPass()); // 확인용. ex) test 123 입력하면 Console에도 나오는지~
		
		ModelAndView mv = new ModelAndView();
		
		CgvMemberDAO dao = new CgvMemberDAO(); //loginController.jsp에서 복사해왔다. 일부 코드만 자바에 맞게 수정!
		int result = dao.select(vo);
		
		if(result == 1){
			//로그인 성공
			mv.addObject("login_result", "ok"); //id와 pass 정보를 넘긴다
			mv.setViewName("index"); //성공하면 인덱스 페이지로 
		}else{
			//로그인 실패
			mv.addObject("login_result", "fail");
			mv.setViewName("/login/login"); // 실패하면 제자리로
		}
		
		return mv;
	}
	
	
	/**
	 * login.do : 로그인 폼
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "/login/login";
	}
	
}
