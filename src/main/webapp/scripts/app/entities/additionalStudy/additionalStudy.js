'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('additionalStudy', {
                parent: 'entity',
                url: '/additionalStudys',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.additionalStudy.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/additionalStudy/additionalStudys.html',
                        controller: 'AdditionalStudyController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('additionalStudy');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('additionalStudy.detail', {
                parent: 'entity',
                url: '/additionalStudy/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.additionalStudy.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/additionalStudy/additionalStudy-detail.html',
                        controller: 'AdditionalStudyDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('additionalStudy');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'AdditionalStudy', function($stateParams, AdditionalStudy) {
                        return AdditionalStudy.get({id : $stateParams.id});
                    }]
                }
            })
            .state('additionalStudy.new', {
                parent: 'additionalStudy',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/additionalStudy/additionalStudy-dialog.html',
                        controller: 'AdditionalStudyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    periodStart: null,
                                    periodEnd: null,
                                    institution: null,
                                    description: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('additionalStudy', null, { reload: true });
                    }, function() {
                        $state.go('additionalStudy');
                    })
                }]
            })
            .state('additionalStudy.edit', {
                parent: 'additionalStudy',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/additionalStudy/additionalStudy-dialog.html',
                        controller: 'AdditionalStudyDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['AdditionalStudy', function(AdditionalStudy) {
                                return AdditionalStudy.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('additionalStudy', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('additionalStudy.delete', {
                parent: 'additionalStudy',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/additionalStudy/additionalStudy-delete-dialog.html',
                        controller: 'AdditionalStudyDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['AdditionalStudy', function(AdditionalStudy) {
                                return AdditionalStudy.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('additionalStudy', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
