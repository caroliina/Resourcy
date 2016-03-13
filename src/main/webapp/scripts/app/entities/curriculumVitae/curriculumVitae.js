'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('curriculumVitae', {
                parent: 'entity',
                url: '/curriculumVitaes',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.curriculumVitae.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/curriculumVitae/curriculumVitaes.html',
                        controller: 'CurriculumVitaeController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('curriculumVitae');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('curriculumVitae.detail', {
                parent: 'entity',
                url: '/curriculumVitae/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.curriculumVitae.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/curriculumVitae/curriculumVitae-detail.html',
                        controller: 'CurriculumVitaeDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('curriculumVitae');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'CurriculumVitae', function($stateParams, CurriculumVitae) {
                        return CurriculumVitae.get({id : $stateParams.id});
                    }]
                }
            })
            .state('curriculumVitae.new', {
                parent: 'curriculumVitae',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/curriculumVitae/curriculumVitae-dialog.html',
                        controller: 'CurriculumVitaeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('curriculumVitae', null, { reload: true });
                    }, function() {
                        $state.go('curriculumVitae');
                    })
                }]
            })
            .state('curriculumVitae.edit', {
                parent: 'curriculumVitae',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/curriculumVitae/curriculumVitae-dialog.html',
                        controller: 'CurriculumVitaeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['CurriculumVitae', function(CurriculumVitae) {
                                return CurriculumVitae.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('curriculumVitae', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('curriculumVitae.delete', {
                parent: 'curriculumVitae',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/curriculumVitae/curriculumVitae-delete-dialog.html',
                        controller: 'CurriculumVitaeDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['CurriculumVitae', function(CurriculumVitae) {
                                return CurriculumVitae.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('curriculumVitae', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
