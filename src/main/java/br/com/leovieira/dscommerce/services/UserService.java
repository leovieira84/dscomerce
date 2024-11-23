package br.com.leovieira.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

}
