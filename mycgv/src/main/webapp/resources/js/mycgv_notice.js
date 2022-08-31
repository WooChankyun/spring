$(document).ready(function(){
		
		//페이지가 로딩되면 바로 initAjax 함수를 호출하도록 함!!
		initAjax(1);
		
		
		function initAjax(rpage){
			//페이지의 호출이 시작되면 바로 ajax를 통해 공지사항 리스트를 가져옴
			
				$.ajax({
					url:"notice_list_json.do?rpage="+rpage,
					success:function(result){
						//1. 서버에서 전송된 콜백함수의 파라미터값을 JSON 객체 변환
						let dataset = JSON.parse(result); 
						
						//2. JSON 객체를 Dynamic HTML를 이용하여 화면에 결과 출력	
						var output = "<table class='board'>";
						output += "<tr>"
						output += "<th>번호</th>"
						output += "<th>제목</th>"
						output += "<th>등록날짜</th>"
						output += "<th>조회수</th>"
						output += "</tr>"
						for(obj of dataset.list){
								output += "<tr>"
								output += "<td>"+ obj.rno  +"</td>"
								output += "<td><a href='#' class='bclass' id='"+ obj.nid+"'>"+ obj.ntitle +"</a></td>"
								output += "<td>"+ obj.ndate +"</td>"
								output += "<td>"+ obj.nhits +"</td>"
								output += "</tr>"
						}
						output += "<tr>"
						output += "<td colspan='4'><div id='ampaginationsm'></div></td>"
						output += "</tr>"
						output += "</table>";
						
			
						//3. 출력
						$("table.board").remove();
						$("h1").after(output); 		
						
						
						//페이징 리스트 출력 함수 호출			
						noticePager(dataset.dbCount,dataset.pageSize,dataset.rpage);
						
						
						//페이징 번호 클릭 시 이벤트 처리
						jQuery('#ampaginationsm').on('am.pagination.change',function(e){
							//alert(e.page);
							   jQuery('.showlabelsm').text('The selected page no: '+e.page);
					           //$(location).attr('href', "http://localhost:9000/mycgv/notice/notice_list.jsp?rpage="+e.page);
					           //함수를 호출하는 방식으로 수정
					           initAjax(e.page);
					    });
														
						
						//제목에 대한 이벤트 처리
						$(".bclass").click(function(){
							//alert("제목클릭;; nid=" + $(this).attr("id"));
							//함수 호출을 통해 기능 구현 
							noticeContent($(this).attr("id"));
						});					
						
					}//success
				});//ajax
					
		}//initAjax		
		
		
			
		
		//페이징 처리 출력
		function noticePager(dbCount,pageSize,rpage){
			//alert(dbCount+","+pageSize+","+rpage);
			var pager = jQuery('#ampaginationsm').pagination({
				
			   	maxSize: 7,	    		// max page size
			    totals: dbCount,	// total rows	
			    page: 	rpage,		// initial page		
			    pageSize: pageSize,	// max number items per page
			
			    // custom labels		
			    lastText: '&raquo;&raquo;', 		
			    firstText: '&laquo;&laquo;',		
			    prevText: '&laquo;',		
			    nextText: '&raquo;',
					     
			    btnSize:'sm'	// 'sm'  or 'lg'	 	
			});
		}
		
		
		
		
		
		//제목 클릭 이벤트 - bclass에 대한 기능 구현
		function noticeContent(nid){
			//alert("상세보기;;;nid="+nid);
			$.ajax({
				url:"notice_content_json.do?nid="+nid,
				success:function(result){
					//alert(result);
					let data = JSON.parse(result);
					
					let output = "<table class='boardContent'>";
					output += "<tr><th>등록일자</th>";
					output += "<td>"+ data.ndate +"</td>";
					output += "<th>조회수</th>";
					output += "<td>"+ data.nhits +"</td></tr>";
					output += "<tr>";
					output += "<th>제목</th>";
					output += "<td colspan='3'>" + data.ntitle + "</td>";
					output += "</tr>";
					output += "<tr>";
					output += "<th>내용</th>";
					output += "<td colspan='3'>" + data.ncontent + "<br><br><br></td>";
					output += "</tr>";
					output += "<tr>";
					output += "<td colspan='4'>";
					output += "<button type='button' class='btn_style' id='backList'>리스트</button>";
					output += "<button type='button' class='btn_style' id='backHome'>홈으로</button>";
					output += "</td></tr>";
					output += "</table>";
										
					//출력
					$("table.board").remove();
					$("h1").after(output);
					
					//리스트 버튼에 대한 이벤트
					$("#backList").click(function(){
						$(location).attr("href","notice_list.do");
						//location.href = "이동할 주소";
					});
					
					//홈으로 버튼에 대한 이벤트
					$("#backHome").click(function(){
						$(location).attr("href","http://localhost:9000/mycgv/index.do");
					});
					
				}//success
				
			});//ajax
			
		}//noticeContent	
		
		
		
	});//ready
	
	