'use strict';

describe('Controller Tests', function() {

    describe('WorkExperience Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockWorkExperience, MockCurriculumVitae, MockWorkAssignment;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockWorkExperience = jasmine.createSpy('MockWorkExperience');
            MockCurriculumVitae = jasmine.createSpy('MockCurriculumVitae');
            MockWorkAssignment = jasmine.createSpy('MockWorkAssignment');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'WorkExperience': MockWorkExperience,
                'CurriculumVitae': MockCurriculumVitae,
                'WorkAssignment': MockWorkAssignment
            };
            createController = function() {
                $injector.get('$controller')("WorkExperienceDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'resourcyApp:workExperienceUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
