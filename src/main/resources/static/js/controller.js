app.controller('phoneNumCombinationController', function($scope,$http,$rootScope,$route,$location,$routeParams) {
	$scope.number = "";
	$scope.buttonSubmit = buttonSubmit;
	$scope.gridOptions = {};
	$rootScope.validNumber = false;
	var totalItems = "";
	function digitsLengthCheck(digits) {
		if (isNaN(digits)) {
			$rootScope.validNumber = false;
	    	alert("Please enter digits only");
	        return false;
		} else if (digits.length === 7 || digits.length === 10) {
			$rootScope.validNumber = true;
	        return true;
	    } else {
	    	$rootScope.validNumber = false;
	    	alert("Please fill phone number that has length of 7 or 10");
	        return false;
	    }
	}
	
	function buttonSubmit() {
		$scope.gridOptions = {};
		$scope.gridApi = {};
		if(digitsLengthCheck($scope.number)) {
			var paginationOptions = {
		            pageNumber: 1,
		            pageSize: 10
		        };
			$http.get('phonenumber/'+$scope.number).
				then(function successCallback(response) {
					response.data.content.combinations = [];
					angular.forEach(response.data.content,function(item,index) {
						response.data.content.combinations.push({"combination":item});
					});
					$scope.gridOptions.data = response.data.content.combinations;
					$scope.gridOptions.totalItems = totalItems = response.data.totalElements;
		        }, function errorCallback(response) {
		            console.log(response.statusText);
		        });
			$scope.gridOptions = {
	    	        paginationPageSizes: [5,10,20],
	    	        paginationPageSize: paginationOptions.pageSize,
	    	        enableColumnMenus:false,
	    	        useExternalPagination: true,
	    	        columnDefs: [
	    	           { name: 'combination' }
	    	        ],
	    	        onRegisterApi: function(gridApi) {
	    	           $scope.gridApi = gridApi;
	    	           gridApi.pagination.on.paginationChanged(
	    	             $scope, 
	    	             function (newPage, pageSize) {
	    	               paginationOptions.pageNumber = newPage;
	    	               paginationOptions.pageSize = pageSize;
	    	               $http.get('phonenumber/'+$scope.number+'/page/'+paginationOptions.pageNumber+'/pageSize/'+paginationOptions.pageSize)
	    	                 .success(function(response){
	    	                	 response.data = [];
	    	     				angular.forEach(response,function(item,index) {
	    	     					response.data.push({"combination":item});
	    	     				});
	    	                    $scope.gridOptions.data = response.data;
	    	                    $scope.gridOptions.totalItems = totalItems;
	    	                 });
	    	            });
	    	        }
	    	 };
		}
		
	}
    
});