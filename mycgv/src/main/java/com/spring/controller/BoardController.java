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
		
		//String bid = request.getParameter("bid"); //jsp에서만 사용하는 코드이므로 주석 처리. 스프링에서는 파라미터를 통해 받으면 자동으로 맵핑해준다!
		CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = dao.select(bid);
		
		if(vo != null){
			dao.updateHits(bid);
		}
		    
		mv.addObject("vo", vo); // "vo"라는 이름에 vo객체의 값을 담는다
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
	public ModelAndView board_delete_check(String bid) { //파라미터를 CgvBoardVO vo로 해도 되긴 함(근데 막상 실제로 사용할 부분은 bid 하나라 그냥 bid만 해도 됨)
		
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
		
		//화면에서 전송된 페이지요청 번호 가져오기
		//String rpage = request.getParameter("rpage"); //rpage는 board_list(String rpage)로 파라미터로 바로 받는다
		CgvBoardDAO dao = new CgvBoardDAO();

		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = dao.totalCount();	//DB에서 가져온 전체 행수
		
		//총 페이지 수 계산
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//요청 페이지 계산
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
			//response.sendRedirect("board_list.jsp"); //mvc1임
			mv.setViewName("redirect:/board_list.do"); //redirect를 사용해야 재요청을 통해 컨트롤러를 호출할 수 있다 (redirect 뒤는 별칭주소를 준다)
		}else{
			//response.sendRedirect("../errorPage.jsp"); //mvc1임
			mv.setViewName("error_page"); //지금 당장 있는 페이지는 아니지만 ㅎㅎ...
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
	 * board_write_check.do : 게시판 글쓰기 처리
	 */
	@RequestMapping(value = "/board_write_check.do", method = RequestMethod.POST)
	public ModelAndView board_write_check(CgvBoardVO vo, HttpServletRequest request) throws Exception {
																					 //예외처리 톰캣에 맡긴다
		if(vo.getFile1().getOriginalFilename().equals("")) {
			//System.out.println("title --> " + vo.getBtitle() );
			//System.out.println("content --> " + vo.getBcontent() );
			vo.setBfile("");
			vo.setBsfile(""); //파일 업로드 안했으면 이름 데이터도 공란
		}else {
			UUID uuid = UUID.randomUUID();
			
			vo.setBfile(vo.getFile1().getOriginalFilename());
			vo.setBsfile(uuid + "_" + vo.getFile1().getOriginalFilename()); //파일 업로드 했으면 데이터 저장
			
			//System.out.println("title --> " + vo.getBtitle() );
			//System.out.println("content --> " + vo.getBcontent() );
			//System.out.println("사용자선택 파일명(bfile) --> " + vo.getFile1().getOriginalFilename() );
			//System.out.println("bsfile --> " + uuid + "_" + vo.getFile1().getOriginalFilename() );
		}
		
		
		ModelAndView mv = new ModelAndView();
		
		CgvBoardDAO dao = new CgvBoardDAO();
		int result = dao.insert(vo);
		
		if(result == 1){
			if(!vo.getFile1().getOriginalFilename().equals("")) {
				//upload 폴더에 파일을 저장
				String path = request.getSession().getServletContext().getRealPath("/");
				path += "\\resources\\upload\\";
				//System.out.println(path); //경로 잘 나오나 확인용
				
				File file = new File(path + vo.getBsfile());
				vo.getFile1().transferTo(file);
			}
			
			//성공
			//mv.setViewName("/board/board_list"); //바로 board_list로 가면 DB연동 작업이 이뤄지지 않아 문제가 생긴다
			mv.setViewName("redirect:/board_list.do"); //DB연동을 Controller에서 진행하므로, 새로운 연결을 수행하기 위함!
		}else{
			//실패
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
}




