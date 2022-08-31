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
	 * id_check.do : ���̵� �ߺ� üũ
	 */
	@ResponseBody //���ϰ��� view name�� �ƴ� ajax������ ���޵ǵ��� ���ִ� ������̼�
	@RequestMapping(value = "/id_check.do", method = RequestMethod.GET)
	public String id_check(String id) { //join.jsp�� url:"id_check.do?id="+$("#id").val()���� id=~~�̹Ƿ� �Ķ���͸� String id�� ���� 
	//ajax�� ���ϰ�, �� success:function(result){ �� result�� String Ÿ�Ը� ���� �� �ִ�. �׷��Ƿ� ���⼭�� ModelAndView�� �ƴ϶� String�̴�!!
		
		//String id = request.getParameter("id"); //��� �Ķ���� String id�� �޾Ҵ�
		CgvMemberDAO dao = new CgvMemberDAO();
		int result = dao.idCheck(id);
		
		//System.out.println("result--->" + result);
		//Ajax�� �����ϴ� ����� �ݵ��!!! ���ڿ�(String)���·� �����ؾ� �Ѵ�.
		//�ڹٿ��� ����ȯ : Integer.parseInt(type), String.valueOf(type) --> �� 3���� �������� �״�� �ű� ���̸�, �̹� �޼ҵ��� �������� ������ ����
		
		//out.write(String.valueOf(result)); //��� jsp������ ���ȴ�. ���⼭�� �ڹ� �޼ҵ��̹Ƿ� �̸� ���ϰ����� �����Ѵ�
		return String.valueOf(result);//�� ���ϰ��� String.valueOf(result) -1 �Ǵ� 0�� ���̴�- �� �� view�� ���̴�(������ ���� ����)
									  //�׷��� 1.jsp�� 0.jsp�� �������� �����Ƿ� ������ �߻��� ���̴�(������ �ֿܼ� 404������ �߻��Ѵ�)
								      //�׷��Ƿ� view�� �����°� �ƴ϶� �ٷ� ajax�� ���� �����ϵ��� �ؾ��Ѵ�. �̸� ���� ������̼��� �ִ�
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
			//���� ����
			mv.addObject("join_result", "ok");
			mv.setViewName("/login/login");
		}else{
			//���� ����!
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	
	/**
	 * join.do : ȸ������
	 */
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String join() {
		return "/join/join";
	}
}
