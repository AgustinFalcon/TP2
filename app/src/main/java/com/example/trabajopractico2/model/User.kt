package com.example.trabajopractico2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "nombre")
    val name: String,
    @ColumnInfo(name = "apellido")
    val lastName: String,
    @ColumnInfo(name = "edad")
    val age: Int
): Serializable