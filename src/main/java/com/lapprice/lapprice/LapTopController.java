package com.lapprice.lapprice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/laptop")
public class LapTopController {

	private final LapTopService lapTopService;
}
