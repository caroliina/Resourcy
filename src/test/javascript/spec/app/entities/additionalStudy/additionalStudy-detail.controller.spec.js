'use strict';

describe('Controller Tests', function() {

    describe('AdditionalStudy Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockAdditionalStudy, MockCurriculumVitae;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockAdditionalStudy = jasmine.createSpy('MockAdditionalStudy');
            MockCurriculumVitae = jasmine.createSpy('MockCurriculumVitae');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'AdditionalStudy': MockAdditionalStudy,
                'CurriculumVitae': MockCurriculumVitae
            };
            createController = function() {
                $injector.get('$controller')("AdditionalStudyDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'resourcyApp:additionalStudyUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
