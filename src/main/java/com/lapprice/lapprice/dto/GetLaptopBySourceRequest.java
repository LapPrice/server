package com.lapprice.lapprice.dto;

import lombok.Builder;

@Builder
public record GetLaptopBySourceRequest(String laptopName,
									   String brand,
									   String cpu,
									   Integer ram,
									   Integer inch,
									   Integer ssd,
									   Integer price
									 ){

}
