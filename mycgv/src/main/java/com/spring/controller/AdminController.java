package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.dao.CgvNoticeDAO;
import com.mycgv.vo.CgvMemberVO;
import com.mycgv.vo.CgvNoticeVO;

@Controller
public class AdminController {

	/**
	 * admin.do : ������ ������
	 */
	@RequestMapping(value = "/admin.do", method = RequestMethod.GET)
	public String admin() {
		return "/admin/admin";
	}
	
	
	
	//admin_member
	/**
	 * admin_member_content.do
	 */
	@RequestMapping(value = "/admin_member_content.do", method = RequestMethod.GET)
	public ModelAndView admin_member_content(String id) {
		
		ModelAndView mv = new ModelAndView();

		//String id = request.getParameter("id");
		CgvMemberDAO dao = new CgvMemberDAO();
		CgvMemberVO vo = dao.selectContent(id);
		String address = vo.getZonecode()+" "+vo.getAddr1()+" "+ vo.getAddr2();
		
		mv.addObject("vo", vo);
		mv.addObject("address", address); //address�� �߰����ش�
		mv.setViewName("/admin/admin_member/admin_member_content");
		
		return mv;
	}
	
	/**
	 * admin_member_list.do : ȸ�� ��ü ����Ʈ
	 */
	@RequestMapping(value = "/admin_member_list.do", method = RequestMethod.GET)
	public ModelAndView admin_member_list(String rpage) {
		
		ModelAndView mv = new ModelAndView();
		
		
		//String rpage = request.getParameter("rpage");
		CgvMemberDAO dao = new CgvMemberDAO();
		
		//startCount, endCount
		//����¡ ó�� - startCount, endCount ���ϱ�
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//���������� �Խù� ��
		int reqPage = 1;	//��û������	
		int pageCount = 1;	//��ü ������ ��
		int dbCount = dao.totalCount();	//DB���� ������ ��ü ���
		
		//�� ������ �� ���
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//��û ������ ���
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}


		ArrayList<CgvMemberVO> list = dao.selectAll(startCount, endCount);   
		
		
		mv.addObject("list", list);
		
		mv.addObject("dbCount", dbCount);
		mv.addObject("rpage", rpage);
		mv.addObject("pageSize", pageSize);
		
		mv.setViewName("/admin/admin_member/admin_member_list");
		
		return mv;
	}
	
	
	
	//admin_notice
	/**
	 * admin_notice_content.do
	 */
	@RequestMapping(value = "/admin_notice_content.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_content(String nid) {
		
		ModelAndView mv = new ModelAndView();
		
		//String nid = request.getParameter("nid");
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		
		if(vo != null){
			dao.updateHits(nid);
		}
		
		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_notice/admin_notice_content");
		
		return mv;
	}
	
	
	/**
	 * admin_notice_delete.do
	 */
	@RequestMapping(value = "/admin_notice_delete.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_delete(String nid) {
		
		ModelAndView mv = new ModelAndView();
		
		//String nid = request.getParameter("nid");
		mv.addObject("nid", nid);
		mv.setViewName("/admin/admin_notice/admin_notice_delete");
		
		return mv;
	}
	
	/**
	 * admin_notice_delete_check.do
	 */
	@RequestMapping(value = "/admin_notice_delete_check.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_delete_check(String nid) {
		
		ModelAndView mv = new ModelAndView();
		
		//String nid = request.getParameter("nid");
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.delete(nid);
		
		if(result == 1){
			//response.sendRedirect("admin_notice_list.jsp");
			mv.setViewName("redirect:/admin_notice_list.do");
		}else{
			//response.sendRedirect("../errorPage.jsp");
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	
	/**
	 * admin_notice_list.do : �������� ��ü����Ʈ
	 */
	@RequestMapping(value = "/admin_notice_list.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_list(String rpage) {
		
		ModelAndView mv = new ModelAndView();
		
		//String rpage = request.getParameter("rpage");
		CgvNoticeDAO dao = new CgvNoticeDAO();

		//startCount, endCount
		//����¡ ó�� - startCount, endCount ���ϱ�
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//���������� �Խù� ��
		int reqPage = 1;	//��û������	
		int pageCount = 1;	//��ü ������ ��
		int dbCount = dao.totalCount();	//DB���� ������ ��ü ���
		
		//�� ������ �� ���
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//��û ������ ���
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}

		ArrayList<CgvNoticeVO> list = dao.select(startCount, endCount);
		
		
		mv.addObject("list", list);
		
		mv.addObject("dbCount", dbCount);
		mv.addObject("rpage", rpage);
		mv.addObject("pageSize", pageSize);
		
		mv.setViewName("/admin/admin_notice/admin_notice_list");
		
		return mv;
	}
	
	
	/**
	 * admin_notice_update.do
	 */
	@RequestMapping(value = "/admin_notice_update.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_update(String nid) {
		
		ModelAndView mv = new ModelAndView();
		
		//String nid = request.getParameter("nid");
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_notice/admin_notice_update");
		
		return mv;
	}
	
	/**
	 * admin_notice_update_check.do
	 */
	@RequestMapping(value = "admin_notice_update_check.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_update_check(CgvNoticeVO vo) {
		
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.update(vo);
		if(result == 1){
			//response.sendRedirect("admin_notice_list.jsp");
			mv.setViewName("redirect:/admin_notice_list.do");
		}else{
			//response.sendRedirect("../errorPage.jsp");
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	
	/**
	 * admin_notice_write.do
	 */
	@RequestMapping(value = "/admin_notice_write.do", method = RequestMethod.GET)
	public String admin_notice_write() {
		return "/admin/admin_notice/admin_notice_write";
	}
	
	/**
	 * admin_notice_write_check.do
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "/admin_notice_write_check.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_write_check(CgvNoticeVO vo, HttpServletRequest request) throws Exception {
		
		//������ ���õǾ����� üũ
		if(vo.getFile1().getOriginalFilename().equals("")) {//������ ���õǾ����� ���� ���
			vo.setNfile("");
			vo.setNsfile("");// cf) �ڹٿ����� null�� DB������ null�� Ÿ���� �ٸ���. �ڹٿ��� ""�� �־�߸� DB���� null�� �ν��Ѵ�
		}else {//������ ���õ� ���
			UUID uuid = UUID.randomUUID();
			
			vo.setNfile(vo.getFile1().getOriginalFilename());//DB�� nfile�� ���ε��� ������ �̸��� ���ڵ����ͷ� ��´�
			vo.setNsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
		}
		
		ModelAndView mv = new ModelAndView();
		
		CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = dao.insert(vo);
		if(result == 1){
			
			//������ �ִ� ��� uplodad ������ ���� ����
			if(!vo.getFile1().getOriginalFilename().equals("")) {//DB�� nfile�� �����Ͱ� null�� �ƴ� ��츦 ���Ѵ�
																//�׷��� !vo.getNfile().equals("")�� �����ϴ�
				
				//upload ������ ��θ� �������� ����, HttpServletRequest ��ü�� �Ķ���ͷ� �����´�
				//(��Ŭ������ /resources/upload���� ���� �ٷ� ã�� ���Ѵ�)
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "\\resources\\upload\\";
				
				File file = new File(path+vo.getNsfile()); //������� �۾��ϸ� �̸��� nsfile�� 0kb¥�� ����ִ� ������ �����ȴ�
				vo.getFile1().transferTo(file); //������� �۾��ؾ� ��μ� ���ε��� ������ �����ϰ� ä������ �����ȴ�
			}
			
			//response.sendRedirect("admin_notice_list.jsp");
			mv.setViewName("redirect:/admin_notice_list.do");
		}else{
			//response.sendRedirect("../errorPage.jsp");
			mv.setViewName("error_page");
		}
		
		return mv;
	}
}
