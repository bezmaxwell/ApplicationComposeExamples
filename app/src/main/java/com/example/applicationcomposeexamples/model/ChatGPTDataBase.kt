package com.example.applicationcomposeexamples.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Database
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Insert
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Query
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Update
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String,
    val price: Double
)


@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Query("DELETE FROM items WHERE id = :itemId")
    suspend fun delete(itemId: Long)

    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<Item>
}

@Database(entities = [Item::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}

class ItemRepository(private val itemDao: ItemDao) {
    suspend fun insert(item: Item) {
        itemDao.insert(item)
    }

    suspend fun update(item: Item) {
        itemDao.update(item)
    }

    suspend fun delete(itemId: Long) {
        itemDao.delete(itemId)
    }

    suspend fun getAllItems(): List<Item> {
        return itemDao.getAllItems()
    }
}


class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    fun insert(item: Item) {
        viewModelScope.launch {
            repository.insert(item)
        }
    }

    fun update(item: Item) {
        viewModelScope.launch {
            repository.update(item)
        }
    }

    fun delete(itemId: Long) {
        viewModelScope.launch {
            repository.delete(itemId)
        }
    }
    suspend fun getAllItems() = repository.getAllItems()
}

