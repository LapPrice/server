package com.lapprice.lapprice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lapprice.lapprice.dto.SelectOptionResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/laptop")
public class LapTopController {

	private final LapTopService lapTopService;

	@GetMapping
	public ResponseEntity<SelectOptionResponse> getSelectOption() {
		SelectOptionResponse response = lapTopService.getSelectOptionResponse();

		return ResponseEntity.ok().body(response);
	}
}
