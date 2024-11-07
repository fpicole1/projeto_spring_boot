app.value("urlBase", "/api/")
		.controller("ProdutoController", function ($scope, $http, urlBase) {
			$scope.produto = undefined;
			$scope.produtos = [];
			
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

					if ($scope.produto.id) {
						metodo = 'PUT';
					}

					$http({
						method: metodo,
						url: urlBase + 'produtos/',
						data: $scope.produto
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
				$scope.produto = {};
			};

			$scope.editar = function (produto) {
				console.log('editar ' + produto);
				$scope.produto = produto;
			};

			$scope.excluir = function (produto) {
				const confirmacao = window.confirm('Tem certeza que deseja excluir este registro?');

				if (confirmacao) {
					var metodo = 'DELETE';

					$http({
						method: metodo,
						url: urlBase + 'produtos/' + produto.id + '/'
					}).then(function successCallback(response) {
						$scope.atualizarTabela();
					}).catch(function errorCallback(response) {
						if (response.status === 440) {
							alert('Este produto está em uso, não pode ser excluído');
						} else {
							$scope.deuErro();
						}
					});
				}
			};

			$scope.atualizarTabela = function () {
				$http({
					method: 'GET',
					url: urlBase + 'produtos/'
				}).then(function successCallback(response) {
					$scope.produtos = response.data;
					$scope.produto = undefined;
				}), function errorCallback(response) {
					$scope.deuErro();
				};
			};

			$scope.preencherCategorias = function () {
				$http({
					method: 'GET',
					url: urlBase + 'categorias/'
				}).then(function successCallback(response) {
					$scope.categorias = response.data;
				}), function errorCallback(response) {
					$scope.deuErro();
				};
			};

			$scope.deuErro = function () {
				alert('deu erro');
			};


			$scope.init = function () {
				$scope.atualizarTabela();
				$scope.preencherCategorias();
			};

			$scope.init();
		});