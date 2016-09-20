var acessibilidade = function (instancia) {
	if (instancia) {
		return instancia;
	}

	/*Alteração de fontes*/
	var alterarTamanhoFonte = function (escopo, tamanho) {
		$(escopo).find('*').each(function () {

			var tamanhoCorrente = parseInt($(this).css('font-size'));
			$(this).attr("data-font-atual", tamanhoCorrente);

			var tamanhoAtual = parseInt($(this).data("font-atual"));

			if ($(this).attr("data-font-original") == undefined) {
				$(this).attr("data-font-original", tamanhoAtual);
			}
		});

		$(escopo).find('*').each(function () {
			var atual = parseInt($(this).attr("data-font-atual"));

			var novoTamanho = parseInt(atual + tamanho);

			$(this).css('font-size', novoTamanho);
			$(this).attr('data-font-atual', novoTamanho);
		});
	};

	var voltarFonteOriginal = function (escopo) {
		$(escopo).find('*').each(function () {

			var valorAntigo = parseInt($(this).attr('data-font-original'));

			if (valorAntigo && valorAntigo != 0) {
				$(this).css('font-size', valorAntigo);
				$(this).attr('data-font-atual', valorAntigo);
			}
		});
	};

	var resetarTamanhoFonte = function () {
		voltarFonteOriginal($(".content"));
	};

	var aumentarTamanhoFonte = function () {
		alterarTamanhoFonte($(".content"), +1);
	};

	var diminuirTamanhoFonte = function () {
		alterarTamanhoFonte($(".content"), -1);
	};

	var aplicarAltoContraste = function () {
		if ($('body').hasClass('contraste'))
		{
			$('body').removeClass("contraste");
		} else {
			 $('body').addClass("contraste");
		}
	};

	var aplicarRelevo = function () {
		if ($('body').hasClass('relevo')) {
			$('body').removeClass("relevo");
		} else {
			$('body').addClass("relevo");
		}
	};

	var inverterCores =  function () {
		var css = 'html {-webkit-filter: invert(100%);' + '-moz-filter: invert(100%);' + '-o-filter: invert(100%);' + '-ms-filter: invert(100%); }',
		head = document.getElementsByTagName('head')[0],
		style = document.createElement('style');

		if ($('body').hasClass('contraste')) {
			$('body').removeClass("contraste");
		}

		if (!window.counter) {
			window.counter = 1;
		} else {
			window.counter++;
			if(window.counter % 2 == 0) {
				var css = 'html {-webkit-filter: invert(0%); -moz-filter: invert(0%); -o-filter: invert(0%); -ms-filter: invert(0%); }'
			}
		};

		style.type = 'text/css';
		if (style.styleSheet) {
			style.styleSheet.cssText = css;
		} else {
			style.appendChild(document.createTextNode(css));
		}

		head.appendChild(style);
	};

	return {
		aumentarTamanhoFonte: aumentarTamanhoFonte,
		diminuirTamanhoFonte: diminuirTamanhoFonte,
		inverterCores: inverterCores,
		aplicarAltoContraste: aplicarAltoContraste,
		aplicarRelevo: aplicarRelevo,
		resetarTamanhoFonte: resetarTamanhoFonte
	}
}(acessibilidade || undefined);



/**
 * Inicio - Funcoes de apoio ao ctrl+click e shift+click para a lista paginada.
 * @param nodes
 */
function mySelectEventHandler(nodes) {
	if (myDeselectList) {
		var nodeList = myDeselectList;
		myDeselectList = null;
		this.fnDeselect(nodeList);
	}
	if (mySelectList) {
		var nodeList = mySelectList;
		mySelectList = null;
		this.fnSelect (nodeList);
	}
}
 
function myGetRangeOfRows(oDataTable, fromNode, toNode) {
	var
		fromPos = oDataTable.fnGetPosition(fromNode),
		toPos = oDataTable.fnGetPosition(toNode);
		oSettings = oDataTable.fnSettings(),
		fromIndex = $.inArray(fromPos, oSettings.aiDisplay),
		toIndex = $.inArray(toPos, oSettings.aiDisplay),
		result = [];
 
	if (fromIndex >= 0 && toIndex >= 0 && toIndex != fromIndex) {
		for (var i=Math.min(fromIndex,toIndex); i < Math.max(fromIndex,toIndex); i++) {
			var dataIndex = oSettings.aiDisplay[i];
			result.push(oSettings.aoData[dataIndex].nTr);
		}
	}
	return result.length>0?result:null;
}
/**
 * Fim - Funcoes de apoio ao ctrl+click e shift+click para a lista paginada.
 */
