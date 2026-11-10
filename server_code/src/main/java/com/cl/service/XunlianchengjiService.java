package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.XunlianchengjiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XunlianchengjiView;


/**
 * 训练成绩
 *
 * @author 
 * @email 
 * @date 2026-02-26 16:47:40
 */
public interface XunlianchengjiService extends IService<XunlianchengjiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XunlianchengjiView> selectListView(Wrapper<XunlianchengjiEntity> wrapper);
   	
   	XunlianchengjiView selectView(@Param("ew") Wrapper<XunlianchengjiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XunlianchengjiEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<XunlianchengjiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<XunlianchengjiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<XunlianchengjiEntity> wrapper);



}

