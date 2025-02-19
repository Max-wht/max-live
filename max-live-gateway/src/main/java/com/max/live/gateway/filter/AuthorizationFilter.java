package com.max.live.gateway.filter;

import com.max.inter.IUserRPCService;
import com.max.live.gateway.config.WhiteURLListProperties;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Max
 * @description
 * @date 2025/2/19 16:19
 */

@Component
public class AuthorizationFilter implements GlobalFilter , Ordered {
    @Resource
    private WhiteURLListProperties whiteURLListProperties;

    @DubboReference
    private IUserRPCService userRPCService;

    Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("进入全局过滤器");
        //获取请求的url
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getURI().getPath();
        //判断这个路径是否在白名单上
        for(String path : whiteURLListProperties.getWhiteUrlList()){
            if(url.startsWith(path)){
                log.info("该url存在于白名单，放行");
                return chain.filter(exchange);
            }
        }
        //如果不在白名单上，就需要获取token判断用户是否登录
        List<HttpCookie> httpCookies = request.getCookies().get("cokk");
        if(CollectionUtils.isEmpty(httpCookies)){
            log.error("该url不存在白名单，并且没有cookie，拦截");
            return Mono.empty();
        }
        //如果没有登录，拒绝请求
        String token = httpCookies.get(0).getValue();
        if(!StringUtils.hasText(token)){
            log.error("该用户没有登录过，拒绝请求");
            return Mono.empty();
        }
        //通过token验证用户是否登录
        Boolean isLogin = userRPCService.checkToken(token);
        //如果已经登录，将登录信息传递给下一个服务
        if(isLogin){
            log.info("用户已经登录，放行");
            //将token继续传递给下一个服务
            return chain.filter(exchange);
        }else {
            log.error("登录信息已经失效");
            return Mono.empty();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
