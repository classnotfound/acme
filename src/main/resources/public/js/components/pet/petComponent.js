'use strict';

angular.module('petstoreComponents', [])
	.component('acmePet', {
			templateUrl: 'js/components/pet/pet-template.html',
			//template:'<div>TEST!!!!</div>',
			bindings: { pet: '<' },
			controller: function (){
				this.prefix='prefix!!';
			}
	}
);