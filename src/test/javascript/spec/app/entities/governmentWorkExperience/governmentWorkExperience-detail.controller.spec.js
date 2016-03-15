'use strict';

describe('Controller Tests', function() {

    describe('GovernmentWorkExperience Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockGovernmentWorkExperience, MockCurriculumVitae, MockGovernmentProject, MockWorkAssignment;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockGovernmentWorkExperience = jasmine.createSpy('MockGovernmentWorkExperience');
            MockCurriculumVitae = jasmine.createSpy('MockCurriculumVitae');
            MockGovernmentProject = jasmine.createSpy('MockGovernmentProject');
            MockWorkAssignment = jasmine.createSpy('MockWorkAssignment');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'GovernmentWorkExperience': MockGovernmentWorkExperience,
                'CurriculumVitae': MockCurriculumVitae,
                'GovernmentProject': MockGovernmentProject,
                'WorkAssignment': MockWorkAssignment
            };
            createController = function() {
                $injector.get('$controller')("GovernmentWorkExperienceDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'resourcyApp:governmentWorkExperienceUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
