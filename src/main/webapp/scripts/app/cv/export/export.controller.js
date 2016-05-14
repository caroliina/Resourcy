'use strict';

angular.module('resourcyApp')
    .controller('ExportController', function ($scope, $state, $http, Restangular, $window, $location) {

        $scope.sorting = true;
        $scope.personData = false;
        $scope.persons = {};

        $scope.exportView = function () {

            var boxes = $(":checkbox:checked");
            var doc = new jsPDF('p', 'pt');
            var endPos;
            var res = doc.autoTableHtmlToJson(document.getElementById("personal"));

            doc.text("Curriculum Vitae", 40, 50);
            doc.autoTable(res.columns, res.data, {startY:120});
            $.map(boxes, function(box) {
                var tableName = box.id.substr(0, box.id.indexOf('_'));
                endPos = doc.autoTableEndPosY();
                var res = doc.autoTableHtmlToJson(document.getElementById(tableName));
                doc.autoTable(res.columns, res.data, {
                    startY: endPos ? endPos + 10 : false });
            });

            doc.output("dataurlnewwindow");
        };

        $scope.loadEmployee = function () {
            Restangular.one("api").one("employees", $location.search().id).get().then(function (resp) {
                $scope.persons = resp;
                $scope.personID = resp.id;
                $scope.loadCurriculum();
                $scope.personData = true;
            }, function (error) {
                $scope.editmode = true;
                $scope.sorting = false;
                console.log(error)
            });
        };
        $scope.loadEmployee();
        $scope.loadCurriculum = function () {
            Restangular.one("api").one("curriculums").get({employeeId: $scope.personID}).then(function (resp) {
                $scope.cv_id = resp.id;
                $scope.educations = resp.educations;
                $scope.additionalStudy = resp.additionalStudys;
                $scope.additionalLanguage = resp.languageSkills;
                $scope.additionalSkill = resp.additionalSkills;
                $scope.workExperience = resp.workExperiences;
                $scope.govWorkExperience = resp.governmentWorkExperiences;
            });

        };
});
