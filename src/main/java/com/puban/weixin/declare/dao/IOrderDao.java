package com.puban.weixin.declare.dao;

import com.puban.framework.core.dao.IBaseDao;
import com.puban.weixin.declare.model.Declare;

/**
 * @author zengyong@puban.com
 * @ClassName: IOrderDao
 * @Description: 订单
 * @date: 2016/3/24
 */

public interface IOrderDao extends IBaseDao<Declare>
{
    /**
     * @author zengyong@puban.com
     * @Description: 更新审批状态
     * @param id
     *        申报单id
     * @param start
     *        申报单状态
     */
    public String updateOrderStatus(String applyId,Integer status);
}
