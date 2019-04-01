package com.hmx.media;

import com.hmx.user.entity.po.Vedio;
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