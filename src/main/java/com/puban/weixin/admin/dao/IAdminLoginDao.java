package com.puban.weixin.admin.dao;

import com.puban.framework.core.dao.IBaseDao;
import com.puban.weixin.declare.model.Declare;

/**
 * @author zengyong@puban.com
 * @ClassName: IAdminLoginDao
 * @Description:
 * @date: 2016/4/20
 */
public interface IAdminLoginDao extends IBaseDao<Declare> {
    /**
     * 更新可贷金额
     *
     * @param fdId             订单id
     * @param fdAmountLoanable 可贷金额
     */
    public void updateInfoMoney(Integer fdId, Double fdAmountLoanable, String fdAppointmentContact, String fdAppointmentInformation);

    /**
     * 更新成交金额
     * @param fdId 订单id
     * @param fdBorrowAmount 成交金额
     */
    public void updateAmount(Integer fdId, Double fdBorrowAmount);
}


