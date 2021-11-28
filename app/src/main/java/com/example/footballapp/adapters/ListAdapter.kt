package com.example.footballapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.footballapp.R
import com.example.footballapp.models.Teams

import com.squareup.picasso.Picasso

class ListAdapter(
    private val context: Context,
    private val dataSource: Teams
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.data.standings.size
    }

    override fun getItem(p0: Int): Any {
        return dataSource.data.standings[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        dataSource.data.standings.shuffled()
        val rowView = inflater.inflate(R.layout.list_view_item, parent, false)
        val image = rowView.findViewById<ImageView>(R.id.teamImage)
        val teamName = rowView.findViewById<TextView>(R.id.teamName)
        val score1 = rowView.findViewById<TextView>(R.id.score1)
        val score2 = rowView.findViewById<TextView>(R.id.score2)
        val score3 = rowView.findViewById<TextView>(R.id.score3)
        val score4 = rowView.findViewById<TextView>(R.id.score4)
        score1.text = dataSource.data.standings[position].stats[0].value.toString()
        score2.text = dataSource.data.standings[position].stats[1].value.toString()
        score3.text = dataSource.data.standings[position].stats[2].value.toString()
        score4.text = dataSource.data.standings[position].stats[4].value.toString()
        val order = rowView.findViewById<TextView>(R.id.order)
        teamName.text = dataSource.data.standings[position].team.name
        order.text = (position + 1).toString()
        Picasso.get().load(dataSource.data.standings[position].team.logos[0].href).into(image)
        return rowView
    }
}

