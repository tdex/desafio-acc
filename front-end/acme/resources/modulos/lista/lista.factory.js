app.factory('ListaFactory', function () {

  function convertList(lista) {
    var retorno = [];
    if (lista) {
      lista.forEach(function (item) {
        var ct = new itemLista(item);
        retorno.push(ct);
      });
      return retorno;
    }
  }

  function itemLista(item) {
      this.id = item['id'],
      this.data = item['data'],
      this.createdOn = item['createdOn'],
      this.descricao = item['descricao'],
      this.checked = item['checked']
      this.selecionado = false
  }

  return {
    convertList: convertList
  }
});