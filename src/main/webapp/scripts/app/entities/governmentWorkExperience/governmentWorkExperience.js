'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('governmentWorkExperience', {
                parent: 'entity',
                url: '/governmentWorkExperiences',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.governmentWorkExperience.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/governmentWorkExperience/governmentWorkExperiences.html',
                        controller: 'GovernmentWorkExperienceController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('governmentWorkExperience');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('governmentWorkExperience.detail', {
                parent: 'entity',
                url: '/governmentWorkExperience/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.governmentWorkExperience.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/governmentWorkExperience/governmentWorkExperience-detail.html',
                        controller: 'GovernmentWorkExperienceDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('governmentWorkExperience');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'GovernmentWorkExperience', function($stateParams, GovernmentWorkExperience) {
                        return GovernmentWorkExperience.get({id : $stateParams.id});
                    }]
                }
            })
            .state('governmentWorkExperience.new', {
                parent: 'governmentWorkExperience',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/governmentWorkExperience/governmentWorkExperience-dialog.html',
                        controller: 'GovernmentWorkExperienceDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    periodStart: null,
                                    periodEnd: null,
                                    personalWorkHours: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('governmentWorkExperience', null, { reload: true });
                    }, function() {
                        $state.go('governmentWorkExperience');
                    })
                }]
            })
            .state('governmentWorkExperience.edit', {
                parent: 'governmentWorkExperience',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/governmentWorkExperience/governmentWorkExperience-dialog.html',
                        controller: 'GovernmentWorkExperienceDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['GovernmentWorkExperience', function(GovernmentWorkExperience) {
                                return GovernmentWorkExperience.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('governmentWorkExperience', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('governmentWorkExperience.delete', {
                parent: 'governmentWorkExperience',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/governmentWorkExperience/governmentWorkExperience-delete-dialog.html',
                        controller: 'GovernmentWorkExperienceDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['GovernmentWorkExperience', function(GovernmentWorkExperience) {
                                return GovernmentWorkExperience.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('governmentWorkExperience', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
