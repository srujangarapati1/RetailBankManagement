var app = angular.module("transactionService", ["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider
	.when("/",{
		templateUrl: "TransactionView/TransactionHome.html"
	})
	.when("/deposit",{
		templateUrl: "TransactionView/deposit.html"
	})
	.when("/withdraw",{
		templateUrl: "TransactionView/withdraw.html"
	})
	.when("/viewTransByAccId",{
		templateUrl: "TransactionView/viewTransByAccId.html"
	})
	.when("/viewTransByCustId",{
		templateUrl: "TransactionView/viewTransByCustId.html"
	})
	.when("/viewAllTrans",{
		templateUrl: "TransactionView/viewAllTrans.html"
	})
	.when("/viewByAccIdDisplay",{
		templateUrl: "TransactionView/viewByAccIdDisplay.html"
	})
	.when("/viewTransByCustIdDisplay",{
		templateUrl: "TransactionView/viewTransByCustIdDisplay.html"
	})
	.when("/viewAllTransForm",{
		templateUrl: "TransactionView/viewAllTransForm.html"
	});
});


app.controller("depositController", function($scope, $http) {
	
	 $scope.deposit = function() {
  	 
	        var method = "";
	        var url = "";
	        $scope.message=$scope.transForm.accId;
	        if ($scope.transForm.accId == -1) {
	            method = "POST";
	            url = 'http://localhost:8000/transaction/deposit/'+$scope.transForm.accId+"/"+$scope.transForm.amount;
	        } else {
	            method = "POST";
	            url = 'http://localhost:8000/transaction/deposit/'+$scope.transForm.accId+"/"+$scope.transForm.amount;
	        }
	 
	        $http({
	            method: method,
	            url: url,
	            data: angular.toJson($scope.transForm),
	            headers: {
	                'Content-Type': 'application/json'
	            }
	        }).then(_success, _error);
	    };
	
	    function _success(res) {
	       // _refreshProductData();
	        //_clearFormData();
	    }
	 
	    function _error(res) {
	        var data = res.data;
	        var status = res.status;
	        var header = res.header;
	        var config = res.config;
	        alert("Error: " + status + ":" + data);
	    }
});


app.controller("withdrawController", function($scope, $http) {
	
	 $scope.withdraw = function() {
 	 
	        var method = "";
	        var url = "";
	 
	        if ($scope.transForm.accId == -1) {
	            method = "POST";
	            url = 'http://localhost:8000/transaction/Withdraw/'+$scope.transForm.accId+"/"+$scope.transForm.amount;
	        } else {
	            method = "POST";
	            url = 'http://localhost:8000/transaction/Withdraw/'+$scope.transForm.accId+"/"+$scope.transForm.amount;
	        }
	 
	        $http({
	            method: method,
	            url: url,
	            data: angular.toJson($scope.transForm),
	            headers: {
	                'Content-Type': 'application/json'
	            }
	        }).then(_success, _error);
	    };
	
	    function _success(res) {
	       // _refreshProductData();
	        //_clearFormData();
	    }
	 
	    function _error(res) {
	        var data = res.data;
	        var status = res.status;
	        var header = res.header;
	        var config = res.config;
	        alert("Error: " + status + ":" + data);
	    }
});

app.controller("viewByController",function($scope, $http,srvShareData) {

	$scope.viewBy = function(x) {
		
		srvShareData.addData(x);
	};
});

app.controller("viewByAccIdDisplayController",function($scope, $http,srvShareData) {
	
	
	var id=srvShareData.getData()[0];
	$scope.message=id;
	

_refreshAccData();

function _refreshAccData() {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/transaction/viewByAccId/'+id
    }).then(
        function(res) { // success
            $scope.transaction = res.data;
            console.log($scope.transaction);
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}
	
});

app.service('srvShareData', function() {
    var mydata = [];
    var addData = function(newObj) {
        
    	if(mydata)
    		mydata=[];
        mydata.push(newObj);
        
    };

    var getData = function(){
        return mydata ;
    };

    return {
        addData: addData,
        getData: getData
    };
});


app.controller("viewTransByCustIdDisplayController",function($scope, $http,srvShareData) {
	
	
	var id=srvShareData.getData()[0];
	//$scope.message=id;
	

_refreshAccData();

function _refreshAccData() {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/transaction/viewByCustId/'+id
    }).then(
        function(res) { // success
            $scope.transaction = res.data;
            console.log($scope.transaction);
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}
	
});


app.controller("viewAllTransController",function($scope, $http,srvShareData) {
	
	
	var x=srvShareData.getData()[0];
	var fromMonth=x.from.getMonth()+1;
	var toMonth=x.to.getMonth()+1;
	if(x.from.getDate().toString().length==1)
		$scope.fromDate="0"+x.from.getDate().toString();
	else
		$scope.fromDate=x.from.getDate().toString();
	
	if(x.to.getDate().toString().length==1)
		$scope.toDate="0"+x.to.getDate().toString();
	else
		$scope.toDate=x.to.getDate().toString();
	
	if(fromMonth.toString().length==1)
		$scope.fromMon="0"+fromMonth.toString();
	else
		$scope.fromMon=fromMonth.toString();
	if(toMonth.toString().length==1)
		$scope.toMon="0"+toMonth.toString();
	else
		$scope.toMon=toMonth.toString();
	
	$scope.fromYear=x.from.getFullYear().toString();
	$scope.toYear=x.to.getFullYear().toString();
	//$scope.message1=id.from.getFullYear();
_refreshAccData();

function _refreshAccData() {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/transaction/viewAllTransaction/'+$scope.fromYear+"-"+$scope.fromMon+"-"+$scope.fromDate+"/"+$scope.toYear+"-"+$scope.toMon+"-"+$scope.toDate
       // url: 'http://localhost:8000/transaction/viewAllTransaction/'+x.from+"/"+x.to
    }).then(
        function(res) { // success
            $scope.transaction = res.data;
            console.log($scope.transaction);
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}
	
});

