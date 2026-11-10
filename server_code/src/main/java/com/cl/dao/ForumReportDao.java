package com.cl.dao;

import com.cl.entity.ForumReportEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ForumReportView;


/**
 * 论坛举报
 * 
 * @author 
 * @email 
 * @date 2026-02-26 16:47:39
 */
public interface ForumReportDao extends BaseMapper<ForumReportEntity> {
	
	List<ForumReportView> selectListView(@Param("ew") Wrapper<ForumReportEntity> wrapper);

	List<ForumReportView> selectListView(Pagination page,@Param("ew") Wrapper<ForumReportEntity> wrapper);
	
	ForumReportView selectView(@Param("ew") Wrapper<ForumReportEntity> wrapper);


}
