package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.XunlianjihuaEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XunlianjihuaView;


/**
 * 训练计划
 *
 * @author 
 * @email 
 * @date 2026-02-26 16:47:40
 */
public interface XunlianjihuaService extends IService<XunlianjihuaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XunlianjihuaView> selectListView(Wrapper<XunlianjihuaEntity> wrapper);
   	
   	XunlianjihuaView selectView(@Param("ew") Wrapper<XunlianjihuaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XunlianjihuaEntity> wrapper);
   	
   
}

