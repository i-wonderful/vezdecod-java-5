package com.byby.cache;

import com.byby.dto.model.QuestionDto;
import com.byby.repository.QuestionRepository;
import com.byby.repository.entity.Question;
import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheName;
import io.quarkus.cache.CacheResult;
import org.jboss.logmanager.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.byby.dto.mapper.QuestionMapper.toDto;

@Singleton
public class CacheManager {
    private static final Logger LOG = Logger.getLogger(CacheManager.class.getName());

    @Inject
    @CacheName("questions-cache")
    Cache cache;

    @Inject
    QuestionRepository questionRepository;

    /**
     * Put question to cache
     *
     * @param key
     * @param value
     * @return
     */
    @CacheResult(cacheName = "questions-cache")
    public QuestionDto put(@CacheKey Long key, QuestionDto value) {
        LOG.info(String.format(">>> Put to cache, key=%d, value=%s ", key, value));
        return value;
    }

    /**
     * Get question from cache,
     * or if not found get from db and put question into cache.
     *
     * @param questionId
     * @return
     */
    public QuestionDto getFromCacheOrDb(Long questionId) {
        LOG.info(String.format(">>> Get from cache, key=%d", questionId));
        return cache.get(questionId, k -> {
                    LOG.info(String.format(">>> Get from Db, questionId=%d", questionId));
                    Question question = questionRepository.findByIdOptional(questionId)
                            .orElseThrow(() -> new RuntimeException(String.format("Not found question, id=%d", questionId)));
                    return toDto(question);
                })
                .await().indefinitely();
    }

}
