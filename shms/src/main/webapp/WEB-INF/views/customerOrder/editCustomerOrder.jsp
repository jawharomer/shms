<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	var jsonCustomers = '${jsonCustomers}';
	var jsonCustomerOrder = '${jsonCustomerOrder}';
	var csrf = '${_csrf}';
</script>


<div ng-app="editCustomerOrder" ng-controller="editCustomerOrder"
	ng-init="init()">
	<h2>Edit Customer Order</h2>

	<table class="table table-sm cus-table-borderless">
		<tbody ng-form="customerOrderForm">
			<tr>
				<td>Customer</td>
				<td><select required class="form-control form-control-sm"
					ng-model="customerOrder.customer.id"
					ng-options="item.id as item.name for item in customers">
						<option value=""></option>
				</select></td>
			</tr>
			<tr>
				<td>ReceivedBy</td>
				<td><input required ng-model="customerOrder.receivedBy"
					class="form-control  form-control-sm"></td>
			</tr>

			<tr>
				<td>Note</td>
				<td><input ng-model="customerOrder.note"
					class="form-control  form-control-sm"></td>
			</tr>
		</tbody>
	</table>


	<div class="border-top">
		<table class="table table-bordered">
			<thead>
				<tr>
					<td>P-Code</td>
					<td>P-Name</td>
					<td>QTY</td>
					<td>P-Category</td>
					<td>&nbsp;</td>
				</tr>
				<tr ng-form="newCustomerOrderDetailForm">
					<td><input required
						ng-model="newCustomerOrderDetail.product.code"
						class="form-control form-control-sm" ng-blur="getProduct()"></td>
					<td><input readonly="readonly" required
						ng-model="newCustomerOrderDetail.product.name"
						class="form-control form-control-sm"></td>
					<td><input type="number" min="1" required
						ng-model="newCustomerOrderDetail.quantity"
						class="form-control form-control-sm"></td>
					<td><input readonly="readonly" required
						ng-model="newCustomerOrderDetail.product.productCategory.name"
						class="form-control form-control-sm"></td>
					<td>
						<button ng-disabled="newCustomerOrderDetailForm.$invalid"
							class="btn btn-success bnt-sm rounded-circle"
							ng-click="addCustomerOrderDetail()">
							<i class="fa fa-plus"></i>
						</button>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="item in customerOrder.customerOrderDetails">
					<td>{{item.product.code}}</td>
					<td>{{item.product.name}}</td>
					<td>{{item.quantity}}</td>
					<td>&nbsp;</td>
					<td>
						<button class="btn btn-danger btn-sm rounded-circle"
							ng-click="deleteCustomerOrderDetail($index)">
							<i class="fa fa-times"></i>
						</button>
					</td>
				</tr>
			</tbody>
		</table>

	</div>

	<div class="border-top pt-2">
		<button
			ng-disabled="!customerOrder||!customerOrder.customerOrderDetails.length>0||customerOrderForm.$invalid"
			class="btn btn-warning" ng-click="saveCustomerOrder()">
			<i class="fa fa-edit"></i>
		</button>
	</div>

</div>