package com.puban.weixin.admin.dao.impl;

import com.puban.framework.core.dao.impl.BaseDaoImpl;
import com.puban.weixin.admin.dao.IAdminLoginDao;
import com.puban.weixin.declare.model.Declare;

/**
 * @author zengyong@puban.com
 * @ClassName: IAdminLoginDaoImpl
 * @Description:
 * @date: 2016/4/20
 */

public class AdminLoginDaoImpl extends BaseDaoImpl<Declare> implements IAdminLoginDao
{

	@Override
	public void updateInfoMoney(Integer fdId, Double fdAmountLoanable, String fdAppointmentContact, String fdAppointmentInformation)
	{
		Declare declare = (Declare) this.get(Declare.class, fdId);
		declare.setFdAmountLoanable(fdAmountLoanable);
		declare.setFdStatus(2);
		if(fdAppointmentContact!=null&&!fdAppointmentContact.equals(""))
		{
			declare.setFdAppointmentContact(fdAppointmentContact);
		}
		if(fdAppointmentInformation!=null&&!fdAppointmentInformation.equals(""))
		{
			declare.setFdAppointmentInformation(fdAppointmentInformation);
		}
		
		this.update(declare);
	}

	@Override
	public void updateAmount(Integer fdId, Double fdFillingAmount)
	{
		Declare declare = (Declare) this.get(Declare.class, fdId);
		declare.setFdFillingAmount(fdFillingAmount);
		this.update(declare);
	}
}
