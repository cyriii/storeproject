package com.cyriii.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ContextUtils {

	public static ServletContext getApplication() {
		return RequestContextHolder.getRequestAttributes() == null ? null
				: ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession()
						.getServletContext();
	}

	public static HttpSession getHttpSession() {
		return RequestContextHolder.getRequestAttributes() == null ? null
				: ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}

	public static HttpServletRequest getHttpRequest() {
		return RequestContextHolder.getRequestAttributes() == null ? null
				: ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}


	public static String getToken() {
		String token = getParamFromHeader(getHttpRequest(), "token");
		if (null == token || "".equals(token)||"undefined".equals(token)) {
			token = getHttpRequest().getParameter("token");
			if (null == token || "".equals(token)||"undefined".equals(token)) {
				return null;
			}
		}
		return token;
	}

	public static String getTokenEmptyIfNotExits() {
		String token = getRequestParameter(getHttpRequest(), "token");
		if (StringUtils.isBlank(token)) {
			token = "";
		}
		return token;
	}

	public static String getSign() {
		String sign = getRequestParameter(getHttpRequest(), "sign");
		if (sign.isEmpty()) {
			sign = "";
		}
		return sign.toUpperCase();
	}

	public static String getRequestParameter(HttpServletRequest request, String paramName) {
		if (request == null) {
			return "";
		}
		String token = getParamFromHeader(request, paramName);
		if (StringUtils.isBlank(token)) {
//			token = getTokenFromCookie(request, paramName);
		}
		if (StringUtils.isBlank(token)) {
			token = request.getParameter(paramName);
		}
		return token;
	}

	public static String getServer() {
		return "http://" + getHttpRequest().getServerName() + ":" + getHttpRequest().getServerPort()
				+ getHttpRequest().getContextPath() + "/";
	}

	public static String getServerPath() {
		return "http://" + getHttpRequest().getServerName() + ":" + getHttpRequest().getServerPort();
	}

	public static String getTokenFromCookie(HttpServletRequest request, String paramName) {
		try {
			Cookie[] cookies = request.getCookies();
			if (cookies == null || cookies.length == 0) {
				return "";
			}
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(paramName)) {
					return cookie.getValue();
				}
			}
		} catch (Exception exception) {
		}
		return "";
	}

	private static String getParamFromHeader(HttpServletRequest request, String paramName) {
		return request.getHeader(paramName);
	}

	public static String generateUrlCode(String pattern, RequestMethod method) {
		String code = pattern.replaceAll("/", "_").toUpperCase();
		return method.name() + code;
	}
}