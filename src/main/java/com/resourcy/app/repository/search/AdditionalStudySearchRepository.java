package com.resourcy.app.repository.search;

import com.resourcy.app.domain.AdditionalStudy;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the AdditionalStudy entity.
 */
public interface AdditionalStudySearchRepository extends ElasticsearchRepository<AdditionalStudy, Long> {
}
