package com.cl.dao;

import com.cl.entity.YundongshujuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YundongshujuView;


/**
 * 运动数据
 * 
 * @author 
 * @email 
 * @date 2026-02-26 16:47:40
 */
public interface YundongshujuDao extends BaseMapper<YundongshujuEntity> {
	
	List<YundongshujuView> selectListView(@Param("ew") Wrapper<YundongshujuEntity> wrapper);

	List<YundongshujuView> selectListView(Pagination page,@Param("ew") Wrapper<YundongshujuEntity> wrapper);
	
	YundongshujuView selectView(@Param("ew") Wrapper<YundongshujuEntity> wrapper);


}
