package pl.kocjan.automatizer.domain.user.dto;

import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;

@RequiredArgsConstructor
public enum UserError implements Error {
	
	USERNAME_ALREADY_EXISTS("There is already user with this username"),
	EMAIL_ALREADY_EXISTS("This e-mail is already in use"),
	DATABASE_ERROR("Error during user persistance");
	
	private final String error;
	
	@Override
	public String getError() {
		return this.error;
	}

}
