package com.michaelssss;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {BusinessException.class})
    public Response catchException(BusinessException e) {
        return Response.NonOK(e.getMessage());
    }
}
