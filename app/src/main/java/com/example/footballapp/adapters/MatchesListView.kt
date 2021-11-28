package com.example.footballapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.footballapp.R
import com.example.footballapp.model_extra.MainModel
import com.example.footballapp.models.Teams
import com.squareup.picasso.Picasso

class MatchesListView(
    private val context: Context,
    private val dataSource: MainModel
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.standings.size
    }

    override fun getItem(p0: Int): Any {
        return dataSource.standings[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.list_view_item, parent, false)
        val image = rowView.findViewById<ImageView>(R.id.teamImage)
        val teamName = rowView.findViewById<TextView>(R.id.teamName)
        val score1 = rowView.findViewById<TextView>(R.id.score1)
        val score2 = rowView.findViewById<TextView>(R.id.score2)
        val score3 = rowView.findViewById<TextView>(R.id.score3)
        val score4 = rowView.findViewById<TextView>(R.id.score4)
        score1.text = dataSource.standings[position].table[0].goalsFor.toString()
        score2.text = dataSource.standings[position].table[1].goalsFor.toString()
        score3.text = dataSource.standings[position].table[2].goalsFor.toString()
        score4.text = dataSource.standings[position].table[3].goalsFor.toString()
        val order = rowView.findViewById<TextView>(R.id.order)
        teamName.text = dataSource.standings[position].table[0].team.name
        order.text = (position + 1).toString()
        Picasso.get().load(dataSource.standings[position].table[0].team.crestUrl).into(image)
        return rowView
    }
}