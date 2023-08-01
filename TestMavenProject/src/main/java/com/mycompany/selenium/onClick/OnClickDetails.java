package com.mycompany.selenium.onClick;

public class OnClickDetails {
	private String onClickTag;

	private String onClickResult;

	private Integer level;

	private String textTag;

	private String[] keyWords;
	
	private String keywordsType;

	private Double webPageLoadTime;

	public OnClickDetails(String onClickTag, String onClickResult, Integer level, String textTag, String[] keyWords, String keywordsType,
			Double webPageLoadTime) {
		super();
		this.onClickTag = onClickTag;
		this.onClickResult = onClickResult;
		this.level = level;
		this.textTag = textTag;
		this.keyWords = keyWords;
		this.keywordsType = keywordsType;
		this.webPageLoadTime = webPageLoadTime;
	}

	public OnClickDetails() {
	}

	public String getOnClickTag() {
		return onClickTag;
	}

	public void setOnClickTag(String onClickTag) {
		this.onClickTag = onClickTag;
	}

	public String getOnClickResult() {
		return onClickResult;
	}

	public void setOnClickResult(String onClickResult) {
		this.onClickResult = onClickResult;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getTextTag() {
		return textTag;
	}

	public void setTextTag(String textTag) {
		this.textTag = textTag;
	}

	public String[] getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String[] keyWords) {
		this.keyWords = keyWords;
	}

	public Double getWebPageLoadTime() {
		return webPageLoadTime;
	}

	public void setWebPageLoadTime(Double webPageLoadTime) {
		this.webPageLoadTime = webPageLoadTime;
	}

	public String getKeywordsType() {
		return keywordsType;
	}

	public void setKeywordsType(String keywordsType) {
		this.keywordsType = keywordsType;
	}

}
