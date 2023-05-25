package com.rookie.bigdata.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.UUID;

/**
 * @Class IDFilter
 * @Description IDFilter,用于在日志打印的时候每个线程有唯一的ID
 * @Author rookie
 * @Date 2023/5/25 17:12
 * @Version 1.0
 */
@Component
public class IDFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestId = ((HttpServletRequest) request).getHeader("X-Request-ID");

        if (request instanceof HttpServletRequest) {

            if (StringUtils.isEmpty(requestId)) {
                requestId = UUID.randomUUID().toString();
            }

            MDC.put("request-id", requestId);
        }

        if (response instanceof HttpServletResponse) {
            ((HttpServletResponse) response).setHeader("X-Request-ID", requestId);
        }


        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove("request-id");
        }

    }
}
