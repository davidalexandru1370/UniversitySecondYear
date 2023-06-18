function getAllProjects(callbackFunction){
	$.getJSON("ProjectsController",{
		action: 'getAll'
	}, callbackFunction)
}