package upday.droidconmvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import upday.droidconmvvm.model.Language

class LanguageSpinnerAdapter(context: Context, resource: Int,
                             objects: List<Language>) : ArrayAdapter<Language>(context, resource, objects) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val holder: ViewHolder
        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.language_item, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val language = getItem(position)
        holder.bind(language)

        return view
    }

    private class ViewHolder(view: View?) {
        private val textView = view?.findViewById(android.R.id.text1) as TextView

        fun bind(language: Language?) {
            textView.text = language?.name
        }
    }
}
