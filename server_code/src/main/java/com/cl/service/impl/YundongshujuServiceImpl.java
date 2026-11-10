package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.YundongshujuDao;
import com.cl.entity.YundongshujuEntity;
import com.cl.service.YundongshujuService;
import com.cl.entity.view.YundongshujuView;

@Service("yundongshujuService")
public class YundongshujuServiceImpl extends ServiceImpl<YundongshujuDao, YundongshujuEntity> implements YundongshujuService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YundongshujuEntity> page = this.selectPage(
                new Query<YundongshujuEntity>(params).getPage(),
                new EntityWrapper<YundongshujuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YundongshujuEntity> wrapper) {
		  Page<YundongshujuView> page =new Query<YundongshujuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YundongshujuView> selectListView(Wrapper<YundongshujuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YundongshujuView selectView(Wrapper<YundongshujuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
