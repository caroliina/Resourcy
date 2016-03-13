package com.resourcy.app.repository.search;

import com.resourcy.app.domain.WorkAssignment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the WorkAssignment entity.
 */
public interface WorkAssignmentSearchRepository extends ElasticsearchRepository<WorkAssignment, Long> {
}
