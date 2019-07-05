package com.femicodes.sectionedmultiplerecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun linearSectionedRecyclerViewVertical(view: View) {
        startRecyclerViewActivity(RecyclerViewType.LINEAR_VERTICAL)
    }

    fun linearSectionedRecyclerViewHorizontal(view: View) {
        startRecyclerViewActivity(RecyclerViewType.LINEAR_HORIZONTAL)
    }

    fun gridSectionedRecyclerView(view: View) {
        startRecyclerViewActivity(RecyclerViewType.GRID)
    }

    /**
     * method to start RecyclerViewActivity
     *
     * @param recyclerViewType enum type to show recyclerview on basis if button call
     */
    private fun startRecyclerViewActivity(recyclerViewType: RecyclerViewType) {
        val bundle = Bundle()
        bundle.putSerializable(RecyclerViewActivity.RECYCLER_VIEW_TYPE, recyclerViewType)
        startActivity(Intent(this, RecyclerViewActivity::class.java).putExtras(bundle))
    }


}
