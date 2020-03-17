var app = angular.module("loginservice", []);

app.controller("loginController", function($scope, $http,$window,$location) {
	
	
	$scope.login = function() {
		$http({
	        method: 'POST',
	        url: 'http://localhost:8000/login/login',
	        data: angular.toJson($scope.loginForm),
	        headers: {
	        	'Access-Control-Allow-Headers': 'Content-Type',
	        	'Access-Control-Allow-Methods': 'GET, POST, OPTIONS',
	        	'Access-Control-Allow-Origin': 'http://localhost:8080',

	            'Content-Type': 'application/json'
	        }
	        
	    }).then(_success, _error);
	};
	
	function _success(res) {
	    
	    if(res.data)
	    	{console.log(res.data);
	    	
	    	$window.location.href="/RetailBanking/index.html";
	    	
	    	//$location.href="./index.html";
	    	}
	    else
	    	$location.path("/login" );
	   // _clearFormData();
	}
	function _error(res) {
	    var data = res.data;
	    var status = res.status;
	    var header = res.header;
	    var config = res.config;
	    //alert("Error: " + status + ":" + data);
	    alert("Enter valid credential");
	    $window.location.href="/RetailBanking/login.html";
	}
});