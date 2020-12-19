package pl.kocjan.automatizer.adapter.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@Getter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="PlaybookTask") 
public class TaskEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	String taskName;
	String taskCommand;
	LocalDateTime executionDate;
	String executionResult;
}
