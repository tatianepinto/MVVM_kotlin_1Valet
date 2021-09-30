package com.tatianepinto.mvvm_kotlin_1valet

import android.app.Application
import androidx.recyclerview.widget.RecyclerView
import com.tatianepinto.mvvm_kotlin_1valet.db.ConnectionService
import com.tatianepinto.mvvm_kotlin_1valet.repository.DevicesRepositoryRoom
import org.junit.Test

import org.junit.Assert.*

/**
 * DevicesRecyclerViewAdapter testing.
 *
 */
class CheckConnectionGsonUnitTest {

    @Test
    fun connectionGson_isCorrect() {
        var connectionService: ConnectionService? = ConnectionService.getInstance()
        assertEquals(true, connectionService?.getAllDevices()?.isExecuted)
    }
}