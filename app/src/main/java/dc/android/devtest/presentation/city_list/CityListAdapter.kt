package dc.android.devtest.presentation.city_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dc.android.devtest.R
import dc.android.devtest.presentation.city_list.model.CityModel

class CityListAdapter(
    private val onItemClick: (CityModel) -> Unit
) : RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    private val cities: MutableList<CityModel> = mutableListOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCityName: TextView = view.findViewById(R.id.tvCityName)
        val tvCityState: TextView = view.findViewById(R.id.tvCityState)

        init {
            view.setOnClickListener { onItemClick(cities[adapterPosition]) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCities(newCities: List<CityModel>) {
        cities.clear()
        cities.addAll(newCities)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData(){
        cities.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cities[position].apply {
            holder.tvCityName.text = cityName
            holder.tvCityState.text = cityState
        }
    }

    override fun getItemCount(): Int = cities.size
}