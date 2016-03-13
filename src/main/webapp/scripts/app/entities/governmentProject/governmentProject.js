'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('governmentProject', {
                parent: 'entity',
                url: '/governmentProjects',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.governmentProject.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/governmentProject/governmentProjects.html',
                        controller: 'GovernmentProjectController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('governmentProject');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('governmentProject.detail', {
                parent: 'entity',
                url: '/governmentProject/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.governmentProject.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/governmentProject/governmentProject-detail.html',
                        controller: 'GovernmentProjectDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('governmentProject');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'GovernmentProject', function($stateParams, GovernmentProject) {
                        return GovernmentProject.get({id : $stateParams.id});
                    }]
                }
            })
            .state('governmentProject.new', {
                parent: 'governmentProject',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/governmentProject/governmentProject-dialog.html',
                        controller: 'GovernmentProjectDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    buyer: null,
                                    serviceName: null,
                                    totalProjectWorkHours: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('governmentProject', null, { reload: true });
                    }, function() {
                        $state.go('governmentProject');
                    })
                }]
            })
            .state('governmentProject.edit', {
                parent: 'governmentProject',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/governmentProject/governmentProject-dialog.html',
                        controller: 'GovernmentProjectDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['GovernmentProject', function(GovernmentProject) {
                                return GovernmentProject.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('governmentProject', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('governmentProject.delete', {
                parent: 'governmentProject',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/governmentProject/governmentProject-delete-dialog.html',
                        controller: 'GovernmentProjectDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['GovernmentProject', function(GovernmentProject) {
                                return GovernmentProject.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('governmentProject', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
