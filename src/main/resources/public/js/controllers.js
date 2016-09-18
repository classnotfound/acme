'use strict';

//--
angular.module('petstoreControllers', [])
.controller('PetListCtrl', 
		['$scope', '$location', 'PetService', function($scope, $location, PetService) {
    $scope.pets = PetService.query();
    
    $scope.goCreate = function () {
	  $location.path('/pets/new');
	};
    
  }])
  .controller('PetDetailCtrl', 
		['$scope', '$routeParams', 'PetService', function($scope, $routeParams, PetService) {
     $scope.pet = PetService.get({petId: $routeParams.petId}, function(petService) {
       $scope.mainImageUrl = $scope.pet.photoUrls[0];
     });

     $scope.setImage = function(imageUrl) {
       $scope.mainImageUrl = imageUrl;
     };
   }])
   .controller('PetNewCtrl', 
		['$scope', '$routeParams', '$log', '$location', 'PetService', function($scope, $routeParams, $log, $location, PetService) {
     $scope.jsonPet = {photoUrls: []};
     $scope.newUrl;
     
     $scope.tagEntries = [{
    	  id: 1,
    	  name: 'Walt Disney'
    	}, {
    	  id: 2,
    	  name: 'Tex Avery'
    	}, {
    	  id: 3,
    	  name: 'Pixart'
    	}, {
    	  id: 4,
    	  name: 'Cartoon'
    	}, {
    	  id: 5,
    	  name: 'Comics'
    	}];
     
     $scope.categories = [{
   	  id: 1,
   	  name: 'Cat'
   	}, {
   	  id: 2,
   	  name: 'Dog'
   	}, {
   	  id: 3,
   	  name: 'Mouse'
   	}, {
   	  id: 4,
   	  name: 'Duck'
   	}];
    
     $scope.savePet = function(){
     	$log.log($scope.jsonPet);
     	PetService.save($scope.jsonPet, function() {
     		$location.path('/pets');
    	});
     }
     
     $scope.addUrl = function(){
    	 $log.log("adding url: "+$scope.newUrl)
    	 $scope.jsonPet.photoUrls.push($scope.newUrl);
    	 $scope.newUrl="";
     }
 
   }])
   .controller('LoginCtrl', 
		['$scope', '$log', 'AuthService',  function($scope, $log, AuthService) {
     $scope.credentials = {};
     $scope.error=false;
			
	 $scope.login = function(){
		 AuthService.authenticate($scope.credentials, $scope);
		 
	 };

   }]);
