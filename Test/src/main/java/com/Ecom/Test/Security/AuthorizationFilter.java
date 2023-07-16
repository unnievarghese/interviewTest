package com.Ecom.Test.Security;

import com.Ecom.Test.Model.User;
import com.Ecom.Test.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;

    public AuthorizationFilter(AuthenticationManager authManager, UserRepository userRepository) {
        super(authManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(SecurityConstants.HEADER_STRING);
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)){
            chain.doFilter(req,res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req,res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);

        if (token != null) {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");

            String clientJwt = Jwts.parser()
                    .setSigningKey(SecurityConstants.tokenSecret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            if (clientJwt != null) {
                User user = userRepository.getUserByEmail(clientJwt);
                UserPrincipal userPrincipal =new UserPrincipal(user);
                return  new UsernamePasswordAuthenticationToken(clientJwt, null,
                        userPrincipal.getAuthorities());
            }
            return null;
        }
        return null;
    }
}
