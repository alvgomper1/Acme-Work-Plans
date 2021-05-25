<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm" id="tasks">
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-public-tasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPublicTasks}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-private-tasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPrivateTasks}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-finished-tasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfFinishedTasks}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-non-finished-tasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfNonFinishedTasks}"/>
		</td>
	</tr>
	
</table>
<h2>
	<acme:message code="administrator.dashboard.form.title.execution-period"/>
</h2>

<table class="table table-sm" id="executionPeriods">	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-task-execution-period"/>
		</th>
		<td>
			<acme:print value="${averageTaskExecutionPeriod}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-task-execution-period"/>
		</th>
		<td>
			<acme:print value="${deviationTaskExecutionPeriod}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-task-execution-period"/>
		</th>
		<td>
			<acme:print value="${maxTaskExecutionPeriod}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-task-execution-period"/>
		</th>
		<td>
			<acme:print value="${minTaskExecutionPeriod}"/>
		</td>
	</tr>
	
</table>
<h2>
	<acme:message code="administrator.dashboard.form.title.task-workload"/>
</h2>

<table class="table table-sm" id="workloads">	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-task-workload"/>
		</th>
		<td>
			<acme:print value="${averageTaskWorkload}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-task-workload"/>
		</th>
		<td>
			<acme:print value="${deviationTaskWorkload}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-task-workload"/>
		</th>
		<td>
			<acme:print value="${maxTaskWorkload}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-task-workload"/>
		</th>
		<td>
			<acme:print value="${minTaskWorkload}"/>
		</td>
	</tr>
	
</table>


<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"PUBLISHED", "TOTAL"
			],
			datasets : [
				{
					data : [	
						<jstl:out value="${totalNumberOfPublicWorkplans}"/>,
						<jstl:out value="${totalNumberOfPrivateWorkplans + totalNumberOfPublicWorkplans}"/>
					],
					backgroundColor: [
					      'rgb(255, 99, 132)',
					      'rgb(54, 162, 235)'
					],
					 hoverOffset: 4
				}
			]
		};
		var options = {
		
			legend : {
				display : true
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>