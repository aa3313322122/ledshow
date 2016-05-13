package com.puban.weixin.lading.message.resp;

import com.puban.weixin.lading.message.resp.model.Music;

public class MusicMessage extends BaseMessage
{
	private Music Music;

	public Music getMusic()
	{
		return Music;
	}

	public void setMusic(Music music)
	{
		Music = music;
	}
}
