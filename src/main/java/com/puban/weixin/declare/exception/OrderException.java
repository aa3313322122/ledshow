package com.puban.weixin.declare.exception;

import com.puban.framework.core.exception.BaseException;
import com.puban.framework.core.exception.CommonStateEnum;

/**
 * @author zengyong@puban.com
 * @ClassName: OrderException
 * @Description: 订单异常
 * @date: 2016/3/24
 */

public class OrderException extends BaseException {
    private static final long serialVersionUID = 1L;

    public OrderException(CommonStateEnum type)
    {
        super(type);
    }
}
