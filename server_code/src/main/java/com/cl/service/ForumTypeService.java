package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ForumTypeEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ForumTypeView;


/**
 * 论坛类型
 *
 * @author 
 * @email 
 * @date 2026-02-26 16:47:39
 */
public interface ForumTypeService extends IService<ForumTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ForumTypeView> selectListView(Wrapper<ForumTypeEntity> wrapper);
   	
   	ForumTypeView selectView(@Param("ew") Wrapper<ForumTypeEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ForumTypeEntity> wrapper);
   	
   
}

