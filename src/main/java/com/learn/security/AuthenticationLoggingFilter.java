package com.learn.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class AuthenticationLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // here we are sure that we have a request id
        String requestId = request.getHeader("Request-Id");
        long start = Long.parseLong(request.getHeader("START"));
        log.info("Successfully authenticated request with id {}, time = {}ms", requestId,
                (System.currentTimeMillis() - start));
        filterChain.doFilter(servletRequest, servletResponse);


    }
}
