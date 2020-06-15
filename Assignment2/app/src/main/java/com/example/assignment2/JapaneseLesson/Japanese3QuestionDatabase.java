package com.example.assignment2.JapaneseLesson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.assignment2.JapaneseLesson.Japanese3QuestionContract.QuestionsTable;
import java.util.ArrayList;

public class Japanese3QuestionDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Japanese3.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    // Constructor, only passing the context when creating an instance of this class
    public Japanese3QuestionDatabase(@Nullable Context context) {
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
        // Example layout
        Japanese3Question q1 = new Japanese3Question("Japanese Easy: A is correct", "A", "B", "C", 1, Japanese3Question.DIFFICULTY_EASY);
        addQuestion(q1);
        Japanese3Question q2 = new Japanese3Question("Japanese Medium1: B is correct",
                "ㄱ", "B", "C", 2, Japanese3Question.DIFFICULTY_MEDIUM);
        addQuestion(q2);
        Japanese3Question q3 = new Japanese3Question("Japanese Medium: C is correct",
                "A", "B", "C", 3, Japanese3Question.DIFFICULTY_MEDIUM);
        addQuestion(q3);
        Japanese3Question q4 = new Japanese3Question("Japanese Hard: A is correct",
                "A", "B", "C", 1, Japanese3Question.DIFFICULTY_HARD);
        addQuestion(q4);
        Japanese3Question q5 = new Japanese3Question("Japanese Hard: B is correct",
                "A", "B", "C", 2, Japanese3Question.DIFFICULTY_HARD);
        addQuestion(q5);
        Japanese3Question q6 = new Japanese3Question("Japanese Hard: C is correct",
                "A", "B", "C", 3, Japanese3Question.DIFFICULTY_HARD);
        addQuestion(q6);

    }

    // Method to insert questions into the database
    private void addQuestion(Japanese3Question question) {
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
    public ArrayList<Japanese3Question> getAllQuestions() {
        ArrayList<Japanese3Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        // Move the cursor to the first entry to query it, else will return false if there is no entry
        if (c.moveToFirst()) {
            do {
                // Create a question object for each entry in the database
                Japanese3Question question = new Japanese3Question();
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
    public ArrayList<Japanese3Question> getQuestions(String difficulty) {
        ArrayList<Japanese3Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        // Move the cursor to the first entry to query it, else will return false if there is no entry
        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);
        if (c.moveToFirst()) {
            do {
                // Create a question object for each entry in the database
                Japanese3Question question = new Japanese3Question();
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
