package com.femicodes.sectionedmultiplerecyclerview

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

class RecyclerViewActivity : AppCompatActivity() {
    private var recyclerViewType: RecyclerViewType = RecyclerViewType.LINEAR_VERTICAL
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_activity)

        //get enum type passed from MainActivity
        recyclerViewType = intent.getSerializableExtra(RECYCLER_VIEW_TYPE) as RecyclerViewType
        setUpToolbarTitle()
        setUpRecyclerView()
        populateRecyclerView()
    }

    //set toolbar title and set back button
    private fun setUpToolbarTitle() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        when (recyclerViewType) {
            RecyclerViewType.LINEAR_HORIZONTAL -> supportActionBar!!.title =
                resources.getString(R.string.linear_sectioned_recyclerview_horizontal)
            RecyclerViewType.LINEAR_VERTICAL -> supportActionBar!!.title =
                resources.getString(R.string.linear_sectioned_recyclerview_vertical)
            RecyclerViewType.GRID -> supportActionBar!!.title =
                resources.getString(R.string.grid_sectioned_recyclerview)
        }
    }

    //setup recycler view
    private fun setUpRecyclerView() {
        recyclerView = findViewById(R.id.sectioned_recycler_view)
        recyclerView!!.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = linearLayoutManager
    }

    //populate recycler view
    private fun populateRecyclerView() {
        val sectionModelArrayList = ArrayList<SectionModel>()
        //for loop for sections
        for (i in 1..5) {
            val itemArrayList = ArrayList<String>()
            //for loop for items
            for (j in 1..10) {
                itemArrayList.add("Item $j")
            }

            //add the section and items to array list
            sectionModelArrayList.add(SectionModel("Section $i", itemArrayList))
        }

        val adapter = SectionRecyclerViewAdapter(this, recyclerViewType, sectionModelArrayList)
        recyclerView!!.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

         val RECYCLER_VIEW_TYPE = "recycler_view_type"
    }
}
