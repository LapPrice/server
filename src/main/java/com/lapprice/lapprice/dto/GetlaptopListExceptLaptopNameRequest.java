package com.lapprice.lapprice.dto;

import lombok.Builder;

@Builder
public record GetlaptopListExceptLaptopNameRequest(String brand,
												   String cpu,
												   Integer ssd,
												   Integer ram,
												   Integer inch){

}
