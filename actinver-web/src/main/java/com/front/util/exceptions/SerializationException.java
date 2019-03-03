package com.front.util.exceptions;

public class SerializationException extends Exception
{
  private static final long serialVersionUID = 1925040568284208782L;

  public SerializationException(String message, Throwable e)
  {
    super(message, e);
  }

  public SerializationException(String message) {
    super(message);
  }

  public SerializationException(Throwable e) {
    super(e);
  }
}