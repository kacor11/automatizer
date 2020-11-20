package pl.kocjan.automatizer.host;

import pl.kocjan.automatizer.host.port.HostRepository;

class HostCreator {
	
	private final HostRepository hostRepository;
	
	HostCreator(HostRepository hostRepository) {
		this.hostRepository = hostRepository;
	}
}
