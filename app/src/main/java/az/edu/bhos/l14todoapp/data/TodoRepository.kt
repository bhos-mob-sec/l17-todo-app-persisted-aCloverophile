package az.edu.bhos.l14todoapp.data

import az.edu.bhos.l14todoapp.entities.TodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

interface TodoRepository {
    suspend fun syncTodos()
    fun observeTodoEntries(): Flow<List<TodoEntity>>
}

class TodoRepositoryImpl(
    private val localData: TodoLocalData,
    private val remoteData: TodoRemoteData
) : TodoRepository {

    override suspend fun syncTodos() {
        val todoList = remoteData.getTodos()
        localData.save(todoList.map { it.toLocal() })
    }

    override fun observeTodoEntries(): Flow<List<TodoEntity>> {
        return localData.observeItems().map { todos ->
            todos.map { todo ->
                TodoEntity(
                    id = todo.id,
                    title = todo.title,
                    completed = todo.completed,
                    weekday = todo.weekday
                )
            }
        }
    }
}