function dump(obj) {
	var win = window.open('', 'dump', '');
	var temp=[];
	for (var p in obj) {
		temp.push(p);
	}
	temp.sort();
	
	var str = "<table border=1>";
	for(var i=0;i<temp.length;i++){
		str += "<tr><td>" + temp[i] + "</td><td>" + obj[temp[i]] + "</td></tr>";
	}
	str+= "</table>";
	win.document.body.innerHTML=str;
}