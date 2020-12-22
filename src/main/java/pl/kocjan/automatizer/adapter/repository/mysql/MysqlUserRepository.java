package pl.kocjan.automatizer.adapter.repository.mysql;

import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import pl.kocjan.automatizer.adapter.repository.entity.UserEntity;
import pl.kocjan.automatizer.adapter.repository.entity.UserMapper;
import pl.kocjan.automatizer.domain.user.dto.UserDto;
import pl.kocjan.automatizer.domain.user.port.UserRepository;

@Repository
@AllArgsConstructor
public class MysqlUserRepository implements UserRepository {
	
	SessionFactory session;
	UserMapper mapper;
	
	@Override
	@Transactional
	public Optional<UserDto> findByEmail(String email) {
		Session currentSession = session.getCurrentSession();
		@SuppressWarnings("unchecked")
		Optional<UserEntity> userEntity = currentSession
				.createQuery("from UserEntity e where e.email = :email")
				.setParameter("email", email)
				.uniqueResultOptional();
		return userEntity.map(entity -> mapper.entityToDto(entity));
	}

	@Override
	@Transactional
	public Optional<UserDto> findByUsername(String username) {
		Session currentSession = session.getCurrentSession();
		@SuppressWarnings("unchecked")
		Optional<UserEntity> userEntity = currentSession
				.createQuery("from UserEntity e where e.username = :username")
				.setParameter("username", username)
				.uniqueResultOptional();
		return userEntity.map(entity -> mapper.entityToDto(entity));
	}

	@Override
	@Transactional
	public String saveUser(UserDto userDto) {
		try {
			session.getCurrentSession().merge(mapper.dtoToEntity(userDto));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userDto.getUsername();
	}

}
