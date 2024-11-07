var app = angular.module('meuApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
    .when("/home", {
        templateUrl: "home.html",
        controller: "HomeController as homeController"
    })
	.when("/pais", {
        templateUrl: "cadastros/pais.html",
        controller: "PaisController as paisController"
    })
	.when("/estado", {
        templateUrl: "cadastros/estado.html",
        controller: "EstadoController as estadoController"
    })
	.when("/cidade", {
        templateUrl: "cadastros/cidade.html",
        controller: "CidadeController as cidadeController"
    })
	.when("/categoria", {
        templateUrl: "cadastros/categoria.html",
        controller: "CategoriaController as categoriaController"
    })
	.when("/produto", {
        templateUrl: "cadastros/produto.html",
        controller: "ProdutoController as produtoController"
    })
    .otherwise({
        redirectTo: "/"
    });
});

app.controller('MainController', function() {
    this.mensagem = "Controlador principal!";
});