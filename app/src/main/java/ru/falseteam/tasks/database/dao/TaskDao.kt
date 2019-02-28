package ru.falseteam.tasks.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.falseteam.tasks.database.entity.Task

@Dao
abstract class TaskDao {
    @Query("SELECT * from tasks ORDER BY priority ASC, create_timestamp DESC")
    abstract fun getAllLiveData(): LiveData<List<Task>>

    @Insert
    abstract fun insert(task: Task)

    fun insertOnIO(task: Task): Single<Unit> =
            Single.fromCallable {insert(task)}.subscribeOn(Schedulers.io())
}