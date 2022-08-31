package com.spring.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvBoardDAO;
import com.mycgv.vo.CgvBoardVO;

@Controller
public class BoardController {

	/**
	 * board_content.do
	 */
	@RequestMapping(value = "/board_content.do", method = RequestMethod.GET)
	public ModelAndView board_content(String bid) {
		
		ModelAndView mv = new ModelAndView();
		
		//String bid = request.getParameter("bid"); //jsp������ ����ϴ� �ڵ��̹Ƿ� �ּ� ó��. ������������ �Ķ���͸� ���� ������ �ڵ����� �������ش�!
		CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = dao.select(bid);
		
		if(vo != null){
			dao.updateHits(bid);
		}
		    
		mv.addObject("vo", vo); // "vo"��� �̸��� vo��ü�� ���� ��´�
		mv.setViewName("/board/board_content");
		
		
		return mv;
	}
	
	
	/**
	 * board_delete.do
	 */
	@RequestMapping(value = "/board_delete.do", method = RequestMethod.GET)
	public ModelAndView board_delete(String bid) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("bid", bid);
		mv.setViewName("/board/board_delete");
		
		return mv;
	}
	
	/**
	 * board_delete_check.do
	 */
	@RequestMapping(value = "/board_delete_check.do", method = RequestMethod.POST)
	public ModelAndView board_delete_check(String bid) { //�Ķ���͸� CgvBoardVO vo�� �ص� �Ǳ� ��(�ٵ� ���� ������ ����� �κ��� bid �ϳ��� �׳� bid�� �ص� ��)
		
		ModelAndView mv = new ModelAndView();
		
		// String bid = request.getParameter("bid");
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.delete(bid);
		
		if(result == 1){
			//response.sendRedirect("board_list.jsp");
			mv.setViewName("redirect:/board_list.do");
		}else{
			//response.sendRedirect("../errorPage.jsp");
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	
	/**
	 * board_list.do
	 */
	@RequestMapping(value = "/board_list.do", method = RequestMethod.GET)
	public ModelAndView board_list(String rpage) {
		
		ModelAndView mv = new ModelAndView();
		
		//ȭ�鿡�� ���۵� ��������û ��ȣ ��������
		//String rpage = request.getParameter("rpage"); //rpage�� board_list(String rpage)�� �Ķ���ͷ� �ٷ� �޴´�
		CgvBoardDAO dao = new CgvBoardDAO();

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

		ArrayList<CgvBoardVO> list = dao.select(startCount, endCount);
		
		mv.addObject("list", list);
		
		mv.addObject("dbCount", dbCount);
		mv.addObject("rpage", rpage);
		mv.addObject("pageSize", pageSize);
		
		mv.setViewName("/board/board_list");
		
		return mv;
	}
	
	
	/**
	 * board_update.do
	 */
	@RequestMapping(value = "/board_update.do", method = RequestMethod.GET)
	public ModelAndView board_update(String bid) {
		
		ModelAndView mv = new ModelAndView();
		
		//String bid = request.getParameter("bid");
		CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = dao.select(bid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/board/board_update");
		
		return mv;
	}
	
	/**
	 * board_update_check.do
	 */
	@RequestMapping(value = "/board_update_check.do", method = RequestMethod.POST)
	public ModelAndView board_update_check(CgvBoardVO vo) {
		
		ModelAndView mv = new ModelAndView();
		
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.update(vo);
		
		if(result == 1){
			//response.sendRedirect("board_list.jsp"); //mvc1��
			mv.setViewName("redirect:/board_list.do"); //redirect�� ����ؾ� ���û�� ���� ��Ʈ�ѷ��� ȣ���� �� �ִ� (redirect �ڴ� ��Ī�ּҸ� �ش�)
		}else{
			//response.sendRedirect("../errorPage.jsp"); //mvc1��
			mv.setViewName("error_page"); //���� ���� �ִ� �������� �ƴ����� ����...
		}
		
		return mv;
	}
	
	
	/**
	 * board_write.do
	 */
	@RequestMapping(value = "/board_write.do", method = RequestMethod.GET)
	public String board_write() {
		return "/board/board_write";
	}
	
	/**
	 * board_write_check.do : �Խ��� �۾��� ó��
	 */
	@RequestMapping(value = "/board_write_check.do", method = RequestMethod.POST)
	public ModelAndView board_write_check(CgvBoardVO vo, HttpServletRequest request) throws Exception {
																					 //����ó�� ��Ĺ�� �ñ��
		if(vo.getFile1().getOriginalFilename().equals("")) {
			//System.out.println("title --> " + vo.getBtitle() );
			//System.out.println("content --> " + vo.getBcontent() );
			vo.setBfile("");
			vo.setBsfile(""); //���� ���ε� �������� �̸� �����͵� ����
		}else {
			UUID uuid = UUID.randomUUID();
			
			vo.setBfile(vo.getFile1().getOriginalFilename());
			vo.setBsfile(uuid + "_" + vo.getFile1().getOriginalFilename()); //���� ���ε� ������ ������ ����
			
			//System.out.println("title --> " + vo.getBtitle() );
			//System.out.println("content --> " + vo.getBcontent() );
			//System.out.println("����ڼ��� ���ϸ�(bfile) --> " + vo.getFile1().getOriginalFilename() );
			//System.out.println("bsfile --> " + uuid + "_" + vo.getFile1().getOriginalFilename() );
		}
		
		
		ModelAndView mv = new ModelAndView();
		
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.insert(vo);
		
		if(result == 1){
			if(!vo.getFile1().getOriginalFilename().equals("")) {
				//upload ������ ������ ����
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "\\resources\\upload\\";
				//System.out.println(path); //��� �� ������ Ȯ�ο�
				
				File file = new File(path + vo.getBsfile());
				vo.getFile1().transferTo(file);
			}
			
			//����
			//mv.setViewName("/board/board_list"); //�ٷ� board_list�� ���� DB���� �۾��� �̷����� �ʾ� ������ �����
			mv.setViewName("redirect:/board_list.do"); //DB������ Controller���� �����ϹǷ�, ���ο� ������ �����ϱ� ����!
		}else{
			//����
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
}




