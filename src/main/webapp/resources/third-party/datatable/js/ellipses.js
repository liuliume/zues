/*
 * This plug-in adds another pagination option similar to `full_numbers`, except
 * it adds ellipses around the page numbers when applicable. You can set how
 * many page numbers should be displayed with the iShowPages option.
 * 
 * This plug- in extends the oStdClasses object with the following properties:
 * sPageEllipsis, sPageNumber and sPageNumbers.
 * 
 * It also extends the oSettings object with the following properties:
 * _iShowPages, _iShowPagesHalf, _iCurrentPage, _iTotalPages, _iFirstPage and
 * _iLastPage.
 *
 * Note that DataTables 1.10 has this ability built in. As such, this plug-ins
 * has been marked as deprecated, but may still be useful for if you are using
 * an old version of DataTables.
 *
 * @name Ellipses
 * @summary Show ellipses in the pagination control where there is a gap in numbers
 * @deprecated
 * @author [Dave Kennedy](http://daveden.wordpress.com/)
 * @example
 *     $(document).ready(function() {
 *         $('#example').dataTable({
 *             'sPaginationType': 'ellipses'
 *         });
 *     });
 */

$.extend($.fn.dataTableExt.oStdClasses, {
    'sPageEllipsis': 'paginate_ellipsis',
    'sPageNumber': 'paginate_number',
    'sPageNumbers': 'paginate_numbers'
});

$.fn.dataTableExt.oPagination.ellipses = {
    'oDefaults': {
        'iShowPages': 5
    },
    'fnClickHandler': function(e) {
    	
        var fnCallbackDraw = e.data.fnCallbackDraw,
            oSettings = e.data.oSettings,
            sPage = e.data.sPage;

        if ($(this).is('[disabled]')) {
            return false;
        }

        oSettings.oApi._fnPageChange(oSettings, sPage);
        fnCallbackDraw(oSettings);

        return true;
    },
    // fnInit is called once for each instance of pager
    'fnInit': function(oSettings, nPager, fnCallbackDraw) {
        var oClasses = oSettings.oClasses,
            oLang = oSettings.oLanguage.oPaginate,
            that = this;

        var iShowPages = oSettings.oInit.iShowPages || this.oDefaults.iShowPages,
            iShowPagesHalf = Math.floor(iShowPages / 2);

        $.extend(oSettings, {
            _iShowPages: iShowPages,
            _iShowPagesHalf: iShowPagesHalf,
        });

        var oFirst = $('<li class="first"><a href="#">' + oLang.sFirst + '</a></li>'),
            oPrevious = $('<li class="previous"><a href="#">' + oLang.sPrevious + '</a></li>'),
            oNumbers = $('<li class="' + oClasses.sPageNumbers + '"></li>'),
            oNext = $('<li class="next"><a href="#">' + oLang.sNext + '</a></li>'),
            oLast = $('<li class="last"><a href="#">' + oLang.sLast + '</a></li>');

        oFirst.click({ 'fnCallbackDraw': fnCallbackDraw, 'oSettings': oSettings, 'sPage': 'first' }, that.fnClickHandler);
        oPrevious.click({ 'fnCallbackDraw': fnCallbackDraw, 'oSettings': oSettings, 'sPage': 'previous' }, that.fnClickHandler);
        oNext.click({ 'fnCallbackDraw': fnCallbackDraw, 'oSettings': oSettings, 'sPage': 'next' }, that.fnClickHandler);
        oLast.click({ 'fnCallbackDraw': fnCallbackDraw, 'oSettings': oSettings, 'sPage': 'last' }, that.fnClickHandler);

        var nInput = document.createElement( 'input' );
        nInput.type = "text";
        nInput.style.width = "20px";
        nInput.style.display = "inline";
        // Draw
        ($(nPager)).addClass('pagination');
        var list = $('<ul/>').appendTo($(nPager));
        list.append(oFirst);
        list.append(oPrevious);
        list.append(oNumbers);
        list.append(oNext);
        list.append(oLast);
        list.append('&nbsp;&nbsp;转到&nbsp;&nbsp;');
        list.append(nInput);
        list.append('&nbsp;&nbsp;页&nbsp;&nbsp;');
        //$(nPager).append(oFirst, oPrevious, oNumbers, oNext, oLast);
        
        $(nInput).keyup( function (e) {
            
            if ( e.which == 38 || e.which == 39 )
            {
                this.value++;
            }
            else if ( (e.which == 37 || e.which == 40) && this.value > 1 )
            {
                this.value--;
            }
              
            if ( this.value == "" || this.value.match(/[^0-9]/) )
            {
                /* Nothing entered or non-numeric character */
                return;
            }
              
            var iNewStart = oSettings._iDisplayLength * (this.value - 1);
            if ( iNewStart > oSettings.fnRecordsDisplay() )
            {
                /* Display overrun */
                oSettings._iDisplayStart = (Math.ceil((oSettings.fnRecordsDisplay()-1) /
                    oSettings._iDisplayLength)-1) * oSettings._iDisplayLength;
                fnCallbackDraw( oSettings );
                return;
            }
              
            oSettings._iDisplayStart = iNewStart;
            fnCallbackDraw( oSettings );
        } );
    },
    // fnUpdate is only called once while table is rendered
    'fnUpdate': function(oSettings, fnCallbackDraw) {
    	var oClasses = oSettings.oClasses,
        that = this;

	    var tableWrapper = oSettings.nTableWrapper;
	
	    // Update stateful properties
	    this.fnUpdateState(oSettings);
	
	    if (oSettings._iCurrentPage === 1) {
	    	$('.' + oClasses.sPageFirst, tableWrapper).addClass('active');
	    	$('.' + oClasses.sPagePrevious, tableWrapper).addClass('active');
	    } else {
	    	$('.' + oClasses.sPageFirst, tableWrapper).removeClass('active');
	    	$('.' + oClasses.sPagePrevious, tableWrapper).removeClass('active');
	    }
	
	    if (oSettings._iTotalPages === 0 || oSettings._iCurrentPage === oSettings._iTotalPages) {
	    	$('.' + oClasses.sPageNext, tableWrapper).addClass('active');
	    	$('.' + oClasses.sPageLast, tableWrapper).addClass('active');
	    } else {
	    	$('.' + oClasses.sPageNext, tableWrapper).removeClass('active');
	    	$('.' + oClasses.sPageLast, tableWrapper).removeClass('active');
	    }
	
	    var i, oNumber, oNumbers = $('.' + oClasses.sPageNumbers, tableWrapper);
	
	    // Erase
	    oNumbers.html('');
	
	    for (i = oSettings._iFirstPage; i <= oSettings._iLastPage; i++) {
	        oNumber = $('<a href="#" class="' + oClasses.sPageButton + ' ' + oClasses.sPageNumber + '">' + oSettings.fnFormatNumber(i) + '</a>');
	
	        if (oSettings._iCurrentPage === i) {
	            oNumber.attr('active', true).attr('disabled', true);
	            oNumber.css("cursor","default");
	            oNumber.css("color","#ACA899");
	            oNumber.css("background","#F5F5F5");
	        } else {
	            oNumber.click({ 'fnCallbackDraw': fnCallbackDraw, 'oSettings': oSettings, 'sPage': i - 1 }, that.fnClickHandler);
	        }
	
	        // Draw
	        oNumbers.append(oNumber);
	    }
	
	    // Add ellipses
	    if (1 < oSettings._iFirstPage) {
	        oNumbers.prepend('<span class="' + oClasses.sPageEllipsis + '">...</span>');
	    }
	
	    if (oSettings._iLastPage < oSettings._iTotalPages) {
	        oNumbers.append('<span class="' + oClasses.sPageEllipsis + '">...</span>');
	    }
    },
    // fnUpdateState used to be part of fnUpdate
    // The reason for moving is so we can access current state info before fnUpdate is called
    'fnUpdateState': function(oSettings) {
        var iCurrentPage = Math.ceil((oSettings._iDisplayStart + 1) / oSettings._iDisplayLength),
            iTotalPages = Math.ceil(oSettings.fnRecordsDisplay() / oSettings._iDisplayLength),
            iFirstPage = iCurrentPage - oSettings._iShowPagesHalf,
            iLastPage = iCurrentPage + oSettings._iShowPagesHalf;

        if (iTotalPages < oSettings._iShowPages) {
            iFirstPage = 1;
            iLastPage = iTotalPages;
        } else if (iFirstPage < 1) {
            iFirstPage = 1;
            iLastPage = oSettings._iShowPages;
        } else if (iLastPage > iTotalPages) {
            iFirstPage = (iTotalPages - oSettings._iShowPages) + 1;
            iLastPage = iTotalPages;
        }

        $.extend(oSettings, {
            _iCurrentPage: iCurrentPage,
            _iTotalPages: iTotalPages,
            _iFirstPage: iFirstPage,
            _iLastPage: iLastPage
        });
    }
};