package com.puban.weixin.declare.service;

import java.util.Map;

import com.puban.framework.core.page.PageView;
import com.puban.framework.core.service.IBaseService;
import com.puban.weixin.declare.exception.OrderException;
import com.puban.weixin.declare.model.Declare;

/**
 * @author zengyong@puban.com
 * @ClassName: IOrderService
 * @Description: 订单业务层
 * @date: 2016/3/24
 */
public interface IOrderService extends IBaseService<Declare>
{

	/**
	 * @author zengyong@puban.com
	 * @Description: 用户订单分页查询
	 * @param fdOpenId
	 *            微信号
	 * @param fdCustomerName
	 *            客户名称
	 * @param currentpage
	 *            当前页数
	 * @return
	 * @throws OrderException
	 */
	public PageView<Declare> queryIndexOrder(Integer userId, String fdCustomerName, String currentpage) throws OrderException;


	public Map<String, Integer> findOrderCount(Integer userId)throws OrderException;


	public PageView<Declare> queryOrderByStatus(Integer userId, Integer fdStatus, String currentpage) throws OrderException;


	/**
	 * @author zengyong@puban.com
	 * @Description: 更新审批状态
	 * @param appid
	 *          申报单id
	 * @param fdStatus
	 *          申报单状态
	 * @throws OrderException
	 */
    public String updateOrderByStatus(String applyId,Integer fdStatus) throws OrderException;
	
}
