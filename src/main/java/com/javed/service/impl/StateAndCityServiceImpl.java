package com.javed.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javed.entity.City;
import com.javed.entity.Location;
import com.javed.entity.State;
import com.javed.repo.CountryRepository;
import com.javed.repo.LocationRepo;
import com.javed.repo.StateRepository;
import com.javed.service.StateAndCityService;

@Service
public class StateAndCityServiceImpl implements StateAndCityService{
	
	
	@Autowired
	private CountryRepository ctrRepo;
	
	@Autowired
	private StateRepository staRepo;
	
	@Autowired
	private LocationRepo locRepo;

	@Override
	public Map<Integer, String> getAllCountries() {
		Map<Integer,String> map=new LinkedHashMap<Integer,String>();
		List<State> countriesList=ctrRepo.findAll();
		countriesList.forEach(Country->{
			map.put(Country.getCountryId(),Country.getCountryName());
		}
		);
		return map;
	}


	@Override
	public Map<Integer,String> getStatesByCountryId(Integer countryId) {
		Map<Integer,String> statesMap=new LinkedHashMap<>();
		List<City> states=staRepo.findAllByCountryId(countryId);
		states.forEach(state->{
			statesMap.put(state.getStateId(),state.getStateName());
		});
		return statesMap;
	
	}
	
	@Override
	public Integer saveLocation(Location location) {
		return locRepo.save(location).getId();
	}
	
}
