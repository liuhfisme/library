package com.library.data.es;

import com.library.data.es.model.Blog;
import com.library.data.es.service.BlogService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataEsApplication.class)
@EnableAutoConfiguration
class DataEsApplicationTests {
	@Autowired
	BlogService blogService;

	@Test
	void save() {
		for (int i = 1; i <= 10; i++) {
			Blog blog = new Blog(i, "测试标题"+i, "中国红，美丽大江南北！"+i, 10+i,
					Timestamp.from(Instant.now()), 20+i, 30+i);
			blogService.save(blog);
		}
	}

	@Test
	void queryByTitle() {
		List<Blog> blogs = blogService.queryByTitle("测试标题1");
		System.out.println(blogs);
	}

	@Test
	void queryByTitleContaining() {
		List<Blog> blogs = blogService.queryByTitleContaining("测试");
		System.out.println(blogs.size());
	}

	@Test
	void queryByReadSizeBetween() {
		List<Blog> blogs = blogService.queryByReadSizeBetween(22, 25);
		System.out.println(blogs);
	}

	@Test
	void queryByTitleStartingWith() {
		List<Blog> blogs = blogService.queryByTitleStartingWith("测");
		System.out.println(blogs);
	}

	@Test
	void rhl() {
		RestHighLevelClient restClient = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.7.150", 9200, "http")));
		IndexRequest indexRequest = new IndexRequest("test", "blog");
		Map map = new HashMap();
		map.put("x", "测试标题1测试标题2测试标题3测试标题4");
		map.put("y", "测试标题1测试标题2测试标题3测试标题4yyy");
		indexRequest.source(map, XContentType.JSON);
		RequestOptions.Builder requestOptions = RequestOptions.DEFAULT.toBuilder();
		try {
			restClient.index(indexRequest, requestOptions.build());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void rhlQuery() {
		RestHighLevelClient restClient = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.7.150", 9200, "http")));
		SearchRequest searchRequest = new SearchRequest("test");
		searchRequest.types("blog");

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.size(3);
		RequestOptions.Builder requestOptions = RequestOptions.DEFAULT.toBuilder();
		try {
			SearchResponse search = restClient.search(searchRequest, requestOptions.build());
			System.out.println(search);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
