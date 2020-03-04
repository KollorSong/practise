package cn.song.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/param")
public class ParamController {

    @RequestMapping(value = "/index")
    public String testParamIndex(){
        return "param";
    }
}
