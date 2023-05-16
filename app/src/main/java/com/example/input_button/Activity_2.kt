package com.example.input_button

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Activity_2 : AppCompatActivity() {

    var cityName = findViewById<TextView>(R.id.DispMessage2)
    val place: String? = intent.getStringExtra("city")
    val  api: String="a2541b0937c4a838c671bb173cc38da1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

    }


    inner class weatherTask() : AsyncTask<String, Void, String>()
    {
        override fun doInBackground(vararg p0: String?): String? {
            cityName.text=place
            var response:String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$cityName&units=metric&appid=$api")
                    .readText(Charsets.UTF_8)
            }
            catch (e: Exception){
                response =  null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val jsonObj = JSONObject(result)
            val main = jsonObj.getJSONObject("main")
            val sys = jsonObj.getJSONObject("sys")
            val wind = jsonObj.getJSONObject("wind")
            val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
            val updatedAt:Long = jsonObj.getLong("dt")
            val updatedAtText = "Updated at "+ SimpleDateFormat("dd/MM/yyyy hh:mm a",
                Locale.ENGLISH).format(
                Date(updatedAt*1000)
            )
            val temp = main.getString("temp")+"°C"
            val pressure = main.getString("pressure")
            val humidity = main.getString("humidity")
            val sunrise:Long = sys.getLong("sunrise")
            val sunset:Long = sys.getLong("sunset")
            val windSpeed = wind.getString("speed")
            val weatherDescription = weather.getString("description")


            findViewById<TextView>(R.id.updated_at).text = updatedAtText
            findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
            findViewById<TextView>(R.id.temp).text = temp
            findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                Date(sunrise*1000)
            )
            findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                Date(sunset*1000)
            )
            findViewById<TextView>(R.id.wind).text = windSpeed
            findViewById<TextView>(R.id.pressure).text =pressure
            findViewById<TextView>(R.id.humidity).text = humidity

        }

    }
}