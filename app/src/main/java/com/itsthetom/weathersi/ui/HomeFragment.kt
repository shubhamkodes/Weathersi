package com.itsthetom.weathersi.ui

import android.R
import android.app.Dialog
import android.app.SearchManager
import android.database.MatrixCursor
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import com.itsthetom.weathersi.MainViewModel
import com.itsthetom.weathersi.adapters.TodayTempAdapter
import com.itsthetom.weathersi.databinding.FragmentHomeBinding
import com.itsthetom.weathersi.models.Current
import com.itsthetom.weathersi.util.Constant
import kotlinx.coroutines.*


class HomeFragment : Fragment()  {
   private lateinit var binding:FragmentHomeBinding
   private lateinit var viewModel:MainViewModel
   private lateinit var cursorAdapter:SimpleCursorAdapter
    private lateinit var dialog:Dialog
    val suggestions = listOf("Apple", "Blueberry", "Carrot", "Daikon")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater)
        viewModel=ViewModelProvider(requireActivity()).get(MainViewModel::class.java);

        dialog= context?.let { Dialog(it) }!!
        dialog.setContentView(com.itsthetom.weathersi.R.layout.layout_loading)
        dialog.setCancelable(false)
        dialog.show()


        viewModel.getCurrentWeaterData().observe(viewLifecycleOwner, Observer {
            if (it!=null){
                initView(it)
            }
        })



        return binding.root
    }

    private fun initView(current: Current) {


        binding.tvTemperature.setText((current.temp-273.15).toInt().toString()+"\u2103")
        binding.tvFeelsLike.setText("Feels like "+(current.feels_like-273.15).toInt().toString()+"\u2103");


        val imgId= current.weather[0].icon
        binding.ivWeatherImg.setImageResource(Constant.weatherIcon[imgId]!!)
        platteImage(Constant.weatherIcon[imgId]!!)

        binding.ivHumidity.visibility=View.VISIBLE
        binding.tvHumidity.setText(current.humidity.toString()+"%")

        binding.ivWind.visibility=View.VISIBLE
        binding.tvWind.setText(current.wind_speed.toInt().toString()+"km/h")


        binding.tvDesc.setText(current.weather[0].description)
        viewModel.getCityName().observe(viewLifecycleOwner,{
            if (it!=null){
                binding.tvCityName.text=it
            }
        })

        val adapter=TodayTempAdapter()
        binding.rvTodaysTemp.adapter=adapter

        viewModel.getHourlyWeather().observe(viewLifecycleOwner,{
            if (it!=null){
                binding.tvTodayTemp.visibility=View.VISIBLE
                adapter.submitList(it)
                dialog.dismiss()
            }
        })

 


        binding.rvTodaysTemp.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }



    private fun platteImage(drawableId:Int){
        CoroutineScope(Dispatchers.IO).launch{
            val resource=BitmapFactory.decodeResource(resources,drawableId)
            Palette.from(resource)
                .generate(object : Palette.PaletteAsyncListener{
                    override fun onGenerated(palette: Palette?) {
                        val darkVibrantSwatch: Palette.Swatch? =
                            palette?.getDarkVibrantSwatch()
                        val dominantSwatch: Palette.Swatch? = palette?.getDominantSwatch()
                        val lightVibrantSwatch: Palette.Swatch? =
                            palette?.getLightVibrantSwatch()

                        CoroutineScope(Dispatchers.Main).launch{
                            if (dominantSwatch != null) {
                                binding.constraintLayoutMain.setBackgroundColor(dominantSwatch.rgb)

                            } else {
                                if (lightVibrantSwatch != null) {
                                    binding.constraintLayoutMain.setBackgroundColor(
                                        lightVibrantSwatch.rgb
                                    )
                                }
                            }
                        }
                    }
                })
        }

    }



}