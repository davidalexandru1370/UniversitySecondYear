class Helper {
    static number = 0;
    static questions = [];
    static allQuestions = [];
    static answeredQuestions = [];
}

function getAllProjects(callbackFunction) {
    $.getJSON("ProjectsController", {
        action: 'getAll'
    }, callbackFunction)
}

function getAllQuestions(callbackFunction) {
    $.getJSON("QuestionController", {}, callbackFunction)
}
