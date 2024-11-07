app.value("urlBase", "/api/")
		.controller("PaisController", function ($scope, $http, urlBase) {
			$scope.pais = undefined;
			$scope.paises = [];

			console.log('0');

			$scope.salvar = function (form) {
				console.log('1');
				if (form.$valid) {

					var metodo = 'POST';

					if ($scope.pais.id) {
						metodo = 'PUT';
					}

					$http({
						method: metodo,
						url: urlBase + 'paises/',
						data: $scope.pais
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
				$scope.pais = {};
			};

			$scope.editar = function (pais) {
				console.log('editar ' + pais);
				$scope.pais = pais;
			};

			$scope.excluir = function (pais) {
				const confirmacao = window.confirm('Tem certeza que deseja excluir este registro?');

				if (confirmacao) {
					var metodo = 'DELETE';

					$http({
						method: metodo,
						url: urlBase + 'paises/' + pais.id + '/'
					}).then(function successCallback(response) {
						$scope.atualizarTabela();
					}).catch(function errorCallback(response) {
						if (response.status === 440) {
							alert('Este país está em uso nos estados, não pode ser excluído');
						} else {
							$scope.deuErro();
						}
					});
				}
			};

			$scope.atualizarTabela = function () {
				$http({
					method: 'GET',
					url: urlBase + 'paises/'
				}).then(function successCallback(response) {
					$scope.paises = response.data;
					$scope.pais = undefined;
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