package com.niici.study.bean;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Import 导入的第三种方式：实现 ImportBeanDefinitionRegistrar，并重写registerBeanDefinitions方法
 * 将ImportTest02注册到容器中
 * 1、importingClassMetadata：表示当前被@Import注解给标注的所有注解信息
 * 2、registry：spring自带的bean定义注册器，将bean的定义注册到spring容器中，采用map存储 key为beanName，value为been的定义
 */
public class ImportTest03 implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(JSR303Check.class);
        registry.registerBeanDefinition("importTest03", rootBeanDefinition);
    }
}
