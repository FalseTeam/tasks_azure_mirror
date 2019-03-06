package ru.falseteam.tasks.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.falseteam.tasks.database.entity.Task

@Dao
abstract class TaskDao {
    @Query("SELECT * from tasks ORDER BY priority ASC, create_timestamp DESC")
    abstract fun getAllLiveData(): LiveData<List<Task>>

    @Query("SELECT * from tasks ORDER BY priority ASC, create_timestamp DESC")
    abstract fun getAllDataSource(): DataSource.Factory<Int, Task>

    @Query("DELETE from tasks")
    abstract fun deleteAll(): Int

    @Insert
    abstract fun insert(task: Task)

    @Insert
    abstract fun insert(task: List<Task>)

    fun insertOnIO(task: Task): Single<Unit> =
            Single.fromCallable { insert(task) }.subscribeOn(Schedulers.io())

    fun insertOnIO(task: List<Task>): Single<Unit> =
            Single.fromCallable { insert(task) }.subscribeOn(Schedulers.io())

    fun deleteAllOnIO(): Single<Int> =
            Single.fromCallable { deleteAll() }.subscribeOn(Schedulers.io())

}