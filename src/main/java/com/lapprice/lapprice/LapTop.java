package com.lapprice.lapprice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lap_top")
public class LapTop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String brand;
	private String source;
	private String lapTopName;
	private String cpu;
	private String sourceURL;
	private Integer ssd;
	private Integer ram;
	private Integer price;
	private Integer inch;

}
