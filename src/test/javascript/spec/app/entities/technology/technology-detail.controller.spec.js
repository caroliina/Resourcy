'use strict';

describe('Controller Tests', function() {

    describe('Technology Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockTechnology, MockGovernmentProject;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockTechnology = jasmine.createSpy('MockTechnology');
            MockGovernmentProject = jasmine.createSpy('MockGovernmentProject');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Technology': MockTechnology,
                'GovernmentProject': MockGovernmentProject
            };
            createController = function() {
                $injector.get('$controller')("TechnologyDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'resourcyApp:technologyUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
