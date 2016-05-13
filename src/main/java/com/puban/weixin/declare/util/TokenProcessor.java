package com.puban.weixin.declare.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TokenProcessor
{
	private static String TOKEN_KEY = "com.puban.token";

	private long previous;

	private static TokenProcessor instance = new TokenProcessor();

	public static TokenProcessor getInstance()
	{
		return instance;
	}

	public synchronized boolean isTokenValid(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);

		if (session == null)
		{
			return false;
		}
		String saved = (String) session.getAttribute(TOKEN_KEY);
		if (saved == null)
		{
			return false;
		}

		resetToken(request);

		String token = request.getParameter(TOKEN_KEY);
		if (token == null)
		{
			return false;
		}
		return saved.equals(token);
	}

	public synchronized void resetToken(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);

		if (session == null)
		{
			return;
		}
		session.removeAttribute(TOKEN_KEY);
	}

	public synchronized void saveToken(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String token = generateToken(request);
		if (token != null)
		{
			session.setAttribute(TOKEN_KEY, token);
		}
	}

	public synchronized String generateToken(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		try
		{
			byte id[] = session.getId().getBytes();
			long current = System.currentTimeMillis();
			if (current == previous)
			{
				current++;
			}
			previous = current;
			byte now[] = new Long(current).toString().getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(id);
			md.update(now);
			return toHex(md.digest());
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return null;
		}

	}

	private String toHex(byte[] buffer)
	{
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int t = 0; t < buffer.length; t++)
		{
			sb.append(Character.forDigit((buffer[t] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[t] & 0x0f, 16));
		}
		return sb.toString();
	}

	public synchronized String getToken(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if (null == session)
		{
			return null;
		}
		String token = (String) session.getAttribute(TOKEN_KEY);
		if (null == token)
		{
			token = generateToken(request);
			if (token != null)
			{
				session.setAttribute(TOKEN_KEY, token);
				return token;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return token;
		}
	}
}
