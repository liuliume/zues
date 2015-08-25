/**
 * 配置项目中的默认属性
 *
 **/
function getProperty(aoData , sProperty){
	for( var i = 0 ; i < aoData.length ; i++ ){
		oData = aoData[i];
		if( oData.name === sProperty ){
			return oData.value;
		}
	}
	return undefined;
}
$.extend(true, $.fn.dataTable.defaults, {
	"sPaginationType": "full_numbers",
	"bSort": true,
//	"bJQueryUI":true,
    "sErrMode": "throw",
	"bStateSave": true,
	"bFilter": true,
	"bServerSide": true,
	"sServerMethod": "POST",
	"bAutoWidth": false,
    "sPaginationType": "bootstrap",
	"sAjaxDataProp" : "items",
	"oLanguage" : {
			"sProcessing":   "处理中...",
			"sLengthMenu":   "显示 _MENU_ 项结果",
			"sZeroRecords":  "没有匹配结果",
			"sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			"sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
			"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix":  "",
			"sSearch":       "搜索",
			"sUrl":          "",
			"oPaginate": {
				"sFirst":    "首页",
				"sPrevious": "上页",
				"sNext":     "下页",
				"sLast":     "末页"
			}
	},
    /*
	"fnServerParams": function ( aoData ) {
		aoData.push( { "name" : "start" , "value": getProperty(aoData,"iDisplayStart") } );
		aoData.push( { "name" : "limit" , "value": getProperty(aoData,"iDisplayLength") } );
		//将查询参数与参数名称对应，eg: mDataProp_1 : "name" sSearch_1 : "datatable" ==> "name" : "datatable"
		var sSearchRex = /sSearch_(\d+)/;
		var mDataProp_Prefix = "mDataProp_"
		$.each( aoData , function( idx , el ){
			var matchResult = el.name.match(sSearchRex);
			if( matchResult != null ){
				aoData.push( { "name" : getProperty(aoData, mDataProp_Prefix + matchResult[1]) , "value" : getProperty(aoData,matchResult[0])});
			}
		});
	},
*/
	"fnServerData": function ( sUrl, aoData, fnCallback, oSettings ) {
		oSettings.jqXHR = $.ajax( {
			"url":  sUrl,
			"data": aoData,
			"success": function (json) {
				if( ! json){
					console.error("datatable not return data, result is null! url:" ,sUrl)
					return;
				}
				if ( json.sError ) {
					oSettings.oApi._fnLog( oSettings, 0, json.sError );
				}
				json.iTotalRecords = json.totalCount;
				json.iTotalDisplayRecords = json.totalCount;
				
				$(oSettings.oInstance).trigger('xhr', [oSettings, json]);
				fnCallback( json );
			},
			"dataType": "json",
			"cache": false,
			"type": oSettings.sServerMethod,
			"error": function (xhr, error, thrown) {
				if ( error == "parsererror" ) {
                    alert('长时间不操作，系统已自动退出，请重新登录。');
                    document.location.href='';
                    return false;
                    //					oSettings.oApi._fnLog( oSettings, 0, "DataTables warning: JSON data from "+
//						"server could not be parsed. This is caused by a JSON formatting error." );
				}
			}
		} );
	},
    
   "fnStateSave": function (oSettings, oData) {
        localStorage.setItem( 'DataTables_'+window.location.pathname, JSON.stringify(oData) );
    },
    "fnStateLoad": function (oSettings) {
        var data = localStorage.getItem('DataTables_'+window.location.pathname);
        return JSON.parse(data);
    }

});
/* Set the defaults for DataTables initialisation */
$.extend( true, $.fn.dataTable.defaults, {
	"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
	"sPaginationType": "bootstrap"
} );


/* Default class modification */
$.extend( $.fn.dataTableExt.oStdClasses, {
	"sWrapper": "dataTables_wrapper form-inline"
} );


/* API method to get paging information */
$.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
{
	return {
		"iStart":         oSettings._iDisplayStart,
		"iEnd":           oSettings.fnDisplayEnd(),
		"iLength":        oSettings._iDisplayLength,
		"iTotal":         oSettings.fnRecordsTotal(),
		"iFilteredTotal": oSettings.fnRecordsDisplay(),
		"iPage":          Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
		"iTotalPages":    Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
	};
};

/* API method to clear all filter settings */
$.fn.dataTableExt.oApi.fnResetAllFilters = function (oSettings, bDraw/*default false*/) {
    for(var iCol = 0; iCol < oSettings.aoPreSearchCols.length; iCol++) {
            oSettings.aoPreSearchCols[ iCol ].sSearch = '';
    }
    oSettings.oPreviousSearch.sSearch = '';

    if(typeof bDraw === 'undefined') bDraw = false;
    if(bDraw) this.fnDraw();
};

/* Bootstrap style pagination control */
$.extend( $.fn.dataTableExt.oPagination, {
	"bootstrap": {
		"fnInit": function( oSettings, nPaging, fnDraw ) {
			var oLang = oSettings.oLanguage.oPaginate;
			var fnClickHandler = function ( e ) {
				e.preventDefault();
				if ( oSettings.oApi._fnPageChange(oSettings, e.data.action) ) {
					fnDraw( oSettings );
				}
			};

			$(nPaging).addClass('pagination').append(
				'<ul>'+
//					'<li class="first disabled"><a href="#">'+oLang.sFirst+'</a></li>'+
					'<li class="prev disabled"><a href="#">'+oLang.sPrevious+'</a></li>'+
					'<li class="next disabled"><a href="#">'+oLang.sNext+'</a></li>'+
//					'<li class="last disabled"><a href="#">'+oLang.sLast+'</a></li>'+
				'</ul>'
			);
			var els = $('a', nPaging);
			$(els[0]).bind( 'click.DT', { action: "previous" }, fnClickHandler );
			$(els[1]).bind( 'click.DT', { action: "next" }, fnClickHandler );
		},

		"fnUpdate": function ( oSettings, fnDraw ) {
			var iListLength = 5;
			var oPaging = oSettings.oInstance.fnPagingInfo();
			var an = oSettings.aanFeatures.p;
			var i, j, sClass, iStart, iEnd, iHalf=Math.floor(iListLength/2);

			if ( oPaging.iTotalPages < iListLength) {
				iStart = 1;
				iEnd = oPaging.iTotalPages;
			}
			else if ( oPaging.iPage <= iHalf ) {
				iStart = 1;
				iEnd = iListLength;
			} else if ( oPaging.iPage >= (oPaging.iTotalPages-iHalf) ) {
				iStart = oPaging.iTotalPages - iListLength + 1;
				iEnd = oPaging.iTotalPages;
			} else {
				iStart = oPaging.iPage - iHalf + 1;
				iEnd = iStart + iListLength - 1;
			}

			for ( i=0, iLen=an.length ; i<iLen ; i++ ) {
				// Remove the middle elements
				$('li:gt(0)', an[i]).filter(':not(:last)').remove();

				// Add the new list items and their event handlers
				for ( j=iStart ; j<=iEnd ; j++ ) {
					sClass = (j==oPaging.iPage+1) ? 'class="active"' : '';
					$('<li '+sClass+'><a href="#">'+j+'</a></li>')
						.insertBefore( $('li:last', an[i])[0] )
						.bind('click', function (e) {
							e.preventDefault();
							oSettings._iDisplayStart = (parseInt($('a', this).text(),10)-1) * oPaging.iLength;
							fnDraw( oSettings );
						} );
				}

				// Add / remove disabled classes from the static elements
				if ( oPaging.iPage === 0 ) {
					$('li:first', an[i]).addClass('disabled');
				} else {
					$('li:first', an[i]).removeClass('disabled');
				}

				if ( oPaging.iPage === oPaging.iTotalPages-1 || oPaging.iTotalPages === 0 ) {
					$('li:last', an[i]).addClass('disabled');
				} else {
					$('li:last', an[i]).removeClass('disabled');
				}
			}
		}
	}
} );


/*
 * TableTools Bootstrap compatibility
 * Required TableTools 2.1+
 */
if ( $.fn.DataTable.TableTools ) {
	// Set the classes that TableTools uses to something suitable for Bootstrap
	$.extend( true, $.fn.DataTable.TableTools.classes, {
		"container": "DTTT btn-group",
		"buttons": {
			"normal": "btn",
			"disabled": "disabled"
		},
		"collection": {
			"container": "DTTT_dropdown dropdown-menu",
			"buttons": {
				"normal": "",
				"disabled": "disabled"
			}
		},
		"print": {
			"info": "DTTT_print_info modal"
		},
		"select": {
			"row": "active"
		}
	} );

	// Have the collection use a bootstrap compatible dropdown
	$.extend( true, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
		"collection": {
			"container": "ul",
			"button": "li",
			"liner": "a"
		}
	} );
}

