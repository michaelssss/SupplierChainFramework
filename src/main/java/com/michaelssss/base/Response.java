package com.michaelssss.base;

import lombok.Data;
import lombok.ToString;

/**
 * Sample: 比如返回一个List则
 *
 * <pre>
 *      Response body = new Response<List>();
 *      return body;
 * </pre>
 *
 * @param <T> 返回值的内容的泛型
 */
@Data
@ToString
public class Response<T> {

  private Status status;
  private T result;

  private Response() {
  }

  public static <T> Response<T> OK(T o) {
    Response response = new Response<>();
    response.status = Status.OK;
    response.result = o;
    return response;
  }

  public static Response<String> TokenValidateFailed() {
    Response<String> response = new Response<>();
    response.status = Status.TOKENNOTVALIDATE;
    response.result = "Token已经过期或者找不到，请重新登陆";
    return response;
  }

  public static <T> Response<T> NonOK(T o) {
    Response response = new Response<>();
    response.status = Status.ERROR;
    response.result = o;
    return response;
  }
}
