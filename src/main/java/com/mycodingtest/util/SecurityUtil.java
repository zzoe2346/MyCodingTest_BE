package com.mycodingtest.util;

import com.mycodingtest.security.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
            throw new RuntimeException("유저 인증 정보가 없습니다.");
        }

        return userDetails.getUserId();
    }
}
