package com.femicodes.sectionedmultiplerecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

internal class SectionRecyclerViewAdapter(
    private val context: Context,
    private val recyclerViewType: RecyclerViewType,
    private val sectionModelArrayList: ArrayList<SectionModel>
) : RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder>() {


    internal inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val sectionLabel: TextView = itemView.findViewById(R.id.section_label)
        internal val showAllButton: TextView = itemView.findViewById(R.id.section_show_all_button)
        val itemRecyclerView: RecyclerView = itemView.findViewById(R.id.item_recycler_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.section_custom_row_layout, parent, false)
        return SectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val sectionModel = sectionModelArrayList[position]
        holder.sectionLabel.text = sectionModel.sectionLabel

        //recycler view for items
        holder.itemRecyclerView.setHasFixedSize(true)
        holder.itemRecyclerView.isNestedScrollingEnabled = false

        /* set layout manager on basis of recyclerview enum type */
        when (recyclerViewType) {
            RecyclerViewType.LINEAR_VERTICAL -> {
                val linearLayoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                holder.itemRecyclerView.layoutManager = linearLayoutManager
            }
            RecyclerViewType.LINEAR_HORIZONTAL -> {
                val linearLayoutManager1 =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                holder.itemRecyclerView.layoutManager = linearLayoutManager1
            }
            RecyclerViewType.GRID -> {
                val gridLayoutManager = GridLayoutManager(context, 3)
                holder.itemRecyclerView.layoutManager = gridLayoutManager
            }
        }
        val adapter = ItemRecyclerViewAdapter(context, sectionModel.itemArrayList)
        holder.itemRecyclerView.adapter = adapter

        //show toast on click of show all button
        holder.showAllButton.setOnClickListener {
            Toast.makeText(
                context,
                "You clicked on Show All of : " + sectionModel.sectionLabel,
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun getItemCount(): Int {
        return sectionModelArrayList.size
    }


}
