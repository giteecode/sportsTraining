package com.cl.dao;

import com.cl.entity.XunlianjihuaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XunlianjihuaView;


/**
 * 训练计划
 * 
 * @author 
 * @email 
 * @date 2026-02-26 16:47:40
 */
public interface XunlianjihuaDao extends BaseMapper<XunlianjihuaEntity> {
	
	List<XunlianjihuaView> selectListView(@Param("ew") Wrapper<XunlianjihuaEntity> wrapper);

	List<XunlianjihuaView> selectListView(Pagination page,@Param("ew") Wrapper<XunlianjihuaEntity> wrapper);
	
	XunlianjihuaView selectView(@Param("ew") Wrapper<XunlianjihuaEntity> wrapper);


}
