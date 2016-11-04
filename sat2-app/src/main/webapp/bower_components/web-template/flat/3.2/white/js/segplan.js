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



