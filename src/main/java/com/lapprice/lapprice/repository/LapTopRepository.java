package com.lapprice.lapprice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lapprice.lapprice.CustomLapTopRepository;
import com.lapprice.lapprice.LapTop;

@Repository
public interface LapTopRepository extends JpaRepository<LapTop, Long>, CustomLapTopRepository {
	@Query("SELECT DISTINCT brand FROM LapTop")
	List<String> findDistinctBrands();

	@Query("SELECT DISTINCT cpu FROM LapTop")
	List<String> findDistinctCpus();

	@Query("SELECT DISTINCT ram FROM LapTop ORDER BY ram ASC")
	List<Integer> findDistinctRAMsSorted();

	@Query("SELECT DISTINCT ssd FROM LapTop ORDER BY ssd ASC")
	List<Integer> findDistinctSSdsSorted();

	@Query("SELECT DISTINCT inch FROM LapTop ORDER BY inch ASC")
	List<Integer> findDistinctInchesSorted();

	List<LapTop> findAllByBrandAndCpuAndSsdAndRamAndInch(String brand,String cpu, Integer ssd, Integer ram, Integer inch);



	List<LapTop> findAllByBrandAndCpuAndSsdAndRamAndInchAndLapTopNameAndSourceOrderByPrice(String brand,String cpu, Integer ssd, Integer ram, Integer inch, String laptopName, String source);

}
