package com.cl.dao;

import com.cl.entity.ForumTypeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ForumTypeView;


/**
 * 论坛类型
 * 
 * @author 
 * @email 
 * @date 2026-02-26 16:47:39
 */
public interface ForumTypeDao extends BaseMapper<ForumTypeEntity> {
	
	List<ForumTypeView> selectListView(@Param("ew") Wrapper<ForumTypeEntity> wrapper);

	List<ForumTypeView> selectListView(Pagination page,@Param("ew") Wrapper<ForumTypeEntity> wrapper);
	
	ForumTypeView selectView(@Param("ew") Wrapper<ForumTypeEntity> wrapper);


}
