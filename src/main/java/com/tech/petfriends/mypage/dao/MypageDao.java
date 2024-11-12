package com.tech.petfriends.mypage.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tech.petfriends.admin.dto.CouponDto;
import com.tech.petfriends.admin.dto.OrderStatusDto;
import com.tech.petfriends.helppetf.dto.PethotelFormDataDto;
import com.tech.petfriends.helppetf.dto.PethotelMemDataDto;
import com.tech.petfriends.login.dto.MemberAddressDto;
import com.tech.petfriends.login.dto.MemberLoginDto;
import com.tech.petfriends.mypage.dto.MyCartDto;
import com.tech.petfriends.mypage.dto.MyOrderDto;
import com.tech.petfriends.mypage.dto.MyPetDto;
import com.tech.petfriends.mypage.dto.MyWishDto;

@Mapper
public interface MypageDao {
	
	// 회원가입시 펫등록
	void insertMyPet(MyPetDto pet);
	
	// 내새꾸
	// 펫 이미지 삭제
	void deletePetImgForPetCode(String petCode);
	
	// 펫 전체 리스트 가져오기
	ArrayList<MyPetDto> getPetList();
	
	// 펫코드로 이미지 가져오기
	String getPetImgForPetCode(String petCode);

	ArrayList<MyPetDto> getPetsByMemberCode(String mem_code);
	void removeMainPet(String mem_code);
	void setMainPet(String newlyChecked);
	ArrayList<String> getBreedOptionByType(String petType);
	void insertPet(MyPetDto myPetDto);
	MyPetDto getInfoByPetCode(String petCode);
	void modifyPetByPetCode(MyPetDto myPetDto);
	void deletePetByPetCode(String petCode);
	
	// 쿠폰
	ArrayList<CouponDto> getAllCoupon();
	ArrayList<CouponDto> getCouponByMemberCode(String mem_code);
	CouponDto searchCouponByKeyword(String keyword);
	int checkIssued(String mem_code, int cp_no);
	void insertCouponByCouponNo(String mc_code, String mem_code, int cp_no);
	
	// 내 정보 변경
	ArrayList<MemberAddressDto> getAddrByMemberCode(String mem_code);
	void updatePhoneNumber(String memCode, String phoneNumber);
	void updateDefaultAddress(String memCode);
	void setMainAddress(String addrCode);
	void deleteAddress(String addrCode);
	boolean insertNewAddress(String addrCode, String memCode, String addrPostal, String addrLine1, String addrLine2);
	void updateMemberInfo(MemberLoginDto loginUser);
	
	// 장바구니
	List<MyCartDto> getCartByMemberCode(String mem_code);
	void deleteAllCartItems(String mem_code);
	boolean updateCartQuantity(String newQuantity, String cartCode);
	List<MyCartDto> getItemByCartCode(String cartCode);
	void deleteCartItem(String cartCode);
	List<MyCartDto> getItemsByCartCodes(List<String> cartCodes);
	
	// 결제
	void insertOrderCode(String cartCode, String o_code);
	void insertOrder(MyOrderDto orderData);
	void insertOrderStatus(String o_code);
	void updateCouponByOrder(String mc_code);
	void updateAmountByOrder(MyOrderDto orderData);
	
	// 주문내역
	ArrayList<MyOrderDto> getOrderByMemberCode(String mem_code);
	Collection<? extends OrderStatusDto> getStatusByOrderCode(String o_code);
	Collection<? extends MyCartDto> getCartByOrderCode(String o_code);
	
	// 즐겨찾는 상품
	ArrayList<MyWishDto> getAllWishInfoByMemberCode(String mem_code, String sortType);
	List<MyOrderDto> getAllOrderInfoByMemberCode(String mem_code, String orderable);
	void deleteWishByProCode(String mem_code, String pro_code);

	ArrayList<PethotelMemDataDto> pethotelReserveMypageMem(String mem_code);

	PethotelMemDataDto pethotelReserveMypageMemNo(String reserveNo);

	ArrayList<PethotelFormDataDto> pethotelReserveMypagePets(String reserveNo);

	void pethotelReserveMyPageCancel(String reserveNo);
}
