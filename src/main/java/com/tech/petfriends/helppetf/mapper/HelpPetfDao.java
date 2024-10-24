package com.tech.petfriends.helppetf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.tech.petfriends.helppetf.dto.HelpPetfDto;

@Mapper
public interface HelpPetfDao {
	
	public HelpPetfDto petteacherDetail(String hpt_seq);

	public ArrayList<HelpPetfDto> petteacherList(String petType, String category);

	public String findUserAddr(String userId);

	public void upViews(String hpt_seq);
}
