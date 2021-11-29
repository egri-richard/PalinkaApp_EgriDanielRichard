package com.example.palinkaapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyDB.db"
val TABLE_NAME = "palinkak"
val COL_ID = "id"
val COL_FOZO = "fozo"
val COL_GYUMOLCS = "gyumolcs"
val COL_ALKOHOL = "alkohol"

class DBHandler(var context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable =   "CREATE TABLE " + TABLE_NAME + " (" +
                            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                            COL_FOZO + " VARCHAR(256)," +
                            COL_GYUMOLCS + " VARCHAR(256)," +
                            COL_ALKOHOL + " INTEGER)";

        p0?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insert(p:Palinka) {
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_FOZO, p.fozo)
        cv.put(COL_GYUMOLCS, p.gyumolcs)
        cv.put(COL_ALKOHOL, p.alkohol)

        var result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong()) {
            Toast.makeText(context, "Sikertelen", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Sikeres", Toast.LENGTH_SHORT).show()
        }
    }

    fun listPalinka() : MutableList<Palinka>{
        var list : MutableList<Palinka> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val p = Palinka(
                    result.getString(result.getColumnIndex(COL_ID)).toInt(),
                    result.getString(result.getColumnIndex(COL_FOZO)),
                    result.getString(result.getColumnIndex(COL_GYUMOLCS)),
                    result.getString(result.getColumnIndex(COL_ALKOHOL)).toInt()
                )

                list.add(p)
            } while (result.moveToNext())
        }

        result.close()
        db.close()

        return list
    }
}