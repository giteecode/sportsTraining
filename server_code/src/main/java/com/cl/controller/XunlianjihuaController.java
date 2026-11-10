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

import com.cl.entity.XunlianjihuaEntity;
import com.cl.entity.view.XunlianjihuaView;

import com.cl.service.XunlianjihuaService;
import com.cl.service.TokenService;

/**
 * 训练计划
 * 后端接口
 * @author 
 * @email 
 * @date 2026-02-26 16:47:40
 */
@RestController
@RequestMapping("/xunlianjihua")
public class XunlianjihuaController {
    @Autowired
    private XunlianjihuaService xunlianjihuaService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XunlianjihuaEntity xunlianjihua,
                                                                                                                    HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                        if(tableName.equals("yonghu")) {
                    xunlianjihua.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                            EntityWrapper<XunlianjihuaEntity> ew = new EntityWrapper<XunlianjihuaEntity>();
                                                                                                                                                                                
        
        
        PageUtils page = xunlianjihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xunlianjihua), params), params));
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
    public R list(@RequestParam Map<String, Object> params,XunlianjihuaEntity xunlianjihua,
		HttpServletRequest request){
        EntityWrapper<XunlianjihuaEntity> ew = new EntityWrapper<XunlianjihuaEntity>();

		PageUtils page = xunlianjihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xunlianjihua), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XunlianjihuaEntity xunlianjihua){
       	EntityWrapper<XunlianjihuaEntity> ew = new EntityWrapper<XunlianjihuaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xunlianjihua, MPUtil.camelToSnake("xunlianjihua")));
        return R.ok().put("data", xunlianjihuaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XunlianjihuaEntity xunlianjihua){
        EntityWrapper< XunlianjihuaEntity> ew = new EntityWrapper< XunlianjihuaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xunlianjihua, MPUtil.camelToSnake("xunlianjihua")));
		XunlianjihuaView xunlianjihuaView =  xunlianjihuaService.selectView(ew);
		return R.ok("查询训练计划成功").put("data", xunlianjihuaView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XunlianjihuaEntity xunlianjihua = xunlianjihuaService.selectById(id);
		xunlianjihua = xunlianjihuaService.selectView(new EntityWrapper<XunlianjihuaEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xunlianjihua,deSens);
        return R.ok().put("data", xunlianjihua);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XunlianjihuaEntity xunlianjihua = xunlianjihuaService.selectById(id);
		xunlianjihua = xunlianjihuaService.selectView(new EntityWrapper<XunlianjihuaEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xunlianjihua,deSens);
        return R.ok().put("data", xunlianjihua);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增训练计划")
    public R save(@RequestBody XunlianjihuaEntity xunlianjihua, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xunlianjihua);
        xunlianjihuaService.insert(xunlianjihua);
        return R.ok().put("data",xunlianjihua.getId());
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增训练计划")
    @RequestMapping("/add")
    public R add(@RequestBody XunlianjihuaEntity xunlianjihua, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xunlianjihua);
        xunlianjihuaService.insert(xunlianjihua);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改训练计划")
    public R update(@RequestBody XunlianjihuaEntity xunlianjihua, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xunlianjihua);
        xunlianjihuaService.updateById(xunlianjihua);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除训练计划")
    public R delete(@RequestBody Long[] ids){
        xunlianjihuaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
