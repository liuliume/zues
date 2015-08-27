<%@ page contentType="text/html;charset=UTF-8" %>
<div id="util-dialog-alert" title="提示" style="display:none">
	<p><i class="icon-exclamation-sign margin-right20"></i><span class="util-dialog-alert-content"></span></p>
</div>
<div id="util-dialog-confirm" title="确认" style="display:none">
	<p><i class="icon-question-sign margin-right20"></i><span class="util-dialog-confirm-content"></span></p>
</div>
<script id="util-result_tpl"  type="text/x-jquery-tmpl">
	<div id="util-embed-alert" class="hidden alert">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong id="util-embed-alert-title"></strong><span id="util-embed-alert-msg"></span>
	</div>  
</script>
<script>

/*
function getFormParam(selector){
	var params = {}
	$(selector).children("[name]").each(function(index,obj){
		if ($(obj).attr('name')){
			params[$(obj).attr('name')] = $(obj).val();
		}
	})
	return params;
}
*/
$(function(){
	var utilAlertTemplate = $( "#util-result_tpl" ).template();

	$("#util-dialog-alert").dialog({
		resizable: false,
		height:180,
		width: 330,
		modal: true,
		autoOpen: false,
		buttons: {
			"确定" : function(){
				var e = jQuery.Event("confirmed");
				$(this).trigger(e)
				$(this).dialog("close")
			}
		}
	})// End of util-dialog-alert
	$("#util-dialog-confirm").dialog({
		resizable: false,
		height:180,
		width: 330,
		modal: true,
		autoOpen: false,
		buttons: {
			"确定" : function(){
				var e = $.Event("confirmed");
				$(this).trigger(e)
				$(this).dialog("close")
			},
			"取消" : function(){
				var e = $.Event("canceled");
				$(this).trigger(e)
				$(this).dialog("close")
			}
		}
	})// End of util-dialog-alert
})//End of Document ready

var Utils = Utils || {}
$.extend( Utils , {
	alert: function(option){
		$("#util-dialog-alert").find("span.util-dialog-alert-content").text(option.text || "")
		$("#util-dialog-alert").dialog("option","title",option.title || "提示")
		$("#util-dialog-alert").off("confirmed").on("confirmed",option.confirm)
		$("#util-dialog-alert").dialog("open")
	},
    xround : function(number, x) {
        return Math.round(number*Math.pow(10, x))/Math.pow(10, x);
    },
    confirm: function(option){
		$("#util-dialog-confirm").find("span.util-dialog-confirm-content").text(option.text || "")
		$("#util-dialog-confirm").dialog("option","title",option.title || "请确认")
		$("#util-dialog-confirm").off("confirmed").on("confirmed",option.confirm)
		$("#util-dialog-confirm").off("canceled").on("canceled",option.cancel)
		$("#util-dialog-confirm").dialog("open")
	},
	prompt: function(){
	},

	formParamsByFormid: function(formid){
		return this.formParams($(formid));
	},

	formParams: function(formObj){
		var params = {}
		$(formObj).find("[name]").each(function(index,obj){
			if ($(obj).attr('name')){
                if ($(obj).attr('type')!=null && $(obj).attr('type')=="radio") {
                    if (this.checked) {
                        params[$(obj).attr('name')] = $(obj).val();
                    }
                }else{
                    params[$(obj).attr('name')] = $(obj).val();
                }
			}
		})
		return params;
	}
	,

	formParamsByItem:function(element ){
		var c = $(element).parent("form");
		return this.formParams(c);
	},
	formSetVal:function(frmid,data){
		data = data || {};
		frmitem = $(frmid).find("[name]")
		$(frmitem).each(function(idx,widget){
			n = $(widget).attr("name")
			if ( $(widget).is("select") ){
				if( data && data[n] && data[n].name ){
					$(widget).val(data[n].name );
				}else{
					$(widget).val( "" );
				}
			}else{
				$(widget).val(data[n] || '');
			}
			
		});
	},
    _printBarcode:function(printer,sku,data,printNum,printMethod){
        var printNum = parseInt(printNum);
        for(var i=0;i < printNum; i++){
            var barcode = data
            if(printMethod==0){
                if (!printer.eplStartPrint(400,200)){
                    return false;
                }
                printer.eplAddText(72,16,sku, 350,100,0,1,2,2);
                printer.eplAddBarCode13(52,60,3,100,barcode,0);
                printer.eplDoPrint();
            }
            else {
                if (!printer.zplStartPrint(500,250)){
                    return false;
                }
                printer.zplAddText(108,20,sku, 350,80,0,28,55);
                printer.zplAddBarCode13(52,60,3,100,barcode,0);
                printer.zplDoPrint();
            }
        }
        return true;
    },    
    _printBarcode128:function(printer,sku,data,printNum,printMethod){
        var printNum = parseInt(printNum);
        for(var i=0;i < printNum; i++){
            var barcode = data
            if(printMethod==0){
                if (!printer.eplStartPrint(400,200)){
                    return false;
                }
                printer.eplAddText(72,16,sku, 350,100,0,1,2,2);
                printer.eplAddBarCode128(22,60,3,100,barcode,0);
                printer.eplDoPrint();
            }
            else {
                if (!printer.zplStartPrint(500,250)){
                    return false;
                }
                printer.zplAddText(108,20,sku, 350,80,0,28,55);
                printer.zplAddBarCode128(22,60,3,100,barcode,0);
                printer.zplDoPrint();
            }
        }
        return true;
    },    
    printBarcode:function(text, data,printNum,printMethod, printFlag)
    {
        var printer = document.createElement('object');
        printer.setAttribute('id','litb_printer_new');
        printer.setAttribute('type','application/x-litb-barcode');
        printer.setAttribute('display','none');
        document.body.appendChild(printer);
        if (!printer.valid) {
            document.body.removeChild(printer);
            $("#msg").modal('show');
            return false;
        }
        
        if (typeof printFlag !== 'undefined' && printFlag == 128) {
        	
            if (!this._printBarcode128(printer,text,data,printNum,printMethod)){
                document.body.removeChild(printer);
                alert('请检查打印机类型是否正确，且保证已设为默认打印机!');
                return false;
            }
            
        } 
        else {   // By default use this.
        	
            if (!this._printBarcode(printer,text,data,printNum,printMethod)){
                document.body.removeChild(printer);
                alert('请检查打印机类型是否正确，且保证已设为默认打印机!');
                return false;
            }        	
        }
        
        document.body.removeChild(printer);
    }
})
</script>
