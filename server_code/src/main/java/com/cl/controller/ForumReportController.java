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

import com.cl.entity.ForumReportEntity;
import com.cl.entity.view.ForumReportView;

import com.cl.service.ForumReportService;
import com.cl.service.TokenService;

/**
 * 论坛举报
 * 后端接口
 * @author 
 * @email 
 * @date 2026-02-26 16:47:39
 */
@RestController
@RequestMapping("/forumreport")
public class ForumReportController {
    @Autowired
    private ForumReportService forumReportService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ForumReportEntity forumReport,
                                                                                                                                                                                HttpServletRequest request){
                                    if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
                    forumReport.setUserid((Long)request.getSession().getAttribute("userId"));
                }
                            EntityWrapper<ForumReportEntity> ew = new EntityWrapper<ForumReportEntity>();
                                                                                                                                                                                                                                                                                    
        
        
        PageUtils page = forumReportService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forumReport), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ForumReportEntity forumReport,
		HttpServletRequest request){
        EntityWrapper<ForumReportEntity> ew = new EntityWrapper<ForumReportEntity>();

		PageUtils page = forumReportService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forumReport), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ForumReportEntity forumReport){
       	EntityWrapper<ForumReportEntity> ew = new EntityWrapper<ForumReportEntity>();
      	ew.allEq(MPUtil.allEQMapPre( forumReport, MPUtil.camelToSnake("forumReport")));
        return R.ok().put("data", forumReportService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ForumReportEntity forumReport){
        EntityWrapper< ForumReportEntity> ew = new EntityWrapper< ForumReportEntity>();
 		ew.allEq(MPUtil.allEQMapPre( forumReport, MPUtil.camelToSnake("forumReport")));
		ForumReportView forumReportView =  forumReportService.selectView(ew);
		return R.ok("查询论坛举报成功").put("data", forumReportView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ForumReportEntity forumReport = forumReportService.selectById(id);
		forumReport = forumReportService.selectView(new EntityWrapper<ForumReportEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(forumReport,deSens);
        return R.ok().put("data", forumReport);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ForumReportEntity forumReport = forumReportService.selectById(id);
		forumReport = forumReportService.selectView(new EntityWrapper<ForumReportEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(forumReport,deSens);
        return R.ok().put("data", forumReport);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增论坛举报")
    public R save(@RequestBody ForumReportEntity forumReport, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(forumReport);
        if(null == forumReport.getUserid()){
            forumReport.setUserid((Long) request.getSession().getAttribute("userId"));
        }
        forumReportService.insert(forumReport);
        return R.ok().put("data",forumReport.getId());
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增论坛举报")
    @RequestMapping("/add")
    public R add(@RequestBody ForumReportEntity forumReport, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(forumReport);
        if(null == forumReport.getUserid()){
            forumReport.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        forumReportService.insert(forumReport);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改论坛举报")
    public R update(@RequestBody ForumReportEntity forumReport, HttpServletRequest request){
        //ValidatorUtils.validateEntity(forumReport);
        forumReportService.updateById(forumReport);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除论坛举报")
    public R delete(@RequestBody Long[] ids){
        forumReportService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
