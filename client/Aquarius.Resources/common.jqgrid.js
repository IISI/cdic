$.extend($cap._fn, {
    iGrid: {
        rule: "[role=grid]",
        fn: {
            /**
             * 移至grid 指定位置
             * @param {Object} selector
             */
            iGridScrollTo: function(selector){
                var $this = $(this).parents(".ui-jqgrid-bdiv:eq(0)");
                if ($this.scrollTo) {
                    $this.scrollTo(selector);
                }
                return $(this);
            },
            
            /**
             * 自動設定寬度
             * @param {Object} maxWidth
             */
            iGridFitSize: function(maxWidth){
                var self = $(this).parents(".ui-jqgrid");
                $(this).each(function(){
                    maxWidth = maxWidth || self.parent().width();
                    if (self.width() > maxWidth) {
                        self.css({
                            width: (maxWidth * 98) / 100
                        }).find(".ui-jqgrid-view,.ui-jqgrid-hdiv,.ui-jqgrid-bdiv,.ui-jqgrid-pager").css({
                            width: (maxWidth * 98) / 100
                        });
                    }
                });
            },
            
            /**
             * Grid hide (extend jQuery hide)
             * @param {Object} speed
             * @param {function} callback
             */
            iGridHide: function(speed, callback){
                $("#gbox_" + $(this).attr("id")).hide(speed, callback);
                return $(this);
            },
            /**
             * Gird show (extend jQuery show)
             * @param {Object} speed
             * @param {function} callback
             */
            iGridShow: function(speed, callback){
                $("#gbox_" + $(this).attr("id")).show(speed, callback);
                return $(this);
            },
            
            /**
             * 將GridData轉換為Array<JSON>值
             */
            iGridSerialize: function(stringify){
                var data = [];
                if ($(this).attr('role') == 'grid') {
                    var tGrid = $(this);
                    tGrid.find('tr[id]').each(function(){
                        data.push($.extend(tGrid.getRowData($(this).attr('id')), {
                            rowId: $(this).attr('id')
                        }));
                    });
                }
                return stringify ? JSON.stringify(data) : data;
            },
            
            /**
             * 將TreeGird全部關閉
             */
            iGridCollapse: function(rid){
                var $this = $(this);
                $.each($this[0].rows, function(i, e){
                    if ($(e).find('td[aria-describedby=gridview_isLeaf]').attr('title') != 'true') {
                    
                        $this.jqGrid('collapseRow', e);
                        $this.jqGrid('collapseNode', e);
                    }
                });
                $this = null;
            },
            
            /**
             * 將TreeGrid全部打開
             */
            iGridExpand: function(rid){
                var $this = $(this);
                $.each($this[0].rows, function(i, e){
                    if ($(e).find('td[aria-describedby=gridview_isLeaf]').attr('title') != 'true') {
                        $this.jqGrid('expandRow', e);
                        $this.jqGrid('expandNode', e);
                    }
                });
                $this = null;
            },
            
            /**
             * add data to grid by Array[json,json,....] or Array[array,array,....]
             * @param {Object} datas
             */
            addGridData: function(datas){
                var $this = $(this), ids = $this.getGridParam("colIds"), rowData, colKey = this[0].colKey;
                if (datas instanceof Array) {
                    for (var data in datas) {
                        var _data = (datas[data] instanceof Array) ? _convertJson(datas[data]) : datas[data], rowId = _data[$this[0].colKey];
                        var _new = !$this[0].rows.namedItem(rowId);
                        $this[_new ? "addRowData" : "setRowData"](rowId, _data);
                    }
                }
                return $this;
                function _convertJson(d){
                    var td = {}, i = 0;
                    for (var i in ids) {
                        td[ids[i]] = d[i++];
                    }
                    return td;
                }
            }
        }
    }
});


// autoBuildGird index
$.ui.iGridIndex = $.ui.iGridIndex || 0;
jQuery.fn.extend({
    /**
     * autoBuildGrid by iqGrid
     * @param {JSON} settings
     */
    iGrid: function(settings){
        var obj = $(this);
        var gridHtml = '<table id="{id}" class="scroll" cellpadding="0" cellspacing="0"></table>';
        var pageHtml = '<div id="{id}" class="scroll" style="text-align:center;"></div>';
        $.ui.iGridIndex++;
        var gridId = settings.id || obj.attr("id") ||
        ("iGrid" + $.ui.iGridIndex);
        var pagerId = settings.pagerId ||
        ('iGridPage' + $.ui.iGridIndex);
        var sortBy = settings.sortBy || settings.handler && 'all' || 'page';
        var colIds = [];
        
        var _key = undefined, _colNames = (function(array){
            var newColNames = [];
            for (var col in array) {
                newColNames.push(array[col].colHeader || array[col].name);
                colIds.push(array[col].name);
                !_key && (_key = (array[col].key && array[col].name));
            }
            return newColNames;
        })(settings.colModel || []);
        var colNames = settings.colNames || _colNames;
		
        settings = $.extend({
            datatype: (!settings.localFirst && (settings.url || settings.handler)) ? 'json' : 'local',
            forceFit: true,
            url: (!settings.localFirst && settings.handler) ? (__ajaxHandler + '&_pa=' + settings.handler) : '',
            // scroll: true,
            pager: pagerId,
            mtype: 'POST',
            sortname: 'oid',
            //treeGridModel: 'adjacency',
            imgpath: '../../jquery/ui/theme/sunny/images',
            needPager: true,
            rowNum: (settings.needPager == false) ? 1000 : 30,
            rowList: [30],
            multiselect: false,
            viewrecords: true,
            loadonce: false,
            autowidth: true,
            localFirst: false,
            //ExpandColumn: 'colunmid',
            onSortCol: function(index){
                $("#" + gridId).setGridParam({
                    datatype: 'page' === sortBy ? 'local' : 'json',
                    page: 1
                });
                $.extend(this.p.postData, {
                    isGroup: (this.p.ExpandColumn == index)
                });
                
            },
            onPaging: function(parm){
                $("#" + gridId).setGridParam({
                    datatype: 'json'
                });
                
                if ('records' == parm) {
                    $("#" + gridId).setGridParam({
                        page: 1
                    });
                }
            }
        }, settings, {
            postData: $.extend(settings.postData ||
            {}, {
                _columnParam: JSON.stringify(settings.colModel, null)
            })
        }, {
            colNames: colNames
        });
        var maxWidth = obj.parent().width();
        var newgrid = obj.append($(gridHtml.replace("{id}", gridId))).append(settings.needPager ? pageHtml.replace("{id}", pagerId) : "").find("#" + gridId).jqGrid($.extend(settings, {})).hideCol('cb');
        
        //增加 Grid 功能
        newgrid = $.extend(newgrid, $cap._fn.iGrid.fn);
        
        newgrid.each(function(){
            this.p.id = gridId;
            this.p.parent = parent;
            this.p.colIds = colIds;
            this.p.$_this = newgrid;
            this.colKey = this.p.colKey = _key;
        }).iGridFitSize(maxWidth);
        
        settings.localFirst &&
        newgrid.setGridParam({
            datatype: (settings.url || settings.handler) ? 'json' : 'local',
            url: settings.handler ? (__ajaxHandler + '&_pa=' + settings.handler) : ''
        });
        obj.removeAttr("id");
        return newgrid;
    }
});
