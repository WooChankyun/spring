<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9000/mycgv/resources/css/mycgv.css">
<script src="http://localhost:9000/mycgv/resources/js/jquery-3.6.0.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="http://localhost:9000/mycgv/resources/js/mycgv_jquery.js"></script>
<script>
	$(document).ready(function(){
		//중복확인 버튼 이벤트 처리 --> AJAX 호출
		$("#idCheck").click(function(){
			if($("#id").val() == ""){
				alert("아이디를 입력해주세요");
				$("#id").focus();
				return false;
			}else{
				//중복체크 진행 : AJAX 호출 및 결과 출력
				$.ajax({
					url:"id_check.do?id="+$("#id").val(),
					success:function(result){
						//alert("result===>>" + result);
						if(result == 1){
							$("#idCheckMsg").text("사용중인 아이디입니다. 다시 입력해주세요~")
								.css("color","red").css("font-size","11px")
								.css("margin","5px 0 0 156px");
							$("#id").val("").focus();							
						}else{
							$("#idCheckMsg").text("사용 가능한 아이디입니다.")
							.css("color","blue").css("font-size","11px")
							.css("margin","5px 0 0 156px");
							$("#pass").focus();
						}
						
					}//success
					
				});//ajax
			}
		});//ready
	});
</script>
</head>
<body>
	<!-- Header Include -->
	<iframe src="http://localhost:9000/mycgv/header.do" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>
	
	
	<!---------------------------------------------->
	<!--------------- Content ----------------------->
	<!---------------------------------------------->
	<div class="content">
		<h1>회원가입</h1>
		<form name="joinForm"  action="joinCheck.do"  method="post">
		
			<ul>
				<li>
					<label>아이디</label>
					<input type="text" name="id" id="id" placeholder="*영문자숫자포함 8자이상">
					<button type="button" class="btn_style" id="idCheck">중복확인</button>
					<div id="idCheckMsg"></div>
				</li>
				<li>
					<label>비밀번호</label>
					<input type="password" name="pass" id="pass">
				</li>
				<li>
					<label>비밀번호 확인</label>
					<input type="password" name="cpass" id="cpass" >
					<span id="passCheckMsg">*비밀번호를 다시 입력해주세요</span>
				</li>
				<li>
					<label>성명</label>
					<input type="text" name="name" id="name">
				</li>
				<li>
					<label>성별</label>
					<input type="radio" name="gender" value="m"><span>남자</span>
					<input type="radio" name="gender" value="f"><span>여자</span>
				</li>
				<li>
					<label>이메일</label>
					<input type="text" name="email1" id="email1"><span>@</span>
					<input type="text" name="email2" id="email2">
					<select id="email3" >
						<option value="default">선택</option>
						<option value="naver.com">네이버</option>
						<option value="gmail.com">구글</option>
						<option value="daum.net">다음</option>
						<option value="self">직접입력</option>
					</select>
				</li>
				<li>
					<label>주소</label>
					<input type="text" name="zonecode" id="zonecode" placeholder="우편번호">
					<input type="text" name="addr1" id="addr1">
					<button type="button" class="btn_style" id="btnSearchAddr">주소찾기</button>
				</li>
				<li>
					<label>상세주소</label>
					<input type="text" name="addr2" id="addr2">
				</li>
				<li>
					<label>휴대폰</label>
					<div>
						<input type="radio" name="hp" value="SKT"><span>SKT</span>
						<input type="radio" name="hp" value="LGU+"><span>LGU+</span>
						<input type="radio" name="hp" value="KT"><span>KT</span>
					</div>
					<select name="pnum1" id="pnum1">
						<option value="default">선택</option>
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
					</select> - 
					<input type="text" name="pnum2" id="pnum2"> -
					<input type="text" name="pnum3" id="pnum3"> 
				</li>
				<li>
					<label>취미</label>
					<input type="checkbox" name="hobby" value="영화보기"><span>영화보기</span>
					<input type="checkbox" name="hobby" value="등산하기"><span>등산하기</span>
					<input type="checkbox" name="hobby" value="게임하기"><span>게임하기</span>
					<input type="checkbox" name="hobby" value="잠자기"><span>잠자기</span>
	
				</li>
				<li>
					<label>자기소개</label>
					<textarea name="intro">*200자 이내로 작성해주세요</textarea>
				</li>
				<li>				
					<button type="button" class="btn_style" id="btnJoin">가입하기</button>
					<button type="reset" class="btn_style">다시입력</button>				
				</li>
			</ul>	
		</form>	 
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9000/mycgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>







