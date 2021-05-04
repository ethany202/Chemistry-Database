var pages = ["terms.html", "molcom.html", "ele.html"];
var index = 0;
var id = null;
document.getElementById("terms").addEventListener("click", function(){
	changePage("terms");
});
document.getElementById("mc").addEventListener("click", function(){
	changePage("molcom");
});
document.getElementById("e").addEventListener("click", function(){
	changePage("ele");
});
function changePage(btn){
	if(btn=="terms"){
		index=0;
	}
	if(btn=="molcom"){
		index=1;
	}
	if(btn=="ele"){
		index=2;
	}
	window.location.href = pages[index];
};
