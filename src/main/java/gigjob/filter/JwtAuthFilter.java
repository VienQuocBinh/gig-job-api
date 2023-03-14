package gigjob.filter;

import gigjob.service.impl.JwtService;
import gigjob.service.impl.UserInfoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoUserDetailsService userDetailsService;

    /**
     * Verify the JWT has valid User information
     * and set the User information to the SecurityContext
     *
     * @param request     {@link HttpServletRequest}
     * @param response    {@link HttpServletResponse}
     * @param filterChain {@link FilterChain}
     * @throws ServletException - Not satisfy filter conditions chains
     * @throws IOException      - Invalid JWT credentials
     * @author Vien Binh
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.replace("Bearer", "");
            // Subject of JWT is set as Email
            email = jwtService.extractSubject(token);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Get UserDetails from the UserInfoUserDetailsService - implementation of the UserDetailsService
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Set the User information to the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
