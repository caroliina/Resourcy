'use strict';

describe('Controller Tests', function() {

    describe('LanguageSkill Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockLanguageSkill, MockCurriculumVitae;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockLanguageSkill = jasmine.createSpy('MockLanguageSkill');
            MockCurriculumVitae = jasmine.createSpy('MockCurriculumVitae');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'LanguageSkill': MockLanguageSkill,
                'CurriculumVitae': MockCurriculumVitae
            };
            createController = function() {
                $injector.get('$controller')("LanguageSkillDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'resourcyApp:languageSkillUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
