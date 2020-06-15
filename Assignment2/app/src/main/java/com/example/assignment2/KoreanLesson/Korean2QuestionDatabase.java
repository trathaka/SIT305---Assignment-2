package com.example.assignment2.KoreanLesson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.assignment2.KoreanLesson.Korean2QuestionContract.QuestionsTable;
import java.util.ArrayList;

public class Korean2QuestionDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Korean2.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    // Constructor, only passing the context when creating an instance of this class
    public Korean2QuestionDatabase(@Nullable Context context) {
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
        Korean2Question q1 = new Korean2Question("개 (gae)", "Fish", "Dog", "Cat", 2, Korean2Question.DIFFICULTY_EASY);
        addQuestion(q1);
        Korean2Question q2 = new Korean2Question("말 (mal)", "Horse", "Monkey", "Bird", 1, Korean2Question.DIFFICULTY_EASY);
        addQuestion(q2);
        Korean2Question q3 = new Korean2Question("소 (so)", "Seal", "Tiger", "Cow", 3, Korean2Question.DIFFICULTY_EASY);
        addQuestion(q3);
        Korean2Question q4 = new Korean2Question("닭 (dak)", "Turtle", "Bee", "Chicken", 3, Korean2Question.DIFFICULTY_EASY);
        addQuestion(q4);
        Korean2Question q5 = new Korean2Question("곰 (saja)", "Lion", "Bear", "Panda", 2, Korean2Question.DIFFICULTY_EASY);
        addQuestion(q5);

        // Create medium questions
        Korean2Question q6 = new Korean2Question("오리 (ori)",
                "Ostrich", "Duck", "Eel", 2, Korean2Question.DIFFICULTY_MEDIUM);
        addQuestion(q6);
        Korean2Question q7 = new Korean2Question("백조 (baekjo)",
                "Peacock", "Goose", "Swan", 3, Korean2Question.DIFFICULTY_MEDIUM);
        addQuestion(q7);
        Korean2Question q8 = new Korean2Question("상어 (sangeo)",
                "Penguin", "Shark", "Bison", 2, Korean2Question.DIFFICULTY_MEDIUM);
        addQuestion(q8);
        Korean2Question q9 = new Korean2Question("낙타 (nakta)",
                "Ant", "Snail", "Camel", 3, Korean2Question.DIFFICULTY_MEDIUM);
        addQuestion(q9);
        Korean2Question q10 = new Korean2Question("치타 (chita)",
                "Mogi", "Cheetah", "Giraffe", 2, Korean2Question.DIFFICULTY_MEDIUM);
        addQuestion(q10);

        // Create hard questions
        Korean2Question q11 = new Korean2Question("까마귀 (kkamagwi)",
                "Crow", "Zebra", "Leopard", 1, Korean2Question.DIFFICULTY_HARD);
        addQuestion(q11);
        Korean2Question q12 = new Korean2Question("하이에나 (haiena)",
                "Antelope", "Hyena", "Koala", 2, Korean2Question.DIFFICULTY_HARD);
        addQuestion(q12);
        Korean2Question q13 = new Korean2Question("북극곰 (bukgeukgom)",
                "A", "Bison", "Polar Bear", 3, Korean2Question.DIFFICULTY_HARD);
        addQuestion(q13);
        Korean2Question q14 = new Korean2Question("캥거루 (kaengeoru)",
                "Kangaroo", "Octopus", "Frog", 1, Korean2Question.DIFFICULTY_HARD);
        addQuestion(q14);
        Korean2Question q15 = new Korean2Question("코끼리 (kokkiri)",
                "Whale", "Elephant", "Pig", 2, Korean2Question.DIFFICULTY_HARD);
        addQuestion(q15);

    }

    // Method to insert questions into the database
    private void addQuestion(Korean2Question question) {
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
    public ArrayList<Korean2Question> getAllQuestions() {
        ArrayList<Korean2Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        // Move the cursor to the first entry to query it, else will return false if there is no entry
        if (c.moveToFirst()) {
            do {
                // Create a question object for each entry in the database
                Korean2Question question = new Korean2Question();
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
    public ArrayList<Korean2Question> getQuestions(String difficulty) {
        ArrayList<Korean2Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        // Move the cursor to the first entry to query it, else will return false if there is no entry
        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);
        if (c.moveToFirst()) {
            do {
                // Create a question object for each entry in the database
                Korean2Question question = new Korean2Question();
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
