package com.lapprice.lapprice.dto;

public record GetOptionBySelectRequest(String cpuName,
									   String brand,
									   Integer ram,
									   Integer ssd,
									   Integer inch) {

}
