package com.anirban.languagederivation.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * The ResponseDTO contains Map of language and score match along with the total
 * number of unique words from the user input. If there are any exception, the
 * ResponseDTO will hold it.*
 * 
 * @author Anirban Adak
 * @version 1.0
 * @since 2016-07-21
 */
public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = -5064045575831264075L;

	private Map<String, Integer> languageScore;
	private int totalCount;
	private String exceptionMessage;

	public Map<String, Integer> getLanguageScore() {
		return languageScore;
	}

	public void setLanguageScore(Map<String, Integer> languageScore) {
		this.languageScore = languageScore;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
