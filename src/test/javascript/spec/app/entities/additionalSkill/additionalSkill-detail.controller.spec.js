'use strict';

describe('Controller Tests', function() {

    describe('AdditionalSkill Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockAdditionalSkill, MockCurriculumVitae;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockAdditionalSkill = jasmine.createSpy('MockAdditionalSkill');
            MockCurriculumVitae = jasmine.createSpy('MockCurriculumVitae');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'AdditionalSkill': MockAdditionalSkill,
                'CurriculumVitae': MockCurriculumVitae
            };
            createController = function() {
                $injector.get('$controller')("AdditionalSkillDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'resourcyApp:additionalSkillUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
