function $cap() {
    var _tmp = $.apply(this, arguments), _t, _r;
    if (_tmp.size() === 1) {
        for ( var j in $cap._fn) {
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

var CommonAPI = {
    iConfirmDialog : function(settings, action) {
        var dialogId = settings.id || "iConfirmDialog";
        if (action && action === 'close') {
            $("#" + settings).dialog('close');
        } else if (settings === 'close') {
            $('#' + dialogId).dialog('close');
        } else {
            var s = $.extend({
                title : "確認",
                closeName : "關閉",
                closeBtnAction : null
            }, settings);
            var cDialog = $('#' + s.id);
            cDialog = ((cDialog.size()) ? cDialog : $(
                    "<div style='iConfirmDialog' id='" + dialogId + "' title='" + s.title + "'><span id='" + dialogId
                            + "Message'></span></div>").appendTo("body"));
            var defaultButton = {};
            defaultButton[s.closeName] = function() {
                cDialog.dialog('close');
            };
            var tmpClose = s.close || function() {
            };
            delete s['close'];
            cDialog.dialog($.extend({
                bgiframe : false,
                autoOpen : false,
                modal : true
            }, $.extend(s, {
                close : function() {
                    tmpClose();
                    cDialog.dialog('destroy');
                }
            }), {
                buttons : $.extend(s.buttons, defaultButton)
            }));

            return cDialog.dialog('open').find('#' + dialogId + 'Message').html(s.message || "是否執行此動作？" || "").end();
        }
    },
    showMessage : function(title, message, action) {
        var randomID = "sysMessage" + parseInt(Math.random() * 1000, 10);
        var closeBtn = {};
        // closeBtn[i18n.def.close] = function(){
        // CommonAPI.iConfirmDialog(randomID, 'close');
        // };
        return CommonAPI.iConfirmDialog({
            id : randomID,
            closeName : "關閉",
            title : message && !jQuery.isFunction(message) ? title : "Info",
            message : message && !jQuery.isFunction(message) ? message : title,
            // buttons: closeBtn,
            close : function() {
                // $("#" + randomID).remove();
                action && action();
                jQuery.isFunction(message || "") && message();
            }
        });
    }
}

// 檔案上傳動作
jQuery.extend({
    capFileUpload : function(setting) {
        var s = $.extend({
            uploadMsg : "檔案上傳中請耐心等候..."
        }, setting);
        var telm = $("#" + s.fileElementId), val = telm.val();

        if (!val.length) {
            CommonAPI.showMessage("請先選擇檔案");
            return;
        }
        // 檢核副檔名
        if (!s.fileCheck)
            return;
        var regs = "";
        $(s.fileCheck).each(function(index, value) {
            regs += (value + "|");
        });

        regs = regs.replace(/\|$/, "");
        if (!((new RegExp("(" + regs + ")$", "i")).test(val))) {
            CommonAPI.showMessage("請使用正確檔案,副檔名為 (" + regs + ")");
            return;
        } else {
            var uploadMsg = CommonAPI.showMessage(s.uploadMsg);
            $.ajaxFileUpload($.extend({}, s, {
                secureuri : false,
                complete : function(xhr, status) {
                    uploadMsg.dialog('close');
                    try {
                        var json = xhr.responseText != "" ? JSON.parse(xhr.responseText) : {};
                    } catch (e) {
                        console.log("ajaxError", e);
                        json = {};
                    }
                    s.complete && s.complete(xhr, status, json);
                },
                error : function(data, status, e) {
                    uploadMsg.dialog('close');
                    CommonAPI.showMessage("檔案讀取失敗，請重新上傳");
                }
            }));
        }
    }
});
