package com.testapp.controllers.util;


import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Entity;
import com.testapp.model.entities.Question;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapConverter {
    public static Map<Long, Question> convertQuestions(List<Question> questionList) {
        Map<Long, Question> map = new LinkedHashMap<Long, Question>();
        for (Question question : questionList) {
            map.put(question.getId(), question);
        }
        return map;
    }
}
