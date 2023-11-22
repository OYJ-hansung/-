package com.ssafy.attraction.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.attraction.model.AttractionSearchDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.mapper.AttractionMapper;


@Service
public class AttractionServiceImpl implements AttractionService {

	private AttractionMapper attractionMapper;

	public AttractionServiceImpl(AttractionMapper aattractionMapper) {
		super();
		this.attractionMapper = aattractionMapper;
	}

	@Override
	public List<AttractionInfoDto> attractionList(AttractionSearchDto attractionSearchDto) throws Exception {
		return attractionMapper.attractionList(attractionSearchDto);
	}

	@Override
	public List<AttractionInfoDto> attractionSearch(String word) throws Exception {
		return attractionMapper.attractionSearch(word);
	}

	@Override
	public List<GugunDto> getGugunList(int sidoCode) throws Exception {
		return attractionMapper.getGugunList(sidoCode);
	}
}
