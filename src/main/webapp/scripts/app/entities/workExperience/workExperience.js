'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('workExperience', {
                parent: 'entity',
                url: '/workExperiences',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.workExperience.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workExperience/workExperiences.html',
                        controller: 'WorkExperienceController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('workExperience');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('workExperience.detail', {
                parent: 'entity',
                url: '/workExperience/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.workExperience.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workExperience/workExperience-detail.html',
                        controller: 'WorkExperienceDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('workExperience');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'WorkExperience', function($stateParams, WorkExperience) {
                        return WorkExperience.get({id : $stateParams.id});
                    }]
                }
            })
            .state('workExperience.new', {
                parent: 'workExperience',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workExperience/workExperience-dialog.html',
                        controller: 'WorkExperienceDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    position: null,
                                    periodStart: null,
                                    periodEnd: null,
                                    location: null,
                                    organization: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('workExperience', null, { reload: true });
                    }, function() {
                        $state.go('workExperience');
                    })
                }]
            })
            .state('workExperience.edit', {
                parent: 'workExperience',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workExperience/workExperience-dialog.html',
                        controller: 'WorkExperienceDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['WorkExperience', function(WorkExperience) {
                                return WorkExperience.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workExperience', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('workExperience.delete', {
                parent: 'workExperience',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workExperience/workExperience-delete-dialog.html',
                        controller: 'WorkExperienceDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['WorkExperience', function(WorkExperience) {
                                return WorkExperience.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workExperience', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
