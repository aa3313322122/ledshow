package com.puban.weixin.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.puban.framework.core.model.BaseModel;
import com.puban.weixin.channel.model.Channel;

@Entity
@Table(name = "t_user")
public class User extends BaseModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fdId;
	
	@Column(name = "fd_status")
	private String fdStatus;
	
	@Column(name = "fd_subcribe")
	private Boolean fdSubcribe;
	
	@Column(name = "fd_open_id")
	private String fdOpenId;
	
	@Column(name = "fd_nick_name")
	private String fdNickName;
	
	@Column(name = "fd_create_time")
	private String fdCreateTime;
	
	@ManyToOne
	@JoinColumn(name = "channel_id")
	private Channel channel;

	public Integer getFdId()
	{
		return fdId;
	}

	public void setFdId(Integer fdId)
	{
		this.fdId = fdId;
	}
	public String getFdStatus()
	{
		return fdStatus;
	}

	public void setFdStatus(String fdStatus)
	{
		this.fdStatus = fdStatus;
	}

	public Boolean getFdSubcribe()
	{
		return fdSubcribe;
	}

	public void setFdSubcribe(Boolean fdSubcribe)
	{
		this.fdSubcribe = fdSubcribe;
	}

	public String getFdOpenId()
	{
		return fdOpenId;
	}

	public void setFdOpenId(String fdOpenId)
	{
		this.fdOpenId = fdOpenId;
	}

	public String getFdCreateTime()
	{
		return fdCreateTime;
	}

	public void setFdCreateTime(String fdCreateTime)
	{
		this.fdCreateTime = fdCreateTime;
	}

	public Channel getChannel()
	{
		return channel;
	}

	public void setChannel(Channel channel)
	{
		this.channel = channel;
	}

	public String getFdNickName()
	{
		return fdNickName;
	}

	public void setFdNickName(String fdNickName)
	{
		this.fdNickName = fdNickName;
	}
	
}
