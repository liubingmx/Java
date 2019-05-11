package com.bing.security.server.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

;

@Configuration
@Order(0)
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "order";


    @Autowired(required = false)
    UserDetailsService userDetailsService;

    @Autowired(required = false)
    AuthenticationManager authenticationManager;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                //允许客户端发送表单来进行权限认证获取令牌
                .allowFormAuthenticationForClients();
    }

    /**
     * ClientDetailsServiceConfigurer 能够使用内存或 JDBC 方式实现获取已注册的客户端详情，有几个重要的属性：
     *     clientId：客户端标识 ID
     *     secret：客户端安全码
     *     scope：客户端访问范围，默认为空则拥有全部范围
     *     authorizedGrantTypes：客户端使用的授权类型，默认为空
     *     authorities：客户端可使用的权限
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
        clients.withClientDetails(new JdbcClientDetailsService(dataSource));//memory or jdbc or other
//                .withClient("73fd74c4dfd3")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .secret("0bc2b785d173419ca4e0948c90896b06")
//                .authorizedGrantTypes("authorization_code","password")
//                .accessTokenValiditySeconds(3600)
//                //登录后绕过批准询问(/oauth/confirm_access)
//                .scopes("read")
//                .autoApprove(true);
    }

    @Autowired(required = false)
    private JdbcClientDetailsService jdbcClientDetailsService;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**
     * 配置授权服务器端点，如令牌存储，令牌自定义，用户批准和授权类型，不包括端点安全配置
     * AuthorizationServerEndpointsConfigurer  ，默认是支持除了密码授权外所有标准授权类型
     *它可配置以下属性：
     *      authenticationManager：认证管理器，当你选择了资源所有者密码（password）授权类型的时候，请设置这个属性注入一个 AuthenticationManager 对象
     *      userDetailsService：可定义自己的 UserDetailsService 接口实现
     *      authorizationCodeServices：用来设置收取码服务的（即 AuthorizationCodeServices 的实例对象），主要用于 "authorization_code" 授权码类型模式
     *      implicitGrantService：这个属性用于设置隐式授权模式，用来管理隐式授权模式的状态
     *      tokenGranter：完全自定义授权服务实现（TokenGranter 接口实现），只有当标准的四种授权模式已无法满足需求时
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain=new TokenEnhancerChain();

        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        //是否可以重用刷新令牌
        defaultTokenServices.setReuseRefreshToken(false);
        //是否支持刷新令牌
        defaultTokenServices.setSupportRefreshToken(false);
        //通过 JDBC 存储令牌
        defaultTokenServices.setTokenStore(jdbcTokenStore());
        defaultTokenServices.setClientDetailsService(jdbcClientDetailsService);
        //令牌失效时间
        defaultTokenServices.setAccessTokenValiditySeconds(3600);
        defaultTokenServices.setRefreshTokenValiditySeconds(0);
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        endpoints.authenticationManager(authenticationManager);
//        endpoints
//                .tokenStore(tokenStore())
////                .accessTokenConverter(jwtAccessTokenConverter())
//                .userDetailsService(userDetailsService);

        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .setClientDetailsService(jdbcClientDetailsService);
    }


    @Autowired
    DataSource dataSource;
    @Bean
    public TokenStore jdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
//        return new JwtTokenStore(jwtAccessTokenConverter());
    }
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("6030c73421c5");
//        return converter;
//    }




}
