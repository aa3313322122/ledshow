package com.puban.weixin.admin.service.impl;

import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.weixin.admin.dao.IAdminLoginDao;
import com.puban.weixin.admin.service.IAdminLoginService;
import com.puban.weixin.declare.model.Declare;
import com.puban.weixin.declare.service.IDeclareService;
import com.puban.weixin.sms.service.ISmsService;

import javax.annotation.Resource;

/**
 * @author zengyong@puban.com
 * @ClassName: AdminLoginServiceImpl
 * @Description:
 * @date: 2016/4/20
 */

public class AdminLoginServiceImpl extends BaseServiceImpl<Declare> implements IAdminLoginService{

    @Resource(name = "adminDao")
    private IAdminLoginDao adminLoginDao;
    
    @Resource(name="smsService")
    private ISmsService smsService;
    
    @Resource(name="declareService")
    private IDeclareService declareService;

    @Override
    public void updateInfoMoney(Integer fdId, Double fdAmountLoanable, String fdAppointmentContact, String fdAppointmentInformation) 
    {
        if(fdId>0 && !fdAmountLoanable.equals("") && fdAmountLoanable!=null)
        {
            adminLoginDao.updateInfoMoney(fdId, fdAmountLoanable,fdAppointmentContact, fdAppointmentInformation);
            
            Declare declare=(Declare)declareService.get(Declare.class, fdId);
            String mobile=declare.getFdDeclarerPhone();
            if(mobile!=null&&!mobile.equals(""))
            {
            	 StringBuffer sb=new StringBuffer();
            	 String customerName=declare.getFdCustomerName();
                 sb.append("您提交的单据已经处理,请及时查看！(客户名称").append(customerName).append(",最大可贷金额").append(declare.getFdAmountLoanable()).append("万元,");
                 sb.append("预约联系人").append(fdAppointmentContact).append(",");
                 sb.append("联系方式 ").append(fdAppointmentInformation);
                 sb.append(")");
                 smsService.sendSms(mobile, sb.toString());
            }
        }
    }

    @Override
    public void updateAmount(Integer fdId, Double fdFillingAmount) {
        if (fdId > 0 && fdFillingAmount>0)
        {
            adminLoginDao.updateAmount(fdId, fdFillingAmount);
        }
    }
}
