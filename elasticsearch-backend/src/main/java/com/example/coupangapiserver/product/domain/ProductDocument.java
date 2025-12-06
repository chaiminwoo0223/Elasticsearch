package com.example.coupangapiserver.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = "products")
@Setting(settingPath = "/elasticsearch/product-settings.json")
@Getter
@Setter
@AllArgsConstructor
public class ProductDocument {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "products_name_analyzer"),
    otherFields = {
            @InnerField(suffix = "auto_complete", type = FieldType.Search_As_You_Type, analyzer = "nori")
    })
    private String name;

    @Field(type = FieldType.Text, analyzer = "products_description_analyzer")
    private String description;

    @Field(type = FieldType.Integer)
    private Integer price;

    @Field(type = FieldType.Double)
    private Double rating;

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "products_category_analyzer"),
            otherFields = {
                    @InnerField(suffix = "raw", type = FieldType.Keyword)
    })
    private String category;
}
