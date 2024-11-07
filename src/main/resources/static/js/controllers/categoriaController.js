app.value("urlBase", "/api/")
		.controller("CategoriaController", function ($scope, $http, urlBase) {
			$scope.categoria = undefined;
			$scope.categorias = [];

			$scope.salvar = function (form) {
				console.log('1');
				if (form.$valid) {

					var metodo = 'POST';

					if ($scope.categoria.id) {
						metodo = 'PUT';
					}

					$http({
						method: metodo,
						url: urlBase + 'categorias/',
						data: $scope.categoria
					}).then(function successCallback(response) {
						$scope.atualizarTabela();
						alert('Dados salvos com sucesso!');
					}), function errorCallback(response) {
						$scope.deuErro();
					};
				} else {
					console.log('Por favor, preencha todos os campos corretamente');
				}
			};

			$scope.novo = function () {
				console.log('novo');
				$scope.categoria = {};
			};

			$scope.editar = function (categoria) {
				console.log('editar ' + categoria);
				$scope.categoria = categoria;
			};

			$scope.excluir = function (categoria) {
				const confirmacao = window.confirm('Tem certeza que deseja excluir este registro?');

				if (confirmacao) {
					var metodo = 'DELETE';

					$http({
						method: metodo,
						url: urlBase + 'categorias/' + categoria.id + '/'
					}).then(function successCallback(response) {
						$scope.atualizarTabela();
					}).catch(function errorCallback(response) {
						if (response.status === 440) {
							alert('Esta categoria está em uso nos produtos, não pode ser excluída');
						} else {
							$scope.deuErro();
						}
					});
				}
			};

			$scope.atualizarTabela = function () {
				$http({
					method: 'GET',
					url: urlBase + 'categorias/'
				}).then(function successCallback(response) {
					$scope.categorias = response.data;
					$scope.categoria = undefined;
				}), function errorCallback(response) {
					$scope.deuErro();
				};
			};

			$scope.deuErro = function () {
				alert('deu erro');
			};


			$scope.init = function () {
				$scope.atualizarTabela();
			};

			$scope.init();
		});