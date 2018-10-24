package com.cerner.sample.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.cerner.sample.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.cerner.sample.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.PersistentToken.class.getName(), jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.Region.class.getName(), jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.Country.class.getName(), jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.Location.class.getName(), jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.Department.class.getName(), jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.Department.class.getName() + ".employees", jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.Task.class.getName(), jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.Task.class.getName() + ".jobs", jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.Employee.class.getName(), jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.Employee.class.getName() + ".jobs", jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.Job.class.getName(), jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.Job.class.getName() + ".tasks", jcacheConfiguration);
            cm.createCache(com.cerner.sample.domain.JobHistory.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
