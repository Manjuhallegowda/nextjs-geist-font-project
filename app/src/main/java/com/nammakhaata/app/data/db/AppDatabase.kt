package com.nammakhaata.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nammakhaata.app.data.model.Transaction
import com.nammakhaata.app.data.model.TransactionTypeConverter

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
@TypeConverters(TransactionTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nammakhaata_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
