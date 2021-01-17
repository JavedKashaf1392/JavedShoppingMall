package com.javed.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javed.entity.City;

public interface StateRepository extends JpaRepository<City,Integer>{

	List<City> findAllByCountryId(Integer countryId);

}
