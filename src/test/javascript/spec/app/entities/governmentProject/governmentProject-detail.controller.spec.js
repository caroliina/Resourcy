'use strict';

describe('Controller Tests', function() {

    describe('GovernmentProject Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockGovernmentProject, MockGovernmentWorkExperience, MockTechnology;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockGovernmentProject = jasmine.createSpy('MockGovernmentProject');
            MockGovernmentWorkExperience = jasmine.createSpy('MockGovernmentWorkExperience');
            MockTechnology = jasmine.createSpy('MockTechnology');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'GovernmentProject': MockGovernmentProject,
                'GovernmentWorkExperience': MockGovernmentWorkExperience,
                'Technology': MockTechnology
            };
            createController = function() {
                $injector.get('$controller')("GovernmentProjectDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'resourcyApp:governmentProjectUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
