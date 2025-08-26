package org.campuswall.springbootcampuslovewall.controller;

import lombok.extern.slf4j.Slf4j;
import org.campuswall.springbootcampuslovewall.common.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Web控制器类
 * 处理Web相关的请求，提供基本的Web接口
 */
@Slf4j
@RestController
@RequestMapping("/web")
public class WebController {
    /**
     * 返回hello消息
     * @return 包含hello消息的响应结果
     */
    @GetMapping("/hello")
    public R<Object> hello() {
        return R.ok().message("hello");
    }

}
