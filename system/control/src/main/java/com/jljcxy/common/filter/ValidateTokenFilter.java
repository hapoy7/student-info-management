package com.jljcxy.common.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.jljcxy.common.config.Result;
import com.jljcxy.manage.sys.common.LoginVO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @summary【验证令牌】过滤器
 * @author 高振中
 * @date 2021-02-01 20:20:20
 **/
@Slf4j
@Component
public class ValidateTokenFilter extends OncePerRequestFilter {
	private final Cache<Long, LoginVO> userCache;// 用于存放用户登录信息的guava缓存
	private final ObjectMapper mapper;
	private static final String MESSAGE01 = "token为空,请重新登录!";
	private static final String MESSAGE02 = "token过期,请重新登录!";
	private static final String MESSAGE03 = "token失效,请重新登录!";
	private static final int CODE = 201;

	public ValidateTokenFilter(Cache<Long, LoginVO> userCache, ObjectMapper mapper) {
		this.userCache = userCache;
		this.mapper = mapper;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String path = request.getServletPath();
		String token = request.getHeader("token");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "application/json");
		if (PathTools.isSkip(path)) {
			filterChain.doFilter(request, response);
			return;
		}
		if (!StringUtils.hasLength(token)) {
			log.error(MESSAGE01 + "token={}", token);
			response.sendRedirect("/");
			response.getWriter().write(writeResult(CODE, MESSAGE01));
			return;
		}
		Claims claims = null;
		try {
			claims = JwtTools.parseToken(token);
		} catch (Exception e) {
			response.getWriter().write(writeResult(CODE, MESSAGE02));
			return;
		}

		Boolean expired = JwtTools.isTokenExpired(claims);
		if (expired) {
			log.error(MESSAGE02);
			response.getWriter().write(writeResult(CODE, MESSAGE02));
			return;
		}
		Long userId = JwtTools.getUserId(token);
		request.setAttribute("userId", userId);
		LoginVO login = userCache.getIfPresent(userId);

		if (login == null || login.getUser() == null) {
			log.error(MESSAGE03);
			response.getWriter().write(writeResult(CODE, MESSAGE03));
			return;
		}
		filterChain.doFilter(request, response);
	}

	private String writeResult(int code, String msg) throws JsonProcessingException {
		return new String(mapper.writeValueAsBytes(Result.error(code, msg)));
	}
}
