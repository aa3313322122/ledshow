package com.puban.weixin.channel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.puban.framework.core.model.BaseModel;

@Entity
@Table(name="t_channel")
public class Channel extends BaseModel
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fdId;

	@Column(name="fd_name")
	private String fdName;
	
	@Column(name="fd_code")
	private String fdCode;
	
	@Column(name="fd_status")
	private String fdStauts;
	
	@Column(name="fd_create_date")
	private String fdCreateDate;
	
	
	/**渠道签约人**/
	@Column(name="fd_main_name")
	private String fdMainName;

	public Integer getFdId()
	{
		return fdId;
	}

	public void setFdId(Integer fdId)
	{
		this.fdId = fdId;
	}

	public String getFdName()
	{
		return fdName;
	}

	public void setFdName(String fdName)
	{
		this.fdName = fdName;
	}

	public String getFdCode()
	{
		return fdCode;
	}

	public void setFdCode(String fdCode)
	{
		this.fdCode = fdCode;
	}

	public String getFdStauts()
	{
		return fdStauts;
	}

	public void setFdStauts(String fdStauts)
	{
		this.fdStauts = fdStauts;
	}

	public String getFdCreateDate()
	{
		return fdCreateDate;
	}

	public void setFdCreateDate(String fdCreateDate)
	{
		this.fdCreateDate = fdCreateDate;
	}
	
	public String getFdMainName()
	{
		return fdMainName;
	}

	public void setFdMainName(String fdMainName)
	{
		this.fdMainName = fdMainName;
	}

	@Override
	public String toString()
	{
		return "Channel [fdId=" + fdId + ", fdName=" + fdName + ", fdCode=" + fdCode + ", fdStauts=" + fdStauts + ", fdCreateDate=" + fdCreateDate + ", fdMainName=" + fdMainName + "]";
	}
	
}
