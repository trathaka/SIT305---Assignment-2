package com.example.assignment2.KoreanLesson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.assignment2.KoreanLesson.Korean1QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Korean1QuizDb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Korean1.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public Korean1QuizDb(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    private void fillQuestionsTable() {
        Korean1Question q1 = new Korean1Question("배 (bae)", "Orange", "Pineapple", "Pear", 3, Korean1Question.DIFFICULTY_EASY);
        addQuestion(q1);
        Korean1Question q2 = new Korean1Question("잼 (jaem)", "Cookies", "Jam", "Milk", 2, Korean1Question.DIFFICULTY_EASY);
        addQuestion(q2);
        Korean1Question q3 = new Korean1Question("차 (cha)", "Tea", "Coffee", "Juice", 1, Korean1Question.DIFFICULTY_EASY);
        addQuestion(q3);
        Korean1Question q4 = new Korean1Question("꿀 (kkul)", "Sugar", "Cream", "Honey", 3, Korean1Question.DIFFICULTY_EASY);
        addQuestion(q4);
        Korean1Question q5 = new Korean1Question("빵 (ppang)", "Sandwich", "Bread", "Ham", 2, Korean1Question.DIFFICULTY_EASY);
        addQuestion(q5);

        Korean1Question q6 = new Korean1Question("식초 (kecheob)",
                "Vinegar", "Ketchup", "Seasoning", 2, Korean1Question.DIFFICULTY_MEDIUM);
        addQuestion(q6);
        Korean1Question q7 = new Korean1Question("국수 (gugsu)",
                "Pasta", "Wheat", "Noodle", 3, Korean1Question.DIFFICULTY_MEDIUM);
        addQuestion(q7);
        Korean1Question q8 = new Korean1Question("도넛 (doneos)",
                "Chocolate", "Doughnut", "Cheese", 2, Korean1Question.DIFFICULTY_MEDIUM);
        addQuestion(q8);
        Korean1Question q9 = new Korean1Question("설탕 (seoltang)",
                "Candy", "Desert", "Sugar", 3, Korean1Question.DIFFICULTY_MEDIUM);
        addQuestion(q9);
        Korean1Question q10 = new Korean1Question("오일 (aoil)",
                "Cereal", "Oil", "Cake", 2, Korean1Question.DIFFICULTY_MEDIUM);
        addQuestion(q10);


        Korean1Question q11 = new Korean1Question("아이스크림 (aiseukeulim)",
                "Ice Cream", "Brownies", "Custard", 1, Korean1Question.DIFFICULTY_HARD);
        addQuestion(q11);
        Korean1Question q12 = new Korean1Question("감자 튀김 (gamja twigim)",
                "Ground Beef", "French Fries", "Confectionery", 2, Korean1Question.DIFFICULTY_HARD);
        addQuestion(q12);
        Korean1Question q13 = new Korean1Question("아보카도 (abokado)",
                "Persimmon", "Apricot", "Avocado", 3, Korean1Question.DIFFICULTY_HARD);
        addQuestion(q13);
        Korean1Question q14 = new Korean1Question("마요네즈 (mayonejeu)",
                "Mayonnaise", "Tomato Sauce", "Sour Cream", 1, Korean1Question.DIFFICULTY_HARD);
        addQuestion(q14);
        Korean1Question q15 = new Korean1Question("바게트 (bageteu)",
                "Dumplings", "Baguette", "Lemonade", 2, Korean1Question.DIFFICULTY_HARD);
        addQuestion(q15);

    }

    private void addQuestion(Korean1Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Korean1Question> getAllQuestions() {
        ArrayList<Korean1Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Korean1Question question = new Korean1Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Korean1Question> getQuestions(String difficulty) {
        ArrayList<Korean1Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);
        if (c.moveToFirst()) {
            do {
                Korean1Question question = new Korean1Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
