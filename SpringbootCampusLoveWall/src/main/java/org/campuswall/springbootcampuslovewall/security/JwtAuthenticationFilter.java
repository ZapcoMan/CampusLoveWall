package org.campuswall.springbootcampuslovewall.security;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.campuswall.springbootcampuslovewall.auth.service.AccountService;
import org.campuswall.springbootcampuslovewall.auth.strategy.AccountConvertStrategy;
import org.campuswall.springbootcampuslovewall.common.exception.CustomerException;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private List<AccountService<?, ?>> accountServices;

    @Resource
    private Map<String, AccountConvertStrategy<?>> strategyMap;

    private final Map<String, AccountService<?, ?>> serviceMap = new HashMap<>();

    private static final List<String> WHITE_LIST = Arrays.asList(
            "/auth/login", "/auth/register", "/auth/refresh-token",
            "/files/upload", "/files/download", "/favicon.ico", "/web/hello"
    );

    @jakarta.annotation.PostConstruct
    public void initMap() {
        for (AccountService<?, ?> service : accountServices) {
            serviceMap.put(service.getRole().getCode(), service);
            log.info("初始化角色服务: {}", service.getRole().getCode());
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();
        for (String white : WHITE_LIST) {
            if (uri.startsWith(white)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) token = request.getParameter("token");
        if (StrUtil.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String[] split = JWT.decode(token).getAudience().get(0).split("-");
            Integer userId = Integer.parseInt(split[0]);
            String role = split[1];

            AccountService<Object, Integer> service = (AccountService<Object, Integer>) serviceMap.get(role);
            if (service == null) throw new CustomerException("非法角色");

            Object domain = service.selectById(userId);
            if (domain == null) throw new CustomerException("用户不存在");

            AccountConvertStrategy<?> strategy = strategyMap.get(role);
            if (strategy == null) throw new CustomerException("未找到转换策略");

            Account account = convertDomainWithStrategy(domain, strategy);

            // 验证 token
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(account.getPassword())).build();
            verifier.verify(token);

            // 设置Spring Security认证信息
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(account.getUsername(), null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authToken);

            // 自动续签
            if (shouldRenewToken(token, 30 * 60 * 1000)) {
                String newToken = createToken(account.getId() + "-" + account.getRole(),
                        account.getPassword(), 24);
                response.setHeader("Renew-Token", newToken);
            }

        } catch (CustomerException e) {
            response.setStatus(401);
            response.getWriter().write(e.getMessage());
            return;
        } catch (Exception e) {
            log.error("JWT验证失败", e);
        }

        filterChain.doFilter(request, response);
    }

    @SuppressWarnings("unchecked")
    private <T> Account convertDomainWithStrategy(Object domain, AccountConvertStrategy<T> strategy) {
        return strategy.convertToAccount((T) domain);
    }

    private boolean shouldRenewToken(String token, long thresholdMs) {
        try {
            Date expiresAt = JWT.decode(token).getExpiresAt();
            return expiresAt != null && (expiresAt.getTime() - System.currentTimeMillis() < thresholdMs);
        } catch (Exception e) {
            return false;
        }
    }

    private String createToken(String audience, String secret, int hours) {
        return JWT.create()
                .withAudience(audience)
                .withExpiresAt(DateUtil.offsetHour(new Date(), hours))
                .sign(Algorithm.HMAC256(secret));
    }
}
