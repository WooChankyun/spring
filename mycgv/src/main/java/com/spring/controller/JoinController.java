package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.vo.CgvMemberVO;

@Controller
public class JoinController {
	
	/**
	 * id_check.do : 아이디 중복 체크
	 */
	@ResponseBody //리턴값이 view name이 아닌 ajax쪽으로 전달되도록 해주는 어노테이션
	@RequestMapping(value = "/id_check.do", method = RequestMethod.GET)
	public String id_check(String id) { //join.jsp의 url:"id_check.do?id="+$("#id").val()에서 id=~~이므로 파라미터를 String id로 받음 
	//ajax의 리턴값, 즉 success:function(result){ 의 result는 String 타입만 받을 수 있다. 그러므로 여기서는 ModelAndView가 아니라 String이다!!
		
		//String id = request.getParameter("id"); //얘는 파라미터 String id로 받았다
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.idCheck(id);
		
		//System.out.println("result--->" + result);
		//Ajax에 전송하는 결과는 반드시!!! 문자열(String)형태로 전송해야 한다.
		//자바에서 형변환 : Integer.parseInt(type), String.valueOf(type) --> 이 3줄은 예전것을 그대로 옮긴 것이며, 이번 메소드의 변경점과 관련은 없다
		
		//out.write(String.valueOf(result)); //얘는 jsp에서만 사용된다. 여기서는 자바 메소드이므로 이를 리턴값으로 전달한다
		return String.valueOf(result);//이 리턴값인 String.valueOf(result) -1 또는 0일 것이다- 가 곧 view인 셈이다(스프링 패턴 참고)
									  //그런데 1.jsp나 0.jsp는 존재하지 않으므로 에러가 발생할 것이다(브라우저 콘솔에 404에러가 발생한다)
								      //그러므로 view로 보내는게 아니라 바로 ajax로 값을 전달하도록 해야한다. 이를 위한 어노테이션이 있다
																							// ---> @ResponseBody
	}
	
	
	/**
	 * joinCheck.do
	 */
	@RequestMapping(value = "joinCheck.do", method = RequestMethod.POST)
	public ModelAndView joinCheck(CgvMemberVO vo) {
		ModelAndView mv = new ModelAndView();
		
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.insert(vo);
		
		if(result == 1){
			//가입 성공
			mv.addObject("join_result", "ok");
			mv.setViewName("/login/login");
		}else{
			//가입 실패!
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	
	/**
	 * join.do : 회원가입
	 */
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String join() {
		return "/join/join";
	}
}
