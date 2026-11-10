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

import com.cl.entity.YundongshujuEntity;
import com.cl.entity.view.YundongshujuView;

import com.cl.service.YundongshujuService;
import com.cl.service.TokenService;

/**
 * 运动数据
 * 后端接口
 * @author 
 * @email 
 * @date 2026-02-26 16:47:40
 */
@RestController
@RequestMapping("/yundongshuju")
public class YundongshujuController {
    @Autowired
    private YundongshujuService yundongshujuService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YundongshujuEntity yundongshuju,
                                                                                                                                HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                        if(tableName.equals("yonghu")) {
                    yundongshuju.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                        EntityWrapper<YundongshujuEntity> ew = new EntityWrapper<YundongshujuEntity>();
                                                                                                                                                                                                    
        
        
        PageUtils page = yundongshujuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yundongshuju), params), params));
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
    public R list(@RequestParam Map<String, Object> params,YundongshujuEntity yundongshuju,
		HttpServletRequest request){
        EntityWrapper<YundongshujuEntity> ew = new EntityWrapper<YundongshujuEntity>();

		PageUtils page = yundongshujuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yundongshuju), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YundongshujuEntity yundongshuju){
       	EntityWrapper<YundongshujuEntity> ew = new EntityWrapper<YundongshujuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yundongshuju, MPUtil.camelToSnake("yundongshuju")));
        return R.ok().put("data", yundongshujuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YundongshujuEntity yundongshuju){
        EntityWrapper< YundongshujuEntity> ew = new EntityWrapper< YundongshujuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yundongshuju, MPUtil.camelToSnake("yundongshuju")));
		YundongshujuView yundongshujuView =  yundongshujuService.selectView(ew);
		return R.ok("查询运动数据成功").put("data", yundongshujuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YundongshujuEntity yundongshuju = yundongshujuService.selectById(id);
		yundongshuju = yundongshujuService.selectView(new EntityWrapper<YundongshujuEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yundongshuju,deSens);
        return R.ok().put("data", yundongshuju);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YundongshujuEntity yundongshuju = yundongshujuService.selectById(id);
		yundongshuju = yundongshujuService.selectView(new EntityWrapper<YundongshujuEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yundongshuju,deSens);
        return R.ok().put("data", yundongshuju);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增运动数据")
    public R save(@RequestBody YundongshujuEntity yundongshuju, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yundongshuju);
        yundongshujuService.insert(yundongshuju);
        return R.ok().put("data",yundongshuju.getId());
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增运动数据")
    @RequestMapping("/add")
    public R add(@RequestBody YundongshujuEntity yundongshuju, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yundongshuju);
        yundongshujuService.insert(yundongshuju);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改运动数据")
    public R update(@RequestBody YundongshujuEntity yundongshuju, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yundongshuju);
        yundongshujuService.updateById(yundongshuju);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除运动数据")
    public R delete(@RequestBody Long[] ids){
        yundongshujuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
