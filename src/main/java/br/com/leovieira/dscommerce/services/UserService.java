package br.com.leovieira.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.leovieira.dscommerce.dto.UserDTO;
import br.com.leovieira.dscommerce.entities.Role;
import br.com.leovieira.dscommerce.entities.User;
import br.com.leovieira.dscommerce.projections.UserDetailsProjection;
import br.com.leovieira.dscommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserDetailsProjection> userDetails = repo.searchUserAndRolesByEmail(username);
		
		if(userDetails.size() == 0) throw new UsernameNotFoundException("Usuário não encontrado");
		
		User user = new User();
		user.setEmail(userDetails.get(0).getUsername());
		user.setPassword(userDetails.get(0).getPassword());
		for(UserDetailsProjection u : userDetails) {
			user.addRole(new Role(u.getRoleId(),u.getAuthority()));
		}
		return user;
	}
	
	protected User authenticated() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");
			return repo.findByEmail(username).get();
		} catch (Exception e) {
			throw new UsernameNotFoundException("Email não encontrado!");
		}
	}
	
	@Transactional(readOnly = true)
	public UserDTO getMe() {
		User user = authenticated();
		
		return new UserDTO(user);
	}

}
