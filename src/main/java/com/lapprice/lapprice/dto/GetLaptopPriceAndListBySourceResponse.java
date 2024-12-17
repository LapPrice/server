package com.lapprice.lapprice.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class GetLaptopPriceAndListBySourceResponse {
	private final Integer representativePrice;
	private final List<GetLaptopBySourceResponse> laptopList;

	public GetLaptopPriceAndListBySourceResponse(Integer price, List<GetLaptopBySourceResponse> laptopList) {
		this.representativePrice = price;
		this.laptopList = laptopList;
	}
}
