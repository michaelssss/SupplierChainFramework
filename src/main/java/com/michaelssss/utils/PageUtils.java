package com.michaelssss.utils;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * 本类用于Controller分页功能 本类不可继承，不可实例化
 *
 * @author Michaelssss
 * @date 2018年10月11日
 */
public final class PageUtils {

  private PageUtils() {
  }

  public static Pageable getPageableFromRequest(HttpServletRequest request) {
    String pn = request.getHeader("pageNum") != null ? request.getHeader("pageNum") : "1";
    String ps = request.getHeader("pageSize") != null ? request.getHeader("pageSize") : "20";
    int pageNum = Integer.valueOf(pn);
    int pageSize = Integer.valueOf(ps);
    return PageRequest.of(pageNum - 1, pageSize);
  }

  public static Page<?> getPageFromPageable(List<?> list, Pageable pageable) {
    Long from = pageable.getOffset();
    Long end =
        from + pageable.getPageSize() > list.size() ? list.size() : from + pageable.getPageSize();
    return new PageImpl<>(list.subList(from.intValue(), end.intValue()), pageable, list.size());
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
