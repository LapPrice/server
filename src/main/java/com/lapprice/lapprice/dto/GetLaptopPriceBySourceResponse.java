package com.lapprice.lapprice.dto;

import java.util.List;
import lombok.Builder;

@Builder
public class GetLaptopPriceBySourceResponse {
	String source;
	List<Integer> prices;

}
