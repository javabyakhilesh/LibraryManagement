package com.example.jwt;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		        //get the header
		final String authheader = request.getHeader("Authorization");
		final String jwtToken;
		final String username;
		
		         //check if Authorization header is present and starts with ""Bearer"
		if(authheader==null || !authheader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		     //Extract the JWT Token
		jwtToken = authheader.substring(7);
		username = jwtService.extractUsername(jwtToken);
		
		     //check if we have username and no authentication exist yet
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		       // Get the userDetails from the database
			var userDetails = userRepository.findByUsername(username).orElseThrow(()-> new
					RuntimeException("User Not Found"));
			
			if(jwtService.isTokenValid(jwtToken, userDetails)) {
				
				//create the authentication with user roles
				List<SimpleGrantedAuthority>  authorities = userDetails.getRoles().stream().map(
						SimpleGrantedAuthority::new).collect(Collectors.toList());
						                                    
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,null, authorities);
				
				 //set the authentication 
				 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				 
				 //update security context with authentication
				 SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
