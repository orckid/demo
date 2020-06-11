package com.orc.demo.controller;

import com.orc.demo.util.io.IoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping("/login")
    public String demo(Model model) {
        LOG.info("start...");
        model.addAttribute("hello", "hello thymeleaf");
        return "demo";
    }

    @RequestMapping("/read")
    @ResponseBody
    public String read() {
        return IoUtils.readFile("text.txt");
    }

    @RequestMapping("/write")
    @ResponseBody
    public String write() {
        IoUtils.writeFile("text.txt");
        return "write success!";
    }
}
