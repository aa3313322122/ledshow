package com.puban.weixin.lading.user;

import java.util.List;

public class WeiXinUserList
{
	private int total;
	
	private int count;
	
	private List<String> openIdList;
	
	private String nextOpenId;

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public List<String> getOpenIdList()
	{
		return openIdList;
	}

	public void setOpenIdList(List<String> openIdList)
	{
		this.openIdList = openIdList;
	}

	public String getNextOpenId()
	{
		return nextOpenId;
	}

	public void setNextOpenId(String nextOpenId)
	{
		this.nextOpenId = nextOpenId;
	}
}
