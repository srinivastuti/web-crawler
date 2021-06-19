$(function(){
    $("#crawledata").hide();
});
function webCrowler(){
	 $("#crawledata").hide();
     $('#other-top-level-element').empty();
	 var search = {}
	 search["rootUrl"] = $("#url").val();
	 $.ajax({
		url : getContextPath() + "/crowler",
		type : 'POST',
		data :JSON.stringify(search),
		contentType : 'application/json',
		success : function(data) {
			try {
				loadPage(JSON.stringify(data))
			} catch (err) {
			 toggleLoader(true)
			}
		},
		error : function() {
			toggleLoader(true)
		}
	});
	
};
function displayError(err){
	
}

function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

function toggleLoader(action){
	if(action){
		 $("#crawledata").show();
		$('#other-top-level-element').append('<center><p><font color="red"><b>Record not found</b></font></p></center>')
	}
}

function loadPage(items){
var arr = items.split(",");

if(arr.length>0){
	 $("#crawledata").show();
  for(var i=0; i< arr.length; i++){
     $('#other-top-level-element').append('<center><p>' + arr[i] + '</p></center>')
    }
}

}