package com.lapprice.lapprice;

import com.lapprice.lapprice.dto.GetOptionBySelectRequest;
import com.lapprice.lapprice.dto.GetSelectOptionResponse;

public interface CustomLapTopRepository {
	GetSelectOptionResponse getLapTopByCustom(GetOptionBySelectRequest request);
}
