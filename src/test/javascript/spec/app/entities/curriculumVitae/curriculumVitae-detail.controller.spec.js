'use strict';

describe('Controller Tests', function() {

    describe('CurriculumVitae Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCurriculumVitae, MockEmployee, MockEducation, MockWorkExperience, MockGovernmentWorkExperience, MockAdditionalStudy, MockLanguageSkill, MockAdditionalSkill;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCurriculumVitae = jasmine.createSpy('MockCurriculumVitae');
            MockEmployee = jasmine.createSpy('MockEmployee');
            MockEducation = jasmine.createSpy('MockEducation');
            MockWorkExperience = jasmine.createSpy('MockWorkExperience');
            MockGovernmentWorkExperience = jasmine.createSpy('MockGovernmentWorkExperience');
            MockAdditionalStudy = jasmine.createSpy('MockAdditionalStudy');
            MockLanguageSkill = jasmine.createSpy('MockLanguageSkill');
            MockAdditionalSkill = jasmine.createSpy('MockAdditionalSkill');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'CurriculumVitae': MockCurriculumVitae,
                'Employee': MockEmployee,
                'Education': MockEducation,
                'WorkExperience': MockWorkExperience,
                'GovernmentWorkExperience': MockGovernmentWorkExperience,
                'AdditionalStudy': MockAdditionalStudy,
                'LanguageSkill': MockLanguageSkill,
                'AdditionalSkill': MockAdditionalSkill
            };
            createController = function() {
                $injector.get('$controller')("CurriculumVitaeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'resourcyApp:curriculumVitaeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
