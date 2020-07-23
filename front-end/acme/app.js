var app = angular.module('myApp', [
  'ngRoute',
  'ngMaterial'
]);

app.config(function ($routeProvider, $locationProvider, $mdDateLocaleProvider) {
  $locationProvider.hashPrefix('');

  $routeProvider
    .otherwise({ redirectTo: '/' })
    .when('/', {
      title: 'Home',
      templateUrl: 'resources/modulos/lista/lista.template.html',
      controller: 'ListaController',
      controllerAs: 'listCtrl'
    });

  // datePicker config
  $mdDateLocaleProvider.formatDate = function (date) {
    return date ? moment(date).format('DD/MM/YYYY') : '';
  };

  $mdDateLocaleProvider.parseDate = function (dateString) {
    var m = moment(dateString, 'DD/MM/YYYY', true);
    return m.isValid() ? m.toDate() : new Date(NaN);
  };
  $mdDateLocaleProvider.months = ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'];
  $mdDateLocaleProvider.shortMonths = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];
  $mdDateLocaleProvider.days = ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta'];
  $mdDateLocaleProvider.shortDays = ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'];
});