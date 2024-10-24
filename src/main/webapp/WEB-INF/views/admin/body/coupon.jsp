<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="/static/css/admin/coupon.css">
<title>
	admin_body
</title>
</head>
<body>
<div class="title"><h3>쿠폰 관리</h3></div>

<!-- 탭 영역 -->
<div class="tab-section">
    <button class="tab-btn active" data-tab="couponRegister">쿠폰 등록</button>
    <button class="tab-btn" data-tab="couponStatus">회원쿠폰현황</button>
</div>

<!-- 탭별 내용 -->
<!-- 쿠폰 등록 -->
<div id="couponRegister" class="tab-content">
	<!-- 필터링 영역 -->
	<div class="filter-section-1" id="status-filter">
	    <div class="radio-group">
	        <div class="filter-title">발급상태</div>
	        <label><input type="radio" name="status-filter" value="전체" checked> 전체</label>
	        <label><input type="radio" name="status-filter" value="발급중"> 발급중</label>
	        <label><input type="radio" name="status-filter" value="예정"> 예정</label>
	        <label><input type="radio" name="status-filter" value="종료"> 종료</label>
	    </div>
	    <div class="radio-group" id="type-filter">
	        <div class="filter-title">쿠폰 종류</div>
	        <label><input type="radio" name="type-filter" value="전체" checked> 전체</label>
	        <label><input type="radio" name="type-filter" value="A"> 할인액</label>
	        <label><input type="radio" name="type-filter" value="R"> 할인율</label>
	    </div>
	</div>
	
	<div class="array-section">
	    <!-- 정렬 드롭다운 -->
        <select id="sort-order">
            <option value="최신순">최신순</option>
            <option value="발급순">발급순</option>
            <option value="사용액순">사용액순</option>
        </select>
	    <!-- 신규등록 버튼 -->
	    <button id="new-coupon-btn" class="btn-style">신규등록</button>
	</div>
	
	<!-- 리스트 영역 -->
	<div class="coupon-list-container">
		<table class="coupon-list">
		    <thead class="thead">
		        <tr>
		            <th>번호</th>
		            <th>쿠폰명</th>
		            <th>쿠폰 키워드</th>
		            <th>발급일</th>
		            <th>종료일</th>
		            <th>할인액/율</th>
		            <th>발급수</th>
		            <th>누적 사용액</th>
		            <th>수정/삭제</th>
		        </tr>
		    </thead>
		    <tbody id="coupon-table-body">
		        <!-- 전체 쿠폰 데이터 출력 -->
		    </tbody>
		</table>
		
		<div id="pagination">
			<!-- 페이징 -->
		</div>
	</div>
</div>

<!-- 회원쿠폰현황 -->
<div id="couponStatus" class="tab-content" style="display: none;">
    <!-- 필터링 영역 -->
	<div class="filter-section-2">
        <!-- 쿠폰 상태 체크박스 필터 -->
	    <div class="filter-section-1">
	        <div class="checkbox-group">
	            <div class="filter-title">쿠폰상태</div>
	            <label><input type="checkbox" name="issue-filter" value="발급" checked> 발급</label>
	            <label><input type="checkbox" name="issue-filter" value="사용" checked> 사용</label>
	            <label><input type="checkbox" name="issue-filter" value="만료" checked> 만료</label>
	        </div>
	    </div>
		
        <!-- 조회 기간 필터 -->
		<div class="filter-section-1">
	        <div class="date-group">
	            <div class="filter-title">조회기간</div>
	            <select id="search-order">
		            <option value="mc_issue">발급</option>
		            <option value="mc_use">사용</option>
		            <option value="mc_dead">만료</option>
		        </select>
	            <label><input type="text" id="start-date" placeholder="YYYY-MM-DD">부터</label>
	            <label><input type="text" id="end-date" placeholder="YYYY-MM-DD">까지</label>
	            <button id="reset-date" class="btn-style">전체보기</button>
	        </div>
	    </div>
	
        <!-- 검색 필터 -->
	    <div class="filter-section-1">
	        <div class="search-group">
	            <div class="filter-title">검색</div>
	            <label>회원코드<input type="text" id="search-member-code"></label>
	            <label>쿠폰번호<input type="text" id="search-coupon-code"></label>
	            <label>결제코드<input type="text" id="search-order-code"></label>
	            <button id="search-btn" class="btn-style">조회</button>
	        </div>
	    </div>
	</div>
	
	<!-- 리스트 영역 -->
	<div class="member-coupon-list-container">
		<table class="coupon-list">
		    <thead class="thead">
		        <tr>
		            <th>회원쿠폰코드</th>
		            <th>회원명</th>
		            <th>쿠폰명</th>
		            <th>발급일시</th>
		            <th>사용일시</th>
		            <th>결제코드</th>
		            <th>만료일시</th>
		        </tr>
		    </thead>
		    <tbody id="member-coupon-table-body">
		        <!-- 회원 쿠폰 데이터 출력 -->
		    </tbody>
		</table>
		
		<div id="pagination">
			<!-- 페이징 -->
		</div>
	</div>
</div>

<script src="/static/js/admin/coupon.js"></script>
</body>
</html>