package com.jt.controller;

import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * https://www.cnblogs.com/lvbinbin2yujie/p/10459303.html
 *
 * @InitBinder
 * 该注解被解析的时机，是该匹配Controller的请求执行映射的方法之前;
 * 同时 @InitBinder标注的方法执行是多次的，一次请求来就执行一次。
 *
 *  当某个Controller上的第一次请求由SpringMvc前端控制器匹配到该Controller之后，
 *  根据Controller的 class 类型 查找 所有方法上标注了@InitBinder的方法，
 *  并且存入RequestMappingHandlerAdapter的 initBinderCache，
 *  下次一请求执行对应业务方法之前时,可以走initBinderCache缓存,
 *  而不用再去解析@InitBinder; 所以 initBinder是controller级别的，
 *  一个controller实例中的所有@initBinder 只对该controller有效；
 *
 *  @InitBinder唯一的一个属性value,作用是限制对哪些 @RequestMapping 方法起作用,
 *  具体筛选条件就是通过@RequestMapping方法入参来筛选，
 *  默认不写就代表对所有@RequestMapping的方法起作用;
 *
 *
 */

@RestController
public class ParamController {


    @InitBinder("date")
    public void dateFormat(WebDataBinder webDataBinder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-DD-YYYY");
        CustomDateEditor customDateEditor = new CustomDateEditor(dateFormat, false);
        webDataBinder.registerCustomEditor(Date.class, customDateEditor);
    }

    @InitBinder
    public void addRre(WebDataBinder webDataBinder){
        webDataBinder.setFieldDefaultPrefix("myPre.");
    }

    @RequestMapping("/getDates")
    public String userBuyItemInDate(@RequestBody String parameter){
        System.out.println(parameter);
        Map map = ObjectMapperUtil.toObj(parameter, Map.class);
        String date = (String)map.get("date1");
        System.out.println(date);
        return "";
    }


    @RequestMapping("/getDate")
    public String getDate(String date){
        System.out.println(date);
        return "";
    }
}
