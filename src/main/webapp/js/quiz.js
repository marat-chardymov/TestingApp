function QuizCtrl($scope) {

    $scope.answers = [
        {content: "Answer1", isRight: true},
        {content: "Answer2", isRight: false}
    ];
    $scope.getTotalAnswers = function () {
        return $scope.answers.length;
    };
    $scope.addAnswer = function () {
        $scope.answers.push({content: $scope.formAnswerText, isRight: false});
        $scope.formAnswerText = '';
    };
    $scope.clearFalse = function () {
        $scope.answers= _.filter($scope.answers, function(answer){
           return answer.isRight;
        });

    };
}
function UserController($scope, $http)
{
    $scope.user = {};

    $scope.createUser = function()
    {
        $http({
            method: 'POST',
            url: '/user',
            headers: {'Content-Type': 'application/json'},
            data:  $scope.user
        }).success(function (data)
            {
                $scope.status=data;
            });
    };
}