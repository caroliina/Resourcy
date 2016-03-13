package com.resourcy.app.repository;

import com.resourcy.app.domain.WorkAssignment;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the WorkAssignment entity.
 */
public interface WorkAssignmentRepository extends JpaRepository<WorkAssignment,Long> {

}
