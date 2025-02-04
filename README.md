# project_Petfriend

### 실제 사이트인 '펫프렌즈' 기반의 펫 라이프 종합 쇼핑몰 커뮤니티   

****레퍼런스**** : <a href="https://pet-friends.co.kr">펫프렌즈(Petfriends)</a>    
****개발 기간**** : 2024.10.14 ~ 2024.11.21     
****개발 인원**** : 5명     
****담당 업무**** : Git 담당자     
****언어**** : JDK 11, HTML/CSS, JavaScript     
****프레임워크**** : Spring 5.3.24, Spring Boot 2.6.12, MyBatis 2.2.2     
****DataBase**** : Oracle 11g XE     


### <담당 세부기능>
#### 고객단 : 반려동물 관련 서비스 페이지  
#### 관리자단 : 게시물 관리, 예약 및 안내문 관리 페이지     

[시설찾기]   

1. 카카오맵API를 이용한 회원의 배송주소 기반 동물병원, 반려동물 동반 가능 시설 찾기
* 카테고리(병원, 시설)에 따른 검색결과를 지도 위의 마커와 리스트로 표현
  
  ![동물병원_1](https://github.com/user-attachments/assets/1e7c6f52-8387-461e-ae59-c7e869dcde53)
  
2. 마커와 리스트를 클릭 시 로드뷰를 표시
* 주소의 좌표와 가장 가까운 로드뷰를 호출

  ![시설찾기_5](https://github.com/user-attachments/assets/16427633-3a1d-46a8-96b9-54d08a9f589c)

3. 지정한 주소는 우편번호찾기 API를 통해 변경 가능
* 변경시 지도가 재구성되며 새로운 결과를 표시

![동물병원_3](https://github.com/user-attachments/assets/0239fbed-b3e6-44f7-a48b-ef355530be8f)
![동물병원_4](https://github.com/user-attachments/assets/6b884967-ab65-485d-b303-3fe2583897c2)

[펫티쳐]  
1. 반려동물 관련 유튜브 영상 열람 
* 각종 필터링에 따른 결과를 표시

![펫티쳐_1](https://github.com/user-attachments/assets/2787907a-9c47-4ef7-8574-9ab79cd19861)

2. 유튜브 iframe API를 이용해 실제 유튜브의 영상을 스트리밍
* 관리자가 작성한 게시물이며 연결된 관리자 페이지에서 수정 및 게시 가능

![펫티쳐_2](https://github.com/user-attachments/assets/e4624d5a-bec0-4ed7-a0ea-54e5505db377)
![헬프펫프_펫티쳐_관리자 (3)](https://github.com/user-attachments/assets/7697deef-132f-453c-9a15-a5c8643c7142)


[입양센터]  
1. 현재 보호중인 구조동물들의 목록 표시
* 공공데이터포털의 오픈API를 이용해 보호소에서 보호중인 동물들의 목록 표시

  ![입양센터_1](https://github.com/user-attachments/assets/bcd37bb7-add2-497a-94fa-d3375a9d6fbc)

2. 각각 동물들의 세부정보 표시
* 지역, 동물종류, 성별 등의 정보와 보호중인 보호소의 지도 표시
  
![입양센터_2](https://github.com/user-attachments/assets/30042706-9ad0-4d29-a168-8366b934f29d)
![입양센터_3](https://github.com/user-attachments/assets/b8e3430f-35d2-40f5-bd51-c1dd1bf108da)

[펫호텔]  
1. 펫 호텔링 서비스 안내
* 간단한 정보와 예약 시스템 안내
  
  ![펫호텔_1](https://github.com/user-attachments/assets/ca2eb84c-9a69-407d-a065-161e92625a6a)
![펫호텔_4](https://github.com/user-attachments/assets/0933bc07-a2dd-4b56-8be2-3744e8d0876e)

2. 펫 호텔링 서비스 예약
* 유저의 데이터베이스에 등록되어 있는 반려동물들의 정보를 불러와 사용가능

![펫호텔_5](https://github.com/user-attachments/assets/e108b20c-14a7-4bea-b108-6d77710324fe)
![펫호텔_8](https://github.com/user-attachments/assets/7432bd61-6c70-4308-91da-d21f878a8b4f)
![펫호텔_9](https://github.com/user-attachments/assets/9d66abcf-6196-4fb7-ba44-2e050af4cca7)

3. 다중 폼(form) 동시 제출
* 여러마리의 동물을 한번에 예약할 수 있음

![펫호텔_13](https://github.com/user-attachments/assets/7132324e-f2fa-4972-9516-f5fa9564f288)
![펫호텔_15](https://github.com/user-attachments/assets/b1740721-9b9d-4994-a51f-485a8087ba88)

4. 펫캠(CCTV열람) 서비스
* 연결된 CCTV 캠을 통한 영상 열람 가능
  
![스크린샷 2025-02-04 222134](https://github.com/user-attachments/assets/a8281c00-4e68-452a-b77d-57b6dc719d72)

5. 연결된 관리자 페이지에서 소개, 안내 문구와 예약된 건들을 관리 
* 소개, 안내글의 수정, 예약건들의 상태 변경
![헬프펫프_펫호텔_관리자_안내문관리 (2)](https://github.com/user-attachments/assets/769f6ddb-dc43-4d4a-983e-3843db956cff)
![헬프펫프_펫호텔_관리자_예약관리 (10)](https://github.com/user-attachments/assets/691e484d-b95d-4cea-baba-38a2bea2952d)


---


 
