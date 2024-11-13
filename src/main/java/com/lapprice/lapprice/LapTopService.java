package com.lapprice.lapprice;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LapTopService {
	private final LapTopRepository lapTopRepository;

}
