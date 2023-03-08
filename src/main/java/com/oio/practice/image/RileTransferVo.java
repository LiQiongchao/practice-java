package com.oio.practice.image;

import lombok.Data;

@Data
public class RileTransferVo {
	private String name;
	private String url;
	private String openUrl;
	/**
	 * 缩略图地址
	 */
	private String miniUrl;
	/**
	 * 缩略图互联网地址
	 */
	private String MiniOpenUrl;

	public RileTransferVo(String url, String name) {
		this.url = url;
		this.name = name;
	}
}