package com.lapprice.lapprice;

import java.util.List;

import com.lapprice.lapprice.dto.GetOptionBySelectRequest;
import com.lapprice.lapprice.dto.GetSelectOptionResponse;

public interface CustomLapTopRepository {
	GetSelectOptionResponse getLapTopByCustom(GetOptionBySelectRequest request);
	List<LapTop> findLaptopsByDynamicSpecs(String brand, String cpu, Integer ssd, Integer ram, Integer inch);
}
