var AJAX_HANDLER_BUNDLE = '_ab';
var AJAX_HANDLER_BUNDLE_VERSION = '_v';
var AJAX_HANDLER = '_ah';

var JS_BUNDLE_ID_KEY = "_jbi";
var JS_BUNDLE_VALUE_KEY = "_jbv";
var JS_HANDLER_NAME_KEY = "_jhn";

function getDefaultJsHandler() {
	var data = {};
	data[AJAX_HANDLER_BUNDLE] = 'platform.aquarius.embedserver';
	data[AJAX_HANDLER] = 'platform.aquarius.embedserver.AquariusAjaxJSHandler';
	return data;
}
