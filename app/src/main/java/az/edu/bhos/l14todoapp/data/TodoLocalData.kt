package az.edu.bhos.l14todoapp.data

import az.edu.bhos.l14todoapp.data.dto.TodoLocalDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface TodoLocalData {
    fun observeItems(): Flow<List<TodoLocalDto>>
    suspend fun save(data: List<TodoLocalDto>)
}

class TodoLocalDataImpl(
    private val todoDao: TodoDao
) : TodoLocalData {

    override fun observeItems(): Flow<List<TodoLocalDto>> {
        return todoDao.getAll().map { todos ->
            todos.sortedBy { todo ->
                when (todo.weekday) {
                    "Monday" -> 1
                    "Tuesday" -> 2
                    "Wednesday" -> 3
                    "Thursday" -> 4
                    "Friday" -> 5
                    "Saturday" -> 6
                    "Sunday" -> 7
                    else -> 8
                }
            }
        }
    }

    override suspend fun save(data: List<TodoLocalDto>) {
        for (todo in data) {
            todoDao.insert(todo)
        }
    }
}