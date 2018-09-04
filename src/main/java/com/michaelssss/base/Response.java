package com.michaelssss.base;

import lombok.Data;
import lombok.ToString;

/**
 * Sample:
 * 比如返回一个List则
 * <pre>
 *      Response body = new Response<List>();
 *      return body;
 * </pre>
 *
 * @param <T> 返回值的内容的泛型，其中考虑到都是内网操作，故而分页等功能由前端完成，后端不提供分页的支持
 */
@Data
@ToString
public class Response<T> {
    private Status status;
    private T result;

    private Response() {
    }

    public static Response<?> OK(Object o) {
        Response response = new Response<>();
        response.status = Status.OK;
        response.result = o;
        return response;
    }

    public static Response<?> NonOK(Object o) {
        Response response = new Response<>();
        response.status = Status.ERROR;
        response.result = o;
        return response;
    }
}
