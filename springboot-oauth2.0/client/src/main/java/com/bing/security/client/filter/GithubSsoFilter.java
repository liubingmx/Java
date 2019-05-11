package com.bing.security.client.filter;

import com.bing.security.client.model.ClientResources;

public class GithubSsoFilter extends SsoFilter{

    public GithubSsoFilter(ClientResources client) {
        super(client, "/login/github");
    }

}
