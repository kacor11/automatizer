package pl.kocjan.automatizer.host;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import pl.kocjan.automatizer.adapter.repository.inmemory.InMemoryHostRepository;
import pl.kocjan.automatizer.domain.host.HostFacade;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.host.port.HostRepository;

public class HostReaderTest {
	private HostRepository hostRepository = new InMemoryHostRepository();
	private HostFacade hostFacade = new HostFacade(hostRepository);
	
	@Test
	public void findsHostByIp() {
		//given 
		String correctIp = "host1";
		
		//when
		Optional<HostDto> result = hostFacade.readHost(correctIp);
		
		//then
		assertTrue(result.isPresent());
		assertEquals(correctIp, result.get().getIp());
	}
	
	
	@Test
	public void findsHostSByGroup() {
		//given 
		String correctIp = "host1";
		String correctIp2 = "host2";
		String groupName = "DEFAULT";
		
		//when
		List<HostDto> result = hostFacade.getHostsWithGroup(groupName);
		
		//then
		assertEquals(2, result.size());
		assertEquals(correctIp, result.get(0).getIp());
		assertEquals(correctIp2, result.get(1).getIp());
		
	}
}
