class Helper {
    static number = 0;
}

function getAllProjects(callbackFunction) {
    $.getJSON("ProjectsController", {
        action: 'getAll'
    }, callbackFunction)
}

function getAllQuestions(callbackFunction) {
    $.getJSON("QuestionController", {}, callbackFunction)
}
