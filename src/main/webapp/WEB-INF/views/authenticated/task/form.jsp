<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox 	code="authenticated.task.form.label.title" path="title" readonly="true"/>
	<acme:form-moment 	code="authenticated.task.form.label.startDate" path="startDate" readonly="true"/>
	<acme:form-moment 	code="authenticated.task.form.label.endDate" path="endDate" readonly="true"/>
	<acme:form-integer 	code="authenticated.task.form.label.workload" path="workload" readonly="true"/>
	<acme:form-textbox 	code="authenticated.task.form.label.visibility" path="visibility" readonly="true"/>
	<acme:form-textbox 	code="authenticated.task.form.label.finished" path="finished" readonly="true"/>
		<acme:form-textbox 	code="authenticated.task.form.label.executionPeriod" path="executionPeriod" readonly="true"/>
	<acme:form-textbox code="authenticated.task.form.label.optionalLink"
		path="optionalLink" readonly="true" />
	<acme:form-textbox 	code="authenticated.task.form.label.description" path="description" readonly="true"/>
	<acme:form-return code="authenticated.task.form.button.return" />
</acme:form>