'use strict';

/* App Module */

var petstoreApp = angular.module('petstoreApp', [
  'ngRoute',
  'petstoreControllers',
  'petstoreFilters',
  'petstoreAnimations',
  'phonecatServices'
]);

petstoreApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/pets', {
	      templateUrl: 'partials/pet-list.html',
	      controller: 'PetListCtrl'
      }).
      when('/pets/new', {
    	  templateUrl: 'partials/pet-new.html',
    	  controller: 'PetNewCtrl'
      }).
      when('/pets/:petId', {
          templateUrl: 'partials/pet-detail.html',
          controller: 'PetDetailCtrl'
      }).
      when('/login', {
          templateUrl: 'partials/login.html',
          controller: 'LoginCtrl'
        }).
      otherwise({
        redirectTo: '/pets'
      });
  }]);

petstoreApp.config(function($httpProvider, $logProvider){

	$httpProvider.interceptors.push(function($q, $location, $window, $injector) {
		var authSrv;
		var popupSrv;
		return {
			'responseError' : function(rejection) {
				var status = rejection.status;
				if (rejection.data){
					if (status == 401) {//Unauthenticated, redirect to login page
						$window.location.href="/#/login";
						return;
					}// else if (status == 403) {//credentials error, managed in the login screen
					//	return $q.reject(rejection);
					//}
					else if (status == 418) {//undefined error->HTTP code 418 is responsibility of the caller
						return $q.reject(rejection);
					}else if (status>=400 && status <= 499) {//According to HTTP status codes, it is a client error
						popupSrv = popupSrv || $injector.get('popUpSrv');
						popupSrv.alert("Erreur client ("+status+"): "+rejection.data.message+ (rejection.data.stackTrace?"\r\n___________________________________\r\n\r\nErreur complète:\r\n" + rejection.data.stackTrace:''));
						return;
					} else if (status == 507) {//Too many result in the request, ask for a confirmation
						popupSrv = popupSrv || $injector.get('popUpSrv');
						if (popupSrv.confirm(rejection.data.object + ' enregistrements vont être rapatriés.\n\rCela peut prendre du temps.\n\rPour remédier à cela, vous pouvez affinez vos critères et relancer la recherche. \n\r\n\rEtes-vous sûr de vouloir continuer?')) {
							var $http = $injector.get('$http');//if user confirm, we remove the limit of records and resend the same request
							rejection.config.data.maxResult = undefined;
					        return $http(rejection.config);
						}
						return;
					} else if (status >= 500 && status <= 599) {//According to HTTP status codes, it is a client error
						popupSrv = popupSrv || $injector.get('popUpSrv');
						popupSrv.alert("Erreur serveur: "+rejection.data.message+ (rejection.data.stackTrace?"\r\n___________________________________\r\n\r\nErreur complète:\r\n" + rejection.data.stackTrace:''));
						return;
					} 
				} else {
					popupSrv = popupSrv || $injector.get('popUpSrv');
					popupSrv.alert("Erreur inconnue( http status: "+status+")");
					return;
				}
				return $q.reject(rejection);
			}
		};
	});
});
