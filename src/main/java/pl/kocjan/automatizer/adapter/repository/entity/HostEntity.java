package pl.kocjan.automatizer.adapter.repository.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

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
	@Type(type = "numeric_boolean")
	private Boolean isAuthorized;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TaskEntity> executedTasks;
	@ElementCollection
	private Set<String> hostGroups;
}
