package com.ctoader.learn;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RequestTokenFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestTokenFilter.class);

    public String filterType() {
        return "pre";
    }

    public int filterOrder() {
        return 0;
    }

    public boolean shouldFilter() {
        return true;
    }

    public Object run() {
        String token = Long.toString(new Random(System.currentTimeMillis()).nextLong());

        RequestContext ctx =  RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("x-custom-token", token);

        log.info("Added token {} to request.", token);
        return null;
    }
}
