package com.lapprice.lapprice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lapprice.lapprice.dto.SelectOptionResponse;

import com.lapprice.lapprice.dto.GetLaptopListExceptLaptopNameResponse;
import com.lapprice.lapprice.dto.GetSelectOptionResponse;
import com.lapprice.lapprice.dto.GetlaptopListExceptLaptopNameRequest;
import com.lapprice.lapprice.dto.GetLaptopExceptLaptopNameResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class LapTopService {
	private final LapTopRepository lapTopRepository;

	public GetSelectOptionResponse getSelectOptionResponse() {
		List<String> cpu = lapTopRepository.findDistinctCpus();
		List<Integer> ram = lapTopRepository.findDistinctRAMsSorted();
		List<Integer> ssd = lapTopRepository.findDistinctSSdsSorted();
		List<String> brand = lapTopRepository.findDistinctBrands();
		List<Integer> inch = lapTopRepository.findDistinctInchesSorted();
		Map<String, List<String>> responseCpu = new HashMap<>();

		List<String> intelCore = new ArrayList<>();
		List<String> intelUltraCore = new ArrayList<>();
		List<String> intelPentium = new ArrayList<>();
		List<String> intelCeleron = new ArrayList<>();
		List<String> m1 = new ArrayList<>();
		List<String> m2 = new ArrayList<>();
		List<String> m3 = new ArrayList<>();
		List<String> ryzen = new ArrayList<>();
		List<String> etc = new ArrayList<>();

		for (String cpuName : cpu) {
			if (cpuName.startsWith("i3") || cpuName.startsWith("i5") ||
				cpuName.startsWith("i7") || cpuName.startsWith("i9")) {
				intelCore.add(cpuName);
			} else if (cpuName.startsWith("Ultra")) {
				intelUltraCore.add(cpuName);
			} else if (cpuName.contains("Pentium")) {
				intelPentium.add(cpuName);
			} else if (cpuName.contains("Celeron")) {
				intelCeleron.add(cpuName);
			} else if (cpuName.startsWith("M1")) {
				m1.add(cpuName);
			} else if (cpuName.startsWith("M2")) {
				m2.add(cpuName);
			} else if (cpuName.startsWith("M3")) {
				m3.add(cpuName);
			} else if (cpuName.startsWith("Ryzen")) {
				ryzen.add(cpuName);
			} else {
				etc.add(cpuName);
			}
		}

		Collections.sort(intelCore);
		Collections.sort(m1);
		Collections.sort(m2);
		Collections.sort(m3);

		if (!intelCore.isEmpty()) responseCpu.put("Intel Core", intelCore);
		if (!intelUltraCore.isEmpty()) responseCpu.put("Intel Ultra Core", intelUltraCore);
		if (!intelPentium.isEmpty()) responseCpu.put("Intel Pentium", intelPentium);
		if (!intelCeleron.isEmpty()) responseCpu.put("Intel Celeron", intelCeleron);
		if (!m1.isEmpty()) responseCpu.put("Apple M1", m1);
		if (!m2.isEmpty()) responseCpu.put("Apple M2", m2);
		if (!m3.isEmpty()) responseCpu.put("Apple M3", m3);
		if (!ryzen.isEmpty()) responseCpu.put("Ryzen", ryzen);
		if (!etc.isEmpty()) responseCpu.put("ETC", etc);

		return GetSelectOptionResponse.builder()
			.brand(brand)
			.cpu(responseCpu)
			.ram(ram)
			.ssd(ssd)
			.inch(inch)
			.build();
	}

	public GetLaptopListExceptLaptopNameResponse getLaptopListExceptLaptopName(
		GetlaptopListExceptLaptopNameRequest request) {
		List<LapTop> allLaptopList = lapTopRepository.findAllByBrandAndCpuAndSsdAndRamAndInch(request.brand(),request.cpu(),request.ssd(),request.ram(),request.inch());

		Map<String, List<LapTop>> groupedLaptops = allLaptopList.stream()
			.collect(Collectors.groupingBy(LapTop::getLapTopName));

		List <GetLaptopExceptLaptopNameResponse> lapTopList = new ArrayList<>();

		for (Map.Entry<String, List<LapTop>> laptopList : groupedLaptops.entrySet()) {
			String laptopName = laptopList.getKey();
			List<LapTop> lapTops = laptopList.getValue();

			List<Integer> prices = lapTops.stream()
				.map(LapTop::getPrice)
				.sorted()
				.collect(Collectors.toList());

			int representativePrice = calculateMedian(prices);

			GetLaptopExceptLaptopNameResponse response = GetLaptopExceptLaptopNameResponse.toDTO(lapTops.get(0),representativePrice);
			lapTopList.add(response);
		}

		return new GetLaptopListExceptLaptopNameResponse(lapTopList);
	}

	private int calculateMedian(List<Integer> prices) {
		int size = prices.size();
		return prices.get(size / 2);
	}

	public void saveLapTopsFromJson(String filePath) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		// JSON 파일 읽기
		List<LapTop> laptops = objectMapper.readValue(
			new File(filePath),
			new TypeReference<List<LapTop>>() {
			}
		);

		// DB에 저장
		lapTopRepository.saveAll(laptops);
	}
}
