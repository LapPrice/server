	package com.lapprice.lapprice.dto;

	import com.lapprice.lapprice.LapTop;

	public record GetLaptopExceptLaptopNameResponse(String lapTopName,
													String brand,
													String cpu,
													Integer ssd,
													Integer ram,
													Integer price,
													Integer inch){
		public static GetLaptopExceptLaptopNameResponse toDTO(LapTop lapTop, Integer representativePrice) {
			return new GetLaptopExceptLaptopNameResponse(
				lapTop.getLapTopName(),
				lapTop.getBrand(),
				lapTop.getCpu(),
				lapTop.getSsd(),
				lapTop.getRam(),
				representativePrice,
				lapTop.getInch()
				);
		}
	}
