package com.front.util.thread;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.apache.log4j.Logger;

import com.front.controller.ControllerConstantes;

public class Singleton {
	Logger log=Logger.getLogger(Singleton.class);
	private static Singleton uniqueInstance;
	private static HttpSession session;
	
	private Singleton(){}

	public synchronized static Singleton getInstance(){
		if(uniqueInstance==null){
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}

	public String getAplicationName(HttpServletRequest request){
		return request.getSession(false)!=null ? (String)request.getSession(false).getAttribute(ControllerConstantes.APPLICATION_NAME):"";
	}

	public String getAlnovaClient(HttpServletRequest request){
		return (String)request.getSession(false).getAttribute(ControllerConstantes.ID_USUARIO);
	}

	public String getClientFullName(HttpServletRequest request){
		return (String)request.getSession(false).getAttribute(ControllerConstantes.NOMBRE_USUARIO);
	}
	
	public String getClientEmail(HttpServletRequest request){
		return (String)request.getSession(false).getAttribute(ControllerConstantes.EMAIL_USUARIO);
	}

	public String getGetId(HttpServletRequest request) {
//		return request.getSession(false)!=null ? request.getSession(false).getAttribute(ControllerConstantes.ID_USUARIO).toString():"";
		return "misael";
	}

	public String getGetId(HttpSessionEvent request) {
		return request.getSession().getAttribute(ControllerConstantes.ID_USUARIO).toString();
	}

	public static void setSession(HttpSession session) {
		Singleton.session = session;
	}

	public static HttpSession getSession() {
		return session;
	}

}
