package com.library.data.es.repository;

import com.library.data.es.model.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 测试DAO层.
 *
 * @author liufeifei02@beyondsoft.com
 * @version 1.0
 * @date 2020-04-16
 */
@Repository
public interface BlogRepository extends ElasticsearchRepository<Blog, Integer> {

    List<Blog> findByTitle(String title);

    List<Blog> findByTitleContaining(String title);

    List<Blog> findByReadSizeBetween(Integer from,Integer to);


    Optional<Blog> findById(Integer id);

    List<Blog> findByTitleStartingWith(String title);
}
