'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('workAssignment', {
                parent: 'entity',
                url: '/workAssignments',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.workAssignment.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workAssignment/workAssignments.html',
                        controller: 'WorkAssignmentController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('workAssignment');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('workAssignment.detail', {
                parent: 'entity',
                url: '/workAssignment/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.workAssignment.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/workAssignment/workAssignment-detail.html',
                        controller: 'WorkAssignmentDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('workAssignment');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'WorkAssignment', function($stateParams, WorkAssignment) {
                        return WorkAssignment.get({id : $stateParams.id});
                    }]
                }
            })
            .state('workAssignment.new', {
                parent: 'workAssignment',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workAssignment/workAssignment-dialog.html',
                        controller: 'WorkAssignmentDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    description: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('workAssignment', null, { reload: true });
                    }, function() {
                        $state.go('workAssignment');
                    })
                }]
            })
            .state('workAssignment.edit', {
                parent: 'workAssignment',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workAssignment/workAssignment-dialog.html',
                        controller: 'WorkAssignmentDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['WorkAssignment', function(WorkAssignment) {
                                return WorkAssignment.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workAssignment', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('workAssignment.delete', {
                parent: 'workAssignment',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/workAssignment/workAssignment-delete-dialog.html',
                        controller: 'WorkAssignmentDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['WorkAssignment', function(WorkAssignment) {
                                return WorkAssignment.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('workAssignment', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
