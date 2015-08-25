Array.contains = function(a, b){
    for(var i=0; i<a.length; i++){
        if(a[i] == b){
            return true;
        }
    }
    return false;
}

Array.minus = function(a, b){
    var r = [];
    for (var i=0; i< a.length; i++) {
        if (!Array.contains(b, a[i])) {
            r[r.length] = a[i];
        }
    }
    return r;
};

