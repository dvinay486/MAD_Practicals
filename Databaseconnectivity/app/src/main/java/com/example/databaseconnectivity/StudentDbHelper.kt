package com.example.databaseconnectivity

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * StudentDbHelper is a helper class to manage database creation and version management.
 *
 * Why use this class?
 * This class provides a structured way to create, open, and upgrade a SQLite database.
 * By extending SQLiteOpenHelper, we get a robust framework for managing the database lifecycle,
 * which is particularly useful as the app evolves and the database schema changes.
 *
 * When to use this class?
 * Use this class whenever you need to interact with a SQLite database in your Android application.
 * It's the standard way to handle all database operations.
 */
class StudentDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    /**
     * This is the name of the database file.
     */
    companion object {
        private const val DATABASE_NAME = "students.db"
        private const val DATABASE_VERSION = 1
    }

    /**
     * This object defines the schema of the Student table.
     */
    object StudentEntry {
        const val TABLE_NAME = "students"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_EMAIL = "email"
    }

    /**
     * Called when the database is created for the first time.
     * This is where the creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE ${StudentEntry.TABLE_NAME} (" +
                "${StudentEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${StudentEntry.COLUMN_NAME_NAME} TEXT," +
                "${StudentEntry.COLUMN_NAME_EMAIL} TEXT)"
        db.execSQL(createTable)
    }

    /**
     * Called when the database needs to be upgraded.
     * This method will be called when the database version is increased.
     *
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS ${StudentEntry.TABLE_NAME}")
        onCreate(db)
    }
}