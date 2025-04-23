package dev.androidbroadcast.converter

import dev.androidbroadcast.converter.data.auth.AuthManager
import dev.androidbroadcast.converter.presentation.viewmodel.LoginViewModel
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.*

class LoginViewModelTest {

    private val authManager: AuthManager = mock()
    private val viewModel = LoginViewModel(authManager)

    @Test
    fun `successful login saves token`() {
        var result = false
        viewModel.login("user", "pass") { success -> result = success }

        verify(authManager).saveToken(any())
        assertTrue(result)
    }

    @Test
    fun `unsuccessful login does not save token`() {
        var result = true
        viewModel.login("wrong", "wrong") { success -> result = success }

        verify(authManager, never()).saveToken(any())
        assertFalse(result)
    }
}
