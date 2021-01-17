package com.javed.service;

import java.util.Map;

import com.javed.entity.Location;

public interface StateAndCityService {
	
	public Map<Integer,String> getAllCountries();
	Map<Integer,String> getStatesByCountryId(Integer countryId);
	
	public Integer saveLocation(Location location);

}
