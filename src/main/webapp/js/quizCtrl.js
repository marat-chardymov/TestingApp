function QuizCtrl($scope, $http) {

    $scope.quiz;
    $http.get('/TestingApp/jsp/questions/getJSON')
        .then(function (res) {
            $scope.quiz = res.data;
        });
//        [
//        {id: 1, content: "Question1", answers: [
//            {id: 1, content: "Answer1", isRight: true},
//            {id: 2, content: "Answer2", isRight: false}
//        ]},
//        {id: 2, content: "Question2", answers: [
//            {id: 3, content: "Answer 3", isRight: true},
//            {id: 4, content: "Answer 4", isRight: true}
//        ]}
//    ];

    $scope.getTotalQuestions = function () {
        return $scope.quiz.questions.length;
    };
    $scope.addQuestion = function () {
        var newQuestion = {content: $scope.formQuestionText, quizId: $scope.quiz.id};
        $http({
            method: 'POST',
            url: '/TestingApp/jsp/questions/add',
            headers: { 'Content-Type': 'application/json' },
            data: newQuestion
        }).success(function (data) {
                $scope.quiz.questions.push({id: data.id, content: $scope.formQuestionText, answers: []});
                $scope.formQuestionText = '';
            });
    };
    $scope.addAnswer = function (question, formAnswerText) {
        var newAnswer = {content: formAnswerText, questionId: question.id, isRight: false};
        $http({
            method: 'POST',
            url: '/TestingApp/jsp/answers/add',
            headers: { 'Content-Type': 'application/json' },
            data: newAnswer
        }).success(function (data) {
                question.answers.push({id: data.id, content: formAnswerText, questionId: question.id, isRight: false});
                formAnswerText = '';
            });
    };
    $scope.deleteQuestion = function (question) {
        $http({
            method: 'POST',
            url: '/TestingApp/jsp/questions/delete',
            headers: { 'Content-Type': 'application/json' },
            data: question
        }).success(function (data) {
                $scope.quiz.questions.splice($scope.quiz.questions.indexOf(question), 1);
            });
    };
    $scope.deleteAnswer = function (question, answer) {
        $http({
            method: 'POST',
            url: '/TestingApp/jsp/answers/delete',
            headers: { 'Content-Type': 'application/json' },
            data: answer
        }).success(function (data) {
                question.answers.splice(question.answers.indexOf(answer), 1);
            });
    };
    $scope.triggerIsRight = function (answer) {
        $http({
            method: 'POST',
            url: '/TestingApp/jsp/answers/triggerIsRight',
            headers: { 'Content-Type': 'application/json' },
            data: answer
        }).success(function (data) {

            });
    };
}

