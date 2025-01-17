<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
    <jsp:include page="/WEB-INF/views/include_jsp/include_css_js.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/community/community_modifyView.css">
    <script src="https://cdn.ckeditor.com/4.21.0/standard/ckeditor.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/views/include_jsp/header.jsp" />

<div class="container">
    <h3>게시글 수정</h3>

    <form id="postForm" action="${pageContext.request.contextPath}/community/modify" method="post" enctype="multipart/form-data" class="write-form" onsubmit="return validateForm()">
        <input type="hidden" name="board_no" value="${contentView.board_no}"> <!-- 게시글 번호 -->
		<input type="hidden" name="mem_code" value="${sessionScope.loginUser.mem_code}">
		<input type="hidden" name="mem_nick" value="${sessionScope.loginUser.mem_nick}">
		<input type="hidden" name="pet_img" value="${getpetimg.pet_img}">
        <!-- 기존 대표 이미지 파일명을 히든 필드로 전송 -->
		<input type="hidden" name="orepfile" value="${contentView.orepfile}">
		<input type="hidden" name="chrepfile" value="${contentView.chrepfile}">
	
		 <!-- 기존 일반 이미지 파일명을 히든 필드로 전송 -->
	    <input type="hidden" name="corgfile" value="${contentView.corgfile}"> <!-- 기존 일반 이미지 파일명 -->
	    <input type="hidden" name="cchgfile" value="${contentView.cchgfile}"> <!-- 기존 변경된 일반 이미지 파일명 -->
       
       
		<div class="form-group">
		    <div class="profile-info">
		        <img src="/static/Images/pet/${contentView.pet_img}" alt="프로필 이미지 1" class="profile-image" />
		        <label for="user_id" class="author-label">${sessionScope.loginUser.mem_nick}</label>
		    </div>
		</div>
    
		
        <label for="b_cate_no">카테고리</label>
        <select id="b_cate_no" name="b_cate_no" required>
            <option value="">카테고리를 선택하세요</option> <!-- 기본 선택지 -->
            <c:forEach var="category" items="${categoryList}">
                <option value="${category.b_cate_no}" <c:if test="${category.b_cate_no == contentView.b_cate_no}">selected</c:if>>${category.b_cate_name}</option>
            </c:forEach>
        </select>
		
		<label for="file" class="image-label">사진 업로드</label>
		<input type="file" id="file" name="file" multiple accept="image/*">
		
        <label for="board_title">제목</label>
        <input type="text" id="board_title" name="board_title" value="${contentView.board_title}" placeholder="제목을 입력하세요" required>

        <label for="board_content">내용</label>
        <textarea id="board_content" name="board_content" placeholder="내용을 입력하세요" required>${contentView.board_content}</textarea>
		<option value="${category.b_cate_no}" <c:if test="${category.b_cate_no == contentView.b_cate_no}">selected</c:if>>${category.b_cate_name}</option>
        
        <label for="repfile">대표 이미지 업로드</label>
        <input type="file" id="repfile" name="repfile" accept="image/*" onchange="previewRepImage(event)">
        <div id="newImagePreview" class="post-image" style="margin-top: 10px;"></div>

		<!-- 대표 이미지 미리보기 -->
		<div id="repImagePreview" class="post-image" style="max-width: 100%; height: auto; margin-top: 10px;">
		    <img id="previewImage" src="" alt="대표 이미지 미리보기" style="max-width: 100%; height: auto; display: none;">
		</div>
		
		
		<label>현재 대표 이미지</label>
        <div class="post-image">
            <img src="${pageContext.request.contextPath}/static/images/community_img/${contentView.chrepfile}" alt="현재 이미지" style="max-width: 100%; height: auto;">
        </div>
        
        
        	<div class="button-container">
        <input type="button" id="previewButton" class="btn submit-btn" value="내용 미리보기">
        <input type="submit" id="submit-btn" class="btn submit-btn" value="수정 완료">
  		</div>
    </form>

    <!-- 미리보기 팝업 -->
    <div id="previewPopup" style="display:none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.7); justify-content: center; align-items: center;">
        <div class="popup-content" style="background: white; padding: 20px; border-radius: 5px;">
            <h4>미리보기</h4>
            <div id="preview" class="preview-area"></div>
            <input type="button" id="closePreview" class="btn" value="닫기">
        </div>
    </div>
</div>

<script>
	 // CKEditor 초기화
	    CKEDITOR.replace('board_content', {
	        height: 700,
	        on: {
	            instanceReady: function() {
	                this.dataProcessor.htmlFilter.addRules({
	                    elements: {
	                        img: function(el) {
	                            el.attributes.style = 'max-width:1000px;max-height:400px;width:auto;height:auto;';
	                            return el;
	                        }
	                    }
	                });
	            }
	        }
	    });

	    // 대표 이미지 미리보기 함수
	    function previewRepImage(event) {
	        const file = event.target.files[0]; 
	        const reader = new FileReader(); 

	        reader.onload = function(e) {
	            const preview = document.getElementById('previewImage');
	            preview.src = e.target.result; 
	            preview.style.display = 'block'; 
	        };

	        if (file) {
	            reader.readAsDataURL(file); 
	        } else {
	            const preview = document.getElementById('previewImage');
	            preview.src = ""; 
	            preview.style.display = 'none'; 
	        }
	    }

	    // 파일 업로드 이벤트 리스너 추가
	    document.getElementById('file').addEventListener('change', function(event) {
	        for (let i = 0; i < event.target.files.length; i++) {
	            const file = event.target.files[i];
	            const reader = new FileReader();

	            reader.onload = function(e) {
	                const img = '<img src="' + e.target.result + '" style="max-width:100%; max-height:400px; width:auto; height:auto;">';
	                CKEDITOR.instances.board_content.insertHtml(img); 
	            };

	            reader.readAsDataURL(file); 
	        }
	    });

	    // 미리보기 버튼 클릭 시 팝업 열기
	    document.getElementById('previewButton').addEventListener('click', function() {
	        const previewContent = CKEDITOR.instances.board_content.getData();
	        document.getElementById('preview').innerHTML = previewContent; 
	        document.getElementById('previewPopup').style.display = 'flex'; 
	    });

	    // 닫기 버튼 클릭 시 팝업 닫기
	    document.getElementById('closePreview').addEventListener('click', function() {
	        document.getElementById('previewPopup').style.display = 'none'; 
	    });

/* 	    // 폼 유효성 검사 함수
	    function validateForm() {
	        var imageInput = document.getElementById("repfile").value; 
	        if (imageInput === "") {
	            alert("대표 이미지를 선택하세요.");
	            return false; 
	        }
	        return true; 
	    } */
	</script>

<footer>
    <jsp:include page="/WEB-INF/views/include_jsp/footer.jsp" />
</footer>
</body>
</html>