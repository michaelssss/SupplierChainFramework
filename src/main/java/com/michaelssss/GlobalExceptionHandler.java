package com.michaelssss;

import com.michaelssss.base.Response;
import com.michaelssss.business.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {BusinessException.class})
  @ResponseBody
  public Response catchException(BusinessException e) {
    return Response.NonOK(e.getMessage());
  }

  @ExceptionHandler(value = {Exception.class})
  @ResponseBody
  public Response catchGlobalException(Exception e) {
    return Response.NonOK(e.getMessage());
  }
}
