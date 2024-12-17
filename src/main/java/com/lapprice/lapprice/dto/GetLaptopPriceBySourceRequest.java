package com.lapprice.lapprice.dto;

import lombok.Builder;

@Builder
public record GetLaptopPriceBySourceRequest(String laptopName,
											String brand,
											String cpu,
											Integer ram,
											Integer inch,
											Integer ssd,
											Integer price
									 ){

}
