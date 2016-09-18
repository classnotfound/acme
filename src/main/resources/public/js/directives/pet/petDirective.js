'use strict';

/* Directives */

angular.module('petstoreDirectives', [])
	.directive('acmePet', function() {
		return {
			restrict: 'E',
			replace: true,
			templateUrl: 'js/directives/pet/pet-template.html'
		};
	}
);
