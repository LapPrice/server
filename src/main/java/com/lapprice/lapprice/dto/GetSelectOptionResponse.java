package com.lapprice.lapprice.dto;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetSelectOptionResponse {
	private List<String> brand;
	private List<Integer> ssd;
	private List<Integer> ram;
	private List<Integer> inch;

	private Map<String, List<String>> cpu;
}
