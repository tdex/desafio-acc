app.controller('MainController', function ($scope, $mdSidenav, $mdDialog) {
  var vm = $scope;
  var originatorEv;
  vm.title = 'Contratos';

  // abre e fecha menu
  vm.toggleLeft = buildToggler('left');
  vm.toggleRight = buildToggler('right');

  function buildToggler(componentId) {
    return function () {
      $mdSidenav(componentId).toggle();
    };
  }

  vm.openMenuReordenacao = function ($mdMenu, ev) {
    originatorEv = ev;
    $mdMenu.open(ev);
  };

  vm.opcoesMenu = [
    {order: 'createdOn', nome: 'Data criação'},
    {order: 'descricao', nome: 'Descrição'},
  ]
});