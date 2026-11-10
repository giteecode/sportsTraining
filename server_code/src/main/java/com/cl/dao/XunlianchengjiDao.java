package com.cl.dao;

import com.cl.entity.XunlianchengjiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XunlianchengjiView;


/**
 * 训练成绩
 * 
 * @author 
 * @email 
 * @date 2026-02-26 16:47:40
 */
public interface XunlianchengjiDao extends BaseMapper<XunlianchengjiEntity> {
	
	List<XunlianchengjiView> selectListView(@Param("ew") Wrapper<XunlianchengjiEntity> wrapper);

	List<XunlianchengjiView> selectListView(Pagination page,@Param("ew") Wrapper<XunlianchengjiEntity> wrapper);
	
	XunlianchengjiView selectView(@Param("ew") Wrapper<XunlianchengjiEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<XunlianchengjiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<XunlianchengjiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<XunlianchengjiEntity> wrapper);



}
