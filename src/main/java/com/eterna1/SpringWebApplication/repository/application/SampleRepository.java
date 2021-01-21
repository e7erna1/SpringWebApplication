package com.eterna1.SpringWebApplication.repository.application;

import com.eterna1.SpringWebApplication.domain.entity.SampleData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends CrudRepository<SampleData, Long> {

}
