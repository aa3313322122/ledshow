package com.puban.weixin.lading.message.event;

public class QRCodeEvent extends BaseEvent
{
	private String EventKey;
	
	private String Ticker;

	public String getEventKey()
	{
		return EventKey;
	}

	public void setEventKey(String eventKey)
	{
		EventKey = eventKey;
	}

	public String getTicker()
	{
		return Ticker;
	}

	public void setTicker(String ticker)
	{
		Ticker = ticker;
	}
}
