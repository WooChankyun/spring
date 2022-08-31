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
		//System.out.println("pass --> " + vo.getPass()); // Ȯ�ο�. ex) test 123 �Է��ϸ� Console���� ��������~
		
		ModelAndView mv = new ModelAndView();
		
		CgvMemberDAO dao = new CgvMemberDAO(); //loginController.jsp���� �����ؿԴ�. �Ϻ� �ڵ常 �ڹٿ� �°� ����!
		int result = dao.select(vo);
		
		if(result == 1){
			//�α��� ����
			mv.addObject("login_result", "ok"); //id�� pass ������ �ѱ��
			mv.setViewName("index"); //�����ϸ� �ε��� �������� 
		}else{
			//�α��� ����
			mv.addObject("login_result", "fail");
			mv.setViewName("/login/login"); // �����ϸ� ���ڸ���
		}
		
		return mv;
	}
	
	
	/**
	 * login.do : �α��� ��
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "/login/login";
	}
	
}
