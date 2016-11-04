var acessibilidade = function (instancia) {
	if (instancia) {
		return instancia;
	}

	/*Alteração de fontes*/
	var alterarTamanhoFonte = function (escopo, tamanho) {

		if(localStorage.getItem("fonte")){
			localStorage.setItem("fonte", parseInt(Number(localStorage.getItem("fonte")) + Number(tamanho)));
		} else {
			localStorage.setItem("fonte", tamanho);
		}

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

		if(localStorage.getItem("fonte")){
			localStorage.removeItem("fonte");
		}
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

	var aplicarRelevo = function () {
		if ($('body').hasClass('relevo')) {
			$('body').removeClass("relevo");
			if(localStorage.getItem("relevo")){
				localStorage.removeItem("relevo");
			}
		} else {
			$('body').addClass("relevo");
			localStorage.setItem("relevo", true);
		}
	};

	var aplicarAltoContraste = function () {
		if ($('body').hasClass('contraste')) {
			$('body').removeClass("contraste");
			if(localStorage.getItem("contraste")){
				localStorage.removeItem("contraste");
			}
		} else {
			if(localStorage.getItem("inversao-de-cor")){
				inverterCores();
			}

			$('body').addClass("contraste");
			localStorage.setItem("contraste", true);
		}
	};


	var inverterCores =  function () {
		if(localStorage.getItem("contraste")){
			aplicarAltoContraste();
		}

		var css = 'html {-webkit-filter: invert(100%);' + '-moz-filter: invert(100%);' + '-o-filter: invert(100%);' + '-ms-filter: invert(100%); }',
			head = document.getElementsByTagName('head')[0],
			style = document.createElement('style');
		localStorage.setItem("inversao-de-cor", true);


		if (!window.counter) {
			window.counter = 1;
		} else {
			window.counter++;
			if(window.counter % 2 == 0) {
				var css = 'html {-webkit-filter: invert(0%); -moz-filter: invert(0%); -o-filter: invert(0%); -ms-filter: invert(0%); }'
				if(localStorage.getItem("inversao-de-cor")){
					localStorage.removeItem("inversao-de-cor");
				}
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
		resetarTamanhoFonte: resetarTamanhoFonte,
		alterarTamanhoFonte: alterarTamanhoFonte
	}
}(acessibilidade || undefined);

$(document).ready(function() {
	if(localStorage.getItem("contraste")){
		$('body').addClass("contraste");
	}

	if(localStorage.getItem("relevo")){
		$('body').addClass("relevo");
	}

	if(localStorage.getItem("inversao-de-cor")){
		acessibilidade.inverterCores();
	}
});


(function() { // Overriding XMLHttpRequest
	var oldXHR = window.XMLHttpRequest;

	function newXHR() {
		var realXHR = new oldXHR();

		realXHR.addEventListener("readystatechange", function() {
			if(document.readyState === 'complete') {
				setTimeout(function(){
					if(localStorage.getItem("fonte")){
						var fonteLocal = localStorage.getItem("fonte");
						localStorage.removeItem("fonte");
						acessibilidade.resetarTamanhoFonte();
						acessibilidade.alterarTamanhoFonte($(".content"), Number(fonteLocal));
					}
				}, 100);
			}
		}, false);
		return realXHR;
	}

	window.XMLHttpRequest = newXHR;
})();