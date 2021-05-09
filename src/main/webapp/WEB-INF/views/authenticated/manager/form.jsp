

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:message code="authenticated.manager.form.message.confirm"/><br><br>
<acme:form>
 
	<acme:form-submit test="${command == 'create'}"
		code="authenticated.manager.form.button.create"
		action="/authenticated/manager/create" />  

	 


	<acme:form-return code="authenticated.manager.form.button.return" />
	
	

</acme:form>
