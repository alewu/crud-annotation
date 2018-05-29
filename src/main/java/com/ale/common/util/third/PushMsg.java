package com.ale.common.util.third;

import java.util.Map;

public class PushMsg {

	private String title;

	private String msg;

	private Map<String, String> map;


	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	@Override
	public String toString() {
		return "PushMsg [title=" + title + ", msg=" + msg + ", map=" + map + "]";
	}



	public Map<String, String> getMap() {
		return map;
	}



	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
}
