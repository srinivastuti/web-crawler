
function	populate(){
	
	alert("hi")
	 jsonArr=[];
	 
	$('input[class="email"]').each(function(){
		
		var id=$(this).attr('title');
		var email=$(this).val();
		
		var json={};
		json["title"]=id;
		json["email"]=email;
		
		jsonArr.push(json);
	});
	console.log(jsonArr);
};