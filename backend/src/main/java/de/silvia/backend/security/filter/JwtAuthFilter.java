package de.silvia.backend.security.filter;

import de.silvia.backend.security.services.UserService;
import de.silvia.backend.services.JWTUtils;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private static final Log LOG = LogFactory.getLog(JwtAuthFilter.class);

    final JWTUtils jwtUtils;
    final UserService uServ;

    public JwtAuthFilter(JWTUtils jwtUtils, UserService uServ) {
        this.jwtUtils = jwtUtils;
        this.uServ = uServ;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token = getToken(request);
        if (token != null) try {
            final String username = jwtUtils.extractUserName(token);
            if ((username != null) && SecurityContextHolder.getContext().getAuthentication() == null) {
                //User aus Datenbank laden
                final UserDetails userDetails = uServ.loadUserByUsername(username);
                if (jwtUtils.validateToken(token, userDetails.getUsername())) {
                    //Spring informieren, dass die Anmeldung korrekt ist
                    final UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null,
                                    userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            LOG.warn("Error while parsing token", e);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String autHeader = request.getHeader("Authorization");
        if (autHeader == null) {
            return null;
        } else {
            return autHeader.replace("Bearer", "").trim();
        }
    }
}
