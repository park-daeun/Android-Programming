package kr.ac.dankook.mobile.prof.jeong.sharedPref

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.room.Room


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputName = findViewById<EditText>(R.id.inputName)
        val inputPwd = findViewById<EditText>(R.id.inputPwd)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

//        val sharedPref = getSharedPreferences("kr.ac.dankook.mobile.prof.jeong.sharedPref.SHARED_PREF", Context.MODE_PRIVATE)
//        val editor = sharedPref.edit()
//
//        editor.putString("username", "woojin")
//        editor.putString("password", "1234")
//        editor.putInt("id", 1234)
//
//        editor.apply()

//
//
//        val sharedPref = getSharedPreferences("kr.ac.dankook.example.SHARED_PREF", Context.MODE_PRIVATE);
//
//        val username:String? = sharedPref.getString("username", "unknown")
//        val password:String? = sharedPref.getString("password", "****")
//        val id = sharedPref.getInt("id", -1)
//        if(id != -1) {
//            if (username != null) {
//                Log.d(">>>SHAREDPREF", username)
//            }
//            if (password != null) {
//                Log.d(">>>SHAREDPREF", password)
//            }
//            Log.d(">>>SHAREDPREF", id.toString())
//        } else {
//            Log.d(">>>SHAREDPREF", "No login info")
//        }


        val db = Room.databaseBuilder(
                applicationContext,
                UserDB::class.java, "usedb"
        ).allowMainThreadQueries().build()

        val users = db.userDao().getAll()
        if(users.isNotEmpty()) {
            Log.d("USERDB", "Something in db")
            var readuser = db.userDao().findFirstUser()
            if(readuser != null) {
                Log.d("USERDB", readuser.uid.toString()+", "+readuser.username+", "+readuser.password)
                inputName.setText(readuser.username)
                inputPwd.setText(readuser.password)
            }
        }

        btnLogin.setOnClickListener {
            val uname  = inputName.getText().toString()
            val pwd = inputPwd.getText().toString()

            var readuser = db.userDao().findByUserName(uname)
            if(readuser != null) {
                Log.d("USERDB", "Login info exist, Do login!!")
            } else {
                var user = User(1, uname, pwd)
                db.userDao().insertAll(user)
            }
        }
    }
}