package com.bing.security.client.filter;

import com.bing.security.client.model.ClientResources;
import com.bing.security.client.utils.ApplicationContextHolder;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;

public class SsoFilter extends OAuth2ClientAuthenticationProcessingFilter{



    public SsoFilter(ClientResources client, String uri) {
        super(uri);
        OAuth2ClientContext auth2ClientContext = ApplicationContextHolder.getApplicationContext().getBean(OAuth2ClientContext.class);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), auth2ClientContext);
        this.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(
                client.getResource().getUserInfoUri(), client.getClient().getClientId());
        tokenServices.setRestTemplate(template);
        this.setTokenServices(tokenServices);
    }

}
