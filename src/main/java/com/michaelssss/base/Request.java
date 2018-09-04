package com.michaelssss.base;

import lombok.Data;

/**
 * 仅用于查询需要分页的业务数据使用
 *
 * @param <T>
 */
@Data
public class Request<T> {
    //当前页数
    private int currentPage;
    //请求单页数据量大小
    private int size;
    //请求参数
    private T payLoad;
}
