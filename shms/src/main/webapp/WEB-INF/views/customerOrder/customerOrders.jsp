<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>

	<h4>Customer Orders</h4>
	<hr>

	<div>
		<form action="<c:url value="/orders" />">
			<table>
				<tr>
					<td class="text-left">From</td>
					<td><input class="form-control" id="from" name="from"
						value="<fmt:formatDate pattern = "yyyy-MM-dd"  
         value = "${from}" />" /></td>
				</tr>

				<tr>
					<td class="text-left">To</td>
					<td><input class="form-control" id="to" name="to"
						value="<fmt:formatDate pattern = "yyyy-MM-dd"  
         value = "${to}" />" /></td>
				</tr>
				<tr>
					<td><input class="btn btn-outline-info" type="submit"
						value="View" /></td>
				</tr>
			</table>
		</form>


	</div>

	<hr>

	<div style="overflow: auto">
		<table id="customerOrdersTable" class="display nowrap">
			<thead>
				<tr>
					<th>#</th>
					<th>Customer</th>
					<th>ReceivedBy</th>
					<th>Time</th>
					<th>Note</th>
					<th class="cus-not-export">F</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${customerOrders}" var="item">
					<tr>
						<td>${item.id}</td>

						<td>${item.customer.name}</td>
						<td>${item.receivedBy}</td>
						<td><fmt:formatDate value="${item.orderTime}"
								pattern="yyyy-MM-dd hh:mm:ss" /></td>

						<td class="cus-note-td" title="${item.note}">${item.note}</td>

						<td><a class="btn btn-sm btn-outline-warning"
							href="<c:url value="/customerOrders/edit/" />${item.id}"> <i
								class="fa fa-edit"></i>
						</a>
							<button class="btn btn-sm btn-outline-danger"
								onclick="deleteCustomerOrder(${item.id})">
								<i class="fa fa-times"></i>
							</button></td>
					</tr>



				</c:forEach>

			</tbody>
			<tfoot>
				<tr>
					<th>#</th>
					<th>Customer</th>
					<th>ReceivedBy</th>
					<th>Time</th>
					<th>Note</th>
					<th class="cus-not-search">F</th>
				</tr>
			</tfoot>


		</table>
	</div>

	<hr>

	<div>
		<span>Sum Total Payment=</span> <span> <fmt:formatNumber
				type="number" maxFractionDigits="3" value="${sumTotalPayment}" />

		</span>
	</div>

</div>