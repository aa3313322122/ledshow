package com.puban.framework.core.page;

import java.util.List;

public class QueryResult<T>
{
	private boolean success = false;

	private List<T> resultlist;

	private long totalrecord;

	public QueryResult(boolean success, List<T> resultlist, long totalrecord) {
		this.success = success;
		this.resultlist = resultlist;
		this.totalrecord = totalrecord;
	}

	public QueryResult(){}

	public List<T> getResultlist() {return resultlist;}

	public void setResultlist(List<T> resultlist) {this.resultlist = resultlist;}

	public long getTotalrecord()
	{
		return totalrecord;
	}

	public void setTotalrecord(long totalrecord)
	{
		this.totalrecord = totalrecord;
	}

	public boolean isSuccess() {return success;}

	public void setSuccess(boolean success) {this.success = success;}


}
