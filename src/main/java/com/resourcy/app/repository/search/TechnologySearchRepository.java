package com.resourcy.app.repository.search;

import com.resourcy.app.domain.Technology;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Technology entity.
 */
public interface TechnologySearchRepository extends ElasticsearchRepository<Technology, Long> {
}
