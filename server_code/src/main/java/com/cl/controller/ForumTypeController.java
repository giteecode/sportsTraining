package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.cl.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.annotation.SysLog;

import com.cl.entity.ForumTypeEntity;
import com.cl.entity.view.ForumTypeView;

import com.cl.service.ForumTypeService;
import com.cl.service.TokenService;

/**
 * 论坛类型
 * 后端接口
 * @author 
 * @email 
 * @date 2026-02-26 16:47:39
 */
@RestController
@RequestMapping("/forumtype")
public class ForumTypeController {
    @Autowired
    private ForumTypeService forumTypeService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ForumTypeEntity forumType,
                                            HttpServletRequest request){
                                    EntityWrapper<ForumTypeEntity> ew = new EntityWrapper<ForumTypeEntity>();
                                                        
        
        
        PageUtils page = forumTypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forumType), params), params));
        Map<String, String> deSens = new HashMap<>();
                                    //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ForumTypeEntity forumType,
		HttpServletRequest request){
        EntityWrapper<ForumTypeEntity> ew = new EntityWrapper<ForumTypeEntity>();

		PageUtils page = forumTypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forumType), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ForumTypeEntity forumType){
       	EntityWrapper<ForumTypeEntity> ew = new EntityWrapper<ForumTypeEntity>();
      	ew.allEq(MPUtil.allEQMapPre( forumType, MPUtil.camelToSnake("forumType")));
        return R.ok().put("data", forumTypeService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ForumTypeEntity forumType){
        EntityWrapper< ForumTypeEntity> ew = new EntityWrapper< ForumTypeEntity>();
 		ew.allEq(MPUtil.allEQMapPre( forumType, MPUtil.camelToSnake("forumType")));
		ForumTypeView forumTypeView =  forumTypeService.selectView(ew);
		return R.ok("查询论坛类型成功").put("data", forumTypeView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ForumTypeEntity forumType = forumTypeService.selectById(id);
		forumType = forumTypeService.selectView(new EntityWrapper<ForumTypeEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(forumType,deSens);
        return R.ok().put("data", forumType);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ForumTypeEntity forumType = forumTypeService.selectById(id);
		forumType = forumTypeService.selectView(new EntityWrapper<ForumTypeEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(forumType,deSens);
        return R.ok().put("data", forumType);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增论坛类型")
    public R save(@RequestBody ForumTypeEntity forumType, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(forumType);
        forumTypeService.insert(forumType);
        return R.ok().put("data",forumType.getId());
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增论坛类型")
    @RequestMapping("/add")
    public R add(@RequestBody ForumTypeEntity forumType, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(forumType);
        forumTypeService.insert(forumType);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改论坛类型")
    public R update(@RequestBody ForumTypeEntity forumType, HttpServletRequest request){
        //ValidatorUtils.validateEntity(forumType);
        forumTypeService.updateById(forumType);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除论坛类型")
    public R delete(@RequestBody Long[] ids){
        forumTypeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
