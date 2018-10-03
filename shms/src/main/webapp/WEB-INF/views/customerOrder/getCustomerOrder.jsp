<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="p-2">

	<button class="btn btn-info" onclick="window.print()">
		<i class="fa fa-print"></i>
	</button>


</div>

<div id="section-to-print">

	<h4>Customer Order Invoice</h4>

	<table class="table table-bordered w-100">
		<tr>
			<td>#</td>
			<td>${customerOrder.id}</td>
		</tr>

		<tr>
			<td>Customer</td>
			<td>${customerOrder.customer.name}</td>
		</tr>

		<tr>
			<td>Time</td>
			<td><fmt:formatDate value="${customerOrder.orderTime}"
					pattern="yyyy-MM-dd hh:mm:ss" /></td>
		</tr>

		<tr>
			<td>ReceivedBy</td>
			<td>${customerOrder.receivedBy}</td>
		</tr>

		<tr>
			<td>Note</td>
			<td>${customerOrder.note}</td>
		</tr>
	</table>


	<div class="p-1">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>P-Code</th>
					<th>P-Name</th>
					<th>Quantity</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${customerOrder.customerOrderDetails}" var="item">
					<tr>
						<td>${item.product.code}</td>
						<td>${item.product.name}</td>
						<td>${item.quantity}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="row p-2">
		<div class="col-6 text-center">Authorized Signature</div>
		<div class="col-6 text-center">Customer Signature</div>
	</div>



</div>
