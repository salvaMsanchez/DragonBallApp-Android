package com.example.dragonballappavanzado.data.local

import com.example.dragonballappavanzado.data.local.database.CharacterDAO
import com.example.dragonballappavanzado.data.local.sharedPreferences.SharedPreferencesService
import com.example.dragonballappavanzado.domain.models.CharacterLocal
import com.example.dragonballappavanzado.utils.generateLocalCharacters
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {

    // SUT
    private lateinit var localDataSource: LocalDataSource

    // Dependencies
    private val sharedPreferencesService: SharedPreferencesService = mockk()
    private val dao: CharacterDAO = mockk()

    @Before
    fun setUp() {
        localDataSource = LocalDataSource(sharedPreferencesService, dao)
    }

    @Test
    fun `WHEN getCharacters THEN success list`() {
        // Given
        val expected = generateLocalCharacters(5)
        every { dao.getAll() } returns expected

        // When
        val actual = localDataSource.getCharacters()

        // Then
        Assert.assertEquals(true, actual.isNotEmpty())
        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `WHEN getCharacter THEN success character`() {
        val expected = CharacterLocal("123", "Goku", "photo", "Descripción", false)
        every { dao.getCharacterById(expected.id) } returns expected

        val characterId: String = "123"
        val actual = localDataSource.getCharacter(characterId)

        Assert.assertEquals(expected.id, actual.id)
        Truth.assertThat(actual.name).isEqualTo(expected.name)
        Truth.assertThat(actual.photo).isEqualTo(expected.photo)
        Truth.assertThat(actual.description).isEqualTo(expected.description)
        Truth.assertThat(actual.favorite).isEqualTo(expected.favorite)
    }

    @Test
    fun `WHEN getToken THEN success token`() {
        val expectedToken: String = "123456789"
        every { sharedPreferencesService.getToken() } returns expectedToken

        val actualToken = localDataSource.getToken()

        Assert.assertEquals(expectedToken, actualToken)
    }

    @Test
    fun `WHEN getEmail THEN success email`() {
        val expectedEmail: String = "hola@gmail.com"
        every { sharedPreferencesService.getEmail() } returns expectedEmail

        val actualEmail = localDataSource.getEmail()

        Assert.assertEquals(expectedEmail, actualEmail)
    }

    @Test
    fun `WHEN getPassword THEN success password`() {
        val expectedPassword: String = "ertdfgs"
        every { sharedPreferencesService.getPassword() } returns expectedPassword

        val actualPassword = localDataSource.getPassword()

        Assert.assertEquals(expectedPassword, actualPassword)
    }

    @After
    fun tearDown() {

    }

}