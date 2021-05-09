<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox 	code="anonymous.task.form.label.title" path="title" readonly="true"/>
	<acme:form-moment 	code="anonymous.task.form.label.startDate" path="startDate" readonly="true"/>
	<acme:form-moment 	code="anonymous.task.form.label.endDate" path="endDate" readonly="true"/>
	<acme:form-integer 	code="anonymous.task.form.label.workload" path="workload" readonly="true"/>
	<acme:form-textarea code="anonymous.task.form.label.description" path="description" readonly="true"/>
	<acme:form-url 		code="anonymous.task.form.label.optionalLink" path="optionalLink" readonly="true"/>
	<acme:form-textbox 	code="anonymous.task.form.label.visibility" path="visibility" readonly="true"/>
	<acme:form-textbox 	code="anonymous.task.form.label.executionPeriod" path="executionPeriod" readonly="true"/>
	<acme:form-textbox 	code="anonymous.task.form.label.finished" path="finished" readonly="true"/>
	
	
	<acme:form-return code="anonymous.task.form.button.return"/>	
</acme:form>