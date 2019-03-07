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
interface TaskDao {
    @Query("SELECT * from tasks ORDER BY priority ASC, create_timestamp DESC")
    fun getAllLiveData(): LiveData<List<Task>>

    @Query("SELECT * from tasks ORDER BY priority ASC, create_timestamp DESC")
    fun getAllDataSource(): DataSource.Factory<Int, Task>

    @Query("DELETE from tasks")
    fun deleteAll(): Int

    @Insert
    fun insert(task: Task)

    @Insert
    fun insert(task: List<Task>)

}