package com.liuliume.portal.common;

/**
 * Ajax 请求返回数据封装
 * 
 * @author xiayun
 *
 */
public class JData {

	protected boolean success = false;
	protected String detail = "";
	protected Object data = null;
	protected int code = 0;

	public JData() {
		super();
	}

	public JData(String detail, Boolean success) {
		this.detail = detail;
		this.success = success;
	}

	public JData(String detail) {
		this(detail, true);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "JData [success=" + success + ", detail=" + detail + ", data="
				+ data + ", code=" + code + "]";
	}

	public static final class JError extends JData{
		public JError(String msg){
			this.detail = msg;
			this.data = null;
			this.success = false;
			this.code=500;
		}
	}
	public static final class JSuccess extends JData{
		public JSuccess(String msg,Object data){
			this.detail = msg;
			this.data = data;
			this.success = true;
			this.code=200;
		}
		public JSuccess(String msg){
			this.detail = msg;
			this.data = null;
			this.success = true;
			this.code=500;
		}
	}
}
