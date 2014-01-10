package com.testapp.model.dao;

import org.mindrot.jbcrypt.BCrypt;


public class asdf {
    public static void main(String[] args) {
        String hashed = BCrypt.hashpw("student", BCrypt.gensalt());
        System.out.println(hashed);
        System.out.println(BCrypt.checkpw("sesame", hashed));
    }
}
