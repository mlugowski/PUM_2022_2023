package pl.edu.uwr.pum.lists

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHandler(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Lists.db"
        private const val TABLE_LISTS = "ListsTable"
        private const val TABLE_TASKS = "TasksTable"

        private const val COLUMN_ID_LISTS = "_id"
        private const val COLUMN_NAME_LISTS = "name"

        private const val COLUMN_ID_TASKS = "_id"
        private const val COLUMN_NAME_TASKS = "name"
        private const val COLUMN_INDEX_TASKS = "taksIndex"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_LISTS_TABLE =
            "CREATE TABLE $TABLE_LISTS(" +
                    "$COLUMN_ID_LISTS INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "$COLUMN_NAME_LISTS TEXT)"
        db?.execSQL(CREATE_LISTS_TABLE)
        val CREATE_TASKS_TABLE =
            "CREATE TABLE $TABLE_TASKS(" +
                    "$COLUMN_ID_TASKS INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "$COLUMN_NAME_TASKS TEXT," +
                    "$COLUMN_INDEX_TASKS TEXT)"
        db?.execSQL(CREATE_TASKS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_LISTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TASKS")
        onCreate(db)
    }

    fun addList(nameList: String) {
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_LISTS WHERE $COLUMN_NAME_LISTS='$nameList'", null)
        if (!cursor.moveToFirst()) {
            val contentValues = ContentValues()
            contentValues.put(COLUMN_NAME_LISTS, nameList)
            db.insert(TABLE_LISTS, null, contentValues)
        }
        db.close()
        cursor.close()
    }

    fun deleteList(nameList: String) {
        val db = this.writableDatabase
        db.delete(TABLE_LISTS, "$COLUMN_NAME_LISTS='$nameList'", null)
        db.delete(TABLE_TASKS, "$COLUMN_INDEX_TASKS='$nameList'", null)
        db.close()
    }

    fun getLists() : List<TaskList> {
        val lists: MutableList<TaskList> = ArrayList()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_LISTS", null)
        if (cursor.moveToFirst()) {
            do {
                lists.add(TaskList(cursor.getInt(0), cursor.getString(1)))
            } while(cursor.moveToNext())
        }
        db.close()
        cursor.close()
        return lists
    }

    fun addTask(task: Task) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME_TASKS, task.nameTask)
        contentValues.put(COLUMN_INDEX_TASKS, task.nameList)
        db.insert(TABLE_TASKS, null, contentValues)
        db.close()
    }

    fun deleteTask(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_TASKS, "$COLUMN_ID_TASKS=$id", null)
        db.close()
    }

    fun getTasks(nameList: String) : List<Task> {
        val tasks: MutableList<Task> = ArrayList()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_TASKS WHERE $COLUMN_INDEX_TASKS='$nameList'", null)
        if (cursor.moveToFirst()) {
            do {
                tasks.add(Task(cursor.getInt(0), cursor.getString(1), cursor.getString(2)))
            } while(cursor.moveToNext())
        }
        db.close()
        cursor.close()
        return tasks
    }
}