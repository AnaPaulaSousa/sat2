/**
 * Created by ederbd on 09/06/16.
 */
angular.module('goDataTable', []).directive("goDataTable", function () {
    return {
        require: "goDataTable",
        controller: function ($scope, $element, $attrs, $location, $window) {

            var columns = [
                {
                    "class": "edit-table-cell",
                    "orderable": false,
                    "data": null,
                    "defaultContent": "<button class='btn btn-primary'><i class='fa fa-pencil'></i></button>"
                }
            ];

            this.registraItem = function (data) {
                columns.splice(columns.length - 1, 0, {"data": data});
            };

            var elemento = $('#' + $attrs.id);
            var dataTable = null;

            this.criaDataTable = function () {
                dataTable = elemento.DataTable({
                    "processing": true,
                    "serverSide": true,
                    dom: 'T clear rtip', //Remove filtro de pesquisa
                    "ajax": $attrs.ajax,
                    "language": {
                        "sEmptyTable": "Nenhum registro encontrado",
                        "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                        "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
                        "sInfoFiltered": "(Filtrados de _MAX_ registros)",
                        "sInfoPostFix": "",
                        "sInfoThousands": ".",
                        "sLengthMenu": "_MENU_ resultados por página",
                        "sZeroRecords": "Nenhum registro encontrado",
                        "sSearch": "Pesquisar",
                        "oPaginate": {
                            "sNext": "Próximo",
                            "sPrevious": "Anterior",
                            "sFirst": "Primeiro",
                            "sLast": "Último"
                        },
                        "oAria": {
                            "sSortAscending": ": Ordenar colunas de forma ascendente",
                            "sSortDescending": ": Ordenar colunas de forma descendente"
                        }
                    },
                    "columns": columns,
                    "orderCellsTop": true,
                    "bProcessing": false
                });
                dataTable.columns().every(function () {
                    var that = this;

                    var id = $(this.header()).attr("name") + "-search"

                    $("#" + id).on('keyup change', function () {
                        if (that.search() !== this.value) {
                            that.search(this.value).draw();
                        }
                    });
                });

                elemento.on('mouseover', 'tbody tr', function () {
                    var id = $($(this)[0].cells[0]).text()
                    var doClick = function () {
                        if ($attrs.editCtl && $scope[$attrs.editCtl]) {
                            var fClick = $scope[$attrs.editCtl];
                            fClick(id);
                        }
                        if ($attrs.editUrl) {
                            if (/(#)/.test($attrs.editUrl)){
                                var url = $attrs.editUrl.replace("#", "");
                                $location.path(url).search({id: id});
                                $window.location.href = $location.absUrl();
                            }else {
                                $window.location.href = $attrs.editUrl;
                            }
                        }
                    }
                    $($(this)[0].cells[columns.length - 1]).find("button").prop('onclick', null).off('click');
                    $($(this)[0].cells[columns.length - 1]).find("button").on("click", doClick);
                });
            }

        },
        link: function (scope, element, attr, ctrl) {
            ctrl.criaDataTable()
        }
    };
});
angular.module('goDataTable').directive("goDataTableItem", function () {
    return {
        require: "^goDataTable",
        link: function (scope, element, attr, ctrl) {
            ctrl.registraItem(attr.name);
        }
    };
});
