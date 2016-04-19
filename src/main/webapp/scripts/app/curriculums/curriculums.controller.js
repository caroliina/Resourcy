angular.module('resourcyApp')
    .controller('CurriculumsController', function ($scope, $state, Restangular) {

        $scope.refresh = function () {
            Restangular.one('api').one('curriculums').get().then(function (resp) {
                $scope.curriculumVitae = resp;
            });
        };
        $scope.refresh();

        $scope.employee = {};

        $scope.saveEmployee = function () {
            Restangular.all("api").all("employee").post($scope.employee).then(function (resp) {
                $scope.refresh();
            });
        };

        //Get employee
        Restangular.one("api").one("currentEmployee").get().then(function (resp) {
            $scope.employee = resp;
        });

        $scope.education = {};
        $scope.saveEducation = function () {
            $scope.education.curriculumVitaeId = $scope.curriculumVitae.id;
            Restangular.all("api").all("educations").post($scope.education).then(function (resp) {
                $scope.refresh();
            });
        };

        $scope.additionalSkill = {};
        $scope.saveAdditionalSkill = function () {
            $scope.additionalSkill.curriculumVitaeId = $scope.curriculumVitae.id;
            Restangular.all("api").all("addSkill").post($scope.additionalSkill).then(function (resp) {
                $scope.refresh();
            });
        };

        $scope.additionalStudy = {};
        $scope.saveAdditionalStudy = function () {
            $scope.additionalStudy.curriculumVitaeId = $scope.curriculumVitae.id;
            Restangular.all("api").all("addStudy").post($scope.additionalStudy).then(function (resp) {
                $scope.refresh();
            });
        };

        $scope.languageSkill = {};
        $scope.saveLanguageSkill = function () {
            $scope.languageSkill.curriculumVitaeId = $scope.curriculumVitae.id;
            Restangular.all("api").all("addLanguage").post($scope.languageSkill).then(function (resp) {
                $scope.refresh();
            });
        };

        $scope.workExperience = {};
        $scope.workAssignment = {};

        $scope.saveWorkExperience = function () {
            $scope.workExperience.curriculumVitaeId = $scope.curriculumVitae.id;
            Restangular.all("api").all("workExperience").post($scope.workExperience).then(function (response) {
                $scope.refresh();

                $scope.workAssignment.workExperienceId = response.id;
                Restangular.all("api").all("workAssignment").post($scope.workAssignment).then(function (resp) {
                    $scope.refresh();
                })
            });
        };


        $scope.governmentWorkExperience = {};
        $scope.govWorkAssignment = {};
        $scope.governmentProject = {};
        $scope.technology = {};

        $scope.saveGovernmentWorkExperience = function () {
            Restangular.all("api").all("govProject").post($scope.governmentProject).then(function (govProject) {
                $scope.refresh();

                $scope.technology.governmentProjectId = govProject.id;
                Restangular.all("api").all("tech").post($scope.technology).then(function (resp) {
                    $scope.refresh();
                });

                $scope.governmentWorkExperience.curriculumVitaeId = $scope.curriculumVitae.id;
                $scope.governmentWorkExperience.governmentProject = govProject;
                Restangular.all("api").all("govWorkExperience").post($scope.governmentWorkExperience).then(function (govWorkExperience) {
                    $scope.refresh();

                    $scope.govWorkAssignment.governmentWorkExperienceId = govWorkExperience.id;
                    Restangular.all("api").all("workAssignment").post($scope.govWorkAssignment).then(function (resp) {
                        $scope.refresh();
                    });

                });

            });
        };


    });