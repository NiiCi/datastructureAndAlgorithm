package com.niici.study.controller;

import com.niici.study.config.StudyProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Email;

@RestController
@Validated
@Slf4j
public class StudyController {
    @Resource
    StudyProperties studyProperties;

    /**
     * JSR303校验
     * @param email
     * @return
     */
    @GetMapping("/checkEmail/{email}")
    public String testEmail(@Email(message = "邮箱格式错误") @PathVariable String email) {
        return email;
    }

    /**
     * 自定义属性绑定验证
     * @return
     */
    @GetMapping("/configuration")
    public StudyProperties testConfigurationProperties() {
        return studyProperties;
    }
}
