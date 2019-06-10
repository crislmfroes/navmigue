package com.crislmfroes.navmigue.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crislmfroes.navmigue.R
import com.google.firebase.ml.vision.objects.FirebaseVisionObject

class ObjectLabelAdapter internal constructor(context : Context) : RecyclerView.Adapter<ObjectLabelAdapter.LabelHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var labels = emptyList<FirebaseVisionObject>()

    inner class LabelHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val labelTextView : TextView = itemView.findViewById(R.id.object_label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelHolder {
        val itemView = inflater.inflate(R.layout.object_item, parent, false)
        return LabelHolder(itemView)
    }

    override fun onBindViewHolder(holder: LabelHolder, position: Int) {
        val current = labels[position]
        holder.labelTextView.text = current.entityId
    }

    override fun getItemCount(): Int {
        return labels.size
    }

    internal fun setLabels(labels : List<FirebaseVisionObject>) {
        this.labels = labels
        notifyDataSetChanged()
    }
}