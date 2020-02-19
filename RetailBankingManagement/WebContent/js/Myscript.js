/**
 * 
 */
function loginvalidate(){
	var id=document.forms["loginform"]["userId"].value;
	var pass=document.forms["loginform"]["password"].value;
	if(id.length < 8){
		document.getElementById("di").innerHTML="userId mustbe greater than 8";
		 document.getElementById("di").style.color="Red";
		return false;
	}
	else if(pass.length < 10){
		document.getElementById("di1").innerHTML="password mustbe greater than 8";
		 document.getElementById("di1").style.color="Red";
		return false;
	}
	else{
		return true;
	}

}
function accountvalidate(){
	var id=document.forms["accountform"]["custId"].value;
	var amount=document.forms["accountform"]["amount"].value;
	
	if(id.length > 8){
		document.getElementById("di").innerHTML="customerId mustbe less than 8";
		 document.getElementById("di").style.color="Red";
		return false;
	}
	else if(isNaN(id)){
		document.getElementById("di").innerHTML="customerId mustbe a number";
		 document.getElementById("di").style.color="Red"; 
		return false;
	}
	else if(isNaN(amount)){
		document.getElementById("adi").innerHTML="amount mustbe a number";
		 document.getElementById("adi").style.color="Red"; 
		return false;
	}
	else{
		return true;
	}
}
function customervalidate(){
	var id=document.forms["customerform"]["ssnid"].value;
	var name=document.forms["customerform"]["name"].value;
	var age=document.forms["customerform"]["age"].value;
	var city=document.forms["customerform"]["city"].value;
	var state=document.forms["customerform"]["state"].value;
	var alphaExp = /^[a-zA-Z]+$/;
	if(id.length != 9){
		document.getElementById("di").innerHTML="ssnId mustbe 9 charecters";
		 document.getElementById("di").style.color="Red";
		return false;
	}
	else if(isNaN(id)){
		document.getElementById("di").innerHTML="ssnId mustbe numbers only";
		 document.getElementById("di").style.color="Red";
		return false;
	}
	else if(isNaN(age)){
		document.getElementById("bdi").innerHTML="age mustbe numbers";
		 document.getElementById("bdi").style.color="Red";
		return false;
	}
	else if(age<18){
		document.getElementById("bdi").innerHTML="age mustbe greater than 18";
		 document.getElementById("bdi").style.color="Red";
		return false;
	}
	else if(age>200){
		document.getElementById("bdi").innerHTML="plesae check your age";
		 document.getElementById("bdi").style.color="Red";
		return false;
	}
	else if(!(name.match(alphaExp)))
	{
		document.getElementById("adi").innerHTML="name mustbe alphabets only";
		 document.getElementById("adi").style.color="Red";
		return false;
	}
	else if(!(city.match(alphaExp))){
		document.getElementById("cdi").innerHTML="city mustbe alphabets only";
		 document.getElementById("cdi").style.color="Red";
		return false;
	}
	else if(!(state.match(alphaExp))){
		document.getElementById("ddi").innerHTML="state mustbe alphabets only";
		 document.getElementById("ddi").style.color="Red";
		return false;
	}
	else{
		return true;
	}
}
function transfervalidate(){
	var amount=document.forms["transferform"]["tAmount"].value;
	var sId=document.forms["transferform"]["sAcc"].value;
	var tId=document.forms["transferform"]["tAcc"].value;
	if(isNaN(amount)){
		document.getElementById("di").innerHTML="amount must be numbers only";
		 document.getElementById("di").style.color="Red";
		return false;
		
	}
	else if(isNaN(sId)){
		document.getElementById("adi").innerHTML="Account id must contains only numbers";
		 document.getElementById("adi").style.color="Red";
		return false;
	}
	else if(isNaN(tId)){
		document.getElementById("bdi").innerHTML="Account id must contains only numbers";
		 document.getElementById("bdi").style.color="Red";
		return false;
	}
	else if(amount.length>9||sId.length>5||tId.length>5){
		document.getElementById("bdi").innerHTML="one of the values are too long";
		 document.getElementById("bdi").style.color="Red";
		return false;
	}
	else{
		return true;
	}
}
function statementvalidate(){
	var accountId=document.forms["statementform"]["accountid"].value;
	if(isNaN(accountId)){
		document.getElementById("di").innerHTML="one of the values are too long";
		 document.getElementById("di").style.color="Red";
		return false;
	}
	else if(accountId.length>5){
		document.getElementById("di").innerHTML="one of the values are too long";
		 document.getElementById("di").style.color="Red";
		return false;
	}
	else{
		return true;
	}
	
	
}