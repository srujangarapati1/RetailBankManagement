
var app = angular.module("customerservice", ["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider
	.when("/",{
		templateUrl: "CustomerView/customerHome.html"
	})
	.when("/add",{
			templateUrl: "CustomerView/addCustomer.html"
		})
	.when("/viewBySsn",{
		templateUrl: "CustomerView/viewBySsn.html"
	})
	.when("/viewByName",{
		templateUrl: "CustomerView/viewByName.html"
	})
	.when("/viewByContact",{
		templateUrl: "CustomerView/viewByContact.html"
	})
	.when("/viewByEmail",{
		templateUrl: "CustomerView/viewByEmail.html"
	})
	.when("/viewByCity",{
		templateUrl: "CustomerView/viewByCity.html"
	})
	.when("/viewAll",{
		templateUrl: "CustomerView/viewAll.html"
	})
	.when("/existance",{
		templateUrl: "CustomerView/existance.html"
	})
	.when("/viewBySsnDisplay",{
		templateUrl: "CustomerView/viewBySsnDisplay.html"
	})
	.when("/viewByNameDisplay",{
		templateUrl: "CustomerView/viewByNameDisplay.html"
	})
	.when("/viewByContactDisplay",{
		templateUrl: "CustomerView/viewByContactDisplay.html"
	})
	.when("/viewByEmailDisplay",{
		templateUrl: "CustomerView/viewByEmailDisplay.html"
	})
	.when("/viewByCityDisplay",{
		templateUrl: "CustomerView/viewByCityDisplay.html"
	})
	.when("/checkDisplay",{
		templateUrl: "CustomerView/checkDisplay.html"
	})
	.when("/updateForm",{
		templateUrl: "CustomerView/updateForm.html"
	});
});


app.controller("addCustController", function($scope, $http) {
	
	 $scope.addCust = function() {
   	 
	        var method = "";
	        var url = "";
	 
	        if ($scope.customerForm.ssnId == -1) {
	            method = "POST";
	            url = 'http://localhost:8000/customer/addCustomer';
	        } else {
	            method = "POST";
	            url = 'http://localhost:8000/customer/addCustomer';
	        }
	 
	        $http({
	            method: method,
	            url: url,
	            data: angular.toJson($scope.customerForm),
	            headers: {
	                'Content-Type': 'application/json'
	            }
	        }).then(_success, _error);
	    };
	
	    function _success(res) {
	    	if(res.data!=0)
	    		{
	    	$scope.message="Customer created successfully with Customer Id:"+res.data;
	    	$scope.customerForm="";
	    }
	    	else
	    		{
	    		$scope.message="SSN Id already exist";
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
	       url: 'http://localhost:8000/customer/checkCustomer/'+x
	    }).then(
	        function(res) { // success
	            var bool = res.data;
	            
	            if(bool){

	            	$scope.message="Yes Customer Exist";
	            }
	            else{
	            	$scope.message=" Customer does not Exist";
	            }
	        },
	        function(res) { // error
	            console.log("Error: " + res.status + " : " + res.data);
	        }
	    );
	}
		
	});

app.controller("viewBySsnDisplayController",function($scope, $http,srvShareData) {
	
	
	var id=srvShareData.getData()[0];
	//$scope.message=id;
	

_refreshProductData();

function _refreshProductData() {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/customer/findCustomer/'+id
    }).then(
        function(res) { // success
            $scope.customers = res.data;
            console.log($scope.customers);
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}

$scope.editCustomer = function(x) {
	
	$scope.dataToShare = x;
    srvShareData.addData($scope.dataToShare);
  
	
};

$scope.deleteCustomer = function(x) {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/customer/deleteCustomer/'+x.ssnId,
        //data: angular.toJson(x),
        headers: {
            'Content-Type': 'application/json'
        }
        
    }).then(_success, _error);
};

function _success(res) {
    
    if(res.data)
    	{_refreshProductData();$scope.message="";}
    else
    	$scope.message="Customer cannot be deleted since he/she has an account";
   // _clearFormData();
}
function _error(res) {
    var data = res.data;
    var status = res.status;
    var header = res.header;
    var config = res.config;
    alert("Error: " + status + ":" + data);
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

app.controller("viewByNameDisplayController",function($scope, $http,srvShareData) {
	
	
	var name=srvShareData.getData()[0];
	$scope.message=name.customerName;
	

_refreshProductData();

function _refreshProductData() {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/customer/findByName/'+name
    }).then(
        function(res) { // success
            $scope.customers = res.data;
            console.log($scope.customers);
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}

$scope.editCustomer = function(x) {
	
	$scope.dataToShare = x;
    srvShareData.addData($scope.dataToShare);
  
	
};

$scope.deleteCustomer = function(x) {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/customer/deleteCustomer/'+x.ssnId,
        //data: angular.toJson(x),
        headers: {
            'Content-Type': 'application/json'
        }
        
    }).then(_success, _error);
};
function _success(res) {
    
    if(res.data)
    	{_refreshProductData();$scope.message="";}
    else
    	$scope.message="Customer cannot be deleted since he/she has an account";
   // _clearFormData();
}
function _error(res) {
    var data = res.data;
    var status = res.status;
    var header = res.header;
    var config = res.config;
    alert("Error: " + status + ":" + data);
}
	
});

app.controller("viewByContactDisplayController",function($scope, $http,srvShareData) {
	
	
	var contact=srvShareData.getData()[0];
	
	

_refreshProductData();

function _refreshProductData() {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/customer/findByNumber/'+contact
    }).then(
        function(res) { // success
            $scope.customers = res.data[0];
            console.log($scope.customers);
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}

$scope.editCustomer = function(x) {
	
	$scope.dataToShare = x;
    srvShareData.addData($scope.dataToShare);
  
	
};

$scope.deleteCustomer = function(x) {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/customer/deleteCustomer/'+x.ssnId,
        //data: angular.toJson(x),
        headers: {
            'Content-Type': 'application/json'
        }
        
    }).then(_success, _error);
};

function _success(res) {
    
    if(res.data)
    	{_refreshProductData();$scope.message="";}
    else
    	$scope.message="Customer cannot be deleted since he/she has an account";
   // _clearFormData();
}
function _error(res) {
    var data = res.data;
    var status = res.status;
    var header = res.header;
    var config = res.config;
    alert("Error: " + status + ":" + data);
}
	
});



app.controller("viewByEmailDisplayController",function($scope, $http,srvShareData) {
	
	
	var email=srvShareData.getData()[0];
	
	

_refreshProductData();

function _refreshProductData() {
    $http({
        method: 'GET',
       url: 'http://localhost:8000/customer/findByMail/'+email
    }).then(
        function(res) { // success
            $scope.customers = res.data;
            console.log($scope.customers);
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}


$scope.editCustomer = function(x) {
	
	$scope.dataToShare = x;
    srvShareData.addData($scope.dataToShare);
  
	
};

$scope.deleteCustomer = function(x) {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/customer/deleteCustomer/'+x.ssnId,
        //data: angular.toJson(x),
        headers: {
            'Content-Type': 'application/json'
        }
        
    }).then(_success, _error);
};

function _success(res) {
    
    if(res.data)
    	{_refreshProductData();$scope.message="";}
    else
    	$scope.message="Customer cannot be deleted since he/she has an account";
   // _clearFormData();
}
function _error(res) {
    var data = res.data;
    var status = res.status;
    var header = res.header;
    var config = res.config;
    alert("Error: " + status + ":" + data);
}
	
});


app.controller("viewByCityDisplayController",function($scope, $http,srvShareData) {
	
	
	var city=srvShareData.getData()[0];
	//$scope.message=id;
	

_refreshProductData();

function _refreshProductData() {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/customer/findByCity/'+city
    }).then(
        function(res) { // success
            $scope.customers = res.data;
            console.log($scope.customers);
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}


$scope.editCustomer = function(x) {
	
	$scope.dataToShare = x;
    srvShareData.addData($scope.dataToShare);
  
	
};

$scope.deleteCustomer = function(x) {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/customer/deleteCustomer/'+x.ssnId,
        //data: angular.toJson(x),
        headers: {
            'Content-Type': 'application/json'
        }
        
    }).then(_success, _error);
};

function _success(res) {
    
    if(res.data)
    	{_refreshProductData();$scope.message="";}
    else
    	$scope.message="Customer cannot be deleted since he/she has an account";
   // _clearFormData();
}
function _error(res) {
    var data = res.data;
    var status = res.status;
    var header = res.header;
    var config = res.config;
    alert("Error: " + status + ":" + data);
}
	
});
app.controller("checkDisplayController",function($scope, $http,srvShareData) {
	
	
	var id=srvShareData.getData()[0];
	

_refreshProductData();

function _refreshProductData() {
    $http({
        method: 'GET',
       url: 'http://172.26.49.50:8000/customer/checkCustomer/'+id
    }).then(
        function(res) { // success
            var bool = res.data;
            if(bool){

            	$scope.message="Yes Customer Exist";
            }
            else{
            	$scope.message=" Customer does not Exist";
            }
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}
	
});

app.controller("viewAllController",function($scope, $http,srvShareData) {
	
	$scope.dataToShare = [];
	$scope.customer = [];

_refreshProductData();


function _refreshProductData() {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/customer/findAll'
    }).then(
        function(res) { // success
            $scope.customer = res.data;
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
}

$scope.editCustomer = function(x) {
	
	$scope.dataToShare = x;
    srvShareData.addData($scope.dataToShare);
  
	
};

$scope.deleteCustomer = function(x) {
    $http({
        method: 'GET',
        url: 'http://localhost:8000/customer/deleteCustomer/'+x.ssnId,
        //data: angular.toJson(x),
        headers: {
            'Content-Type': 'application/json'
        }
        
    }).then(_success, _error);
};

function _success(res) {
    
    if(res.data)
    	{_refreshProductData();$scope.message="";}
    else
    	$scope.message="Customer cannot be deleted since he/she has an account";
   // _clearFormData();
}
function _error(res) {
    var data = res.data;
    var status = res.status;
    var header = res.header;
    var config = res.config;
    alert("Error: " + status + ":" + data);
}

});


app.controller("updateController",function($scope, $http,srvShareData) {
	
	
	
	$scope.customerForm = srvShareData.getData()[0];
   
	
	 $scope.updateCust = function() {
   	 
	        var method = "";
	        var url = "";
	 
	        if ($scope.customerForm.ssnId == -1) {
	            method = "POST";
	            url = 'http://localhost:8000/customer/updateCustomer';
	        } else {
	            method = "POST";
	            url = 'http://localhost:8000/customer/updateCustomer';
	        }
	 
	        $http({
	            method: method,
	            url: url,
	            data: angular.toJson($scope.customerForm),
	            headers: {
	                'Content-Type': 'application/json'
	            }
	        }).then(_success, _error);
	    };
	
	    function _success(res) {
	    	if(res.data)
	    		{
	    		$scope.message="updated successfully";
	    		}
	        //_refreshProductData();
	       // _clearFormData();
	    }
	 
	    function _error(res) {
	        var data = res.data;
	        var status = res.status;
	        var header = res.header;
	        var config = res.config;
	        alert("Error: " + status + ":" + data);
	    }
});

