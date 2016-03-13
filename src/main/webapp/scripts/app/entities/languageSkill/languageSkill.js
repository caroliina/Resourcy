'use strict';

angular.module('resourcyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('languageSkill', {
                parent: 'entity',
                url: '/languageSkills',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.languageSkill.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/languageSkill/languageSkills.html',
                        controller: 'LanguageSkillController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('languageSkill');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('languageSkill.detail', {
                parent: 'entity',
                url: '/languageSkill/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'resourcyApp.languageSkill.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/languageSkill/languageSkill-detail.html',
                        controller: 'LanguageSkillDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('languageSkill');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'LanguageSkill', function($stateParams, LanguageSkill) {
                        return LanguageSkill.get({id : $stateParams.id});
                    }]
                }
            })
            .state('languageSkill.new', {
                parent: 'languageSkill',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/languageSkill/languageSkill-dialog.html',
                        controller: 'LanguageSkillDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    language: null,
                                    speaking: null,
                                    writing: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('languageSkill', null, { reload: true });
                    }, function() {
                        $state.go('languageSkill');
                    })
                }]
            })
            .state('languageSkill.edit', {
                parent: 'languageSkill',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/languageSkill/languageSkill-dialog.html',
                        controller: 'LanguageSkillDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['LanguageSkill', function(LanguageSkill) {
                                return LanguageSkill.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('languageSkill', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('languageSkill.delete', {
                parent: 'languageSkill',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/languageSkill/languageSkill-delete-dialog.html',
                        controller: 'LanguageSkillDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['LanguageSkill', function(LanguageSkill) {
                                return LanguageSkill.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('languageSkill', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
