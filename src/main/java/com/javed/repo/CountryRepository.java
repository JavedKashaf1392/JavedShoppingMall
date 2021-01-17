package com.javed.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javed.entity.State;

public interface CountryRepository extends JpaRepository<State,Integer>{

}
