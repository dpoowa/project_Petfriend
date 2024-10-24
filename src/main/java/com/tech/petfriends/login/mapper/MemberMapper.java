package com.tech.petfriends.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tech.petfriends.login.dto.MemberAddressDto;
import com.tech.petfriends.login.dto.MemberLoginDto;

@Mapper
public interface MemberMapper {
    // 이메일로 사용자 조회
    MemberLoginDto getMemberByEmail(String email);
    
    // 아이디 찾기
    MemberLoginDto findUserId(String name, String phoneNumber);
    
    // 회원가입
    void insertMember(MemberLoginDto member);
    
    // 회원가입 기본 주소 입력
    void insertJoinAddress(MemberAddressDto address);
    
    // 닉네임 중복 체크
    int isNicknameDuplicate(String nickname);
    
    // 이메일 중복 검사
    int isEmailDuplicate(String email);
    
    // 비밀번호 변경
    void updatePassword(String email, String encryptedPassword);
    
    // 중복회원 검사
    int isPhoneNumberDuplicate(String phoneNumber);
}
