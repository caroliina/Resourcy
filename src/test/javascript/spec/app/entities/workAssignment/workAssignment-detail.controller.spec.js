'use strict';

describe('Controller Tests', function() {

    describe('WorkAssignment Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockWorkAssignment, MockWorkExperience, MockGovernmentWorkExperience;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockWorkAssignment = jasmine.createSpy('MockWorkAssignment');
            MockWorkExperience = jasmine.createSpy('MockWorkExperience');
            MockGovernmentWorkExperience = jasmine.createSpy('MockGovernmentWorkExperience');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'WorkAssignment': MockWorkAssignment,
                'WorkExperience': MockWorkExperience,
                'GovernmentWorkExperience': MockGovernmentWorkExperience
            };
            createController = function() {
                $injector.get('$controller')("WorkAssignmentDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'resourcyApp:workAssignmentUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
