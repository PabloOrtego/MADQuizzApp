package com.example.quizzapp;

import android.content.Context;
import android.content.SharedPreferences;

public class UserData {
    private static final String PREF_NAME = "UserPrefs";
    private static final String USER_PREFIX = "user_";
    private static final String QUESTIONS_PREFIX = "question_";

    public static boolean validateLogin(Context context, String email, String password) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String storedPassword = prefs.getString(USER_PREFIX + email, null);
        return storedPassword != null && storedPassword.equals(password);
    }

    public static void addUser(Context context, String name, String email, String password) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_PREFIX + email, password);
        editor.apply();
    }

    public static boolean userExists(Context context, String email) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.contains(USER_PREFIX + email);
    }

    public static void removeUser(Context context, String email) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(USER_PREFIX + email);
        editor.apply();
    }

    public static void editUser(Context context, String email, String newPassword) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_PREFIX + email, newPassword);
        editor.apply();
    }

    public static void saveQuestion(Context context, String id, String question, String[] answers) {
        if (answers.length != 4) return;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String questionData = question + "," + String.join(",", answers) + ",normal";
        editor.putString(QUESTIONS_PREFIX + id, questionData);
        editor.apply();
    }

    public static String getQuestion(Context context, String id) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(QUESTIONS_PREFIX + id, null);
    }

    public static void markQuestionAsMistake(Context context, String id) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String questionData = prefs.getString(QUESTIONS_PREFIX + id, null);
        if (questionData != null) {
            String[] parts = questionData.split(",");
            if (parts.length == 6) {
                parts[5] = "mistake";
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(QUESTIONS_PREFIX + id, String.join(",", parts));
                editor.apply();
            }
        }
    }
}
