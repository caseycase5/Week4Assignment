package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import business.MyTimerService;
import business.OrdersBusinessInterface;

@ManagedBean
@ViewScoped

public class FormController {
	
	@Inject
	OrdersBusinessInterface services;
	
	@EJB
	MyTimerService timer;
	
	public String onSubmit() {
		
		// Gets user values from the form
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		// Puts the user object into the Post request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		// Start a time when login is clicked
		timer.setTimer(10000);
		
		// Prints a message to the console telling which business service is selected
		services.test();
		
		// Takes to the next page listed
		return "TestResponse.xhtml";

		
	}
	
	public String onFlash() {
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", user);
		
		return "TestResponse2.xhtml?faces-redirect=true";
		
	}
	
	public String onBack() {
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", user);
		return "TestForm.xhtml";
	}
	
	public OrdersBusinessInterface getService() {
		return services;
		
	}

}
