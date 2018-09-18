package com.michaelssss.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public final class PageUtils {
    private PageUtils() {
    }

    public static Pageable getPageableFromRequest(HttpServletRequest request) {
        String pn = request.getHeader("pageNum") != null ? request.getHeader("pageNum") : "1";
        String ps = request.getHeader("pageSize") != null ? request.getHeader("pageSize") : "20";
        int pageNum = Integer.valueOf(pn);
        int pageSize = Integer.valueOf(ps);
        return new PageRequest(pageNum - 1, pageSize);
    }

    public static Page<?> getPageFromPageable(List<?> list, Pageable pageable) {
        int from = pageable.getOffset();
        int end = from + pageable.getPageSize() > list.size() ? list.size() : from + pageable.getPageSize();
        return new PageImpl<>(list.subList(from, end), pageable, list.size());
    }

    public static void writeResponsePageHeader(Page page, HttpServletResponse response) {
        int pageNum = page.getNumber() + 1;
        int pageSize = page.getSize();
        long total = page.getTotalElements();
        int totalPage = page.getTotalPages();
        response.setHeader("pageNum", Integer.toString(pageNum));
        response.setHeader("pageSize", Integer.toString(pageSize));
        response.setHeader("totalElement", Long.toString(total));
        response.setHeader("totalPage", Integer.toString(totalPage));
    }
}
