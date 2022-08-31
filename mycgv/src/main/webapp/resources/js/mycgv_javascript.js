/*********************
	공지사항 등록폼 유효성 체크
**********************/
noticeWriteCheck = () => {
	const ntitle = document.getElementById("ntitle");
	if(ntitle.value == ""){
		alert("제목을 입력해주세요");
		ntitle.focus();
		return false;
	}else{
		//서버전송
		boardWriteForm.submit();
	}
}

/*********************
	공지사항 수정폼 유효성 체크
**********************/
noticeUpdateCheck = () => {
	const ntitle = document.getElementById("ntitle");
	if(ntitle.value == ""){
		alert("제목을 입력해주세요");
		ntitle.focus();
		return false;
	}else{
		//서버전송
		boardUpdateForm.submit();
	}
}


/*********************
	게시판 등록폼 유효성 체크
**********************/
boardWriteCheck = () => {
	const btitle = document.getElementById("btitle");
	if(btitle.value == ""){
		alert("제목을 입력해주세요");
		btitle.focus();
		return false;
	}else{
		//서버전송
		boardWriteForm.submit();
	}
}

/*********************
	게시판 수정폼 유효성 체크
**********************/
boardUpdateCheck = () => {
	const btitle = document.getElementById("btitle");
	if(btitle.value == ""){
		alert("제목을 입력해주세요");
		btitle.focus();
		return false;
	}else{
		//서버전송
		boardUpdateForm.submit();
	}
}

/*********************
	회원가입 폼 유효성 체크 --> 서버의 효율성을 높이기위함:부하를 줄임
**********************/
joinCheck = () => {
	const id = document.getElementById("id");
	const pass = document.getElementById("pass");
	const cpass = document.getElementById("cpass");
	const name = document.getElementById("name");
	const email1 = document.getElementById("email1");
	const email2 = document.getElementById("email2");
	const email3 = document.getElementById("email3");
	const addr1 = document.getElementById("addr1");
	const addr2 = document.getElementById("addr2");
	const pnum1 = document.getElementById("pnum1");
	const pnum2 = document.getElementById("pnum2");
	const pnum3 = document.getElementById("pnum3");
	
	if(id.value == ""){
		alert("아이디를 입력해주세요");
		id.focus();
		return false;
	}else if(pass.value == ""){
		alert("패스워드를 입력해주세요");
		pass.focus();
		return false;
	}else if(cpass.value == ""){
		alert("패스워드 확인을 입력해주세요");
		cpass.focus();
		return false;
	}else if(name.value == ""){
		alert("성명을 입력해주세요");
		name.focus();
		return false;
	}else if(checkCount("gender") == 0){
		alert("성별을 선택해주세요");
		return false;
	}else if(email1.value == ""){
		alert("이메일을 입력해주세요");
		email1.focus();
		return false;
	}else if(email2.value == ""){
		alert("이메일 주소를 선택해주세요");
		email3.focus();
		return false;
	}else if(addr1.value == ""){
		alert("주소를 입력해주세요");
		addr1.focus();
		return false;
	}else if(addr2.value == ""){
		alert("상세주소를 입력해주세요");
		addr2.focus();
		return false;
	}else if(checkCount("hp") == 0){
		alert("통신사를 선택해주세요");
		return false;
	}else if(pnum1.value == "default"){
		alert("폰번호를 선택해주세요");
		pnum1.focus();
		return false;
	}else if(pnum2.value == ""){
		alert("폰번호를 입력해주세요");
		pnum2.focus();
		return false;
	}else if(pnum3.value == ""){
		alert("마지막 폰번호를 입력해주세요");
		pnum3.focus();
		return false;
	}else if(checkCount("hobby") == 0){
		alert("취미를 선택해주세요");
		return false;
	}else{
		//서버전송
		joinForm.submit();
	}
	
}


/*********************
	radio, checkbox --> checked 결과를 count로 체크
**********************/
//function checkCount(fname){
checkCount = (fname) => {
	const fnameList = document.getElementsByName(fname);
	let count = 0;
	
	for(element of fnameList){
		if(element.checked){
			count++;
		}
	}
	return count;
}


/*********************
	이메일 주소 선택
**********************/
//function emailCheck(){
emailCheck = () => {
	const email2 = document.getElementById("email2");
	const email3 = document.getElementById("email3");
	
	if(email3.value == "default"){
		alert("이메일 주소를 선택해주세요");
		email3.focus();
		email2.value = "";
	}else if(email3.value == "self"){
		email2.value = "";
		email2.focus();
	}else{
		email2.value = email3.value;
	}	
}




/*********************
	비밀번호, 비밀번호 확인 비교
**********************/
//function passCheck(){
passCheck = () =>{
	const pass = document.getElementById("pass");
	const cpass = document.getElementById("cpass");
	const msg = document.getElementById("passCheckMsg");
	
	//pass와 cpass가 비어있지 않은상태에서만 비교!!
	if(pass.value != "" && cpass.value != ""){
		if(pass.value == cpass.value){
			msg.innerHTML = "*비밀번호가 동일합니다.";
			msg.style.color = "blue";
			msg.style.fontSize = "12px";
			
		}else{
			msg.innerHTML = "*비밀번호가 동일하지 않습니다. 다시 입력해주세요.";
			msg.style.color = "red";
			msg.style.fontSize = "12px";
			pass.value = "";
			cpass.value = "";
			pass.focus();
		}
	}//else{} --> 회원가입 폼의 유효성 체크로 진행됨
}




/*********************
	로그인폼 체크 함수
**********************/
//function loginCheck(){
loginCheck = () => {  //arrow function:ES6
	const id = document.getElementById("id");
	const pass = document.getElementById("pass");
	
	if(id.value == ""){
		alert("아이디를 입력해주세요");
		id.focus();
		return false;
	}else if(pass.value == ""){
		alert("패스워드를 입력해주세요");
		pass.focus();
		return false;
	}else{
		//서버전송
		loginForm.submit();
	}
	
}//loginCheck()



