package cn.song.controller;


import cn.song.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/param")
public class ParamController {

    @RequestMapping(value = "/index")
    public String testParamIndex(){
        return "param";
    }


    /**
     * @Description: 处理表单提交
     * @author: syq
     * @Date: 2020/3/4
     * @param:
     * @return:
     * @throws：
     */
    @RequestMapping(value = "/submit")
    public void testSubmit(Account account){
        System.out.println(account);

    }




}
