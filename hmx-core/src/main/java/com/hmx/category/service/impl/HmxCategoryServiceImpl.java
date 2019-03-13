package com.hmx.category.service.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hmx.category.service.HmxCategoryService;
import com.hmx.utils.result.PageBean;
import com.hmx.category.entity.HmxCategory;
import com.hmx.category.dto.HmxCategoryDto;
import com.hmx.category.entity.HmxCategoryExample;
import com.hmx.category.entity.HmxCategoryExample.Criteria;
import com.hmx.category.dao.HmxCategoryMapper;
/**
 * Service implementation class
 *
 */
 @Service
 public class HmxCategoryServiceImpl implements HmxCategoryService{
 
 	@Autowired
	private HmxCategoryMapper hmxCategoryMapper;
	
	
	/**
	 * @Method: insert 
	 * @Description: 添加
	 * @param hmxCategory 要添加的对象
	 * @return 
	 */
	@Override
	public Boolean insert( HmxCategory hmxCategory ) {
		return hmxCategoryMapper.insertSelective( hmxCategory ) > 0;
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
			HmxCategoryExample hmxCategoryExample = new HmxCategoryExample();
			hmxCategoryExample.or().andCategoryIdIn( idArray );
			
			int ret = hmxCategoryMapper.deleteByExample( hmxCategoryExample );
			return ret > 0;
		}catch( Exception e ){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @Method: update 
	 * @Description: 修改
	 * @param hmxCategory 要修改的对象
	 * @return true 修改成功  false 修改失败
	 */
	@Override
	public Boolean update(HmxCategory hmxCategory) {
		return hmxCategoryMapper.updateByPrimaryKeySelective( hmxCategory ) > 0;
	}
	
	
	/**
	 * @Method: info 
	 * @Description: 根据自增主键查询对象信息
	 * @param hmxCategory 根据自增对象查询信息
	 * @return HmxCategory 查询的对象
	 */
	public HmxCategory info (Integer hmxCategoryId) {
		return hmxCategoryMapper.selectByPrimaryKey( hmxCategoryId );
	}
	
	/**
	 * @Method: getPage 
	 * @Description: 分页查询
	 * @param page 分页参数
	 * @param hmxCategoryDto 查询条件
	 * @return PageBean<HmxCategory> 查询到的分页值
	 */
	public PageBean<HmxCategory> getPage(PageBean<HmxCategory> page,HmxCategoryDto hmxCategoryDto) {
		
		HmxCategoryExample hmxCategoryExample = new HmxCategoryExample();
		
		hmxCategoryExample.setOffset(page.getStartOfPage());
		hmxCategoryExample.setLimit(page.getPageSize());
		
		Criteria where = hmxCategoryExample.createCriteria();
		
  		if ( hmxCategoryDto.getCategoryId() != null && hmxCategoryDto.getCategoryId() != 0 ) {
			where.andCategoryIdEqualTo( hmxCategoryDto.getCategoryId() );
		}
  		if ( StringUtils.isEmpty( hmxCategoryDto.getCategoryName() ) ) {
			where.andCategoryNameEqualTo( hmxCategoryDto.getCategoryName() );
		}
  		if ( hmxCategoryDto.getSort() != null && hmxCategoryDto.getSort() != 0 ) {
			where.andSortEqualTo( hmxCategoryDto.getSort() );
		}
  		if ( hmxCategoryDto.getIsClose() != null && hmxCategoryDto.getIsClose() != 0 ) {
			where.andIsCloseEqualTo( hmxCategoryDto.getIsClose() );
		}
  		if ( hmxCategoryDto.getCreateTime() != null ) {
  			where.andCreateTimeEqualTo( hmxCategoryDto.getCreateTime() );
  		}
  		if ( hmxCategoryDto.getNewTime() != null ) {
  			where.andNewTimeEqualTo( hmxCategoryDto.getNewTime() );
  		}
  		if ( hmxCategoryDto.getState() != null && hmxCategoryDto.getState() != 0 ) {
			where.andStateEqualTo( hmxCategoryDto.getState() );
		}
  		if ( hmxCategoryDto.getVersion() != null && hmxCategoryDto.getVersion() != 0 ) {
			where.andVersionEqualTo( hmxCategoryDto.getVersion() );
		}
  		if ( hmxCategoryDto.getCreateid() != null && hmxCategoryDto.getCreateid() != 0 ) {
			where.andCreateidEqualTo( hmxCategoryDto.getCreateid() );
		}
		
		Integer count = hmxCategoryMapper.countByExample( hmxCategoryExample );
			
		boolean haveData = page.setTotalNum((int)(long)count);
			
		if(!haveData){
			return page;
		}
			
		List<HmxCategory> data = hmxCategoryMapper.selectByExample( hmxCategoryExample );
		
		page.setPage(data);
		
		return page;
	}
	
	
	/**
	 * @Method: list 
	 * @Description: 查询某个条件下的所有数据
	 * @param hmxCategoryDto 查询参数
	 * @return List<HmxCategory> 符合条件的list集合
	 */
	public List<HmxCategory> list( HmxCategoryDto hmxCategoryDto ) {
	
		HmxCategoryExample hmxCategoryExample = new HmxCategoryExample();
		
		Criteria where = hmxCategoryExample.createCriteria();
		
  		if ( hmxCategoryDto.getCategoryId() != null && hmxCategoryDto.getCategoryId() != 0 ) {
			where.andCategoryIdEqualTo( hmxCategoryDto.getCategoryId() );
		}
  		if ( !StringUtils.isEmpty( hmxCategoryDto.getCategoryName() ) ) {
			where.andCategoryNameEqualTo( hmxCategoryDto.getCategoryName() );
		}
  		if ( hmxCategoryDto.getSort() != null && hmxCategoryDto.getSort() != 0 ) {
			where.andSortEqualTo( hmxCategoryDto.getSort() );
		}
  		if ( hmxCategoryDto.getIsClose() != null && hmxCategoryDto.getIsClose() != 0 ) {
			where.andIsCloseEqualTo( hmxCategoryDto.getIsClose() );
		}
  		if ( hmxCategoryDto.getCreateTime() != null ) {
  			where.andCreateTimeEqualTo( hmxCategoryDto.getCreateTime() );
  		}
  		if ( hmxCategoryDto.getNewTime() != null ) {
  			where.andNewTimeEqualTo( hmxCategoryDto.getNewTime() );
  		}
  		if ( hmxCategoryDto.getState() != null && hmxCategoryDto.getState() != 0 ) {
			where.andStateEqualTo( hmxCategoryDto.getState() );
		}
  		if ( hmxCategoryDto.getVersion() != null && hmxCategoryDto.getVersion() != 0 ) {
			where.andVersionEqualTo( hmxCategoryDto.getVersion() );
		}
  		if ( hmxCategoryDto.getCreateid() != null && hmxCategoryDto.getCreateid() != 0 ) {
			where.andCreateidEqualTo( hmxCategoryDto.getCreateid() );
		}
		
		if( hmxCategoryDto.getLimit() != null ){
			hmxCategoryExample.setLimit( hmxCategoryDto.getLimit() );
		}
		if( !StringUtils.isEmpty( hmxCategoryDto.getOrderByClause() ) ){
			hmxCategoryExample.setOrderByClause( hmxCategoryDto.getOrderByClause() );
		}
		return hmxCategoryMapper.selectByExample(hmxCategoryExample);
	}
	
}
 
 