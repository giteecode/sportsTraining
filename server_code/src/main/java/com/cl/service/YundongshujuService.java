package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YundongshujuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YundongshujuView;


/**
 * 运动数据
 *
 * @author 
 * @email 
 * @date 2026-02-26 16:47:40
 */
public interface YundongshujuService extends IService<YundongshujuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YundongshujuView> selectListView(Wrapper<YundongshujuEntity> wrapper);
   	
   	YundongshujuView selectView(@Param("ew") Wrapper<YundongshujuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YundongshujuEntity> wrapper);
   	
   
}

