package com.front.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class CustomLogException
{
  static Logger log = Logger.getLogger(CustomLogException.class);

  public static void stackTraceToString(Throwable e) { StringBuilder sb = new StringBuilder();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss,SSS");
    Date date = new Date();
    int aleatorio = (int)(Math.random() * 1000000000.0D);
    for (StackTraceElement element : e.getStackTrace()) {
      sb.append(format.format(date) + " LOGID-[" + aleatorio + "]\tat " + element.toString());
      sb.append("\n");
    }
    Throwable exTemp = e;
    while (exTemp.getCause() != null) {
      exTemp = exTemp.getCause();
      sb.append(format.format(date) + " LOGID-[" + aleatorio + "]  Caused by " + exTemp.toString() + "\n");
      for (StackTraceElement element : exTemp.getStackTrace()) {
        sb.append(format.format(date) + " LOGID-[" + aleatorio + "]\tat " + element.toString());
        sb.append("\n");
      }
    }
    log.info(e.toString() + "\n\t" + sb.toString()); }

}