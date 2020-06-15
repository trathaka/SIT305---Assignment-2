package com.example.assignment2.KoreanLesson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.assignment2.KoreanLesson.Korean3QuestionContract.QuestionsTable;
import java.util.ArrayList;

public class Korean3QuestionDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Korean3.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    // Constructor, only passing the context when creating an instance of this class
    public Korean3QuestionDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Initial place where the database gets created
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
        // Call a method to fill the questions into the table
        fillQuestionsTable();

    }

    // Update the database by dropping the current table then recreate it
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    // Method to fill questions to the table
    private void fillQuestionsTable() {
        // Create easy questions
        Korean3Question q1 = new Korean3Question("오다 (oda)", "Cry", "Come", "Call",2 , Korean3Question.DIFFICULTY_EASY);
        addQuestion(q1);
        Korean3Question q2 = new Korean3Question("먹다 (meogda)", "Teach", "Wait", "Eat", 3, Korean3Question.DIFFICULTY_EASY);
        addQuestion(q2);
        Korean3Question q3 = new Korean3Question("가다 (gada)", "Ask", "Help", "Go", 3, Korean3Question.DIFFICULTY_EASY);
        addQuestion(q3);
        Korean3Question q4 = new Korean3Question("앉다 (anjda)", "Sit", "Stand", "Run", 1, Korean3Question.DIFFICULTY_EASY);
        addQuestion(q4);
        Korean3Question q5 = new Korean3Question("자다 (jada)", "Write", "Sleep", "Talk", 2, Korean3Question.DIFFICULTY_EASY);
        addQuestion(q5);

        // Create medium questions
        Korean3Question q6 = new Korean3Question("싸우다 (ssauda)",
                "Exercise", "Fight", "Pay", 2, Korean3Question.DIFFICULTY_MEDIUM);
        addQuestion(q6);
        Korean3Question q7 = new Korean3Question("이기다 (igida)",
                "Draw", "Lose", "Win", 3, Korean3Question.DIFFICULTY_MEDIUM);
        addQuestion(q7);
        Korean3Question q8 = new Korean3Question("나가다 (nagada)",
                "Exit", "Enter", "Stay", 1, Korean3Question.DIFFICULTY_MEDIUM);
        addQuestion(q8);
        Korean3Question q9 = new Korean3Question("빌리다 (billida)",
                "Return", "Keep", "Borrow", 3, Korean3Question.DIFFICULTY_MEDIUM);
        addQuestion(q9);
        Korean3Question q10 = new Korean3Question("끓이다 (kkeulh-ida)",
                "Boil", "Fry", "Steam", 1, Korean3Question.DIFFICULTY_MEDIUM);
        addQuestion(q10);

        // Create hard questions
        Korean3Question q11 = new Korean3Question("사랑하다 (salanghada)",
                "Love", "Hate", "Like", 1, Korean3Question.DIFFICULTY_HARD);
        addQuestion(q11);
        Korean3Question q12 = new Korean3Question("약속하다 (yagsoghada)",
                "Lie", "Promise", "Confess", 2, Korean3Question.DIFFICULTY_HARD);
        addQuestion(q12);
        Korean3Question q13 = new Korean3Question("결혼하다 (gyeolhonhada)",
                "Break up", "Marry", "Born", 2, Korean3Question.DIFFICULTY_HARD);
        addQuestion(q13);
        Korean3Question q14 = new Korean3Question("도착하다 (dochaghada)",
                "Depart", "Arrive", "Answer", 2, Korean3Question.DIFFICULTY_HARD);
        addQuestion(q14);

        Korean3Question q15 = new Korean3Question("기억하다 (gieoghada)",
                "Worry", "Forget", "Remember", 3, Korean3Question.DIFFICULTY_HARD);
        addQuestion(q15);


    }

    // Method to insert questions into the database
    private void addQuestion(Korean3Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    // Method to retrieve the data out of the database
    public ArrayList<Korean3Question> getAllQuestions() {
        ArrayList<Korean3Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        // Move the cursor to the first entry to query it, else will return false if there is no entry
        if (c.moveToFirst()) {
            do {
                // Create a question object for each entry in the database
                Korean3Question question = new Korean3Question();
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

    // Method to retrieve the data out of the database based on the difficulty selected
    public ArrayList<Korean3Question> getQuestions(String difficulty) {
        ArrayList<Korean3Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        // Move the cursor to the first entry to query it, else will return false if there is no entry
        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);
        if (c.moveToFirst()) {
            do {
                // Create a question object for each entry in the database
                Korean3Question question = new Korean3Question();
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
