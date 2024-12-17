package com.lapprice.lapprice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lapprice.lapprice.dto.GetLaptopBySourceRequest;
import com.lapprice.lapprice.dto.GetLaptopListBySourceResponse;
import com.lapprice.lapprice.dto.GetLaptopListExceptLaptopNameResponse;
import com.lapprice.lapprice.dto.GetOptionBySelectRequest;
import com.lapprice.lapprice.dto.GetSelectOptionResponse;
import com.lapprice.lapprice.dto.GetlaptopListExceptLaptopNameRequest;
import com.lapprice.lapprice.repository.LapTopRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/laptop")
public class LapTopController {

	private final LapTopService lapTopService;
	private final LapTopRepository lapTopRepository;

	@PostMapping(value = "/option")
	public ResponseEntity<GetSelectOptionResponse> getSelectOption(@RequestBody GetOptionBySelectRequest request) {
		GetSelectOptionResponse response = lapTopService.getOptionBySelect(request);

		return ResponseEntity.ok().body(response);
	}

	@PostMapping(value = "/laptop-name-list")
	public ResponseEntity<GetLaptopListExceptLaptopNameResponse> getLaptopListExceptLaptopNameResponseResponseEntity(
		@RequestBody GetlaptopListExceptLaptopNameRequest request) {
		GetLaptopListExceptLaptopNameResponse response = lapTopService.getLaptopListExceptLaptopName(request);

		return ResponseEntity.ok().body(response);
	}

	@PostMapping(value = "/laptops")
	public ResponseEntity<String> uploadLaptops(@RequestBody List<LapTop> laptops) {
		// 데이터베이스에서 이미 존재하는 URL 목록 가져오기
		List<String> distinctUrls = lapTopRepository.findDistinctUrls();

		// 중복되지 않은 JSON 데이터를 필터링
		Set<String> seenUrls = new HashSet<>();
		List<LapTop> filtered = laptops.stream()
			// DB에 없는 값만 필터링
			.filter(e -> !distinctUrls.contains(e.getSourceURL()))
			// 리스트 내 중복 제거 (sourceURL 기준)
			.filter(e -> seenUrls.add(e.getSourceURL()))
			.toList();

		// 필터링된 데이터 저장
		lapTopRepository.saveAll(filtered);

		return ResponseEntity.ok("Laptops data saved successfully!");
	}

	@PostMapping(value = "/marketPrice")
	public ResponseEntity<GetLaptopListBySourceResponse> getLaptopListBySourceResponseResponseEntity(
		@RequestBody GetLaptopBySourceRequest request) {

		return ResponseEntity.ok().body(lapTopService.getLaptopListBySource(request));
	}
}
