package com.orc.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author orcki
 */
@Controller
public class DemoController {
    private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "Hello world!";
    }

    @RequestMapping({"/login", "/"})
    public String login() {
        LOG.info("start...");
        return "login";
    }

}
