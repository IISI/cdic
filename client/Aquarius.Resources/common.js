function $cap(){
    var _tmp = $.apply(this, arguments), _t, _r;
    if (_tmp.size() === 1) {
        for (var j in $cap._fn) {
            _r = $cap._fn[j];
            if ($.isFunction(_r.rule) && _r.rule(_t) || typeof _r.rule === "string" && _tmp.is(_r.rule)) {
                $.extend(_tmp, _r.fn);
            }
        }
    }
    return _tmp;
}

$cap._fn = {};

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
