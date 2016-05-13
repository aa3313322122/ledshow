package com.puban.weixin.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.puban.framework.core.model.BaseModel;

@Entity
@Table(name="t_test")
public class Test extends BaseModel
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fdId;

	@Column(name="fd_name")
	private String fdName;

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

}
