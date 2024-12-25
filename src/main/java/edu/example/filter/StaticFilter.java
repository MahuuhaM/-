package edu.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class StaticFilter implements Filter {
    private List<String> white;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        white = new ArrayList<>();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String requestURI = req.getRequestURI();
        String indexPage = new StringBuffer().append(req.getContextPath()).append("/").toString();
        if (!requestURI.contains(".jsp") && !indexPage.equals(requestURI)) {
            filterChain.doFilter(req, resp);
        } else {
            req.setAttribute("reMessage", "不能直接访问jsp页面");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
