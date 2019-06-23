package com.oio.practice.learn.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: LiQiongchao
 * @Date: 2019/6/23 11:00
 */
@Controller
@RequestMapping("/socket")
public class WebSocketController {

    @GetMapping("/connect/{uid}")
    public ModelAndView socket(@PathVariable String uid) {
        ModelAndView mv = new ModelAndView("/socket");
        mv.addObject("uid", uid);
        return mv;
    }

    @ResponseBody
    @GetMapping("/send/{uid}")
    public String sendMessage(@PathVariable String uid, @RequestParam String message) {
        WebSocketService.sendInfo(message, uid);
        return "{\n" +
                "  \"code\": 0,\n" +
                "  \"message\": \"success\"\n" +
                "}";
    }

    @ResponseBody
    @GetMapping("/users")
    public String user() {
        return "{\n" +
                "  \"code\": 0,\n" +
                "  \"message\": \"success\",\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"name\": \"小明\",\n" +
                "      \"age\": 18,\n" +
                "      \"favorite\": \"游戏\"\n" +
                "    }, {\n" +
                "      \"name\": \"小红\",\n" +
                "      \"age\": 20,\n" +
                "      \"favorite\": \"看书，音乐\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

}
