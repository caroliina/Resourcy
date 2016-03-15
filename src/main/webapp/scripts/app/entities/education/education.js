'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('education', {
                parent: 'entity',
                url: '/educations',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.education.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/education/educations.html',
                        controller: 'EducationController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('education');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('education.detail', {
                parent: 'entity',
                url: '/education/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.education.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/education/education-detail.html',
                        controller: 'EducationDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('education');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Education', function($stateParams, Education) {
                        return Education.get({id : $stateParams.id});
                    }]
                }
            })
            .state('education.new', {
                parent: 'education',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/education/education-dialog.html',
                        controller: 'EducationDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    institution: null,
                                    periodStart: null,
                                    periodEnd: null,
                                    speciality: null,
                                    degree: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('education', null, { reload: true });
                    }, function() {
                        $state.go('education');
                    })
                }]
            })
            .state('education.edit', {
                parent: 'education',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/education/education-dialog.html',
                        controller: 'EducationDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Education', function(Education) {
                                return Education.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('education', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('education.delete', {
                parent: 'education',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/education/education-delete-dialog.html',
                        controller: 'EducationDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Education', function(Education) {
                                return Education.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('education', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
