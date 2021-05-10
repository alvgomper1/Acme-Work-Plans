/*
 * AuthenticatedManagerCreateService.java

 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.manager;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedManagerCreateService implements AbstractCreateService<Authenticated, Manager> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedManagerRepository repository;

	// AbstractCreateService<Authenticated, Manager> ---------------------------


	 
	@Override
	public boolean authorise(final Request<Manager> request) {
		assert request != null;
	final Collection<Integer> res = this.repository.findAllManagers().stream().map(x->x.getUserAccount().getId()).collect(Collectors.toList());
		if (res.contains(request.getPrincipal().getAccountId())) {
			return false;
		}
		
		else {
				return true;
		}
	
	}

	@Override
	public void validate(final Request<Manager> request, final Manager entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void bind(final Request<Manager> request, final Manager entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Manager> request, final Manager entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	@Override
	public Manager instantiate(final Request<Manager> request) {
		assert request != null;

		Manager result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Manager();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void create(final Request<Manager> request, final Manager entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Manager> request, final Response<Manager> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
