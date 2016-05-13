package com.puban.framework.core.page;

import java.util.List;

public class PageView<T>
{

	/**分页数据**/
	private List<T> records;

	/**页码开始索引 结束索引**/
	private PageIndex pageindex;

	/**总页数**/
	private long totalpage = 1;

	/**每页显示记录数**/
	private int maxresult = 20;

	/**当前页**/
	private int currentpage = 1;

	/**总记录数**/
	private long totalrecord;


	/**new PageView 必须传入这两个参数**/
	public PageView(int maxresult, int currentpage)
	{
		this.maxresult = maxresult;
		this.currentpage = currentpage;
	}
	
	public int getFirstResult()
	{
		return (this.currentpage - 1) * this.maxresult;
	}

	public void setQueryResult(QueryResult<T> qr)
	{
		setTotalrecord(qr.getTotalrecord());
		setRecords(qr.getResultlist());
	}

	public long getTotalrecord()
	{
		return totalrecord;
	}

	public void setTotalrecord(long totalrecord)
	{
		this.totalrecord = totalrecord;
		setTotalpage(this.totalrecord % this.maxresult == 0 ? this.totalrecord / this.maxresult : this.totalrecord / this.maxresult + 1);
	}

	public List<T> getRecords()
	{
		return records;
	}

	public void setRecords(List<T> records)
	{
		this.records = records;
	}

	public PageIndex getPageindex()
	{
		return pageindex;
	}

	public long getTotalpage()
	{
		return totalpage;
	}

	public void setTotalpage(long totalpage)
	{
		this.totalpage = totalpage;
		this.pageindex = WebTool.getPageIndex(maxresult, currentpage, totalpage);
	}

	public int getMaxresult()
	{
		return maxresult;
	}

	public int getCurrentpage()
	{
		return currentpage;
	}

}
