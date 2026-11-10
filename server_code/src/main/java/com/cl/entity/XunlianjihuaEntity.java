package com.cl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 训练计划
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-02-26 16:47:40
 */
@TableName("xunlianjihua")
public class XunlianjihuaEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public XunlianjihuaEntity() {
		
	}
	
	public XunlianjihuaEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 计划名称
	 */
					
	private String jihuamingcheng;
	
	/**
	 * 计划目标
	 */
					
	private String jihuamubiao;
	
	/**
	 * 计划内容
	 */
					
	private String jihuaneirong;
	
	/**
	 * 计划时间
	 */
					
	private String jihuashijian;
	
	/**
	 * 制定时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date zhidingshijian;
	
	/**
	 * 用户账号
	 */
					
	private String yonghuzhanghao;
	
	/**
	 * 用户姓名
	 */
					
	private String yonghuxingming;
	

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：计划名称
	 */
	public void setJihuamingcheng(String jihuamingcheng) {
		this.jihuamingcheng = jihuamingcheng;
	}
	/**
	 * 获取：计划名称
	 */
	public String getJihuamingcheng() {
		return jihuamingcheng;
	}
	/**
	 * 设置：计划目标
	 */
	public void setJihuamubiao(String jihuamubiao) {
		this.jihuamubiao = jihuamubiao;
	}
	/**
	 * 获取：计划目标
	 */
	public String getJihuamubiao() {
		return jihuamubiao;
	}
	/**
	 * 设置：计划内容
	 */
	public void setJihuaneirong(String jihuaneirong) {
		this.jihuaneirong = jihuaneirong;
	}
	/**
	 * 获取：计划内容
	 */
	public String getJihuaneirong() {
		return jihuaneirong;
	}
	/**
	 * 设置：计划时间
	 */
	public void setJihuashijian(String jihuashijian) {
		this.jihuashijian = jihuashijian;
	}
	/**
	 * 获取：计划时间
	 */
	public String getJihuashijian() {
		return jihuashijian;
	}
	/**
	 * 设置：制定时间
	 */
	public void setZhidingshijian(Date zhidingshijian) {
		this.zhidingshijian = zhidingshijian;
	}
	/**
	 * 获取：制定时间
	 */
	public Date getZhidingshijian() {
		return zhidingshijian;
	}
	/**
	 * 设置：用户账号
	 */
	public void setYonghuzhanghao(String yonghuzhanghao) {
		this.yonghuzhanghao = yonghuzhanghao;
	}
	/**
	 * 获取：用户账号
	 */
	public String getYonghuzhanghao() {
		return yonghuzhanghao;
	}
	/**
	 * 设置：用户姓名
	 */
	public void setYonghuxingming(String yonghuxingming) {
		this.yonghuxingming = yonghuxingming;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getYonghuxingming() {
		return yonghuxingming;
	}

}
