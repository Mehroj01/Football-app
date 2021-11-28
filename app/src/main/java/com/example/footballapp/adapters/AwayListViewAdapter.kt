package com.example.footballapp.adapters

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

class AwayListViewAdapter(
    private val context: Context,
    private val dataSource: Teams
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var countItem = 1
    override fun getCount(): Int {
        return if (dataSource.data.standings.size % 2 == 0) {
            dataSource.data.standings.size / 2
        } else {
            (dataSource.data.standings.size + 1) / 2
        }

    }

    override fun getItem(p0: Int): Any {
        return dataSource.data.standings[p0]
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
        score1.text =
            dataSource.data.standings[dataSource.data.standings.size - 1 - position].stats[0].value.toString()
        score2.text =
            dataSource.data.standings[dataSource.data.standings.size - 1 - position].stats[1].value.toString()
        score3.text =
            dataSource.data.standings[dataSource.data.standings.size - 1 - position].stats[2].value.toString()
        score4.text =
            dataSource.data.standings[dataSource.data.standings.size - 1 - position].stats[4].value.toString()
        val order = rowView.findViewById<TextView>(R.id.order)
        teamName.text =
            dataSource.data.standings[dataSource.data.standings.size - 1 - position].team.name
        order.text = (position + 1).toString()
        Picasso.get()
            .load(dataSource.data.standings[dataSource.data.standings.size - 1 - position].team.logos[0].href)
            .into(image)
        return rowView
    }
}