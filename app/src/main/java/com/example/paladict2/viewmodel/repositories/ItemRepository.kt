package com.example.paladict2.viewmodel.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.paladict2.Constants
import com.example.paladict2.model.Item
import com.example.paladict2.model.ItemDao
import com.example.paladict2.networking.PaladinsAPIService
import com.example.paladict2.utils.JavaUtils.createSignature
import com.example.paladict2.utils.JavaUtils.getDate
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync
import retrofit2.HttpException

class ItemRepository(
    internal val itemDao: ItemDao
) {

    private var items = mutableListOf<Item>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCorService by lazy {
        PaladinsAPIService.createCoreService()
    }

    private fun add(item: Item) {
        itemDao.insert(item)
    }

    fun get(id: Int): LiveData<Item> {
        return itemDao.getItemByID(id)
    }


    fun updateDBFromApi(session: String) {
        coroutineScope.launch {
            val request = thisApiCorService.getItems(
                Constants.PALADINS_DEV_ID,
                createSignature("getitems"),
                getDate(),
                session,
                "1"
            )
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    items = response
                    addItemsToDB(items)
                } catch (e: HttpException) {
                    Log.d("", "")
                }
            }
        }
    }

    private fun addItemsToDB(
        items: MutableList<Item>) {
        doAsync {
            for (item in items) {
                add(item)
            }
        }
    }

}