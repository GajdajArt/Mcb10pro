package com.labralab.mkbpro10.ui.fragment.catalog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.labralab.mkbpro10.R
import com.labralab.mkbpro10.model.entity.Section

class CatalogAdapter() : RecyclerView.Adapter<CatalogListItemHolder>() {

    var list = ArrayList<Section>()

    lateinit var listener: CatalogListItemHolder.OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CatalogListItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_calalog_list_item, parent, false)
        return CatalogListItemHolder(view, listener)
    }

    override fun getItemCount() =  list.size

    override fun onBindViewHolder(holder: CatalogListItemHolder, pos: Int) {
        holder.bind(list[pos])
    }
}