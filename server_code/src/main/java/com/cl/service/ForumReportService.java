package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ForumReportEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ForumReportView;


/**
 * 论坛举报
 *
 * @author 
 * @email 
 * @date 2026-02-26 16:47:39
 */
public interface ForumReportService extends IService<ForumReportEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ForumReportView> selectListView(Wrapper<ForumReportEntity> wrapper);
   	
   	ForumReportView selectView(@Param("ew") Wrapper<ForumReportEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ForumReportEntity> wrapper);
   	
   
}

