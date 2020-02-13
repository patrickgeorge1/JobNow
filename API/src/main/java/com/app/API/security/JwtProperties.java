package com.app.API.security;

public class JwtProperties {
    public static final String SECRET = "cheie";
    public static final int EXPIRATION_TIME = 864000000;  // 10 zile
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
