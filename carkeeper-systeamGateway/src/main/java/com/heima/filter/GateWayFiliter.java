package com.heima.filter;


import com.heima.utils.AppJwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@Order(0)
public class GateWayFiliter implements GlobalFilter {


    @Override
    //验证是否是登录方法 是放行 不是拦截并检验token
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest ();
        ServerHttpResponse response = exchange.getResponse ();
        String path = request.getURI ().getPath ();
        if(path.contains ("/member/login")){
            return chain.filter (exchange);
        }
        //获得令牌是否为空 为空返回异常
        String token = request.getHeaders ().getFirst ("token");
        if(token==null){
            response.setStatusCode (HttpStatus.UNAUTHORIZED);
            return response.setComplete ();
        }

        //解析令牌信息是否正确
        try {

            Jws<Claims> claimsJws = AppJwtUtil.getJws (token);
            Claims claimsBody =claimsJws.getBody ();
            if (AppJwtUtil.verifyToken (claimsBody) > 0) {
                response.setStatusCode (HttpStatus.UNAUTHORIZED);
                return response.setComplete ();
            }
            Integer id = claimsBody.get ("id", Integer.class);
            log.info ("head token id:{},url:{}", id, request.getURI ().getPath ());
            ServerHttpRequest httpRequest = request.mutate ().headers (httpHeaders -> {
                httpHeaders.add ("userId", String.valueOf (id));
            }).build ();
        }catch (Exception exception){
            exception.printStackTrace ();
            response.setStatusCode (HttpStatus.UNAUTHORIZED);
            return response.setComplete ();
        }
        return chain.filter (exchange);
    }
}
