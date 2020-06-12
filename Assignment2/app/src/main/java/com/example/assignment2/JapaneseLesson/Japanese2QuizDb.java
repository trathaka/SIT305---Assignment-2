package com.example.assignment2.JapaneseLesson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.assignment2.JapaneseLesson.Japanese2QuizContract.QuestionsTable;

import java.util.ArrayList;

public class Japanese2QuizDb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Japanese2.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public Japanese2QuizDb(@Nullable Context context) {
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
        Japanese2Question q1 = new Japanese2Question("とり (Tori)", "Bird", "Pig", "Sheep", 1, Japanese2Question.DIFFICULTY_EASY);
        addQuestion(q1);
        Japanese2Question q2 = new Japanese2Question("ねこ (Neko)", "Eagle", "Cat", "Goat", 2, Japanese2Question.DIFFICULTY_EASY);
        addQuestion(q2);
        Japanese2Question q3 = new Japanese2Question("いぬ (Inu)", "Oyster", "Dog", "Crab", 2, Japanese2Question.DIFFICULTY_EASY);
        addQuestion(q3);
        Japanese2Question q4 = new Japanese2Question("ハチ(Hachi)", "Firefly", "Lizard", "Bee", 3, Japanese2Question.DIFFICULTY_EASY);
        addQuestion(q4);
        Japanese2Question q5 = new Japanese2Question("ハエ(Hae)", "Tiger", "Otter", "Fly", 3, Japanese2Question.DIFFICULTY_EASY);
        addQuestion(q5);

        Japanese2Question q6 = new Japanese2Question("キツネ (Kitsune)",
                "Horse", "Fox", "Owl", 2, Japanese2Question.DIFFICULTY_MEDIUM);
        addQuestion(q6);
        Japanese2Question q7 = new Japanese2Question("タヌキ (Tanuki)",
                "Dolphin", "Centipede", "Raccoon", 3, Japanese2Question.DIFFICULTY_MEDIUM);
        addQuestion(q7);
        Japanese2Question q8 = new Japanese2Question("ゴキブリ (Gokiburi)",
                "Cockroach", "Peafowl", "Chimpanzee", 1, Japanese2Question.DIFFICULTY_MEDIUM);
        addQuestion(q8);
        Japanese2Question q9 = new Japanese2Question("カンガルー (kangaru)",
                "Kangaroo", "Coyote", "Dingo", 1, Japanese2Question.DIFFICULTY_MEDIUM);
        addQuestion(q9);
        Japanese2Question q10 = new Japanese2Question("ハクチョウ (hakuchou)",
                "Giraffe", "Hornbill", "Swan", 3, Japanese2Question.DIFFICULTY_MEDIUM);
        addQuestion(q10);

        Japanese2Question q11 = new Japanese2Question("シマウマ (Shimauma)",
                "Meerkat", "Zebra", "Koala", 2, Japanese2Question.DIFFICULTY_HARD);
        addQuestion(q11);
        Japanese2Question q12 = new Japanese2Question("ムカデ (mukade)",
                "Moth", "Centipede", "Skunk", 2, Japanese2Question.DIFFICULTY_HARD);
        addQuestion(q12);
        Japanese2Question q13 = new Japanese2Question("コウモリ (Koumori)",
                "Rabbit", "Quail", "Bat", 3, Japanese2Question.DIFFICULTY_HARD);
        addQuestion(q13);
        Japanese2Question q14 = new Japanese2Question("じんちょう(jinchou)",
                "Penguin", "Owl", "Yak", 1, Japanese2Question.DIFFICULTY_HARD);
        addQuestion(q14);
        Japanese2Question q15 = new Japanese2Question("カタツムリ (katatsumuri)",
                "Snail", "Leopard", "Jaguar", 1, Japanese2Question.DIFFICULTY_HARD);
        addQuestion(q15);

    }

    private void addQuestion(Japanese2Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Japanese2Question> getAllQuestions() {
        ArrayList<Japanese2Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Japanese2Question question = new Japanese2Question();
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

    public ArrayList<Japanese2Question> getQuestions(String difficulty) {
        ArrayList<Japanese2Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);
        if (c.moveToFirst()) {
            do {
                Japanese2Question question = new Japanese2Question();
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
