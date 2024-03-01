package com.microservice.apigateway.filter;

import com.microservice.apigateway.exception.NotFoundException;
import com.microservice.apigateway.jwtUtil.JWTServiceUtil;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;
    private final JWTServiceUtil jwtServiceUtil;


    public AuthenticationFilter(RouteValidator validator, JWTServiceUtil jwtServiceUtil) {
        super(Config.class);
        this.validator = validator;
        this.jwtServiceUtil = jwtServiceUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(validator.isSecured.test(exchange.getRequest())){
                //header contains token or not
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){

                    throw new NotFoundException("Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if(authHeader!=null && authHeader.startsWith("Bearer")){
                    authHeader = authHeader.substring(7);
                }

                try {
                    jwtServiceUtil.validateToken(authHeader);
                }catch (Exception e){
                    throw new RuntimeException("Validation Error");
                }
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config{

    }
}
