package com.test.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig
{
    /**
     * 配置了一个id为route-name的路由规则
     * 当访问地址 http://localhost:9527/game时会自动转发到地址： http://news.baidu.com/game
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder)
    {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //http://news.baidu.com/game
        routes.route("path_route_test", r -> r.path("/game").uri("http://news.baidu.com/game"))
                .build();

        return routes.build();
    }

    @Bean
    public RouteLocator customerRouteLocator2(RouteLocatorBuilder routeLocatorBuilder)
    {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //http://news.baidu.com/game
        routes.route("path_route_test", r -> r.path("/guoji").uri("http://news.baidu.com/guoji"))
                .build();

        return routes.build();
    }
}
