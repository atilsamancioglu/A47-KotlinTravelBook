package com.atilsamancioglu.kotlintravelbook.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.atilsamancioglu.kotlintravelbook.model.Place
import com.atilsamancioglu.kotlintravelbook.adapter.PlaceAdapter
import com.atilsamancioglu.kotlintravelbook.R
import com.atilsamancioglu.kotlintravelbook.databinding.ActivityMainBinding
import com.atilsamancioglu.kotlintravelbook.roomdb.PlaceDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mDisposable = CompositeDisposable()
    private lateinit var places: ArrayList<Place>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        places = ArrayList()

        val db = Room.databaseBuilder(applicationContext, PlaceDatabase::class.java, "Places")
            //.allowMainThreadQueries()
            .build()

        val placeDao = db.placeDao()

        mDisposable.add(placeDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))

    }

    private fun handleResponse(placeList: List<Place>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val placeAdapter = PlaceAdapter(placeList)
        binding.recyclerView.adapter = placeAdapter
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.travel_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_place) {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("info", "new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable.clear()
    }

}