package com.hmx.media;

import com.hmx.user.entity.po.Garousel;
import com.hmx.user.entity.po.Vedio;
import com.hmx.utils.result.Result;
import freemarker.ext.beans.HashAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


/**
 * @Author: 肖映彤
 * @Description: 媒介管理
 * @Date: 0:37 2019-4-1
 */
@RestController
@RequestMapping("/media")
public class MediaController {

    @RequestMapping("/vedio/init")
    public ModelAndView vedioInit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/media/vedio/list");
        return modelAndView;
    }

    @RequestMapping("/mp3/init")
    public ModelAndView mp3Init() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/media/mp3/list");
        return modelAndView;
    }

    @RequestMapping("/pic/init")
    public ModelAndView picInit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/media/pic/list");
        return modelAndView;
    }

    @RequestMapping("/vedio/getList")
    public Map<String,Object> getVedioList(){
        Map<String,Object> map = new HashMap();
        Vedio vedio = new Vedio();
        vedio.setId(1);
        vedio.setCategoryContentId(1);
        vedio.setName("声腔研究");
        vedio.setSize(1024);
        vedio.setType("mp4");
        vedio.setUrl("");
        vedio.setCreateDate(new Date());
        List<Vedio> list =  new ArrayList<>();
        list.add(vedio);
        map.put("rows", list);
        map.put("total", list.size());
        return map;
    }

    @RequestMapping("/pic/getList")
    public Map<String,Object> getPicList(){
        Map<String,Object> map = new HashMap();
        Garousel garousel = new Garousel();
        garousel.setId(1);
        garousel.setUrl("http://47.107.234.198:8081/app/v1/images/15434866301414495.jpg");
        garousel.setContentId(1);
        garousel.setTitle("轮播图1");
        garousel.setCreateTime(new Date());
        List<Garousel> list =  new ArrayList<>();
        list.add(garousel);
        map.put("rows", list);
        map.put("total", list.size());
        return map;
    }

    @RequestMapping("/pic/update")
    public Result<Object> updatePicList(){
        Result<Object> result = new Result<>();
        result.setStatus(10000);
        result.setMsg("修改成功");
        return result;
    }

    @RequestMapping("/vedio/add")
    public ModelAndView vedioAdd(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/media/vedio/add");
        return modelAndView;
    }

    @RequestMapping("/vedio/show")
    public ModelAndView show(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/media/vedio/show");
        return modelAndView;
    }

    @RequestMapping("/mp3/show")
    public ModelAndView mp3Play(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/media/mp3/show");
        return modelAndView;
    }

}