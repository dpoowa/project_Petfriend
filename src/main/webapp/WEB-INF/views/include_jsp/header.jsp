<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="menu">
    <img src="<c:url value='/static/Images/MainImg/LOGO_white.png'/>" id="top_logo" alt="">
    <div class="menu_icons">
        <img src="<c:url value='/static/Images/MainImg/search_icon.png'/>" id="search_icon" alt="">
        <img src="<c:url value='/static/Images/MainImg/user_icon.png'/>" id="user_icon" alt="">
        <a href="<c:url value='/admin/home' />"><img src="<c:url value='/static/Images/MainImg/admin_icon.png'/>" id="admin_icon" alt=""></a>
        <c:if test="${sessionScope.name == '이창재'}">
    		<a href="/admin/home">
        	<img src="<c:url value='/static/Images/MainImg/admin_icon.png'/>" id="admin_icon" alt="관리자 아이콘">
   			</a>
		</c:if>
    </div>
</div><br>
<div id="main_nav">
    <ul>
        <li><a href="/product/productlist">PRODUCT</a></li>
        <li><a href="">NOTICE</a></li>
        <li><a href="">COMMUNITY</a></li>
        <li><a href="/helppetf/find/pet_hospital">HELP PETF!</a></li>
    </ul>
</div>
