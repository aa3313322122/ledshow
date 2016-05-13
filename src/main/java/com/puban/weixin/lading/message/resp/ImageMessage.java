package com.puban.weixin.lading.message.resp;

import com.puban.weixin.lading.message.resp.model.Image;

public class ImageMessage extends BaseMessage
{
	private Image Image;

	public Image getImage()
	{
		return Image;
	}

	public void setImage(Image image)
	{
		Image = image;
	}
}
