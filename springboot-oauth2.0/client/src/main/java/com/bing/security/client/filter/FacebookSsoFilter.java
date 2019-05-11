package com.bing.security.client.filter;


import com.bing.security.client.model.ClientResources;

public class FacebookSsoFilter extends SsoFilter{

    public FacebookSsoFilter(ClientResources client) {
        super(client, "/login/facebook");

    }

}
