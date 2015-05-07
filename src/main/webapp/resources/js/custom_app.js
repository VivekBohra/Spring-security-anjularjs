
var taskManagerModule = angular.module('taskManagerApp',[]);


taskManagerModule.controller('AppController', function ($scope,$http,$location) {
 
 var urlBase="https://localhost:"+$location.port()+"/ProjectCMS";
 $scope.divEmployee = false;
 $scope.toggle=true;
 $scope.selection = [];
 $scope.statuses=['ACTIVE','COMPLETED'];
 $scope.priorities=['HIGH','LOW','MEDIUM'];
 $scope.phoneNumbr = /^\+?\d{2}[- ]?\d{3}[- ]?\d{5}$/;
 //$scope.test="A";
 
$http.defaults.headers.post["Content-Type"] = "application/json";
 
    
 //get all registered users
 $http.get(urlBase+'/showall').
     success(function(data) {     
         alert($location.path());
    	 $scope.tasks = data;
         $scope.newtasks=data;
         
         //$scope.newtasks1;
    	 var j= 0;
         for(var i=0;i<$scope.tasks.length;i++){
        	 $scope.tasks[i];
        	 $scope.selection.push($scope.tasks[i].id);  
        	
         }        
 });
 
 
// Delete Users
$scope.deleteUser = function deleteUser(ids) {	   
   // is currently selected
	
  
 
     $http.delete(urlBase +'/tasks/archive/'+ids).success(function(data){
  	   alert("Successfully Deleted");
  	   $scope.tasks = data;         
    });  
  
};
$scope.enableaddTask = function enableaddTask(action) {	   
   // is currently selected
        $('#grid').toggleClass('hide');
	 $scope.divEmployee = true;
          $scope.Action = action;
  
};
$scope.defaultScope = function defaultScope(){	   
   // is currently selected
       /* $scope.email = 'john.doe@gmail.com';
        $scope.first_name= 'ext';
        /* $scope.last_name= 'text';
         $scope.mobile_number='0123456789';*/
        
  
};

$scope.getData= function getData()
{
      
          
        $scope.test='B';
         
         
       
   
    /*else{ 
         $scope.interface='A';
         alert("A");
         
    }*/
};
$scope.disabledaddTask = function disabledaddTask() {	   
   // is currently selected
   
        $('#grid').toggleClass('hide');
        
	 $scope.divEmployee =false;
         $scope.id= "";
         $scope.first_name= "";
         $scope.last_name= "";
         $scope.mobile_number="";
         $scope.email="";
         $scope.Action = "";
         
   $scope.myForm.$setPristine();
};
 $scope.addTask = function addTask() {
     $scope.getData();
               var getAction = $scope.Action;
              
        if (getAction == "Update") {
             
		 $http.post(urlBase + '/tasks/update/' +$scope.first_name+'/'+$scope.last_name+'/'+$scope.email+'/'+$scope.mobile_number+'/'+$scope.id).
		  success(function(data) {
			 alert("Task update");
			 $scope.tasks = data;	
                        
			
			 $scope.toggle='!toggle';			 
		    });
             
        }
        else
        {
           
                    
                    $http.post(urlBase + '/tasks/insert/' +$scope.first_name+'/'+$scope.last_name+'/'+$scope.email+'/'+$scope.mobile_number).
		  success(function(data) {
			 alert("Task added");
			 $scope.tasks = data;	
                        
			
			 $scope.toggle='!toggle';			 
		    });
        }
                                   $scope.id= "";
                                   $scope.first_name= "";
        	                   $scope.last_name= "";
        	                   $scope.mobile_number="";
        	                   $scope.email="";
                                   $scope.Action = "";
                                   $scope.disabledaddTask();
	};
 
 $scope.getTask = function getTask(idx) {
	       		
		 $http.post(urlBase +'/tasks/'+idx).
		  success(function(data) {
                            
                                   $scope.newtasks = data;
                                   $scope.id= $scope.newtasks[0].id;
                                   $scope.first_name= $scope.newtasks[0].first_name;
        	                   $scope.last_name= $scope.newtasks[0].last_name;
        	                   $scope.mobile_number=$scope.newtasks[0].mobile_number;
        	                   $scope.email=$scope.newtasks[0].email;
                                   $scope.toggle='toggle';
                                   $scope.enableaddTask("Update");	
                        
                   });
		
	};
 // toggle selection for a given task by task id
   $scope.toggleSelection = function toggleSelection(taskId) {	   
     var idx = $scope.selection.indexOf(taskId);
     // is currently selected
     alert(idx);
     if (idx > -1) {
       $http.post(urlBase + '/tasks/' +taskId+'/ACTIVE').
       success(function(data){
    	   alert("Task unmarked");
    	   $scope.tasks = data;         
      });
      $scope.selection.splice(idx, 1);
     }

     // is newly selected
     else {
       $http.post(urlBase + '/tasks/' +taskId+'/COMPLETED').
    success(function(data) {
    alert("Task marked completed");
    $scope.tasks = data;
      });
       $scope.selection.push(taskId);
     }
   };
   
 
 // Archive Completed Tasks
  
});

  // Function to get the data
 


//Angularjs Directive for confirm dialog box
taskManagerModule.directive('ngConfirmClick', [
 function(){
         return {
             link: function (scope, element, attr) {
                 var msg = attr.ngConfirmClick || "Are you sure?";
                 var clickAction = attr.confirmedClick;
                 element.bind('click',function (event) {
                     if ( window.confirm(msg) ) {
                         scope.$eval(clickAction);
                     }
                 });
             }
         };
 }]);