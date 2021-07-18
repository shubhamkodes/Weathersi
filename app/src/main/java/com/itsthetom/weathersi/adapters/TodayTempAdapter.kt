package com.itsthetom.weathersi.adapters

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.itsthetom.weathersi.R
import com.itsthetom.weathersi.databinding.TemperatureLayoutBinding
import com.itsthetom.weathersi.models.Hourly
import com.itsthetom.weathersi.util.Constant
import java.util.*


class TodayTempAdapter : RecyclerView.Adapter<TodayTempAdapter.TempHolder>() {

    private val calendar=Calendar.getInstance()
    private var list= emptyList<Hourly>()
    override fun onBindViewHolder(holder: TempHolder, position: Int) {
        val hourly=list[position]

        val imgId=hourly.weather[0].icon
        holder.binding.ivWeatherImg.setImageResource(Constant.weatherIcon[imgId]!!)

        holder.binding.tvTemperature.setText((hourly.temp-273.15).toInt().toString()+"\u2103")

        holder.binding.tvTime.text=getTime(position)

        holder.binding.tvStatus.text=hourly.weather[0].main

        if (imgId.endsWith("d")){
            holder.binding.backgroundMeshImage.setImageResource(R.drawable.img_mesh_clearsky)
        }else{
            holder.binding.backgroundMeshImage.setImageResource(R.drawable.img_mesh_night)
        }
       // holder.imageSetter(imgId = Constant.weatherIcon[imgId]!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.temperature_layout,parent,false);
        return TempHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    public fun submitList(list:List<Hourly>){
        this.list=list
        notifyDataSetChanged()
    }

    inner class TempHolder (view: View):RecyclerView.ViewHolder(view) {

        val binding = TemperatureLayoutBinding.bind(view)
    }

     private fun getTime(position: Int):String{

        var am_pm=""

        var time=calendar.get(Calendar.HOUR_OF_DAY)+position

        if (time>=24){
            time -= 24;
        }

        if (time==0){
            time=12
            am_pm="AM"
        }else if (time>=12 ){
                am_pm=" PM"
                time=time-12
                if (time==0)
                    time=12
            }else{
                am_pm=" AM"
            }
        return time.toString()+am_pm
    }

}