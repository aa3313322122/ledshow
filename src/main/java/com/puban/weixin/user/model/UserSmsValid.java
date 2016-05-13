package com.puban.weixin.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.puban.framework.core.model.BaseModel;

@Entity
@Table(name = "t_user_sms_valid")
public class UserSmsValid extends BaseModel
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fdId;
	
	@Column(name = "fd_open_id")
	private String fdOpenId;
	
	@Column(name = "fd_phone")
	private String fdPhone;
	
	@Column(name = "fd_last_code")
	private String fdLastCode;
	
	@Column(name = "fd_last_time")
	private String fdLastTime;
	
	@Column(name = "fd_expired_time")
	private String fdExpiredTime;

	public Integer getFdId()
	{
		return fdId;
	}

	public void setFdId(Integer fdId)
	{
		this.fdId = fdId;
	}

	public String getFdOpenId()
	{
		return fdOpenId;
	}

	public void setFdOpenId(String fdOpenId)
	{
		this.fdOpenId = fdOpenId;
	}

	public String getFdPhone()
	{
		return fdPhone;
	}

	public void setFdPhone(String fdPhone)
	{
		this.fdPhone = fdPhone;
	}

	public String getFdLastCode()
	{
		return fdLastCode;
	}

	public void setFdLastCode(String fdLastCode)
	{
		this.fdLastCode = fdLastCode;
	}

	public String getFdLastTime()
	{
		return fdLastTime;
	}

	public void setFdLastTime(String fdLastTime)
	{
		this.fdLastTime = fdLastTime;
	}

	public String getFdExpiredTime()
	{
		return fdExpiredTime;
	}

	public void setFdExpiredTime(String fdExpiredTime)
	{
		this.fdExpiredTime = fdExpiredTime;
	}
	
}
