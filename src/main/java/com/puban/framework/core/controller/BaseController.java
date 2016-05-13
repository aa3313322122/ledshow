package com.puban.framework.core.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController
{
	
	protected  static Logger log = Logger.getLogger(BaseController.class);
	
	/** ajax输出，返回null ***/
	public String ajax(String content, String type, HttpServletResponse response)
	{
		try
		{
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		}
		catch (IOException e)
		{
			log.error("ajax", e);
		}
		return null;
	}

	/** AJAX输出HTML，返回null **/
	public String ajaxHtml(String html, HttpServletResponse response)
	{
		return ajax(html, "text/html", response);
	}

	/** AJAX输出json，返回null **/
	public String ajaxJson(String json, HttpServletResponse response)
	{
		return ajax(json, "application/json", response);
	}

}
