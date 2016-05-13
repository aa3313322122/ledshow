package com.puban.weixin.declare.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload
{
	private static final String ENCODING = "UTF-8";

	private static boolean checkFormat(List<String> allowformat, String oldName)
	{
		boolean fg = false;
		String format = oldName.substring(oldName.lastIndexOf(".") + 1);
		if (allowformat.contains(format.toLowerCase()))
		{
			fg = true;
		}
		return fg;
	}

	public static Map<String, String> upload(MultipartFile file, HttpServletRequest request, String inputName)
	{
		try
		{
			request.setCharacterEncoding(ENCODING);
		}
		catch (UnsupportedEncodingException e)
		{
			return null;
		}

		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String selfPath = "upload/photo/"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"/";
		String target = new StringBuffer(rootPath + selfPath).toString();
		Map<String, String> result = new HashMap<String, String>();

		List<String> allowformat = new ArrayList<String>();
		allowformat.add("jpg");
		allowformat.add("jpeg");
		allowformat.add("png");
		allowformat.add("gif");
		String filePath = "";
		if (file != null && !file.isEmpty())
		{
			String oldName = file.getOriginalFilename();
			if (!checkFormat(allowformat, oldName))
			{
				System.err.println("格式错误！");
			}
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + oldName.substring(oldName.lastIndexOf("."));
			
			File targetFile = new File(target, fileName);
			if (!targetFile.exists())
			{
				targetFile.mkdirs();
			}
			try
			{
				file.transferTo(targetFile);
				filePath = selfPath + fileName;
			}
			catch (IllegalStateException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		result.put("inputName", inputName);
		result.put("filePath", filePath);
		result.put("showPath", filePath);
		
		return result;
	}
}
