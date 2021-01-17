package com.javed.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javed.entity.Location;

public interface LocationRepo extends JpaRepository<Location,Integer>{

}
