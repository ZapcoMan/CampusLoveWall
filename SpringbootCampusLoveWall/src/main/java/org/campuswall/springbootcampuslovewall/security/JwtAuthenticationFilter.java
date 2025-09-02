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

/**
 * JWT身份验证过滤器
 * 用于验证请求中的JWT令牌，并设置Spring Security的认证信息
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private List<AccountService<?, ?>> accountServices;

    @Resource
    private Map<String, AccountConvertStrategy<?>> strategyMap;

    private final Map<String, AccountService<?, ?>> serviceMap = new HashMap<>();

    // 白名单路径列表，这些路径不需要JWT验证
    private static final List<String> WHITE_LIST = Arrays.asList(
            "/auth/login", "/auth/register", "/auth/refresh-token",
            "/files/upload", "/files/download", "/favicon.ico", "/web/hello"
    );

    /**
     * 初始化角色服务映射
     * 在Spring容器初始化完成后，将所有AccountService按角色代码存入serviceMap中
     */
    @jakarta.annotation.PostConstruct
    public void initMap() {
        for (AccountService<?, ?> service : accountServices) {
            serviceMap.put(service.getRole().getCode(), service);
            log.info("初始化角色服务: {}", service.getRole().getCode());
        }
    }

    /**
     * 过滤请求并验证JWT令牌
     *
     * @param request  HTTP请求对象
     * @param response HTTP响应对象
     * @param filterChain 过滤器链
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
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
        log.info("JwtAuthenticationFilter 获取 的 token: {}", token);
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

    /**
     * 使用指定策略将领域对象转换为Account对象
     *
     * @param domain 领域对象
     * @param strategy 转换策略
     * @param <T> 领域对象类型
     * @return Account对象
     */
    @SuppressWarnings("unchecked")
    private <T> Account convertDomainWithStrategy(Object domain, AccountConvertStrategy<T> strategy) {
        return strategy.convertToAccount((T) domain);
    }

    /**
     * 判断是否需要续签令牌
     *
     * @param token JWT令牌
     * @param thresholdMs 续签阈值（毫秒）
     * @return 是否需要续签
     */
    private boolean shouldRenewToken(String token, long thresholdMs) {
        try {
            Date expiresAt = JWT.decode(token).getExpiresAt();
            return expiresAt != null && (expiresAt.getTime() - System.currentTimeMillis() < thresholdMs);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 创建新的JWT令牌
     *
     * @param audience 令牌受众信息（用户ID-角色）
     * @param secret 签名密钥
     * @param hours 有效期（小时）
     * @return 新的JWT令牌
     */
    private String createToken(String audience, String secret, int hours) {
        return JWT.create()
                .withAudience(audience)
                .withExpiresAt(DateUtil.offsetHour(new Date(), hours))
                .sign(Algorithm.HMAC256(secret));
    }
}
