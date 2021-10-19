package com.brm.domaincheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.brm.domaincheck.databinding.ActivityMainBinding
import com.brm.domaincheck.model.Domain
import com.brm.domaincheck.network.MyApi
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var api: MyApi

    private val timer = object : CountDownTimer(10000, 1000){
        override fun onTick(p0: Long) {
            binding.timerText.text = (p0 / 1000).toString() + "seconds\nleft to send one more request"
        }

        override fun onFinish() {
           binding.fab.visibility = View.VISIBLE
            binding.timerText.visibility = View.INVISIBLE
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            binding.spinKit.visibility = View.VISIBLE
            binding.fab.visibility = View.INVISIBLE
            onDomainGet()
        }
    }

    private fun onDomainGet(){
        val resp: Call<Domain> =  api.getDomain(
            host = BuildConfig.RAPID_HOST,
            key = BuildConfig.RAPID_KEY,
            name = binding.domainEdt.text.toString()
        )
        resp.enqueue(object : Callback<Domain>{
            override fun onResponse(call: Call<Domain>, response: Response<Domain>) {
                Toast.makeText(applicationContext, response.body()?.isAvailable.toString(), Toast.LENGTH_LONG).show()
                binding.spinKit.visibility = View.INVISIBLE
                binding.timerText.visibility = View.VISIBLE
                timer.start()
            }

            override fun onFailure(call: Call<Domain>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                binding.spinKit.visibility = View.INVISIBLE
            }
        })
    }

}