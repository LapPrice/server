package com.lapprice.lapprice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LapTop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String source;
	private String lapTopName;
	private String cpuName;
	private String sourceURL;
	private Integer disk;
	private Integer ram;
	private Integer price;
	private Integer inch;

}
