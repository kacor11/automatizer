package pl.kocjan.automatizer.adapter.repository.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class HostEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String ip;
	private String username;
	private int hostPort;
	private boolean isAuthorized;
	@OneToMany
	private List<TaskEntity> executedTasks;
	@ElementCollection
	private Set<String> hostGroups;
}
