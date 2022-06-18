package com.example.assm_sof3021.filters;

import com.example.assm_sof3021.modal.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/user/addcart?*","/admin/*" })

public class AuthenticationFilter implements Filter {
    public AuthenticationFilter() {
    }

    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();
        Users user = (Users) session.getAttribute("userdn");

        if (user == null) {
            session.setAttribute("error", "Bạn cần đăng nhập mới có thể vào trang");
            httpRes.sendRedirect("/user/home");
            return;
        }

        chain.doFilter(request, response);
    }
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
