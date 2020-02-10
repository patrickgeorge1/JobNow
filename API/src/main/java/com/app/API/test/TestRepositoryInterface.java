package com.app.API.test;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepositoryInterface extends CrudRepository<Test, Long> {

}
