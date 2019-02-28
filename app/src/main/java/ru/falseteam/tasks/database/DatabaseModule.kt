package ru.falseteam.tasks.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.falseteam.tasks.database.dao.TaskDao
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    //TODO delete this
    fun getDatabase(context: Context): Database =
            Room.databaseBuilder(context, Database::class.java, "database")
                    .fallbackToDestructiveMigration()
                    .build()

    @Provides
    fun getTaskDao(database: Database): TaskDao = database.getTaskDao()
}