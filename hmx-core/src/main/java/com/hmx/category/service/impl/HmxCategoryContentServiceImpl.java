package com.hmx.category.service.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import com.hmx.category.service.HmxCategoryContentService;
import com.hmx.utils.result.PageBean;
import com.hmx.category.entity.HmxCategoryContent;
import com.hmx.category.dto.HmxCategoryContentDto;
import com.hmx.category.entity.HmxCategoryContentExample;
import com.hmx.category.entity.HmxCategoryContentExample.Criteria;
import com.hmx.category.dao.HmxCategoryContentMapper;
/**
 * Service implementation class
 *
 */
 @Service
 public class HmxCategoryContentServiceImpl implements HmxCategoryContentService{
 
 	@Autowired
	private HmxCategoryContentMapper hmxCategoryContentMapper;
	
	
	/**
	 * @Method: insert 
	 * @Description: 添加
	 * @param hmxCategoryContent 要添加的对象
	 * @return 
	 */
	@Override
	public Boolean insert( HmxCategoryContent hmxCategoryContent ) {
		return hmxCategoryContentMapper.insertSelective( hmxCategoryContent ) > 0;
	}
		
	/**
	 * @Method: deleteByIdArray 
	 * @Description: 批量删除
	 * @param ids 将要删除的对象主键字符串 例如:1,5,10,12
	 * @return true 删除成功  false 删除失败
	 */
	 @Override
	 @Transactional
	 public Boolean deleteByIdArray(String ids) {
	 	List<Integer> idArray = new ArrayList<Integer>();
		String[] arrayStr = null ;
		try{
			if( ids == null || ids == "" ){
				return false;
			}
			
			if( ids.length() > 0 ){
				arrayStr = ids.split(",");
			}
			
			for(String strid: arrayStr){
				Integer id = Integer.parseInt(strid);
				idArray.add(id);
			}
			HmxCategoryContentExample hmxCategoryContentExample = new HmxCategoryContentExample();
			hmxCategoryContentExample.or().andCategoryContentIdIn( idArray );
			
			int ret = hmxCategoryContentMapper.deleteByExample( hmxCategoryContentExample );
			return ret > 0;
		}catch( Exception e ){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @Method: update 
	 * @Description: 修改
	 * @param hmxCategoryContent 要修改的对象
	 * @return true 修改成功  false 修改失败
	 */
	@Override
	public Boolean update(HmxCategoryContent hmxCategoryContent) {
		return hmxCategoryContentMapper.updateByPrimaryKeySelective( hmxCategoryContent ) > 0;
	}
	
	
	/**
	 * @Method: info 
	 * @Description: 根据自增主键查询对象信息
	 * @param hmxCategoryContent 根据自增对象查询信息
	 * @return HmxCategoryContent 查询的对象
	 */
	public HmxCategoryContent info (Integer hmxCategoryContentId) {
		return hmxCategoryContentMapper.selectByPrimaryKey( hmxCategoryContentId );
	}
	
	/**
	 * @Method: getPage 
	 * @Description: 分页查询
	 * @param page 分页参数
	 * @param hmxCategoryContentDto 查询条件
	 * @return PageBean<HmxCategoryContent> 查询到的分页值
	 */
	public PageBean<HmxCategoryContent> getPage(PageBean<HmxCategoryContent> page,HmxCategoryContentDto hmxCategoryContentDto) {
		
		HmxCategoryContentExample hmxCategoryContentExample = new HmxCategoryContentExample();
		
		hmxCategoryContentExample.setOffset(page.getStartOfPage());
		hmxCategoryContentExample.setLimit(page.getPageSize());
		
		Criteria where = hmxCategoryContentExample.createCriteria();
		
  		if ( hmxCategoryContentDto.getCategoryContentId() != null && hmxCategoryContentDto.getCategoryContentId() != 0 ) {
			where.andCategoryContentIdEqualTo( hmxCategoryContentDto.getCategoryContentId() );
		}
  		if ( StringUtils.isEmpty( hmxCategoryContentDto.getCategoryTitle() ) ) {
			where.andCategoryTitleEqualTo( hmxCategoryContentDto.getCategoryTitle() );
		}
  		if ( StringUtils.isEmpty( hmxCategoryContentDto.getCategoryContent() ) ) {
			where.andCategoryContentEqualTo( hmxCategoryContentDto.getCategoryContent() );
		}
  		if ( hmxCategoryContentDto.getContentType() != null && hmxCategoryContentDto.getContentType() != 0 ) {
			where.andContentTypeEqualTo( hmxCategoryContentDto.getContentType() );
		}
  		if ( StringUtils.isEmpty( hmxCategoryContentDto.getContentImages() ) ) {
			where.andContentImagesEqualTo( hmxCategoryContentDto.getContentImages() );
		}
  		if ( hmxCategoryContentDto.getMovieId() != null && hmxCategoryContentDto.getMovieId() != 0 ) {
			where.andMovieIdEqualTo( hmxCategoryContentDto.getMovieId() );
		}
  		if ( hmxCategoryContentDto.getMusicId() != null && hmxCategoryContentDto.getMusicId() != 0 ) {
			where.andMusicIdEqualTo( hmxCategoryContentDto.getMusicId() );
		}
  		if ( hmxCategoryContentDto.getBrowseNum() != null && hmxCategoryContentDto.getBrowseNum() != 0 ) {
			where.andBrowseNumEqualTo( hmxCategoryContentDto.getBrowseNum() );
		}
  		if ( hmxCategoryContentDto.getCreateTime() != null ) {
  			where.andCreateTimeEqualTo( hmxCategoryContentDto.getCreateTime() );
  		}
  		if ( hmxCategoryContentDto.getNewTime() != null ) {
  			where.andNewTimeEqualTo( hmxCategoryContentDto.getNewTime() );
  		}
  		if ( hmxCategoryContentDto.getState() != null && hmxCategoryContentDto.getState() != 0 ) {
			where.andStateEqualTo( hmxCategoryContentDto.getState() );
		}
  		if ( hmxCategoryContentDto.getVersion() != null && hmxCategoryContentDto.getVersion() != 0 ) {
			where.andVersionEqualTo( hmxCategoryContentDto.getVersion() );
		}
  		if ( hmxCategoryContentDto.getCreateid() != null && hmxCategoryContentDto.getCreateid() != 0 ) {
			where.andCreateidEqualTo( hmxCategoryContentDto.getCreateid() );
		}
		
		Integer count = hmxCategoryContentMapper.countByExample( hmxCategoryContentExample );
			
		boolean haveData = page.setTotalNum((int)(long)count);
			
		if(!haveData){
			return page;
		}
			
		List<HmxCategoryContent> data = hmxCategoryContentMapper.selectByExample( hmxCategoryContentExample );
		
		page.setPage(data);
		
		return page;
	}
	
	
	/**
	 * @Method: list 
	 * @Description: 查询某个条件下的所有数据
	 * @param hmxCategoryContentDto 查询参数
	 * @return List<HmxCategoryContent> 符合条件的list集合
	 */
	public List<HmxCategoryContent> list( HmxCategoryContentDto hmxCategoryContentDto ) {
	
		HmxCategoryContentExample hmxCategoryContentExample = new HmxCategoryContentExample();
		
		Criteria where = hmxCategoryContentExample.createCriteria();
		
  		if ( hmxCategoryContentDto.getCategoryContentId() != null && hmxCategoryContentDto.getCategoryContentId() != 0 ) {
			where.andCategoryContentIdEqualTo( hmxCategoryContentDto.getCategoryContentId() );
		}
  		if ( !StringUtils.isEmpty( hmxCategoryContentDto.getCategoryTitle() ) ) {
			where.andCategoryTitleEqualTo( hmxCategoryContentDto.getCategoryTitle() );
		}
  		if ( !StringUtils.isEmpty( hmxCategoryContentDto.getCategoryContent() ) ) {
			where.andCategoryContentEqualTo( hmxCategoryContentDto.getCategoryContent() );
		}
  		if ( hmxCategoryContentDto.getContentType() != null && hmxCategoryContentDto.getContentType() != 0 ) {
			where.andContentTypeEqualTo( hmxCategoryContentDto.getContentType() );
		}
  		if ( !StringUtils.isEmpty( hmxCategoryContentDto.getContentImages() ) ) {
			where.andContentImagesEqualTo( hmxCategoryContentDto.getContentImages() );
		}
  		if ( hmxCategoryContentDto.getMovieId() != null && hmxCategoryContentDto.getMovieId() != 0 ) {
			where.andMovieIdEqualTo( hmxCategoryContentDto.getMovieId() );
		}
  		if ( hmxCategoryContentDto.getMusicId() != null && hmxCategoryContentDto.getMusicId() != 0 ) {
			where.andMusicIdEqualTo( hmxCategoryContentDto.getMusicId() );
		}
  		if ( hmxCategoryContentDto.getBrowseNum() != null && hmxCategoryContentDto.getBrowseNum() != 0 ) {
			where.andBrowseNumEqualTo( hmxCategoryContentDto.getBrowseNum() );
		}
  		if ( hmxCategoryContentDto.getCreateTime() != null ) {
  			where.andCreateTimeEqualTo( hmxCategoryContentDto.getCreateTime() );
  		}
  		if ( hmxCategoryContentDto.getNewTime() != null ) {
  			where.andNewTimeEqualTo( hmxCategoryContentDto.getNewTime() );
  		}
  		if ( hmxCategoryContentDto.getState() != null && hmxCategoryContentDto.getState() != 0 ) {
			where.andStateEqualTo( hmxCategoryContentDto.getState() );
		}
  		if ( hmxCategoryContentDto.getVersion() != null && hmxCategoryContentDto.getVersion() != 0 ) {
			where.andVersionEqualTo( hmxCategoryContentDto.getVersion() );
		}
  		if ( hmxCategoryContentDto.getCreateid() != null && hmxCategoryContentDto.getCreateid() != 0 ) {
			where.andCreateidEqualTo( hmxCategoryContentDto.getCreateid() );
		}
		
		if( hmxCategoryContentDto.getLimit() != null ){
			hmxCategoryContentExample.setLimit( hmxCategoryContentDto.getLimit() );
		}
		if( !StringUtils.isEmpty( hmxCategoryContentDto.getOrderByClause() ) ){
			hmxCategoryContentExample.setOrderByClause( hmxCategoryContentDto.getOrderByClause() );
		}
		return hmxCategoryContentMapper.selectByExample(hmxCategoryContentExample);
	}
	
}
 
 