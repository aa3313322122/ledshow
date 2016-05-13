package com.puban.weixin.lading.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.puban.framework.core.controller.BaseController;
import com.puban.weixin.lading.service.IWeiXinCoreService;
import com.puban.weixin.lading.util.SignUtil;

@Controller
@RequestMapping(value="/weixin")
public class WeiXinCoreContrlloer extends BaseController
{
	@Resource(name="weiXinCoreService")
	private IWeiXinCoreService weiXinCoreService;
	
	@RequestMapping(value="/core",method=RequestMethod.GET)
	public void check(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String signature = request.getParameter("signature");

		String timestamp = request.getParameter("timestamp");

		String nonce = request.getParameter("nonce");

		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		try
		{
			if (SignUtil.checkSignature(signature, timestamp, nonce))
			{
				out.print(echostr);
				System.out.println("success");
			}
		}
		catch (Exception e)
		{
			System.out.println("error");
			e.printStackTrace();

		}
		finally
		{
			out.close();
			out = null;
		}
	}
	
	@RequestMapping(value="/core",method=RequestMethod.POST)
	public void coreServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String signature = request.getParameter("signature");

		String timestamp = request.getParameter("timestamp");

		String nonce = request.getParameter("nonce");

		String echostr = request.getParameter("echostr");
		System.out.println("echostr:"+echostr);
		
		PrintWriter out = response.getWriter();
		try
		{
			if (SignUtil.checkSignature(signature, timestamp, nonce))
			{
				String respXml = weiXinCoreService.processRequest(request);
				out.print(respXml);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			out.close();
			out = null;
		}
	}

}
