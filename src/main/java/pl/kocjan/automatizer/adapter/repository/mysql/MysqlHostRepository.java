package pl.kocjan.automatizer.adapter.repository.mysql;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import pl.kocjan.automatizer.adapter.repository.entity.HostEntity;
import pl.kocjan.automatizer.adapter.repository.entity.HostMapper;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.host.port.HostRepository;

@Repository
@AllArgsConstructor
public class MysqlHostRepository implements HostRepository {

	SessionFactory session;
	HostMapper mapper;
	
	@Override
	@Transactional
	public String saveHost(HostDto host) {
		try {
			session.getCurrentSession().merge(mapper.dtoToHostEntity(host));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return host.getIp();
	}

	@Override
	@Transactional
	public Optional<HostDto> findHostByIp(String ip) {
			Session currentSession = session.getCurrentSession();
			@SuppressWarnings("unchecked")
			Optional<HostEntity> hostEntity = currentSession
					.createQuery("from HostEntity e where e.ip = :ip")
					.setParameter("ip", ip)
					.uniqueResultOptional();
			return hostEntity.map(entity -> mapper.hostEntityToDto(entity));
	}

	@Override
	@Transactional
	public List<HostDto> findHostsByGroup(String group) {
		Session currentSession = session.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<HostEntity> hostList = currentSession
				.createQuery("from HostEntity he join he.hostGroups g where g = :group")
				.setParameter("group", group)
				.list();
		return hostList.stream()
				.map(entity -> mapper.hostEntityToDto(entity))
				.collect(Collectors.toList());
	}
}
