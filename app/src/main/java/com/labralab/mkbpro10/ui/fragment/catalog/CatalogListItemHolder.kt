package com.labralab.mkbpro10.ui.fragment.catalog

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.labralab.mkbpro10.model.entity.Section
import kotlinx.android.synthetic.main.view_calalog_list_item.view.*

class CatalogListItemHolder(itemView: View, val listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {

    var description: TextView = itemView.descriptionTV

    var code: TextView = itemView.codeTV

    fun bind(section: Section) {
        description.text = section.description
        code.text = section.code

        itemView.setOnClickListener {
            listener.onClick(section)
        }
    }

    interface OnItemClickListener {

        fun onClick(section: Section)
    }
}