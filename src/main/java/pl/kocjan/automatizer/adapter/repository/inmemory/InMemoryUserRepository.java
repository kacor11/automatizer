package pl.kocjan.automatizer.adapter.repository.inmemory;

import java.util.Optional;

import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.user.port.UserRepository;

public class InMemoryUserRepository implements UserRepository {

	private final Map<String, UserDto> users;
	public InMemoryUserRepository() {
		this.users = new HashMap<>();
	}
	@Override
	public Optional<Error> findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Error> findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
