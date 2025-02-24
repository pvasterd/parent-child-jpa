package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * JPA interface for the ResourceEntity.
 */
@Repository
public interface ResourceEntity3Repository extends JpaRepository<ResourceEntity3, String> {

}
