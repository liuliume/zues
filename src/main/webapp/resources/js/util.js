$.extend(String.prototype,{
	format : function() {
		var formatted = this;
		for (var i = 0; i < arguments.length; i++) {
			var regexp = new RegExp('\\{'+i+'\\}', 'gi');
			formatted = formatted.replace(regexp, arguments[i]);
		}   
		return formatted;
	}
});
$.extend(Date,{
	parseMillisecondToChineseString: function(minisecond){
		var d = new Date(minisecond);
		return "{0}年{1}月{2}日 {3}时{4}分{5}秒".format(d.getFullYear(),d.getMonth()+1,d.getDate(),d.getHours(),d.getMinutes(),d.getSeconds());
	}
});


$.extend(String.prototype,{
	endWith:function(str){
		if (str == null || str == "" || this.length == 0
				|| str.length > this.length)
			return false;
		if (this.substring(this.length - str.length) == str)
			return true;
		else
			return false;
		return true;
	},
	
	startWith:function(str){
		if (str == null || str == "" || this.length == 0
				|| str.length > this.length)
			return false;
		if (this.substr(0, str.length) == str)
			return true;
		else
			return false;
		return true;
	},
	
	trim:function(){
		return this.replace(/(^\s*)|(\s*$)/g, "");
	},
	
	ltrim:function(){
		 return this.replace(/(^\s*)/g,"");
	},
	
	rtrim:function(){
		return this.replace(/(\s*$)/g,"");
	}
	
});

$.extend({
	addMask:function() {
		var loadingMask = document.createElement("div");
        loadingMask.id = "loadingMask";
        loadingMask.className = "loadingMask";
        var heightScrollTop = document.documentElement.scrollTop;
        var heightClient = document.documentElement.clientHeight;
        if (heightScrollTop == 0)
            heightScrollTop = document.body.scrollTop;
        if (heightClient == 0)
            heightClient = document.body.clientHeight;
        loadingMask.style.height = (heightScrollTop + heightClient / 2) * 2;
        document.body.style.overflow = "hidden";
        document.body.appendChild(loadingMask);
	},
	
	removeMask :function(){
		var loadingMask = document.getElementById("loadingMask");
	    if (loadingMask) {
	        document.body.removeChild(loadingMask);
	    }
	}
});

var Utils = Utils || {}
$.extend(Utils , {
//var Utils = {
	/**
	 * 数字转中文，不带任何单位
	 *
	 * @number {Integer} 形如123的数字
     * @return {String} 返回转换成的形如 一二三 的字符串
     */ 
	n2c_u : function(number){
		var chars = '零一二三四五六七八九';
		var a = (number + '').split(''), s = [];
		for ( var i = 0, j = a.length - 1; i <= j; i++) {
			s.push(chars.charAt(a[i]));
		}
		return s.join('');
	},
    xround : function(number, x) {
        return Math.round(number*Math.pow(10, x))/Math.pow(10, x);
    },
	/**
	 * 数字转中文
	 * 
	 * @number {Integer} 形如123的数字
	 * @return {String} 返回转换成的形如 一百二十三 的字符串
	 */
	n2c : function(number) {
		/*
		 * 单位
		 */
		var units = '个十百千万@#%亿^&~';
		/*
		 * 字符
		 */
		var chars = '零一二三四五六七八九';
		var a = (number + '').split(''), s = [];
		if (a.length > 12) {
			throw new Error('too big');
		} else {
			for ( var i = 0, j = a.length - 1; i <= j; i++) {
				if (j == 1 || j == 5 || j == 9) {// 两位数 处理特殊的 1*
					if (i == 0) {
						if (a[i] != '1')
							s.push(chars.charAt(a[i]));
					} else {
						s.push(chars.charAt(a[i]));
					}
				} else {
					s.push(chars.charAt(a[i]));
				}
				if (i != j) {
					s.push(units.charAt(j - i));
				}
			}
		}
		// return s;
		return s.join('').replace(/零([十百千万亿@#%^&~])/g, function(m, d, b) {// 优先处理 零百 零千 等
			b = units.indexOf(d);
			if (b != -1) {
				if (d == '亿')
					return d;
				if (d == '万')
					return d;
				if (a[j - b] == '0')
					return '零'
			}
			return '';
		}).replace(/零+/g, '零').replace(/零([万亿])/g, function(m, b) {// 零百 零千处理后 可能出现 零零相连的 再处理结尾为零的
			return b;
		}).replace(/亿[万千百]/g, '亿').replace(/[零]$/, '').replace(/[@#%^&~]/g, function(m) {
			return {
				'@' : '十',
				'#' : '百',
				'%' : '千',
				'^' : '十',
				'&' : '百',
				'~' : '千'
			}[m];
		}).replace(/([亿万])([一-九])/g, function(m, d, b, c) {
			c = units.indexOf(d);
			if (c != -1) {
				if (a[j - c] == '0')
					return d + '零' + b
			}
			return m;
		});
	}
});

/*
 * Bootstrap pagination extension
 */

!function ($) {

  "use strict"; // jshint ;_;


 /* Bootstrp的分页器，如果总页数小于设定显示页数时，只显示部分页数
  * ==================== */

  var Pager = function (element , options) {
    this.$element = $(element) //为具有pagination样式的div
	this.oOptions = $.extend({}, $.fn.pager.defaults, options)
	this.sUrl = this.oOptions.sUrl || this.sUrl
	this.iRecords = this.oOptions.iRecords || this.iRecords //一页显示的记录数
	this.iPages = this.oOptions.iPages || this.iPages //一屏显示的页码数
	this.sPreviousPage = this.oOptions.sPreviousPage || this.sPreviousPage
	this.sNextPage = this.oOptions.sNextPage || this.sNextPage
	this.sDataNumber = this.oOptions.sDataNumber || this.sDataNumber

	!function(){
		//是否需要初始化？？
	}()
  }

  Pager.prototype = {

    constructor: Pager
	//JSP版的分页器，只需要渲染功能
  , _render: function(count , currentPage) {
	  var $this = this.$element
		, totalPages =  Math.ceil( count / this.iRecords )// 共几页
		, totalScreen = Math.ceil( totalPages / this.iPages ) // 共几屏
		, currentScreen = Math.ceil( currentPage / this.iPages ) //当前第几屏
		, startPage = ( currentScreen - 1 ) * this.iPages + 1
		, endPage = startPage + this.iPages - 1
		, realEndPage = endPage > totalPages ? totalPages : endPage
	    , hasNext = totalScreen > currentScreen
		, hasPrevious = currentScreen > 1 
		, prefix = this.sUrl.indexOf("?") == -1 ? "?" : "&"
		, requestUrl = this.sUrl + prefix + "start={0}" + "&limit=" + this.iRecords
		, $ul = $('<ul></ul>')
		, iPreviousStart = (currentPage - 2) * this.iRecords
		, iNextStart = currentPage * this.iRecords
		, $previousLi = $('<li><a href="{0}">{1}</a></li>'.format( hasPrevious ? requestUrl.format(iPreviousStart) : "#" , this.sPreviousPage))
		, $nextLi = $('<li><a href="{0}">{1}</a></li>'.format( hasNext ? requestUrl.format(iNextStart) : "#" , this.sNextPage))
	  
	  $previousLi.appendTo($ul)
	  if ( !hasPrevious ) {
		  $previousLi.addClass("disabled")
		  //$previousLi.unbind("click")
	  }else{
		  $previousLi.removeClass("disabled")
		  //$previousLi.click($.proxy(this.previous , this))
	  }
	  for( var i = startPage ; i <= realEndPage ; i++ ){
		  var $li = $('<li><a href="{0}">{1}</a></li>'.format( requestUrl.format( (i -1) * this.iRecords) , i)).data(this.sDataNumber , i);
		  if( i == currentPage ) $li.addClass("active")
		  $ul.append($li);
	  }
	  $nextLi.appendTo($ul)
	  if ( !hasNext ) {
		  $nextLi.addClass("disabled")
		  //$nextLi.unbind("click")
	  }else{
		  $nextLi.removeClass("disabled")
		  //$nextLi.click($.proxy(this.previous , this))
	  }
	  $this.empty().append($ul)
  }
/* ajax版的分页器 
  , _render: function(count , currentPage) {
	  var $this = this.$element
		, totalPages =  Math.ceil( count / this.iRecords )// 共几页
		, totalScreen = Math.ceil( totalPages / this.iPages ) // 共几屏
		, currentScreen = Math.ceil( currentPage / this.iPages ) //当前第几屏
		, startPage = ( currentScreen - 1 ) * this.iPages + 1
		, endPage = startPage + this.iPages - 1
		, realEndPage = endPage > totalPages ? totalPages : endPage
	    , hasNext = totalScreen > currentScreen
		, hasPrevious = currentScreen > 1 
		, $ul = $('<ul></ul>')
		, $previousLi = $('<li><a href="#">{0}</a></li>'.format(this.sPreviousPage))
		, $nextLi = $('<li><a href="#">{0}</a></li>'.format(this.sNextPage))
	  
	  $previousLi.appendTo($ul)
	  if ( !hasPrevious ) {
		  $previousLi.addClass("disabled")
		  $previousLi.unbind("click")
	  }else{
		  $previousLi.click($.proxy(this.previous , this))
	  }
	  for( var i = startPage ; i <= realEndPage ; i++ ){
		  var $li = $('<li><a href="#">{0}</a></li>'.format(i)).data(this.sDataNumber , i).click(
				  $.proxy((function(remainIndex){
					  return function() {
						  this.toPage(remainIndex)
					  }})(i), this));
		  if( i == currentPage ) $li.addClass("active")
		  $ul.append($li);
	  }
	  $nextLi.appendTo($ul)
	  if ( !hasNext ) {
		  $nextLi.addClass("disabled")
		  $nextLi.unbind("click")
	  }else{
		  $nextLi.click($.proxy(this.previous , this))
	  }
	  $this.empty().append($ul)
  }

  , toPage: function( index ){
	  var $this = this.$element
		, self = this
		, $ul = $this.children("ul")
		, $previous = $ul.find("li:first-child")
		, $next = $ul.find("li:last-child")
		, $active = $ul.find("li.active")
		, prefix = this.sUrl.indexOf("?") == -1 ? "?" : "&"
		, current = index
		, start = ( (index - 1 ) * this.iRecords )
		, requestUrl = this.sUrl + prefix + "start=" +  start  + "&limit=" + this.iRecords
		, e

	  $.post( requestUrl , 
			function(result){
				//TODO 根据总数重新渲染控件,并将结果通知到外面
			    self.items = result.items; 
			    self.count = result.totalCount;
			    self._render(self.count , current); 
			});      
  }

  , next: function(){
	  var $activeLi = this._getActiveLi()
		, currentPage = $activeLi.data(this.sDataNumber) ? $activeLi.data(this.sDataNumber) : 0

	  this.toPage(currentPage + 1)	
  }

  , previous: function(){
	  var $activeLi = this._getActiveLi()
		, currentPage = $activeLi.data(this.sDataNumber) ? $activeLi.data(this.sDataNumber) : 0

	  this.toPage(currentPage - 1)	
  }

  , _getActiveLi: function(){
	  return this.$element.find("ul > li.active")
  }

  */ //AJAX 版分页器渲染
  , render: function(oParam){
	this._render(oParam.count,oParam.currentPage)
  }

  }


 /* Pager PLUGIN DEFINITION
  * ===================== */

  $.fn.pager = function ( option , oParam) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('pager')
      if (!data) $this.data('pager', (data = new Pager(this,option)))
      if (typeof option == 'string') data[option](oParam)
    })
  }

  $.fn.pager.defaults = {
	iRecords: 25 , 
	iPages: 4 , 
	sDataNumber: "data-number" , 
	sPreviousPage : '上一页' , 
	sNextPage : '下一页'
  }
  $.fn.pager.Constructor = Pager


 /* Pager DATA-API
  * ============ */
/*
  $(function () {
    $('body').on('click.pager.data-api', '[data-toggle="pager"] ', function (e) {
      e.preventDefault()
      $(this).pager('show')
    })
  })
*/
}(window.jQuery);/* =============================================================*/

