package com.hmx.movie.entity;

import java.lang.Integer;
import java.lang.String;
import java.util.Date;

public class HmxMovie{
    private Integer movieId;
    private String movieName;
    private String ratio;
    private Integer duration;
    private String movieUrl;
    private Date createTime;
    private Date newTime;
    private Integer state;
    private Integer version;
    private Integer createid;

    public HmxMovie() {
		super();
	}

    public HmxMovie(Integer movieId, String movieName, String ratio, Integer duration, String movieUrl, Date createTime, Date newTime, Integer state, Integer version, Integer createid) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.ratio = ratio;
		this.duration = duration;
		this.movieUrl = movieUrl;
		this.createTime = createTime;
		this.newTime = newTime;
		this.state = state;
		this.version = version;
		this.createid = createid;
	}
	
    public Integer getMovieId() {
		return movieId;
	}
	
	public void setMovieId(Integer movieId){
		this.movieId = movieId;
	}
	
    public String getMovieName() {
		return movieName;
	}
	
	public void setMovieName(String movieName){
		this.movieName = movieName;
	}
	
    public String getRatio() {
		return ratio;
	}
	
	public void setRatio(String ratio){
		this.ratio = ratio;
	}
	
    public Integer getDuration() {
		return duration;
	}
	
	public void setDuration(Integer duration){
		this.duration = duration;
	}
	
    public String getMovieUrl() {
		return movieUrl;
	}
	
	public void setMovieUrl(String movieUrl){
		this.movieUrl = movieUrl;
	}
	
    public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
    public Date getNewTime() {
		return newTime;
	}
	
	public void setNewTime(Date newTime){
		this.newTime = newTime;
	}
	
    public Integer getState() {
		return state;
	}
	
	public void setState(Integer state){
		this.state = state;
	}
	
    public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version){
		this.version = version;
	}
	
    public Integer getCreateid() {
		return createid;
	}
	
	public void setCreateid(Integer createid){
		this.createid = createid;
	}
	
}