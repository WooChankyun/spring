<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mycgv.vo.CgvNoticeVO"  %>
<%@ page import="com.mycgv.dao.CgvNoticeDAO"  %>
<%@ page import="java.util.ArrayList"  %>
<%@ page import="com.google.gson.*"  %>
<%
	String rpage = request.getParameter("rpage");	

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
	//{list:[{rno:1,ntitle:재밌어요,ndate:2022-08-01,nhits:100},. ],
	// dbCount:10, rpage:1, pageSize:5 	
	// }
	out.write(gson.toJson(jobject));
%>





