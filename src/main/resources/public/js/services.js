'use strict';

/* Services */

var phonecatServices = angular.module('phonecatServices', ['ngResource']);

phonecatServices.factory('Phone', ['$resource', function($resource){
	//return a resource object (see https://docs.angularjs.org/api/ngResource/service/$resource)
    return $resource('phones/:phoneId.json', {}, {
      query: {method:'GET', params:{phoneId:'phones'}, isArray:true}
    });
  }]);

phonecatServices.factory('PetService', ['$resource', function($resource){
	var petResource = $resource('pet/:petId', {}, {
	       query: {method:'GET', params:{petId:'all'}, isArray:true}
	     }); 
	return petResource;
   }]);

phonecatServices.factory('AuthService', ['$resource', '$window' , function($resource, $window){
	var authService = {}
    	
	authService.authenticate = function (credentials, $scope){
		var resource = $resource('/login', {}, {
		       post: {method:'post', params:{username: credentials.username, password: credentials.password}, headers:{'Content-Type': 'application/x-www-form-urlencoded'}}
	     }); 
		resource.post({}, function(){$scope.error=false; $window.location.href='/#/pets/'}, function(){$scope.error=true})
	}
    return authService;
  }]);

phonecatServices.service('popUpSrv', function() {

	/**
	 * Create a friendly message from an error code (like http status).
	 * 
	 * @param status
	 *            an error code
	 * 
	 * @return a friendly message
	 */
	function frendlyErrorCode(status) {
		var message = 'erreur inconnue';

		switch (status) {
		case 404:
			message = 'fichier non trouvé';
			break;

		case 405:
			message = 'méthode Non Autorisée';
			break;

		default:
			message = 'erreur inattendue';
			break;
		}

		return 'Erreur ' + status + ' : ' + message;
	}

	/**
	 * Create an pop-up to display on the browser.
	 * 
	 * @param message
	 *            message of the pop-up.
	 */
	this.alert = function(message) {
		alert(message);
	};

	/**
	 * Create an pop-up to display a friendly message of the error code on the
	 * browser.
	 * 
	 * @param errorCode
	 *            the code error to friendly display.
	 */
	this.frendlyAlert = function(errorCode) {
		this.alert(frendlyErrorCode(errorCode));
	};

	/**
	 * Create a confirmation dialog to display on the browser.
	 * 
	 * @param message
	 *            message of the pop-up.
	 * 
	 * 
	 * @return boolean true if the user clicked "OK".
	 */
	this.confirm = function(message) {
		return confirm(message);
	}

	/**
	 * Create a confirmation dialog to display a friendly message of the error
	 * code on the browser.
	 * 
	 * @param errorCode
	 *            the code error to friendly display.
	 * 
	 * 
	 * @return boolean true if the user clicked "OK".
	 */
	this.frendlyConfirm = function(errorCode) {
		return this.confirm(frendlyErrorCode(errorCode));
	};

});
