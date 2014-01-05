package com.testapp.controllers.util;


import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Entity;
import com.testapp.model.entities.Question;
import com.testapp.model.entities.Quiz;

import java.util.List;
import java.util.Map;

public class QuizResultCalc {
    public static int calculateResult(Quiz quiz, Map<String, String[]> parameters) {
        int result = 0;
        List<Question> questionList = quiz.getQuestions();
        //convert List to Map for more convenient parameters matching
        Map<Long, Question> qMap = MapConverter.convertQuestions(questionList);
        //we iterate through received questions ID's
        for (String qId : parameters.keySet()) {
            //answers keys(id's) of current question
            String[] chosenAnsIds = parameters.get(qId);
            Question question = qMap.get(Long.parseLong(qId));

            boolean qFlag = checkQuestion(question, chosenAnsIds);
            if (qFlag) {
                result++;
            }
        }
        return result;
    }

    //return true if question was answered right
    private static boolean checkQuestion(Question question, String[] chosenAnsIds) {
        List<Answer> answersList = question.getAnswers();
        //count question as true by default
        boolean qFlag = true;
        for (Answer answer : answersList) {
            boolean isInChosen = isInChosen(answer, chosenAnsIds);
            //if at least one condition is true - question was not correct answered
            if (isInChosen == true && answer.isRight() == false) {
                qFlag = false;
                break;
            }
            if (isInChosen == false && answer.isRight() == true) {
                qFlag = false;
                break;
            }
        }
        return qFlag;
    }

    private static boolean isInChosen(Answer answer, String[] chosenAnsIds) {
        boolean isInChosen = false;
        for (String chosenAnsId : chosenAnsIds) {
            if (answer.getId() == Long.parseLong(chosenAnsId)) {
                isInChosen = true;
                break;
            }
        }
        return isInChosen;
    }
}
