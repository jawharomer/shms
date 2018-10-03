$(document).ready()
{
	console.log("csrf=", csrf);

	$("#expiration-date,#production-date").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "yy-mm-dd"
	}).datepicker("setDate", new Date());

}

// Angular

appAddCustomerOrder = angular.module("addCustomerOrder", []);

appAddCustomerOrder.factory('httpRequestInterceptor', function() {
	return {
		request : function(config) {
			config.headers['X-CSRF-TOKEN'] = csrf;
			return config;
		}
	};
});
appAddCustomerOrder.config(function($httpProvider) {
	$httpProvider.interceptors.push('httpRequestInterceptor');
});

appAddCustomerOrder
		.controller(
				'addCustomerOrder',
				function($scope, $http, $q) {

					$scope.customers;
					$scope.customerOrder;
					$scope.newCustomerOrderDetail = {
						product : {
							id : "",
							name : "",
							productCategory : {
								id : ""
							}
						},
						quantity : ""
					};

					$scope.resetNewCustomerOrderDetail = angular
							.copy($scope.newCustomerOrderDetail);

					$scope.productCategories;

					$scope.init = function() {

						console.log("init->fired");
						console.log("jsonCustomers=", jsonCustomers);
						console.log("jsonCustomerOrder=", jsonCustomerOrder);

						$scope.customers = JSON.parse(jsonCustomers);
						$scope.customerOrder = JSON.parse(jsonCustomerOrder);
						console.log("$scope.customers=", $scope.customers);
						console.log("$scope.customerOrder=",
								$scope.customerOrder);

					}

					$scope.getProduct = function() {
						console.log("$scope.getProduct->fired");
						console.log(
								"$scope.newCustomerOrderDetail.product.code=",
								$scope.newCustomerOrderDetail.product.code);

						$scope.newCustomerOrderDetail.product.name = "";
						$scope.newCustomerOrderDetail.product.id = "";
						$scope.newCustomerOrderDetail.product.productCategory.name = "";

						$http(
								{
									method : 'GET',
									url : $$ContextURL
											+ '/products/code/'
											+ $scope.newCustomerOrderDetail.product.code
								})
								.then(
										function(response) {

											if (response.data) {
												console.log("response.data=",
														response.data);
												$scope.newCustomerOrderDetail.product.name = response.data.name;
												$scope.newCustomerOrderDetail.product.id = response.data.id;

												//
												$scope.newCustomerOrderDetail.product.productCategory.name = response.data.productCategory.name;
											}

										},
										function(response) {
											$("#modal-body")
													.html(response.data);
											$("#modal").modal("show");
										});

					}

					$scope.addCustomerOrderDetail = function() {
						console.log("addCustomerOrderDetail->fired");
						$scope.customerOrder.customerOrderDetails
								.push($scope.newCustomerOrderDetail);
						$scope.newCustomerOrderDetail = angular
								.copy($scope.resetNewCustomerOrderDetail);
					}

					$scope.deleteCustomerOrderDetail = function(index) {
						console.log("deleteCustomerOrderDetail->fired");
						console.log("Delete item index=", index);
						console.log("Delete item =",
								$scope.customerOrder.customerOrderDetails[index]);
						$scope.customerOrder.customerOrderDetails.splice(index, 1);
					}

					$scope.saveCustomerOrder = function() {
						console.log("saveCustomerOrder->fired");
						console.log("$scope.customerOrder=", $scope.customerOrder);
						$http({
							method : 'POST',
							data : $scope.customerOrder,
							url : $$ContextURL + '/customerOrders/add'
						}).then(function(response) {
							console.log(response);
							$("#modal-body").html(response.data);
							$("#modal").modal("show");
						}, function(response) {
							$("#modal-body").html(response.data);
							$("#modal").modal("show");
						});

					}
				});