package pl.kocjan.automatizer.adapter.repository.inmemory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import pl.kocjan.automatizer.domain.user.dto.UserDto;
import pl.kocjan.automatizer.domain.user.port.UserRepository;

public class InMemoryUserRepository implements UserRepository {

	private final Map<String, UserDto> users;
	public InMemoryUserRepository() {
		this.users = new HashMap<>();
	}
	@Override
	public Optional<UserDto> findByEmail(String email) {
		return users.entrySet()
				.stream()
				.filter(e -> e.getValue().getEmail().contains(email))
				.map(e -> e.getValue())
				.findFirst();
	}

	@Override
	public Optional<UserDto> findByUsername(String username) {
		return Optional.ofNullable(users.get(username));
	}
	@Override
	public String saveUser(UserDto userDto) {
		String username = userDto.getUsername();
		users.put(username, userDto);
		return username;
	}

}
