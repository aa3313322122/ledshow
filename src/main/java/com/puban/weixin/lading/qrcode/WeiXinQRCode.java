package com.puban.weixin.lading.qrcode;

public class WeiXinQRCode
{
	private String ticket;

	private int expireSeconds;

	public String getTicket()
	{
		return ticket;
	}

	public void setTicket(String ticket)
	{
		this.ticket = ticket;
	}

	public int getExpireSeconds()
	{
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds)
	{
		this.expireSeconds = expireSeconds;
	}

}
