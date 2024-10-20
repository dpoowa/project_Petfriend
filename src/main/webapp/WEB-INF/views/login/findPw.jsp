<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/include_jsp/include_css_js.jsp" />
<link rel="stylesheet" href="/static/css/login/findIdAndPw.css" />
<script src="/static/js/findIdAndPw.js"></script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/include_jsp/header.jsp" />
    <div class="find-container">
        <div class="find-tabs">
            <a href="findId">
            아이디 찾기
            </a>
            <a href="findPw" class="active" >
            비밀번호 찾기
            </a>
        </div>

        <form action="/findId" method="post" class="find-form">       	
        	<div class="form-group">
                <label for="name">아이디(Email)</label>
                <input type="email" id="email" name="email" placeholder="이메일을 입력해주세요">
            </div>
        
            <div class="form-group">
                <label for="name">이름</label>
                <input type="text" id="name" name="name" placeholder="이름 또는 닉네임을 입력해주세요">
            </div>

            <div class="form-group">
                <label for="phoneNumber">휴대폰 번호</label>
                <input type="text" id="phoneNumber" name="phoneNumber" placeholder="'-'를 제외한 숫자만 입력해주세요">
                <button type="button" class="send-code-btn" disabled>인증번호 전송</button>
            </div>

            <div class="form-group">
                <label for="verificationCode">인증번호</label>
                <input type="text" id="verificationCode" name="verificationCode" placeholder="인증번호를 입력하세요">
            </div>

            <button type="submit" class="submit-btn-Pw">인증확인</button>
        </form>
        <a href=""><h4>내새꾸 정보로 비밀번호 찾기</h4></a>
    </div>
</body>
</html>
