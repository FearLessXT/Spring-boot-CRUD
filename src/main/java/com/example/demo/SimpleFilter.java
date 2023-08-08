package com.example.demo;

import lombok.Builder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;


@Component
public class SimpleFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        System.out.println("Remote Host: " + request.getRemoteHost());
        System.out.println("Remote Address: " + request.getRemoteAddr());
    }

    @Override
    public boolean isLoggable(LogRecord logRecord) {
        return false;
    }
}
