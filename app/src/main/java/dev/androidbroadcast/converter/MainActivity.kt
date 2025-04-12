package dev.androidbroadcast.converter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.androidbroadcast.converter.databinding.ActivityMainBinding
import dev.androidbroadcast.converter.presentation.activity.CurrencyConverterActivity
import dev.androidbroadcast.converter.presentation.adapter.CurrencyAdapter
import dev.androidbroadcast.converter.presentation.di.DependencyProvider
import dev.androidbroadcast.converter.presentation.viewmodel.CurrencyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CurrencyViewModel
    private lateinit var adapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получаем ViewModel через DependencyProvider
        viewModel = DependencyProvider.provideCurrencyViewModel()

        adapter = CurrencyAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.currencies.observe(this, Observer { currencies ->
            adapter.submitList(currencies)
        })

        viewModel.loadCurrencies()

        binding.converterBtn.setOnClickListener {
            val intent = Intent(this, CurrencyConverterActivity::class.java)
            startActivity(intent)
        }
    }
}

