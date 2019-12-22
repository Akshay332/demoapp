package com.example.demoapp.activities

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.adapter.UsersRecyclerAdapter
import com.example.demoapp.model.User
import com.example.demoapp.sql.DatabaseHelper
import kotlinx.android.synthetic.main.activity_home.*

class USersListActivity : AppCompatActivity() {

    private val activity = this@USersListActivity

    private lateinit var listUsers: MutableList<User>

    private lateinit var recyclerView: RecyclerView

    private lateinit var usersRecyclerAdapter: UsersRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        initObjects()
    }
    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        listUsers = ArrayList()
        usersRecyclerAdapter = UsersRecyclerAdapter(listUsers)

        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = usersRecyclerAdapter
        databaseHelper = DatabaseHelper(activity)

        val emailFromIntent = intent.getStringExtra("EMAIL")
        txtv_welcome.text = emailFromIntent

        val getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }

    /**
     * This class is to fetch all user records from SQLite
     */
    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<User>>() {

        override fun doInBackground(vararg p0: Void?): List<User> {
            return databaseHelper.getAllUser()
        }

        override fun onPostExecute(result: List<User>?) {
            super.onPostExecute(result)
            listUsers.clear()
            listUsers.addAll(result!!)
        }
    }
}
