package com.example.assignment2.JapaneseLesson;

import android.provider.BaseColumns;

// Container for different constants for SQLite operations
public final class Japanese1QuestionContract {

    // Empty constructor, so we cannot create an object of this class by accident
    private Japanese1QuestionContract(){
    }

    // Create inner class for each different table in the database
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String COLUMN_DIFFICULTY = "difficulty";

    }
}
