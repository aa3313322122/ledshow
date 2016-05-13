package com.puban.weixin.lading.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignUtil
{
	private static String token = WeiXinApiUtil.TOKEN;

	public static boolean checkSignature(String signature, String timestamp, String nonce)
	{
		String[] paramArr = new String[] { token, timestamp, nonce };
		Arrays.sort(paramArr);
		
		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
		String ciphertext = null;
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			
			byte[] digest = md.digest(content.toString().getBytes());
			ciphertext = byteToStr(digest);
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}

		if(ciphertext!=null)
		{
			if((ciphertext.toUpperCase()).equals(signature.toUpperCase()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	private static String byteToStr(byte[] byteArray)
	{
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++)
		{
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	private static String byteToHexStr(byte mByte)
	{
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
}
