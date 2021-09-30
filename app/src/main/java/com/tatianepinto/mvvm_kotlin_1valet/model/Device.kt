package com.tatianepinto.mvvm_kotlin_1valet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.Nullable

@Entity
class Device (

    @PrimaryKey(autoGenerate = true) var id: Long? = null,

    var name: String = "",
    var type: String = "",
    var price: Double = 0.0,
    var currency: String = "",
    var isFavorite: Boolean = false,
    var imageUrl: String = "",
    var title: String = "",
    var description: String = ""
)

//@Entity
//public class Playlist {
//    @PrimaryKey(autoGenerate = true)
//    long playlistId
//    String name
//    @Nullable
//    String description
//    @ColumnInfo(defaultValue = "normal")
//    String category;
//    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
//    String createdTime;
//    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
//    String lastModifiedTime;
//}