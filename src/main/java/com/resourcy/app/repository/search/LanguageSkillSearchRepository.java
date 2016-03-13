package com.resourcy.app.repository.search;

import com.resourcy.app.domain.LanguageSkill;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the LanguageSkill entity.
 */
public interface LanguageSkillSearchRepository extends ElasticsearchRepository<LanguageSkill, Long> {
}
