package com.example.assignment2.JapaneseLesson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.assignment2.JapaneseLesson.Japanese1Question;
import com.example.assignment2.JapaneseLesson.Japanese1QuizContract.QuestionsTable;

import java.util.ArrayList;

public class Japanese1QuizDb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Japanese1.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public Japanese1QuizDb(@Nullable Context context) {
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
        Japanese1Question q1 = new Japanese1Question("コーヒー (Kōhī)", "Coffee", "Milk", "Tea", 1, Japanese1Question.DIFFICULTY_EASY);
        addQuestion(q1);
        Japanese1Question q2 = new Japanese1Question("牛乳 (Gyūnyū)", "Tea", "Milk", "Coffee", 2, Japanese1Question.DIFFICULTY_EASY);
        addQuestion(q2);
        Japanese1Question q3 = new Japanese1Question("ビール (Bīru)", "Champagne", "Wine", "Beer", 3, Japanese1Question.DIFFICULTY_EASY);
        addQuestion(q3);
        Japanese1Question q4 = new Japanese1Question("肉 (Niku)", "Meat", "Fish", "Vegetables", 1, Japanese1Question.DIFFICULTY_EASY);
        addQuestion(q4);
        Japanese1Question q5 = new Japanese1Question("パン (Pan)", "Dessert", "Bread", "Cookies", 2, Japanese1Question.DIFFICULTY_EASY);
        addQuestion(q5);


        Japanese1Question q6 = new Japanese1Question("帽子 (Bōshi)",
                "Skirt", "Hat", "Scarf", 2, Japanese1Question.DIFFICULTY_MEDIUM);
        addQuestion(q6);
        Japanese1Question q7 = new Japanese1Question("シャツ (Shatsu)",
                "Shirt", "Dress", "Socks", 1, Japanese1Question.DIFFICULTY_MEDIUM);
        addQuestion(q7);
        Japanese1Question q8 = new Japanese1Question("スーツ (Sūtsu)",
                "Blazer", "Shoe", "Suit", 3, Japanese1Question.DIFFICULTY_MEDIUM);
        addQuestion(q8);
        Japanese1Question q9 = new Japanese1Question("パンツ (Pantsu)",
                "T-Shirt", "Pants", "Shorts", 2, Japanese1Question.DIFFICULTY_MEDIUM);
        addQuestion(q9);
        Japanese1Question q10 = new Japanese1Question("ジャケット (Jaketto)",
                "Blouse", "Hoodies", "Jacket", 3, Japanese1Question.DIFFICULTY_MEDIUM);
        addQuestion(q10);

        Japanese1Question q11 = new Japanese1Question("スーパー (Sūpā)",
                "Supermarket", "Bicycle Shop", "Pawnshop", 1, Japanese1Question.DIFFICULTY_HARD);
        addQuestion(q11);
        Japanese1Question q12 = new Japanese1Question("はなや (Hanaya)",
                "Bakery", "Florist", "Cafeteria", 2, Japanese1Question.DIFFICULTY_HARD);
        addQuestion(q12);
        Japanese1Question q13 = new Japanese1Question("ちゅうかりょうりてん (Chi ~yuukaryouriten)",
                "Japanese Restaurant", "Chinese Restaurant", "Korean Restaurant", 2, Japanese1Question.DIFFICULTY_HARD);
        addQuestion(q13);
        Japanese1Question q14 = new Japanese1Question("ペットショップ (Petto shoppu)",
                "Pet Shop", "Fruit Shop", "Barber Shop", 1, Japanese1Question.DIFFICULTY_HARD);
        addQuestion(q14);
        Japanese1Question q15 = new Japanese1Question("じょうまえや (Ji ~youmaeya)",
                "Tailor's Shop", "Liquor Store", "Furniture Shop", 3, Japanese1Question.DIFFICULTY_HARD);
        addQuestion(q15);

    }

    private void addQuestion(Japanese1Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Japanese1Question> getAllQuestions() {
        ArrayList<Japanese1Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Japanese1Question question = new Japanese1Question();
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

    public ArrayList<Japanese1Question> getQuestions(String difficulty) {
        ArrayList<Japanese1Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);
        if (c.moveToFirst()) {
            do {
                Japanese1Question question = new Japanese1Question();
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
