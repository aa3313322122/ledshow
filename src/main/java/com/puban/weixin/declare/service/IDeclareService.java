package com.puban.weixin.declare.service;

import javax.servlet.http.HttpServletRequest;

import com.puban.framework.core.service.IBaseService;
import com.puban.weixin.declare.exception.DeclareException;
import com.puban.weixin.declare.model.Declare;

public interface IDeclareService extends IBaseService<Declare>
{
	public boolean validChannel(String userId, Integer channelId) throws DeclareException;

	public boolean validUserStatus(String userId) throws DeclareException;

	public boolean validReSubmit(HttpServletRequest request) throws DeclareException;

	public boolean validCode(String userId, String phone, String fdValidateCode) throws DeclareException;

	public String addDeclare(Declare declare, String userId, HttpServletRequest request) throws DeclareException;

	public String sendValidCode(HttpServletRequest request, String fdDeclarerPhone, String userId) throws DeclareException;
	
	public String queryFromPuTaiTong(Declare declare)throws DeclareException;
}
