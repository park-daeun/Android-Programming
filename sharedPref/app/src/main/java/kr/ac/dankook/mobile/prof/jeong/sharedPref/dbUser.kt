package kr.ac.dankook.mobile.prof.jeong.sharedPref

import androidx.room.*

@Entity(tableName="users")
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "password") val password: String?
)

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users LIMIT 1")
    fun findFirstUser(): User


    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM users WHERE username LIKE :uName LIMIT 1")
    fun findByUserName(uName: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(users: User)
}


@Database(entities = arrayOf(User::class), version=2)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}