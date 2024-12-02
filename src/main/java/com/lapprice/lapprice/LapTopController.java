package com.lapprice.lapprice;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lapprice.lapprice.dto.GetLaptopListExceptLaptopNameResponse;
import com.lapprice.lapprice.dto.GetSelectOptionResponse;
import com.lapprice.lapprice.dto.GetlaptopListExceptLaptopNameRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/laptop")
public class LapTopController {

	private final LapTopService lapTopService;

	@GetMapping(value = "/option")
	public ResponseEntity<GetSelectOptionResponse> getSelectOption() {
		GetSelectOptionResponse response = lapTopService.getSelectOptionResponse();

		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = "/laptop-name-list", produces = "application/json")
	public ResponseEntity<GetLaptopListExceptLaptopNameResponse> getLaptopListExceptLaptopNameResponseResponseEntity(
		@RequestParam String brand,
		@RequestParam String cpu,
		@RequestParam Integer ssd,
		@RequestParam Integer ram,
		@RequestParam Integer inch ) {
		GetlaptopListExceptLaptopNameRequest request = GetlaptopListExceptLaptopNameRequest.builder()
			.brand(brand)
			.cpu(cpu)
			.ssd(ssd)
			.ram(ram)
			.inch(inch)
			.build();
		System.out.println(brand + cpu + ssd + ram + inch);
		GetLaptopListExceptLaptopNameResponse response = lapTopService.getLaptopListExceptLaptopName(request);

		return ResponseEntity.ok().body(response);
	}
}
