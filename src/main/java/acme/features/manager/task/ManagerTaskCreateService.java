package acme.features.manager.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamComponent;
import acme.components.UtilComponent;
import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.tasks.Visibility;
import acme.entities.words.Word;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.features.administrator.word.AdministratorWordRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task>{
		
		// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagerTaskRepository repository;
		
		@Autowired
		protected AdministratorWordRepository wordSpamRepository;
		
		@Autowired
		protected AdministratorSpamRepository spamRepository;

		// AbstractCreateService<Manager, Task> interface --------------

		@Override
		public boolean authorise(final Request<Task> request) {
			assert request != null;

			return true;
		}

		@Override
		public void bind(final Request<Task> request, final Task entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors);
		}

		@Override
		public void unbind(final Request<Task> request, final Task entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "title", "startDate", "endDate", "workload", "description", "optionalLink", "visibility", "executionPeriod");
		}

		@Override
		public Task instantiate(final Request<Task> request) {
			assert request != null;

			Task result;
			Date moment;
			final Date moment2;
			Date today;
			today= new Date();
			
			moment= UtilComponent.addFecha(today,Calendar.MINUTE , 5);
			moment2= UtilComponent.addFecha(moment,Calendar.HOUR, 2);
			
			result = new Task();
			result.setTitle("Example task 00");
			result.setStartDate(moment);
			result.setEndDate(moment2);
			result.setWorkload(1.0);
			result.setDescription("This is the oldest announcement in the system.");
			result.setOptionalLink("http://www.sample-app.com");
			result.setVisibility(Visibility.PUBLIC);
			result.setExecutionPeriod(12l);
			result.setFinished(true);
			
			final Manager manager= this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
			result.setManager(manager);

			return result;
		}

		@Override
		public void validate(final Request<Task> request, final Task entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			
		final Double threshold = this.spamRepository.findUniqueSpamModule().getThreshold();
		final List<Word> spamWords= this.wordSpamRepository.findMany();
		final Date today= new Date();
		
		
		 if (!errors.hasErrors("title")) {
	            errors.state(request,!SpamComponent.containSpam(entity.getTitle(),spamWords,threshold) , "title", "manager.task.form.title.error.spam");
	        }
	     if (!errors.hasErrors("description")) {
	            errors.state(request,!SpamComponent.containSpam(entity.getDescription(),spamWords,threshold) , "description", "manager.task.form.description.error.spam");
	        }
	     if (!errors.hasErrors("optionalLink")) {
	            errors.state(request,!SpamComponent.containSpam(entity.getOptionalLink(),spamWords,threshold) , "optionalLink", "manager.task.form.optionalLink.error.spam");
	        }
	     if (!errors.hasErrors("startDate")) {
	            errors.state(request,entity.getStartDate().compareTo(today)>0 , "startDate", "manager.task.form.startDate.error");
	        }
	     if (!errors.hasErrors("endDate")) {
	            errors.state(request,entity.getEndDate().compareTo(today)>0 , "endDate", "manager.task.form.endDatePast.error");
	        }
	     if (!errors.hasErrors("endDate")) {
	            errors.state(request,entity.getStartDate().before(entity.getEndDate()) , "endDate", "manager.task.form.endDate.error");
	        }
	     
	     if (!errors.hasErrors("workload")) {
	            errors.state(request,entity.getWorkload()<=entity.calculateExecutionPeriod() , "workload", "manager.task.form.workload.error");
	        }
	     
	     
	     
		}

		@Override
		public void create(final Request<Task> request, final Task entity) {
			assert request != null;
			assert entity != null;
			
			final Manager manager= this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
			entity.setManager(manager);
			
			final Boolean finished = false;
			entity.setFinished(finished);

			this.repository.save(entity);
		}

}
