package com.front.beans;

public class AuthenticateTO {

	  private String userName;
	  private String fullName;
	  private AccesoTO.StatusAccess status;
	  private String minutes;
	  private byte[] captcha;

	  public String getUserName()
	  {
	    return this.userName;
	  }
	  public void setUserName(String userName) {
	    this.userName = userName;
	  }
	  public String getFullName() {
	    return this.fullName;
	  }
	  public void setFullName(String fullName) {
	    this.fullName = fullName;
	  }
	  public AccesoTO.StatusAccess getStatus() {
	    return this.status;
	  }
	  public void setStatus(AccesoTO.StatusAccess status) {
	    this.status = status;
	  }
	  public String getMinutes() {
	    return this.minutes;
	  }
	  public void setMinutes(String minutes) {
	    this.minutes = minutes;
	  }
	  public byte[] getCaptcha() {
	    return this.captcha;
	  }
	  public void setCaptcha(byte[] captcha) {
	    this.captcha = captcha;
	  }
}
