app.controller('ListaController', function ($scope, $http, ListaFactory, $mdDialog, $mdToast, $route) {
  var vm = $scope;

  vm.lista = {};

  vm.getListaContratos = function () {
    $http.get('//localhost:8081/acme/api/tarefa/').then(function (res) {
      vm.lista = ListaFactory.convertList(res.data);
    });
  };

  vm.selecionaTodos = function () {
    if (vm.lista) {
      vm.lista.forEach(function (ct) {
        ct.selecionado = !angular.isUndefined(ct.selecionado) ? vm.todosSelecionados : true;
      });
      vm.todosSelecionados = !vm.todosSelecionados;
    }
  };

  vm.excluirContratosSelecionados = function () {
    if (vm.lista) {
      for (var tamanhoLista = vm.lista.length, ct = tamanhoLista - 1; ct >= 0; ct--) {
        if (vm.lista[ct].selecionado) {
          // vm.lista.splice(ct, 1);
          $http.delete(`//localhost:8081/acme/api/tarefa/${vm.lista[ct].id}`)
            .then(function () {
              console.log(`tarefa removida.`);
            })
        }
      }
    }
    $route.reload();
    vm.excluir = false;
  };

  vm.ordenarListaPor = function (campo) {
    vm.ordemLista = campo;
  };

  vm.selecionaContrato = function (ev, contrato) {
    $mdDialog.show({
      controller: contratoModalController,
      templateUrl: 'resources/modulos/contrato/contrato.modal.template.html',
      parent: angular.element(document.body),
      targetEvent: ev,
      clickOutsideToClose: false,
      fullscreen: true,
      locals: {
        contrato: contrato
      }
    });
  };

  vm.addContrato = function (ev) {
    $mdDialog.show({
      controller: contratoModalController,
      templateUrl: 'resources/modulos/contrato/contrato.modal.template.html',
      parent: angular.element(document.body),
      targetEvent: ev,
      clickOutsideToClose: false,
      fullscreen: true,
      locals: {
        contrato: undefined
      }
    });
  };

  vm.alterarStatusTarefa = function (idTarefa) {
    $http.patch(`//localhost:8081/acme/api/tarefa/${idTarefa}`)
      .then(function (res) {
        vm.getListaContratos();
      })
  }

  function contratoModalController($scope, $mdDialog, $http, contrato) {
    $scope.edicao = false;
    $scope.novoct = false;

    (function init() {
      if (contrato) {
        $scope.ct = contrato;
      } else {
        $scope.ct = {};
        $scope.edicao = true;
        $scope.novoct = true;
      }
    })();

    $scope.hide = function () {
      $mdDialog.hide();
    };

    $scope.cancel = function () {
      $mdDialog.cancel();
    };

    $scope.salvar = function (novoCt) {
      $http.post('//localhost:8081/acme/api/tarefa', novoCt).then(function sucesso() {
        notification('Tarefa adicionada');
      }, function error(err) {
        notification('Erro ao salvar tarefa');
      });

      $route.reload();
      $mdDialog.hide();
    };
  }

  vm.confirmaExclusao = function (ev) {
    var totalSelecionado = 0;
    vm.lista.forEach(function (item) {
      if (item.selecionado) {
        totalSelecionado++;
      }
    });

    if (totalSelecionado > 0) {
      var confirm = $mdDialog.confirm()
        .title('Tem certeza que deseja excluir as tarefas selecionadas?')
        .textContent('Esta ação não poderá ser desfeita.')
        .ariaLabel('Confirmação de exclusão')
        .targetEvent(ev)
        .ok('Sim!')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function () {
        vm.excluirContratosSelecionados();
        vm.getListaContratos();
      });
    } else {
      notification('Selecione alguma tarefa para excluir');
    }

  };

  function notification(text) {
    $mdToast.show(
      $mdToast.simple()
        .textContent(text)
        .position("bottom right")
        .hideDelay(3000)
    );
  };

  function activate() {
    vm.getListaContratos();
  }

  activate();
});