package com.example.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "users") // users 인덱스의 Document임을 명시
@Getter
@Setter
@AllArgsConstructor
public class UserDocument {
    @Id
    private String id; // Elasticsearch에서는 Document ID를 문자열(String)로 다룸

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Long)
    private Long age;

    @Field(type = FieldType.Boolean)
    private Boolean isActive;
}
