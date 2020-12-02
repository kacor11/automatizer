package pl.kocjan.automatizer.domain.host;

import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;

@AllArgsConstructor
public class HostFacade {
	private final HostCreator creator;
	
	public Either<Error, Success> createHost(CreateHostDto dto) {
		return creator.createHost(dto);
	}
}
