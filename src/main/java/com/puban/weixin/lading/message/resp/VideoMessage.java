package com.puban.weixin.lading.message.resp;

import com.puban.weixin.lading.message.resp.model.Video;

public class VideoMessage extends BaseMessage
{
	private Video Video;

	public Video getVideo()
	{
		return Video;
	}

	public void setVideo(Video video)
	{
		Video = video;
	}
	
}
