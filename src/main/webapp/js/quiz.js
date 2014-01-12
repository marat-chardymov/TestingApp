function QuizCtrl($scope, $http) {

    $scope.questions = [
        {id: 1, content: "Question1"},
        {id: 2, content: "Question2"}
    ];
    $scope.newQuestion = {};
    $scope.getTotalQuestions = function () {
        return $scope.questions.length;
    };
    $scope.addQuestion = function () {
        var newQuestion = "prostostroka";
        $http({
            method: 'POST',
            url: '/addQuestion',
            headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
            data: {newQuestion: newQuestion}
        }).success(function (data) {
                $scope.questions.push({id: 3, content: $scope.formQuestionText});
                $scope.formQuestionText = '';
            });
//        $.ajax({
//            url: '/addQuestion',
//            type: 'POST',
//            data: {countryname: "Belarus"},
//            success: function(response){
//               alert("sukabljat!");
//            }
//        });

    };
    $scope.delete = function () {
        $scope.answers = _.filter($scope.answers, function (answer) {
            return answer.isRight;
        });

    };
}
