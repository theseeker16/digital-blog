(function() {
    'use strict';

    angular
        .module('digitalBlogApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('categoria', {
            parent: 'entity',
            url: '/categoria',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'Categorias'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/categoria/categorias.html',
                    controller: 'CategoriaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('categoria-detail', {
            parent: 'categoria',
            url: '/categoria/{id}',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'Categoria'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/categoria/categoria-detail.html',
                    controller: 'CategoriaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Categoria', function($stateParams, Categoria) {
                    return Categoria.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'categoria',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('categoria-detail.edit', {
            parent: 'categoria-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categoria/categoria-dialog.html',
                    controller: 'CategoriaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Categoria', function(Categoria) {
                            return Categoria.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('categoria.new', {
            parent: 'categoria',
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categoria/categoria-dialog.html',
                    controller: 'CategoriaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nombre: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('categoria', null, { reload: 'categoria' });
                }, function() {
                    $state.go('categoria');
                });
            }]
        })
        .state('categoria.edit', {
            parent: 'categoria',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categoria/categoria-dialog.html',
                    controller: 'CategoriaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Categoria', function(Categoria) {
                            return Categoria.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('categoria', null, { reload: 'categoria' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('categoria.delete', {
            parent: 'categoria',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categoria/categoria-delete-dialog.html',
                    controller: 'CategoriaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Categoria', function(Categoria) {
                            return Categoria.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('categoria', null, { reload: 'categoria' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
