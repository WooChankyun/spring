package com.spring.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycgv.dao.CgvNoticeDAO;
import com.mycgv.vo.CgvNoticeVO;

@Controller
public class NoticeController {

	/**
	 * notice_content.do   // 더미임
	 */
	/*
	 * @RequestMapping(value = "/notice_content.do", method = RequestMethod.GET)
	 * public String notice_content() { return "/notice/notice_content"; }
	 */
	
	/**
	 * notice_content_json.do : 공지사항 상세정보를 Ajax로 호출
	 */
	@ResponseBody
	@RequestMapping(value = "/notice_content_json.do", method = RequestMethod.GET, produces = "test/plain;charset=UTF-8")
	public String notice_content_json(String nid) {
		
		
		//String nid=request.getParameter("nid");  //파라미터로 받음

		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		
		if(vo != null){
			dao.updateHits(nid);
		}
		
		Gson gson = new Gson();
		JsonObject jobject = new JsonObject();
		jobject.addProperty("nid", vo.getNid()); // ex) "nid":n_10
		jobject.addProperty("ntitle", vo.getNtitle()); //ex) "ntitle": "공지사항 테스트"
		jobject.addProperty("ncontent", vo.getNcontent());
		jobject.addProperty("nhits", vo.getNhits());
		jobject.addProperty("ndate", vo.getNdate());
		

		//out.write(gson.toJson(jobject));  // out.write사용하지 않음. 값은 리턴으로 받음 
					//ex) {"nid":n_10, "ntitle":"공지사항 테스트", ...}
		
		return gson.toJson(jobject);
	}
	
	
	/**
	 * notice_list.do : 공지사항 전체 리스트
	 */
	@RequestMapping(value = "/notice_list.do", method = RequestMethod.GET)
	public String notice_list() {
		return "/notice/notice_list";
	}
	
	/**
	 * notice_list_json.do : 공지사항 전체 리스트를 Ajax로 호출
	 */
	@ResponseBody
	@RequestMapping(value = "/notice_list_json.do", method = RequestMethod.GET, produces = "test/plain;charset=UTF-8")
	public String notice_list_json(String rpage) {
		
		//String rpage = request.getParameter("rpage");	//파라미터로 받음

		//DB에서 공지사항 전체 리스트 가져오기
		CgvNoticeDAO dao = new CgvNoticeDAO();

		//startCount, endCount
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


		ArrayList<CgvNoticeVO> list = dao.select(startCount,endCount);
		
		//{list:[{rno:1,ntitle:"탑건~",ndate:"2022-08-01",nhits:100},
		//         {두번째 vo 값},{셋번째 vo 값}...]}
			
		//gson 라이브러리를 이용하여 자바 list 객체를 JSON 객체로 변환
		JsonObject jobject = new JsonObject(); //CgvNoticeVO
		JsonArray jarray = new JsonArray();  //ArrayList
		Gson gson = new Gson();
		
		for(CgvNoticeVO vo : list){
			JsonObject jo = new JsonObject();
			jo.addProperty("rno", vo.getRno());
			jo.addProperty("nid", vo.getNid());
			jo.addProperty("ntitle", vo.getNtitle());
			jo.addProperty("ndate", vo.getNdate());
			jo.addProperty("nhits", vo.getNhits());
			
			jarray.add(jo);
		}// [{rno:1,ntitle:재밌어요,ndate:2022-08-01,nhits:100},... ]
		
		jobject.add("list", jarray); 
		jobject.addProperty("dbCount", dbCount);
		jobject.addProperty("pageSize", pageSize);
		jobject.addProperty("rpage", reqPage);
		jobject.addProperty("pageCount", pageCount);
		//{list:[{rno:1,ntitle:재밌어요,ndate:2022-08-01,nhits:100},. ],
		// dbCount:10, rpage:1, pageSize:5 	
		// }
		
		//out.write(gson.toJson(jobject)); //얘는 리턴이 받음 out.write를 사용하지 않음
		
		return gson.toJson(jobject);
	}
}
