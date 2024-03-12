package com.codegym.case_study_team_2_study.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutHandlerImpl implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // Đảm bảo phiên đăng nhập hiện tại
        if (authentication != null && authentication.isAuthenticated()) {
            // Xóa thông tin xác thực
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            // Xóa phiên đăng nhập
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            // Xóa cookie (nếu cần)
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        // Chuyển hướng đến trang sau khi logout thành công
        try {
            response.sendRedirect("/account/logoutSuccessful");
        } catch (IOException e) {
            // Xử lý ngoại lệ nếu không thể chuyển hướng
            e.printStackTrace();
        }
    }
    }

