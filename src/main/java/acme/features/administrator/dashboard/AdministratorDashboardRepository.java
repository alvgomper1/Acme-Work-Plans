/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(t) from Task t")
	Integer totalNumberOfTasks();
	
	@Query("select count(t) from Task t where t.visibility = 1")
	Integer totalNumberOfPublicTasks();
	
	
	@Query("select count(t) from Task t where t.finished= true")
	Integer totalNumberOfFinishedTasks();
	
	@Query("select avg(t.workload) from Task t")
	Double averageTaskWorkload();
	
	@Query("select stddev(t.workload) from Task t")
	Double deviationTaskWorkload();
	
	@Query("select min(t.workload) from Task t")
	Double minTaskWorkload();
	
	@Query("select max(t.workload) from Task t")
	Double maxTaskWorkload();
	
	@Query("select avg(t.executionPeriod) from Task t")
	Long averageTaskExecutionPeriod();
	
	@Query("select stddev(t.executionPeriod) from Task t")
	Long deviationTaskExecutionPeriod();
	
	@Query("select min(t.executionPeriod) from Task t")
	Long minTaskExecutionPeriod();
	
	@Query("select max(t.executionPeriod) from Task t")
	Long maxTaskExecutionPeriod();

}
