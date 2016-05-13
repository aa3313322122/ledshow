package com.puban.weixin.admin.model;

import java.io.Serializable;

public class Statistics implements Serializable
{
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = 1L;

	/** 渠道编码  **/
	private String channelCode;
	
	/** 申报单总数  **/
	private Long declareCount;
	
	/** 申报总金额  **/
	private Double declareAmount;
	
	/** 成交单总数  **/
	private Long dealCount;
	
	/** 成交总金额  **/
	private Double dealAmount;

	public String getChannelCode()
	{
		return channelCode;
	}

	public void setChannelCode(String channelCode)
	{
		this.channelCode = channelCode;
	}

	public Long getDeclareCount()
	{
		return declareCount;
	}

	public void setDeclareCount(Long declareCount)
	{
		this.declareCount = declareCount;
	}

	public Double getDeclareAmount()
	{
		return declareAmount;
	}

	public void setDeclareAmount(Double declareAmount)
	{
		this.declareAmount = declareAmount;
	}

	public Long getDealCount()
	{
		return dealCount;
	}

	public void setDealCount(Long dealCount)
	{
		this.dealCount = dealCount;
	}

	public Double getDealAmount()
	{
		return dealAmount;
	}

	public void setDealAmount(Double dealAmount)
	{
		this.dealAmount = dealAmount;
	}
}
