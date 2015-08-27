package com.liuliume.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {

	protected HttpSession httpSession;
	protected HttpServletRequest request ; 
	protected HttpServletResponse response ;
	
	public HttpSession getHttpSession() {
		return httpSession;
	}
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	protected void logSuccessMessage(String successMessage){
		request.setAttribute("successMessage", successMessage);
	}
	
	protected void logInfoMessage(String infoMessage){
		request.setAttribute("infoMessage", infoMessage);
	}
	
	protected void logWarningMessage(String warningMessage){
		request.setAttribute("warningMessage", warningMessage);
	}
	
	protected void logErrorMessage(String errorMessage){
		request.setAttribute("errorMessage", errorMessage);
	}
	
	protected String getResultMessage(List<Object> resultMessages){
		if(resultMessages == null || resultMessages.size() == 0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(Object resultMessage : resultMessages){
			sb.append(resultMessage.toString() + "<br/>");
		}
		return sb.toString();
	}
}
