package com.resourcy.app.repository.search;

import com.resourcy.app.domain.GovernmentProject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the GovernmentProject entity.
 */
public interface GovernmentProjectSearchRepository extends ElasticsearchRepository<GovernmentProject, Long> {
}
