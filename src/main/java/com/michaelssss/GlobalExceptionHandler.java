package com.michaelssss;

import com.michaelssss.account.AuthorityException;
import com.michaelssss.base.Response;
import com.michaelssss.business.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {BusinessException.class, AuthorityException.class})
    public Response catchException(BusinessException e) {
        return Response.NonOK(e.getMessage());
    }
}
