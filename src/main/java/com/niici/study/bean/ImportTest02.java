package com.niici.study.bean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

/**
 * @Import 导入的第二种方式：实现 ImportSelector，并重写selectImports方法
 * 将MyClass注册到容器中
 * 1、返回值： 就是我们实际上要导入到容器中的组件全类名【重点】
 * 2、参数： AnnotationMetadata表示当前被@Import注解给标注的所有注解信息【不是重点】
 */
public class ImportTest02 implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.niici.study.bean.JSR303Check"};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return null;
    }
}
