package com.testapp.model.dao;


import com.testapp.model.dao.impl.QuestionDAOImpl;
import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Question;
import com.testapp.model.entities.Quiz;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QuestionDAOTest {
    @Test
    public void add(){
        Quiz quiz = new Quiz("newTestForAdd");
        IQuizDAO quizDAO=new QuizDAOImpl();
        quizDAO.add(quiz);

        Question question=new Question(quiz,"True or False?: Dumb guys like dumb girls ?");
        List<Answer> answerList=new ArrayList<Answer>();
        answerList.add(new Answer("False. I'm a dumb guy, and I don't like dumb girls.",false));
        answerList.add(new Answer("True. And Smart girls like me get smart guys!",false));
        answerList.add(new Answer("False. I'm not a dumb girl, but I've been with dumb guys.",false));
        answerList.add(new Answer("True. When I act dumb, I attract dumb girls.",true));
        question.setAnswers(answerList);
        IQuestionDAO questionDAO=new QuestionDAOImpl();
        questionDAO.add(question);
    }
}
