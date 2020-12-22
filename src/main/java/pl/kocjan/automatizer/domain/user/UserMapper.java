package pl.kocjan.automatizer.domain.user;

import pl.kocjan.automatizer.domain.user.dto.UserDto;

class UserMapper {
	UserDto userToDto(User user) {
		return UserDto.builder()
				.id(user.getId())
				.username(user.getUsername())
				.password(user.getPassword())
				.email(user.getEmail())
				.role(user.getRole())
				.created(user.getCreated())
				.build();		
	}
	
	User dtoToUser(UserDto dto) {
		return User.builder()
				.id(dto.getId())
				.username(dto.getUsername())
				.password(dto.getPassword())
				.email(dto.getEmail())
				.role(dto.getRole())
				.created(dto.getCreated())
				.build();		
	}
}
