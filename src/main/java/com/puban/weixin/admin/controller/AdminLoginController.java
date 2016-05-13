package com.puban.weixin.admin.controller;

import com.puban.framework.core.controller.BaseController;
import com.puban.framework.core.page.PageView;
import com.puban.framework.core.page.QueryResult;
import com.puban.weixin.admin.service.IAdminLoginService;
import com.puban.weixin.declare.model.Declare;
import com.puban.weixin.declare.service.IDeclareService;
import com.puban.weixin.declare.service.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/login")
public class AdminLoginController extends BaseController
{
	@Resource(name = "orderService")
	private IOrderService orderService;

	@Resource(name = "declareService")
	private IDeclareService declareService;

	@Resource(name = "adminService")
	private IAdminLoginService adminLoginService;

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	@ResponseBody
	public String adminLogin(HttpServletRequest request, String account, String password)
	{
		if (account.trim().equals("admin"))
		{
			if (password.equals("puban@2016"))
			{
				HttpSession session = request.getSession();
				session.setAttribute("loginName", "admin");
				return "1";

			}
			else
			{
				return "-1";
			}
		}
		else if (account.trim().equals("zaf"))
		{
			if (password.equals("zaf123"))
			{
				HttpSession session = request.getSession();
				session.setAttribute("loginName", "zaf");
				return "1";

			}
			else
			{
				return "-1";
			}
		}
		else if (account.trim().equals("sz01"))
		{
			if (password.equals("123abc"))
			{
				HttpSession session = request.getSession();
				session.setAttribute("loginName", "sz01");
				return "1";

			}
			else
			{
				return "-1";
			}
		}
		else if (account.trim().equals("bj01"))
		{
			if (password.equals("456abc"))
			{
				HttpSession session = request.getSession();
				session.setAttribute("loginName", "bj01");
				return "1";

			}
			else
			{
				return "-1";
			}
		}
		else if (account.trim().equals("sh01"))
		{
			if (password.equals("789abc"))
			{
				HttpSession session = request.getSession();
				session.setAttribute("loginName", "sh01");
				return "1";

			}
			else
			{
				return "-1";
			}
		}
		else
		{
			return "-2";
		}
	}

	@RequestMapping(value = "/goIndex")
	public String goIndex(HttpServletRequest request, String channelCode, Integer status, Integer currentPage, Integer pageSize, Model model)
	{
		HttpSession session = request.getSession();
		if (session == null)
		{
			return "admin/login";
		}
		else
		{
			String userName = session.getAttribute("loginName") == null ? "" : (String) session.getAttribute("loginName");
			if (userName != null)
			{
				if (channelCode != null && !channelCode.equals(""))
				{
					model.addAttribute("channelCode", channelCode);
				}

				if ((status != null) &&(!(String.valueOf(status).equals(""))))
				{
					model.addAttribute("status", 1);
				}
				
				if(userName.toUpperCase().startsWith("ADMIN")||userName.toUpperCase().startsWith("ZAF"))
				{
					return this.goInfoPage(channelCode, status,"-1", currentPage, pageSize, model);	
				}
				else if(userName.toUpperCase().startsWith("SZ"))
				{
					return this.goInfoPage(channelCode, status,"SZ", currentPage, pageSize, model);	
				}
				else if(userName.toUpperCase().startsWith("BJ"))
				{
					return this.goInfoPage(channelCode, status,"BJ", currentPage, pageSize, model);	
				}
				else if(userName.toUpperCase().startsWith("SH"))
				{
					return this.goInfoPage(channelCode, status,"SH", currentPage, pageSize, model);	
				}
				else
				{
					return this.goInfoPage(channelCode, status,"-1", currentPage, pageSize, model);	
				}
				
			}
			else
			{
				return "admin/login";
			}
		}
	}

	@RequestMapping(value = "/view/{id}")
	public String view(HttpServletRequest request, @PathVariable(value = "id") Integer id, Model model)
	{
		HttpSession session = request.getSession();
		if (session == null)
		{
			return "admin/login";
		}
		else
		{
			Declare declare = (Declare) declareService.get(Declare.class, id);
			model.addAttribute("declare", declare);
			return "admin/view";
		}
	}
	@RequestMapping(value = "/viewOther/{id}")
	public String viewOther(HttpServletRequest request, @PathVariable(value = "id") Integer id, Model model)
	{
		HttpSession session = request.getSession();
		if (session == null)
		{
			return "admin/login";
		}
		else
		{
			Declare declare = (Declare) declareService.get(Declare.class, id);
			String imgPath=declare.getFdOtherFilePath();
			String[] imgPaths=null;
			if(imgPath!=null&&!imgPath.equals(""))
			{
				imgPaths=imgPath.split(";");
			}
			if(imgPaths!=null)
			{
				model.addAttribute("imgPaths", imgPaths);
			}
			return "admin/viewOther";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session != null)
		{
			session.invalidate();
		}
		return "admin/login";
	}

	@RequestMapping(value = "/queryInfo")
	public String queryInfo(HttpServletRequest request,String channelCode, Integer status, Integer currentPage, Integer pageSize, Model model)
	{
		HttpSession session = request.getSession();
		if (session == null)
		{
			return "admin/login";
		}
		else
		{
			String userName = session.getAttribute("loginName") == null ? "" : (String) session.getAttribute("loginName");
			if (userName != null)
			{
				if(userName.toUpperCase().startsWith("ADMIN")||userName.toUpperCase().startsWith("ZAF"))
				{
					return this.goInfoPage(channelCode, status,"-1", currentPage, pageSize, model);	
				}
				else if(userName.toUpperCase().startsWith("SZ"))
				{
					return this.goInfoPage(channelCode, status,"SZ", currentPage, pageSize, model);	
				}
				else if(userName.toUpperCase().startsWith("BJ"))
				{
					return this.goInfoPage(channelCode, status,"BJ", currentPage, pageSize, model);	
				}
				else if(userName.toUpperCase().startsWith("SH"))
				{
					return this.goInfoPage(channelCode, status,"SH", currentPage, pageSize, model);	
				}
				else
				{
					return this.goInfoPage(channelCode, status,"-1", currentPage, pageSize, model);	
				}
			}
			else
			{
				return "admin/login";
			}
		}
		
	}

	private String goInfoPage(String channelCode, Integer status,String areaFg, Integer currentPage, Integer pageSize, Model map)
	{
		if (currentPage == null)
		{
			currentPage = 1;
		}
		if (pageSize == null)
		{
			pageSize = 10;
		}

		PageView<Declare> pageView = new PageView<Declare>(pageSize, currentPage);
		int firstindex = (pageView.getCurrentpage() - 1) * pageView.getMaxresult();

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("fdId", "desc");

		StringBuffer whereHql = new StringBuffer();
		whereHql.append(" 1=1 ");
		List<Object> queryParams = new ArrayList<Object>();
		if ((channelCode != null) && (!channelCode.equals("")))
		{
			int index = queryParams.size() + 1;
			whereHql.append(" and user.channel.fdCode like :p" + index).append(" ");
			queryParams.add("%" + channelCode + "%");
		}

		if ((status == null) || (String.valueOf(status).equals("")))
		{
			status = 1;
		}

		if ((status != null) && (!String.valueOf(status).equals("")))
		{
			if (status > 0)
			{
				int index = queryParams.size() + 1;
				whereHql.append(" and fdStatus=:p" + index).append(" ");
				queryParams.add(status);
			}
		}
		
		if ((areaFg != null) && (!areaFg.equals("")))
		{
			if (!areaFg.equals("-1"))
			{
				int index = queryParams.size() + 1;
				whereHql.append(" and user.channel.fdCode like :p" + index).append(" ");
				queryParams.add("%" + areaFg + "%");
			}
		}

		if (pageSize == -1)
		{
			QueryResult<Declare> declareQr = declareService.query(Declare.class, -1, -1, whereHql.toString(), queryParams.toArray(), orderby);
			pageView.setQueryResult(declareQr);
		}
		else
		{
			QueryResult<Declare> declareQr = declareService.query(Declare.class, firstindex, pageView.getMaxresult(), whereHql.toString(), queryParams.toArray(), orderby);
			pageView.setQueryResult(declareQr);
		}
		map.addAttribute("pageSize", pageSize);
		map.addAttribute("pageView", pageView);
		map.addAttribute("status", status);
		return "admin/index";
	}

	/**
	 * 更改金额
	 * 
	 * @param fdId
	 * @param fdAmountLoanable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateMoney")
	public String query(Integer fdId, Double fdAmountLoanable,String fdAppointmentContact,String fdAppointmentInformation, Model model)
	{
		adminLoginService.updateInfoMoney(fdId, fdAmountLoanable,fdAppointmentContact,fdAppointmentInformation);
		return "redirect:/login/goIndex";
	}

	/**
	 * 成交金额
	 * @param id
	 * @param fdBorrowAmount
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateAmount")
	public String updateAmount(Integer id, Double fdFillingAmount, Model model) {
		adminLoginService.updateAmount(id, fdFillingAmount);
		return "redirect:/login/goIndex";
	}

}
