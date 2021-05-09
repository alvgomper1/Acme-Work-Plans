package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository {

	@Query("select t from Task t where t.finished = true and t.visibility = 1 order By t.workload asc")
	Collection<Task> findEndedPublicTasks();

	@Query("select t from Task t")
	Collection<Task> findMany();
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskFromId(int id);
}
