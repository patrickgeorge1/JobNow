package com.app.API.requirementType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementTypeRepository extends JpaRepository<RequirementType, Long> {
}
