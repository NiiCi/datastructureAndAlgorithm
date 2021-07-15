package com.niici.study.config;

import com.niici.study.bean.ImportTest01;
import com.niici.study.bean.ImportTest02;
import com.niici.study.bean.ImportTest03;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * @Configuration 默认proxyBeanMethods为true，使用代理生成对象，在json数据转换时，
 * 会抛出 No serializer found for class org.springframework.context.expression.StandardBeanExpressionResolver 异常
 * 建议使用 @Component 或者 @Configuration(proxyBeanMethods = false)
 */
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "niici")
@Import({ImportTest01.class, ImportTest02.class, ImportTest03.class})
public class StudyProperties {
    private String name;
    private Integer age;
    private Integer sex;
    private String job;
}
