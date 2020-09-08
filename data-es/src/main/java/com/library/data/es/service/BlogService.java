package com.library.data.es.service;

import com.library.data.es.model.Blog;
import com.library.data.es.repository.BlogRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * .
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2020-04-16
 */
@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    /**
     * 保存
     * @param blog
     */
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    /**
    * 根据 title 来查找Blog
    * @param title String
    * @return      Blog
    * @exception
    */
    public List<Blog> queryByTitle(String title){
        return blogRepository.findByTitle(title);
    }

    /**

     * 根据 title 来查找Blog
     * @param title
     * @return      Blog
     * @exception
     */
    public List<Blog> queryByTitleContaining(String title){
        return blogRepository.findByTitleContaining(title);
    }

    /**

     * 根据范围 查找 blog
     * @param from
     * @param to
     * @return      Blog
     * @exception
     */
    public List<Blog> queryByReadSizeBetween(Integer from,Integer to){
        return blogRepository.findByReadSizeBetween(from,to);
    }

    /**

     * 根据id 查找
     * @param id
     * @return      java.util.Optional<Blog>
     * @exception
     */
    public Optional<Blog> queryById(Integer id){
        return blogRepository.findById(id);
    }

    /**

     * 针对readSize字段 按降序进行查找
     * @param
     * @return      java.lang.Iterable<Blog>
     * @exception
     */
    public Iterable<Blog> getAll(){
        Sort sort = Sort.by(Sort.Direction.DESC,"readSize");
        return blogRepository.findAll(sort);
    }

    /**

     * 按时间排序查找 从最新的文章开始往下排
     * @param
     * @return      java.lang.Iterable<Blog>
     * @exception
     */
    public Iterable<Blog> getAllBycreateTime(){
        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
        return blogRepository.findAll(sort);
    }


    /**

     * 使用 QueryBuilder进行匹配查询
     * @param
     * @return      java.lang.Iterable<Blog>
     * @exception
     */
    public Iterable<Blog> searchQuery(){
        QueryBuilder queryBuilder = QueryBuilders.termQuery("title", "Web");
        QueryBuilder queryBuilder1 = QueryBuilders.fuzzyQuery("title", "人");
        return blogRepository.search(queryBuilder1);
    }


    /**

     * 以***为开始来进行匹配标题
     * @param title
     * @return      Blog
     * @exception
     */
    public List<Blog> queryByTitleStartingWith(String title){
        return blogRepository.findByTitleStartingWith(title);
    }

    /**

     * 通过id 删除实体
     * @param
     * @return      void
     * @exception
     */
    public void delete(Integer id){
        blogRepository.deleteById(id);
    }
}
