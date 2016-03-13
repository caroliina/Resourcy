'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('additionalSkill', {
                parent: 'entity',
                url: '/additionalSkills',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.additionalSkill.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/additionalSkill/additionalSkills.html',
                        controller: 'AdditionalSkillController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('additionalSkill');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('additionalSkill.detail', {
                parent: 'entity',
                url: '/additionalSkill/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.additionalSkill.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/additionalSkill/additionalSkill-detail.html',
                        controller: 'AdditionalSkillDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('additionalSkill');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'AdditionalSkill', function($stateParams, AdditionalSkill) {
                        return AdditionalSkill.get({id : $stateParams.id});
                    }]
                }
            })
            .state('additionalSkill.new', {
                parent: 'additionalSkill',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/additionalSkill/additionalSkill-dialog.html',
                        controller: 'AdditionalSkillDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    type: null,
                                    description: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('additionalSkill', null, { reload: true });
                    }, function() {
                        $state.go('additionalSkill');
                    })
                }]
            })
            .state('additionalSkill.edit', {
                parent: 'additionalSkill',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/additionalSkill/additionalSkill-dialog.html',
                        controller: 'AdditionalSkillDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['AdditionalSkill', function(AdditionalSkill) {
                                return AdditionalSkill.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('additionalSkill', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('additionalSkill.delete', {
                parent: 'additionalSkill',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/additionalSkill/additionalSkill-delete-dialog.html',
                        controller: 'AdditionalSkillDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['AdditionalSkill', function(AdditionalSkill) {
                                return AdditionalSkill.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('additionalSkill', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
