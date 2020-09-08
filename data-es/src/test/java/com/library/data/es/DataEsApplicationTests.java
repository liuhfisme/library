package com.library.data.es;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.library.data.es.model.Blog;
import com.library.data.es.service.BlogService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

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

}
