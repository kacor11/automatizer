package pl.kocjan.automatizer.adapter.repository.entity;

import org.springframework.stereotype.Component;

import pl.kocjan.automatizer.domain.common.Role;
import pl.kocjan.automatizer.domain.user.dto.UserDto;

@Component
public class UserMapper {
	public UserDto entityToDto(UserEntity entity) {
		return UserDto.builder()
				.id(entity.getId())
				.username(entity.getUsername())
				.password(entity.getPassword())
				.email(entity.getEmail())
				.role(stringToRole(entity.getRole()))
				.created(entity.getCreated())
				.build();		
	}
	
	public UserEntity dtoToEntity(UserDto dto) {
		return UserEntity.builder()
				.id(dto.getId())
				.username(dto.getUsername())
				.password(dto.getPassword())
				.email(dto.getEmail())
				.role(roleToString(dto.getRole()))
				.created(dto.getCreated())
				.build();		
	}
	
	public String roleToString(Role role) {
		if(role == Role.ADMIN) return "ADMIN";
		if(role == Role.VERIFIED) return "VERIFIED";
		return "NOT_VERIFIED";
	}
	
	public Role stringToRole(String role) {
		if(role.equals("ADMIN")) return Role.ADMIN;
		if(role.equals("VERIFIED")) return Role.VERIFIED;
		return Role.NOT_VERIFIED;
	}
}
