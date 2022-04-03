package com.example.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [for_old_people.newInstance] factory method to
 * create an instance of this fragment.
 */
class forOldPeople : Fragment() {
    var service: Service = Service()
    private val viewModel: MainPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_for_old_people, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getWeather(viewModel.cityMain,requireContext())

        val cityNameTextView = view.findViewById<TextView>(R.id.cityNameTextView)
        val temperatureTextView = view.findViewById<TextView>(R.id.temperatureTextView)
        val weatherDescriptionTextView =
            view.findViewById<TextView>(R.id.weatherDescriptionTextView)
        var weatherImageView = view.findViewById<ImageView>(R.id.imageView)
        val cityInput = view.findViewById<EditText>(R.id.cityInput)
        val searchCityButton = view.findViewById<Button>(R.id.searchCityButton)
        val oldPeopleMode = view.findViewById<Button>(R.id.oldPeopleMode)

        searchCityButton.setOnClickListener {
            viewModel.getWeather(cityInput.text.toString(),requireContext())
        }

        oldPeopleMode.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        viewModel.getData()
        viewModel.time.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.dateTime).text = it
        }

        viewModel.weatherResp.observe(viewLifecycleOwner) {
            cityNameTextView.text = it.city
            temperatureTextView.text = it.temperature.split(".")[0] + "°C"
            weatherDescriptionTextView.text = it.description
            if(it.icon == "01d" || it.icon == "01n")
                weatherImageView.setImageResource(R.drawable.sunny)
            else if(it.icon == "02d" || it.icon == "02n")
                weatherImageView.setImageResource(R.drawable.cloud_cloudy_partly)
            else if(it.icon == "02d" || it.icon == "03n" || it.icon == "04d" || it.icon == "04n")
                weatherImageView.setImageResource(R.drawable.cloudy)
            else if(it.icon == "10d" || it.icon == "10n")
                weatherImageView.setImageResource(R.drawable.rain)
            else if(it.icon == "11d" || it.icon == "11n")
                weatherImageView.setImageResource(R.drawable.thunder_weather_icon)
            else if(it.icon == "13d" || it.icon == "13n")
                weatherImageView.setImageResource(R.drawable.cold_snow)
            else if(it.icon == "50d" || it.icon == "50n")
                weatherImageView.setImageResource(R.drawable.cold_snow)
        }
    }
}