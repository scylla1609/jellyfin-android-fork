package org.dzair.mobile.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import org.dzair.mobile.data.dao.DownloadDao
import org.dzair.mobile.data.dao.ServerDao
import org.dzair.mobile.data.dao.UserDao
import org.dzair.mobile.data.entity.DownloadEntity
import org.dzair.mobile.data.entity.ServerEntity
import org.dzair.mobile.data.entity.UserEntity

@Database(
    entities = [
        ServerEntity::class,
        UserEntity::class,
        DownloadEntity::class,
    ],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 2, to = 3),
    ],
)
abstract class JellyfinDatabase : RoomDatabase() {
    abstract val serverDao: ServerDao
    abstract val userDao: UserDao
    abstract val downloadDao: DownloadDao
}
