app.value("urlBase", "/api/")
		.controller("EstadoController", function ($scope, $http, urlBase) {
			$scope.estado = undefined;
			$scope.estados = [];

			console.log('0');
			
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

					if ($scope.estado.id) {
						metodo = 'PUT';
					}

					$http({
						method: metodo,
						url: urlBase + 'estados/',
						data: $scope.estado
					}).then(function successCallback(response) {
						$scope.atualizarTabela();
						alert('Dados salvos com sucesso!');
					}), function errorCallback(response) {
						$scope.deuErro();
					};
				} else {
					$scope.erro = 'Erro ao salvar a cidade: ';
					console.log('Por favor, preencha todos os campos corretamente');
				}
			};

			$scope.novo = function () {
				console.log('novo');
				$scope.estado = {};
			};

			$scope.editar = function (estado) {
				console.log('editar ' + estado);
				$scope.estado = estado;
			};

			$scope.excluir = function (estado) {
				const confirmacao = window.confirm('Tem certeza que deseja excluir este registro?');

				if (confirmacao) {
					var metodo = 'DELETE';

					$http({
						method: metodo,
						url: urlBase + 'estados/' + estado.id + '/'
					}).then(function successCallback(response) {
						$scope.atualizarTabela();
					}).catch(function errorCallback(response) {
						if (response.status === 440) {
							alert('Este estado está em uso nos estados, não pode ser excluído');
						} else {
							$scope.deuErro();
						}
					});
				}
			};

			$scope.atualizarTabela = function () {
				$http({
					method: 'GET',
					url: urlBase + 'estados/'
				}).then(function successCallback(response) {
					$scope.estados = response.data;
					$scope.estado = undefined;
				}), function errorCallback(response) {
					$scope.deuErro();
				};
			};

			$scope.preencherPaises = function () {
				$http({
					method: 'GET',
					url: urlBase + 'paises/'
				}).then(function successCallback(response) {
					$scope.paises = response.data;
				}), function errorCallback(response) {
					$scope.deuErro();
				};
			};

			$scope.deuErro = function () {
				alert('deu erro');
			};


			$scope.init = function () {
				$scope.atualizarTabela();
				$scope.preencherPaises();
			};

			$scope.init();
		});

