/*
 * EmployerJobDeleteService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.words.Word;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorWordDeleteService implements AbstractDeleteService<Administrator, Word> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorWordRepository repository;

	@Override
	public boolean authorise(final Request<Word> request) {
		assert request != null;

		return true;
	}

	

	@Override
	public void unbind(final Request<Word> request, final Word entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"value");
		
	}
	@Override
	public Word findOne(final Request<Word> request) {
		assert request != null;

		Word result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneWordById(id);
		return result;
	}

	@Override
	public void validate(final Request<Word> request, final Word entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Word> request, final Word entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}




}
