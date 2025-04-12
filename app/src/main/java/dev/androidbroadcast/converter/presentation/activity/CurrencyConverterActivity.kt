package dev.androidbroadcast.converter.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.androidbroadcast.converter.R
import dev.androidbroadcast.converter.databinding.ActivityCurrencerConverterBinding
import dev.androidbroadcast.converter.presentation.di.DependencyProvider
import dev.androidbroadcast.converter.presentation.viewmodel.CurrencyViewModel

class CurrencyConverterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCurrencerConverterBinding
    private lateinit var viewModel: CurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrencerConverterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = DependencyProvider.provideCurrencyViewModel()

        binding.buttonConvert.setOnClickListener {
            val amountText = binding.editTextAmount.text.toString()
            val from = binding.editTextFrom.text.toString().uppercase()
            val to = binding.editTextTo.text.toString().uppercase()

            val amount = amountText.toDoubleOrNull()
            if (amount != null && from.isNotBlank() && to.isNotBlank()) {
                viewModel.convertAmount(amount, from, to) { result ->
                    runOnUiThread {
                        binding.textViewResult.text = "Result: $result $to"
                    }
                }
            } else {
                binding.textViewResult.text = getString(R.string.please_fill_all_fields_correctly)
            }
        }
    }
}
