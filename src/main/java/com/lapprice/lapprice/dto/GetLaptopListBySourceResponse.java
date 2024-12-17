package com.lapprice.lapprice.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class GetLaptopListBySourceResponse {

	private final Map<String,GetLaptopPriceAndListBySourceResponse> danawa = new HashMap<>();
	private final Map<String,GetLaptopPriceAndListBySourceResponse> joonggonara = new HashMap<>();
	private final Map<String,GetLaptopPriceAndListBySourceResponse> bungaejangto = new HashMap<>();

	public GetLaptopListBySourceResponse(GetLaptopPriceAndListBySourceResponse danawa,
										 GetLaptopPriceAndListBySourceResponse joonggonara,
										 GetLaptopPriceAndListBySourceResponse bungaejangto) {
		this.danawa.put("다나와", danawa);
		this.joonggonara.put("중고나라", joonggonara);
		this.bungaejangto.put("번개장터", bungaejangto);
	}
}
