package dev.androidbroadcast.converter.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.androidbroadcast.converter.MainActivity
import dev.androidbroadcast.converter.R
import dev.androidbroadcast.converter.data.auth.AuthManager
import dev.androidbroadcast.converter.databinding.ActivityLoginBinding
import dev.androidbroadcast.converter.presentation.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authManager = AuthManager(this)
        viewModel = LoginViewModel(authManager)

        if (viewModel.isTokenValid()) {
            goToMain()
        }

        binding.loginButton.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()
            viewModel.login(username, password) { success ->
                if (success) goToMain()
                else {
                    binding.errorText.text = getString(R.string.login_failed)
                    Toast.makeText(application, getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
