$(document).ready(function(){
	//alert("index 호출!!"); //확인용

	/**
	 * index 페이지 로딩 시 공지사항 리스트 가져오기
     */
	initNoticeList(1);
	
	function initNoticeList(rpage){
		//Ajax를 이용한 데이터 가져오기
		$.ajax({
			url : "notice_list_json.do?rpage="+rpage,
			success : function(result){
				//alert(result); //확인용
				
				//1. 콜백함수의 결과인 result값은 문자열이다!! >> JSON객체로 변환
				let notice = JSON.parse(result);
				
				//ex) result의 꼴
				//		{list:
				//				[{"rno":1,"ntitle":"공지사항~~",...}
				//      		...
				//      		]
				//		}
				
				
				
				//2. Dynamic HTML을 이용하여 JSON 결과를 출력하는 코드 구성 (success를 벗어나면 적용이 안되므로 여기서 html을 구성해야한다)
				let output = "<div class='notice_style'><h3>공지사항</h3>";
				output += "<div>";
				output += "&nbsp;&nbsp;<span id='pre'>pre</span>&nbsp;&nbsp;|";
				output += "&nbsp;&nbsp;<span id='next'>next</span>&nbsp;&nbsp;|";
				output += "&nbsp;&nbsp;<span id='more'>more</span>";
				output += "</div>";
				output += "<ul class='notice_list'>"; //css를 적용하기 위해 class 준다
				for(data of notice.list){
					output += "<li>";
					output += "<span>" + data.rno + "</span>";
					output += "<span>" + data.ntitle + "</span>";
					output += "<span>" + data.ndate + "</span>";
					output += "<span>" + data.nhits + "</span>";
					output += "</li>";
				}
				
				output += "</ul></div>";
				
				
				
				//3. 생성된 Dynamic HTML 코드를 해당위치에 출력
				$("div.notice_style").remove();
				$("div.s2_article > div").after(output);
				
				
				//pre, next 버튼 활성화
				if(rpage >= 1 && rpage < notice.pageCount){ //현재페이지가 1 이상이고, 현재페이지가 pageCount값보다 작은 경우 : pre 비활성화
					//alert(notice.pageCount); //확인용
					$("#pre").css("visibility", "hidden");
					$("#next").css("color", "red");
				}else{//next 버튼 비활성화 : 그렇지 않은 경우
					$("#pre").css("color", "red");
					$("#next").css("visibility", "hidden");
				}
				
				
				
				//4. Dynamic HTML 코드에서 이벤트 처리 진행
				$("#pre").click(function(){
					initNoticeList(rpage-1); //자기자신을 호출 (파라미터에 -1)
				});
				
				
				$("#next").click(function(){
					//alert("next!!"); //확인용
					//alert(rpage+1); //확인용, rpage는 숫자다
					initNoticeList(rpage+1); //자기자신을 호출 (파라미터에 +1)
				});
				
				
				$("#more").hover(function(){ //hover
					$(this).css("text-decoration", "underline");
				}, function(){ //hover:not
					$(this).css("text-decoration", "none");
				});	
				$("#more").click(function(){
					$(location).attr("href", "http://localhost:9000/mycgv/notice_list.do");
				});
				
				
				
			}//success
		});//ajax
	
	
	
	
	}//initNoticeList()
	
});//ready()