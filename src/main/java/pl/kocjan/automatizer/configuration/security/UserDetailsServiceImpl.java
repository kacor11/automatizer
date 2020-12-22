package pl.kocjan.automatizer.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.adapter.repository.entity.UserMapper;
import pl.kocjan.automatizer.domain.user.dto.UserDto;
import pl.kocjan.automatizer.domain.user.port.UserRepository;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	private final PasswordEncoder encoder;
	private  UserRepository userRepository;
	private  UserMapper userMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.map(this::getUserDetails)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	private UserDetails getUserDetails(UserDto user) {
		 org.springframework.security.core.userdetails.User.UserBuilder builder
         = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
		 builder.password(encoder.encode(user.getPassword()));
		 builder.roles(userMapper.roleToString(user.getRole()));
		 return builder.build();
	}
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
}
