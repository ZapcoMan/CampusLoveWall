package org.example.springbootcampuslovewall.controller;

import org.example.springbootcampuslovewall.common.result.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("")
public class WebController {
    @GetMapping("/sayHello")
    public R<String> sayHello() {
        return R.ok("hello");
    }

}
