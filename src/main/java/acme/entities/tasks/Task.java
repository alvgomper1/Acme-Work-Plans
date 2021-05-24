package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.lang.NonNull;

import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity{
	 
	protected static final long serialVersionUID = 1L;
	
	@NotBlank
	@Length(min = 2, max = 80)
	protected String title;
	

	@Temporal(TemporalType.TIMESTAMP)
	@NonNull
	protected Date				startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@NonNull
	protected Date				endDate;
	
	@NonNull
	@Min(0)
	@Digits(integer = 2, fraction = 2)
	protected Double workload;

	@NotBlank
	@Length(min = 8, max = 500)
	protected String description;
	
	@URL
	protected String optionalLink;
	
	@NonNull
	protected Visibility visibility;
	
	@NonNull
	protected Boolean finished;
	
	@NotNull
	@ManyToOne(optional=false)
	protected Manager manager;
	
	@Min(0)
	protected Long executionPeriod;
	
	public long calculateExecutionPeriod() {
		return (this.getEndDate().getTime() / 3600000) - (this.getStartDate().getTime() / 3600000);
	}

}
