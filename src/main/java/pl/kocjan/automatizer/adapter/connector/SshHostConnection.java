package pl.kocjan.automatizer.adapter.connector;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import io.vavr.control.Either;
import io.vavr.control.Try;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.playbook.port.HostConnection;
import pl.kocjan.automatizer.domain.task.dto.TaskDto;
import pl.kocjan.automatizer.domain.task.dto.TaskResultDto;

public class SshHostConnection implements HostConnection {

	@Override
	public Either<Error, TaskResultDto> runOnRemoteHost(TaskDto taskDto) {
	    
	    return configureJsch()
	    .flatMap((jsch -> establishSession(taskDto, jsch)))
	    .flatMap((session -> executeCommand(taskDto, session)))
	    .flatMap(channel -> processCommandExecutionResult(taskDto, channel));
	           		
	}
	
	private Either<Error, TaskResultDto> processCommandExecutionResult(TaskDto taskDto, ChannelExec channel) {
		channel.disconnect();
		int exitValue = channel.getExitStatus();
		return exitValue == 0 ? 
				Either.right(TaskResultDto.
						builder()
						.uuid(taskDto.getUuid())
						.exitValue(exitValue)
						.build()) : Either.left(ConnectionError.HOST_EXECUTION_ERROR);
	}	
	          		        		        
	private Either<Error, ChannelExec> executeCommand(TaskDto taskDto, Session session) {
		return Try.of(() -> {
			ChannelExec channel;
			channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(taskDto.getCommand());		        
			channel.connect();
			return channel;
		}).toEither(ConnectionError.HOST_EXECUTION_ERROR);
	}
	private Either<Error, Session> establishSession(TaskDto taskDto, JSch jsch) {
		return Try.of(() -> {
			Session session;
			session = jsch.getSession(taskDto.getHostName(), taskDto.getHostIp(), taskDto.getHostPort());
			session.setConfig("PreferredAuthentications", "publickey");
			session.setConfig("StrictHostKeyChecking", "yes");
			session.connect(1000);
			return session;
		}).toEither(ConnectionError.HOST_CONNECTION_ERROR);
	}
	private Either<Error, JSch> configureJsch() {
		return Try.of(() -> {
			JSch jsch = new JSch();
			jsch.setKnownHosts(System.getenv("HOME") + "/.ssh/known_hosts");
			jsch.addIdentity(System.getenv("HOME") + "/.ssh/id_rsa");
			return jsch;
		}).toEither(ConnectionError.KNOWN_HOSTS_ERROR);

	}

}
