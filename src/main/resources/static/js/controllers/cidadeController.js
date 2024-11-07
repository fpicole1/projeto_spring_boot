app.value("urlBase", "/api/")
		.controller("CidadeController", function ($scope, $http, urlBase) {
			$scope.cidade = undefined;
			$scope.cidades = [];

			$scope.validarEntrada = function(event) {
				// Obtém o código da tecla pressionada
				const key = event.key;

				// Permite teclas de controle (como Backspace, Delete, etc.)
				if (key === 'Backspace' || key === 'Tab' || key === 'Enter' || key === 'Escape') {
					return;
				}

				// Verifica se o caractere digitado não é um número
				if (!/^[0-9]*$/.test(key)) {
					// Cancela o evento se não for um número
					event.preventDefault();
					alert('Por favor, insira apenas números no campo');
				}
			};

			$scope.salvar = function (form) {
				console.log('1');
				if (form.$valid) {

					var metodo = 'POST';

					if ($scope.cidade.id) {
						metodo = 'PUT';
					}

					$http({
						method: metodo,
						url: urlBase + 'cidades/',
						data: $scope.cidade
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
				$scope.cidade = {};
			};

			$scope.editar = function (cidade) {
				console.log('editar ' + cidade);
				$scope.cidade = cidade;
			};

			$scope.excluir = function (cidade) {
				const confirmacao = window.confirm('Tem certeza que deseja excluir este registro?');

				if (confirmacao) {
					var metodo = 'DELETE';

					$http({
						method: metodo,
						url: urlBase + 'cidades/' + cidade.id + '/'
					}).then(function successCallback(response) {
						$scope.atualizarTabela();
					}), function errorCallback(response) {
						$scope.deuErro();
					};
				}
			};

			$scope.atualizarTabela = function () {
				$http({
					method: 'GET',
					url: urlBase + 'cidades/'
				}).then(function successCallback(response) {
					$scope.cidades = response.data;
					$scope.cidade = undefined;
				}), function errorCallback(response) {
					$scope.deuErro();
				};
			};

			$scope.preencherEstados = function () {
				$http({
					method: 'GET',
					url: urlBase + 'estados/'
				}).then(function successCallback(response) {
					$scope.estados = response.data;
				}), function errorCallback(response) {
					$scope.deuErro();
				};
			};

			$scope.deuErro = function () {
				alert('deu erro');
			};


			$scope.init = function () {
				$scope.atualizarTabela();
				$scope.preencherEstados();
			};

			$scope.init();
		});