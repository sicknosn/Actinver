package com.front.beans;

public class LoginTO {
  private static final long serialVersionUID = 1L;
  private String idAlnova;
  private String customerName;
  private String mail;
  private String tokenLogin;
  private String lastAccessDate;
  private boolean authenticated;
  private boolean hasPayRoll;
  private Object cookie;
  private boolean preactivated;
  private int valueTracking;
  private String trackingNumber;
  
public String getIdAlnova() {
	return idAlnova;
}
public void setIdAlnova(String idAlnova) {
	this.idAlnova = idAlnova;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public String getTokenLogin() {
	return tokenLogin;
}
public void setTokenLogin(String tokenLogin) {
	this.tokenLogin = tokenLogin;
}
public String getLastAccessDate() {
	return lastAccessDate;
}
public void setLastAccessDate(String lastAccessDate) {
	this.lastAccessDate = lastAccessDate;
}
public boolean isAuthenticated() {
	return authenticated;
}
public void setAuthenticated(boolean authenticated) {
	this.authenticated = authenticated;
}
public boolean isHasPayRoll() {
	return hasPayRoll;
}
public void setHasPayRoll(boolean hasPayRoll) {
	this.hasPayRoll = hasPayRoll;
}
public Object getCookie() {
	return cookie;
}
public void setCookie(Object cookie) {
	this.cookie = cookie;
}
public boolean isPreactivated() {
	return preactivated;
}
public void setPreactivated(boolean preactivated) {
	this.preactivated = preactivated;
}
public int getValueTracking() {
	return valueTracking;
}
public void setValueTracking(int valueTracking) {
	this.valueTracking = valueTracking;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public String getTrackingNumber() {
	return trackingNumber;
}
public void setTrackingNumber(String trackingNumber) {
	this.trackingNumber = trackingNumber;
}

  
}