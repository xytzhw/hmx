package com.hmx.media;

import com.hmx.category.dto.HmxCategoryContentDto;
import com.hmx.category.dto.HmxCategoryDto;
import com.hmx.category.entity.HmxCategory;
import com.hmx.category.entity.HmxCategoryContent;
import com.hmx.category.service.HmxCategoryContentService;
import com.hmx.category.service.HmxCategoryService;
import com.hmx.movie.dto.HmxMovieDto;
import com.hmx.movie.entity.HmxMovie;
import com.hmx.movie.service.HmxMovieService;
import com.hmx.movie.service.asyncService.UploadMovieAsync;
import com.hmx.user.entity.po.Garousel;
import com.hmx.user.entity.po.Vedio;
import com.hmx.utils.result.PageBean;
import com.hmx.utils.result.Result;
import com.hmx.utils.upload.InitVodClients;
import com.hmx.utils.upload.UploadVideoDemo;
import freemarker.ext.beans.HashAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;


/**
 * @Author: 肖映彤
 * @Description: 媒介管理
 * @Date: 0:37 2019-4-1
 */
@RestController
@RequestMapping("/media")
public class MediaController {



    @Autowired
    private HmxMovieService hmxMovieService;

    @Autowired
    private HmxCategoryContentService hmxCategoryContentService;

    @Autowired
    private UploadMovieAsync uploadMovieAsync;

    @Autowired
    private InitVodClients initVodClients;

    @RequestMapping("/vedio/init")
    public ModelAndView vedioInit() {
        HmxCategoryContentDto hmxCategoryContentDto = new HmxCategoryContentDto();
        hmxCategoryContentDto.setState(0);
        List<HmxCategoryContent> hmxCategoryContents = hmxCategoryContentService.list(hmxCategoryContentDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/media/vedio/list");
        modelAndView.addObject("contents",hmxCategoryContents);
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
    public Map<String,Object> getVedioList(PageBean<HmxMovie> page, HmxMovieDto hmxMovieDto){
        Map<String,Object> map = new HashMap();
        PageBean<HmxMovie> pageBean = hmxMovieService.getPage(page,hmxMovieDto);
        map.put("rows", pageBean.getPage());
        map.put("total", pageBean.getTotalNum());
        return map;
    }

    @RequestMapping("/upload/video")
    public Result<Object> uploadVideo(MultipartFile file, String title, String duration, Integer contentType, String ratio) throws IOException {
        Result<Object> result = new Result<>();
        //保存进move表中
        HmxMovie hmxMovie = new HmxMovie();
        hmxMovie.setVideoStatus(2);
        hmxMovie.setDuration(duration);
        hmxMovie.setMovieName(title);
        hmxMovie.setRatio(ratio);
        hmxMovie.setCreateTime(new Date());
        hmxMovieService.insert(hmxMovie);

        uploadMovieAsync.uploadVideoAsync(file,title,hmxMovie.getMovieId());
        //返回成功
        result.setMsg("成功");
        result.setStatus(10000);
        return result;
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
    public ModelAndView show(Integer movieId){
        HmxMovie hmxMovie = hmxMovieService.info(movieId);
        String videoId = hmxMovie.getVideoId();
        Map<String,Object> resultMap = initVodClients.getUrl(videoId);
        String url = (String) resultMap.get("url");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/media/vedio/show");
        modelAndView.addObject("movieUrl",url);
        return modelAndView;
    }

    @RequestMapping("/mp3/show")
    public ModelAndView mp3Play(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/media/mp3/show");
        return modelAndView;
    }

}