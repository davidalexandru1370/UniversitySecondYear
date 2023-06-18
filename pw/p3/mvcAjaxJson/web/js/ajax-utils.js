 class Helper{
	static content = [];
	static number = 0;
	static index = 0
}

function getAllProjects(callbackFunction){
	$.getJSON("ProjectsController",{
		action: 'getAll'
	}, callbackFunction)
}


