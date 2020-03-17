var app = angular.module("accservice", ["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider
	.when("/",{
		templateUrl: "AccountView/AccountHome.html"
	})
	.when("/addAccount",{
		templateUrl: "AccountView/addAccount.html"
	})
	.when("/viewByAccId",{
		templateUrl: "AccountView/viewByAccId.html"
	})
	.when("/viewByCustId",{
		templateUrl: "AccountView/viewByCustId.html"
	})
	.when("/viewAllAcc",{
		templateUrl: "AccountView/viewAllAcc.html"
	})
	.when("/check",{
		templateUrl: "AccountView/check.html"
	})
	.when("/viewByAccIdDisplay",{
		templateUrl: "AccountView/viewByAccIdDisplay.html"
	})
	.when("/viewByCustIdDisplay",{
		templateUrl: "AccountView/viewByCustIdDisplay.html"
	});
});

app.controller("addAccController", function($scope, $http) {
	
	 $scope.addAcc = function() {
  	 
	        var method = "";
	        var url = "";
	 
	        if ($scope.accForm.pId == -1) {
	            method = "POST";
	           url = 'http://localhost:8000/account/createAccount';
	        } else {
	            method = "POST";
	            url = 'http://localhost:8000/account/createAccount';
	        }
	 
	        $http({
	            method: method,
	            url: url,
	            data: angular.toJson($scope.accForm),
	            headers: {
	                'Content-Type': 'application/json'
	            }
	        }).then(_success, _error);
	    };
	
	    function _success(res) {
	    	if(res.data==0)
	    		{
	    		$scope.message="Account type already exist for this customer id";
	    		}
	    	else if(res.data==-1)
	    		{
	    		$scope.message="entered customer id does not exist";
	    		}
	    	else
	    		{
	    		$scope.message="Account created successfully with account id:"+res.data;
	    		$scope.accForm="";
	    		}
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
	
	$scope.check=function(x)
	{
		$http({
	        method: 'GET',
	       url: 'http://localhost:8000/account/checkAccount/'+x
	    }).then(
	        function(res) { // success
	            var bool = res.data;
	            if(bool){

	            	$scope.message="Yes Account Exist";
	            }
	            else{
	            	$scope.message=" Account does not Exist";
	            }
	        },
	        function(res) { // error
	            console.log("Error: " + res.status + " : " + res.data);
	        }
	    );
	};
	
});


app.controller("viewByCAccIdDisplayController",function($scope, $http,srvShareData) {
	
	
	var id=srvShareData.getData()[0];

	

_refreshAccData();

function _refreshAccData() {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/account/viewByAccountId/'+id
    }).then(
        function(res) { // success
            $scope.acc = res.data;
            console.log($scope.acc);
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}

$scope.deleteAccount = function(x) {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/account/deleteAccount/'+x.accountId,
        //data: angular.toJson(x),
        headers: {
            'Content-Type': 'application/json'
        }
        
    }).then(_success);
};

function _success(res) {
    console.log(res.data);
    if(res.data)
    	{_refreshProductData();}
    else{
    	$scope.message="Balance of the account is not Zero Please Withdraw the amount and delete Your Account";
    }
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

app.controller("viewByCustIdDisplayController",function($scope, $http,srvShareData) {
	
	
	var id=srvShareData.getData()[0];

	

_refreshAccData();

function _refreshAccData() {
    $http({
        method: 'GET',
       url: 'http://localhost:8000/account/viewByCustomerId/'+id
    }).then(
        function(res) { // success
            $scope.acc = res.data;
            console.log($scope.acc);
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}

$scope.deleteAccount = function(x) {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/account/deleteAccount/'+x.accountId,
        //data: angular.toJson(x),
        headers: {
            'Content-Type': 'application/json'
        }
        
    }).then(_success);
};

function _success(res) {
    console.log(res.data);
    if(res.data)
    	{_refreshProductData();}
    else{
    	$scope.message="Balance of the account is not Zero Please Withdraw the amount and delete Your Account";
    }
}
	
});

app.controller("viewAllCustDisplayController",function($scope, $http,srvShareData) {
	
	$scope.dataToShare = [];
	

_refreshProductData();


function _refreshProductData() {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/account/viewAllAccount'
    }).then(
        function(res) { // success
            $scope.acc = res.data;
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}



$scope.deleteAccount = function(x) {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/account/deleteAccount/'+x.accountId,
        //data: angular.toJson(x),
        headers: {
            'Content-Type': 'application/json'
        }
        
    }).then(_success);
};

function _success(res) {
    console.log(res.data);
    if(res.data)
    	{_refreshProductData();}
    else{
    	$scope.message="Balance of the account is not Zero Please Withdraw the amount and delete Your Account";
    }
   
}
});


