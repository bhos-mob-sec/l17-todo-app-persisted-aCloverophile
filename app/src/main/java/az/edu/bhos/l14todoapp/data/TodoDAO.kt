package az.edu.bhos.l14todoapp.data

import androidx.room.*
import az.edu.bhos.l14todoapp.data.dto.TodoLocalDto
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAll(): Flow<List<TodoLocalDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: TodoLocalDto)

    @Update
    suspend fun update(todo: TodoLocalDto)

    @Delete
    suspend fun delete(todo: TodoLocalDto)
}