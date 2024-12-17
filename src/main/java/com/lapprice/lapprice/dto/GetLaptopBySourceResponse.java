package com.lapprice.lapprice.dto;

import com.lapprice.lapprice.LapTop;

public record GetLaptopBySourceResponse(String sourceURL,
										Integer price){
	public static GetLaptopBySourceResponse toDTO(LapTop lapTop) {
		return new GetLaptopBySourceResponse(
			lapTop.getSourceURL(),
			lapTop.getPrice()
		);
	}
}
