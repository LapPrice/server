package com.lapprice.lapprice.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class GetLaptopListExceptLaptopNameResponse {
	private final List<GetLaptopExceptLaptopNameResponse> laptopList;

	public GetLaptopListExceptLaptopNameResponse(List<GetLaptopExceptLaptopNameResponse> laptopList) {
		this.laptopList = laptopList;
	}
}
