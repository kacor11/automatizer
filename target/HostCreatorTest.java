package pl.kocjan.automatizer.host;

import org.junit.Before;
import org.junit.Test;

import pl.kocjan.automatizer.adapter.repository.inmemory.InMemoryHostRepository;
import pl.kocjan.automatizer.domain.host.port.HostRepository;

public class HostCreatorTest {
	
	private HostRepository hostRepository = new InMemoryHostRepository();
	
	@Before
	public void resetHostList() {
		hostRepository = new InMemoryHostRepository();
	}
	@Test
	public void shouldCreateHost() {
		//when
		HostCreator hostCreator = new HostCreator();
	}
}
