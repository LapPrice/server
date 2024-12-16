package com.lapprice.lapprice;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lapprice.lapprice.dto.GetOptionBySelectRequest;
import com.lapprice.lapprice.dto.GetSelectOptionResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomLapTopRepositoryImpl implements CustomLapTopRepository {
	private final EntityManager entityManager;

	public GetSelectOptionResponse getLapTopByCustom(GetOptionBySelectRequest request){
		StringBuilder jpqlBuilder = new StringBuilder();

		jpqlBuilder.append("select L from LapTop L");

		if(request.cpuName()==null
			&&request.inch()==null
			&&request.ram()==null
			&&request.brand()==null
			&&request.ssd()==null) return execQuery(jpqlBuilder.toString(), request);

		jpqlBuilder.append(" where ");
		Boolean exist= false;
		if(request.cpuName()!=null){
			jpqlBuilder.append("L.cpu = :cpuName");
			exist= true;
		}
		if(request.inch()!=null){
			if(exist) {
				jpqlBuilder.append(" and ");
			}
			jpqlBuilder.append("L.inch = :inch");
			exist= true;
		}
		if(request.ram()!=null){
			if(exist) {
				jpqlBuilder.append(" and ");
			}
			jpqlBuilder.append("L.ram = :ram");
			exist= true;
		}
		if(request.brand()!=null){
			if(exist) {
				jpqlBuilder.append(" and ");
			}
			jpqlBuilder.append("L.brand = :brand");
			exist= true;
		}
		if(request.ssd()!=null){
			if(exist) {
				jpqlBuilder.append(" and ");
			}
			jpqlBuilder.append("L.ssd = :ssd");
		}

		return execQuery(jpqlBuilder.toString(), request);
	}

	private GetSelectOptionResponse execQuery(String query, GetOptionBySelectRequest request) {
		TypedQuery<LapTop> typedQuery = entityManager.createQuery(query, LapTop.class);

		if (request.cpuName() != null) {
			typedQuery.setParameter("cpuName", request.cpuName());
		}
		if (request.inch() != null) {
			typedQuery.setParameter("inch", request.inch());
		}
		if (request.ram() != null) {
			typedQuery.setParameter("ram", request.ram());
		}
		if (request.brand() != null) {
			typedQuery.setParameter("brand", request.brand());
		}
		if (request.ssd() != null) {
			typedQuery.setParameter("ssd", request.ssd());
		}

		List<LapTop> resultList = typedQuery.getResultList();
		List<String> brands = resultList.stream().map(LapTop::getBrand).distinct().toList();
		List<String> cpus = resultList.stream().map(LapTop::getCpu).distinct().toList();
		List<Integer> rams = resultList.stream().map(LapTop::getRam).distinct().toList();
		List<Integer> ssds = resultList.stream().map(LapTop::getSsd).distinct().toList();
		List<Integer> inches = resultList.stream().map(LapTop::getInch).distinct().toList();

		return GetSelectOptionResponse.builder()
			.brand(brands)
			.cpu(LapTopService.getCpuMap(cpus))
			.ram(rams)
			.ssd(ssds)
			.inch(inches).build();
	}
}
