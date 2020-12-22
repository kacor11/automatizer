package pl.kocjan.automatizer.configuration.security;

public final class SecurityConstants {

	    public static final String AUTH_LOGIN_URL = "/api/authenticate";
	    public static final String JWT_SECRET = "0dHCzYQ=]S/Go{8vq{HXo*x%Xu#eFxk47e[c3@CR/$ul$;{Rt?/;GFeZBC^3V++\n";
	    public static final String TOKEN_HEADER = "Authorization";
	    public static final String TOKEN_PREFIX = "Bearer ";
	    public static final String TOKEN_TYPE = "JWT";
	    public static final String TOKEN_ISSUER = "secure-api";
	    public static final String TOKEN_AUDIENCE = "secure-app";
	    public static final int TOKEN_EXPIRATION_TIME = 60000;
	    
	    private SecurityConstants() {
	        throw new IllegalStateException("Cannot create instance of static util class");
	    }



}
