package com.michaelssss.base;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.Collection;

/**
 * Sample:
 * 比如返回一个List则
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
    private Integer currentPage;
    private Integer total;

    private Response() {
    }

    public static Response<?> OK(Object o) {
        Response response = new Response<>();
        response.status = Status.OK;
        response.result = o;
        return response;
    }

    /**
     * 仅用于查询需要分页的业务数据使用
     *
     * @param collection 数据内容
     * @param page       分页情况
     * @return 结果
     */
    public static Response<?> OK(Collection<?> collection, Page page) {
        Response response = OK(collection);
        response.currentPage = page.getNumber();
        response.total = page.getTotalPages();
        return response;
    }

    public static Response<?> NonOK(Object o) {
        Response response = new Response<>();
        response.status = Status.ERROR;
        response.result = o;
        return response;
    }
}
