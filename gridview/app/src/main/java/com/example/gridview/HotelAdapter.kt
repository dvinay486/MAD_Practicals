package com.example.gridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * Custom Adapter class to bind Hotel Management data to the GridView.
 * BaseAdapter is used as a base class for providing data to AdapterView.
 */
class HotelAdapter(private val context: Context, private val items: List<HotelItem>) : BaseAdapter() {

    // Returns the total number of items in the data set
    override fun getCount(): Int = items.size

    // Returns the data item at the specified position
    override fun getItem(position: Int): Any = items[position]

    // Returns the row id associated with the specified position
    override fun getItemId(position: Int): Long = position.toLong()

    /**
     * Get a View that displays the data at the specified position in the data set.
     * This is where we inflate the custom layout (grid_item.xml) and set the data.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            // Inflate the custom layout for each grid item
            view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        // Get the current item based on position
        val currentItem = items[position]

        // Bind data to the views using the ViewHolder pattern for better performance
        viewHolder.imageView.setImageResource(currentItem.imageResource)
        viewHolder.textView.text = currentItem.name

        return view
    }

    /**
     * ViewHolder class to hold references to the views within a grid item layout.
     * This helps in recycling views and improving performance.
     */
    private class ViewHolder(view: View) {
        val imageView: ImageView = view.findViewById(R.id.itemImage)
        val textView: TextView = view.findViewById(R.id.itemText)
    }
}

/**
 * Data class to represent a Hotel Management category item.
 */
data class HotelItem(val name: String, val imageResource: Int)
