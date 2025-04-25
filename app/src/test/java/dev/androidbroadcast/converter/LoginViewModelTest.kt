package dev.androidbroadcast.converter

import dev.androidbroadcast.converter.data.auth.AuthManager
import dev.androidbroadcast.converter.presentation.viewmodel.LoginViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class LoginViewModelTest {

    private val authManager: AuthManager = mock()
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        viewModel = LoginViewModel(authManager)
    }

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

    @Test
    fun `token is valid returns true`() {
        whenever(authManager.isTokenValid()).thenReturn(true)
        assertTrue(viewModel.isTokenValid())
    }

    @Test
    fun `token is valid returns false`() {
        whenever(authManager.isTokenValid()).thenReturn(false)
        assertFalse(viewModel.isTokenValid())
    }

    @Test
    fun `login with empty username fails`() {
        var result = true
        viewModel.login("", "pass") { success -> result = success }

        verify(authManager, never()).saveToken(any())
        assertFalse(result)
    }

    @Test
    fun `login with empty password fails`() {
        var result = true
        viewModel.login("user", "") { success -> result = success }

        verify(authManager, never()).saveToken(any())
        assertFalse(result)
    }
}

