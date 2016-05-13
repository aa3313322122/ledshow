package com.puban.weixin.about.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.puban.framework.core.controller.BaseController;

@Controller
@RequestMapping(value="/weixin")
public class AboutController extends BaseController
{
	@RequestMapping(value="/about")
	public String about()
	{
		return "about";
	}
}
