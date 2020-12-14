package pl.kocjan.automatizer.adapter.connector;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import io.vavr.control.Either;
import io.vavr.control.Try;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.playbook.port.HostConnection;
import pl.kocjan.automatizer.domain.task.dto.TaskDto;
import pl.kocjan.automatizer.domain.task.dto.TaskResultDto;

public class SshHostConnection implements HostConnection {

	private final Logger logger = Logger.getLogger(SshHostConnection.class.getName());
	@Override
	public Either<Error, List<Either<Error, TaskResultDto>>> runOnRemoteHost(List<TaskDto> taskList, HostDto hostDto) {
	    
	    return configureJsch()
	    .flatMap(jsch -> establishSession(hostDto, jsch))
	    .map(session -> executeMultipleCommands(taskList, session));	           		
	}
	
	private Either<Error, JSch> configureJsch() {
		return Try.of(() -> {
			JSch jsch = new JSch();
			jsch.setKnownHosts(System.getenv("HOME") + "/.ssh/known_hosts");
			jsch.addIdentity(System.getenv("HOME") + "/.ssh/id_rsa");
			return jsch;
		}).toEither(ConnectionError.KNOWN_HOSTS_ERROR);
	}
	
	private Either<Error, Session> establishSession(HostDto hostDto, JSch jsch) {
		logger.log(Level.INFO, "Establishing session");
		return Try.of(() -> {
			Session session;
			session = jsch.getSession(hostDto.getUsername(), hostDto.getIp(), hostDto.getPort());
			session.setConfig("PreferredAuthentications", "publickey");
			session.setConfig("StrictHostKeyChecking", "yes");
			session.connect(500);
			return session;
		}).toEither(ConnectionError.HOST_CONNECTION_ERROR);
	}
	
	private List<Either<Error, TaskResultDto>> executeMultipleCommands(List<TaskDto> taskList, Session session) {
		List<Either<Error, TaskResultDto>> results = new ArrayList<>();
		for(TaskDto task : taskList) {
			results.add(executeCommand(task, session)
					.flatMap(channel -> getProcessInformation(channel))
					.flatMap(exitValue -> processCommandExecutionResult(task, exitValue)));
		}
		return results;
	}
	private Either<Error, ChannelExec> executeCommand(TaskDto taskDto, Session session) {
		logger.log(Level.INFO, "Executing command");
		return Try.of(() -> {
			ChannelExec channel;
			channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(taskDto.getCommand());		        
			channel.connect();
			return channel;
		}).toEither(ConnectionError.HOST_EXECUTION_ERROR);
	}
	
	private Either <Error, Integer> getProcessInformation(Channel channel) {
		logger.log(Level.INFO, "Processing info session");
		return Try.of(() -> {
	        InputStream is = channel.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        while((br.readLine()) != null) {
	        	
	        }
	        channel.disconnect();
	        return channel.getExitStatus();
	        }).toEither(ConnectionError.HOST_EXECUTION_ERROR);
	}
		
	
	private Either<Error, TaskResultDto> processCommandExecutionResult(TaskDto taskDto, Integer exitValue) {
		logger.log(Level.INFO, exitValue.toString());
		return exitValue == 0 ? 
				Either.right(TaskResultDto.
						builder()
						.exitValue(exitValue)
						.build()) : Either.left(ConnectionError.HOST_EXECUTION_ERROR);
	}	
	          		        		        


}
