package com.tech.petfriends.admin.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.tech.petfriends.admin.dto.ProductListDto;
import com.tech.petfriends.admin.mapper.AdminProductDao;
import com.tech.petfriends.admin.service.AdminExecuteModel;

public class AdminProductListService implements AdminExecuteModel {

	private AdminProductDao adminProductDao;

	private Map<String, Object> data;

	public AdminProductListService(AdminProductDao adminProductDao, Map<String, Object> data) {
		this.adminProductDao = adminProductDao;
		this.data = data;
	}

	@Override
	public void execute(Model model) {

		String petType = (String) data.get("petType");
		String proType = (String) data.get("proType");
		String detailType = (String) data.get("detailType");
		String searchType = (String) data.get("searchType");
		String proOnOff = (String) data.get("proOnOff");

		if (detailType == null) {
			detailType = "";
		}
		if (searchType == null) {
			searchType = "";
		}

		System.out.println(petType + proType + detailType + searchType);

		List<ProductListDto> productList = adminProductDao.adminProductList(petType, proType, detailType, proOnOff);
		List<ProductListDto> searchProductList = similarList(searchType, productList);

		model.addAttribute("productList", searchProductList);
	}

	private List<ProductListDto> similarList(String searchPro, List<ProductListDto> allList) {
		// 결과 리스트를 초기화
		List<ProductListDto> resultList = new ArrayList<>();

		// 검색어 공백 제거
		String cleanedSearchPro = searchPro.replaceAll("\\s+", "");

		// 검색어를 한 글자씩 나누기
		String[] searchProChars = cleanedSearchPro.split(""); // "아수" -> ["아", "수"]

		for (ProductListDto product : allList) {
			// 제품명, 펫타입, 카테고리, 타입을 모두 공백 제거 후 결합
			String cleanedProName = product.getPro_name().replaceAll("\\s+", "");

			// 검색어의 각 문자가 제품명에 포함되는지 체크
			boolean isMatch = true;
			for (String searchChar : searchProChars) {
				if (!cleanedProName.contains(searchChar)) {
					isMatch = false;
					break; // 하나라도 포함되지 않으면 해당 제품은 제외
				}
			}
			// 모든 문자들이 포함되면 결과 리스트에 추가
			if (isMatch) {
				resultList.add(product);
			}
		}
		// 결과 리스트 반환
		return resultList;
	}

}
