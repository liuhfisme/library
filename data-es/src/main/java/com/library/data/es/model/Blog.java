package com.library.data.es.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.sql.Timestamp;

/**
 * .
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2020-04-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "test", type = "blog")
public class Blog {
    private Integer id;
    private String title;
    private String content;
    private Integer commentSize;
    private Timestamp createTime;
    private Integer readSize;
    private Integer voteSize;
}
