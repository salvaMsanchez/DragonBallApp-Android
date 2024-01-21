package com.example.dragonballappavanzado.data

import com.example.dragonballappavanzado.data.mappers.LocalToUIMapper
import com.example.dragonballappavanzado.data.mappers.RemoteToLocalMapper
import com.example.dragonballappavanzado.data.remote.response.CharacterRemote
import com.example.dragonballappavanzado.domain.models.CharacterLocal
import com.example.dragonballappavanzado.presentation.main.characterDetail.model.CharacterDetailUI
import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI
import com.example.dragonballappavanzado.utils.fakes.FakeLocalDataSource
import com.example.dragonballappavanzado.utils.fakes.FakeRemoteDataSource
import com.example.dragonballappavanzado.utils.generateRemoteCharacters
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    // SUT
    private lateinit var repository: Repository

    // Dependencies
    private val localDataSource: FakeLocalDataSource = FakeLocalDataSource()
    private val remoteDataSource: FakeRemoteDataSource = FakeRemoteDataSource()
    private val localToUIMapper: LocalToUIMapper = LocalToUIMapper()
    private val remoteToLocalMapper: RemoteToLocalMapper = RemoteToLocalMapper()

    @Before
    fun setUp() {
        repository = Repository(localDataSource, remoteDataSource, localToUIMapper, remoteToLocalMapper)
    }

    @Test
    fun `WHEN getCharacters and empty database THEN success list`() = runTest {
        // GIVEN
        val remoteCharacters: List<CharacterRemote> = remoteDataSource.getHeroes()
        val charactersCleaned: List<CharacterRemote> = remoteCharacters.dropLast(1)
        val localCharacters = remoteToLocalMapper.mapCharacters(charactersCleaned)

        // WHEN
        val actual = repository.getCharacters()

        // THEN
        Truth.assertThat(actual).containsExactlyElementsIn(localToUIMapper.mapCharacters(localCharacters))
    }

    @Test
    fun `WHEN getCharacter and empty database THEN success list`() = runTest {
        val character: CharacterLocal = CharacterLocal("1234", "Goku", "photo", "description", false)
        localDataSource.insertCharacters(listOf(character))
        val localCharacter: CharacterLocal = localDataSource.getCharacter(character.id)
        val uiCharacter: CharacterDetailUI = localToUIMapper.mapCharacterDetail(localCharacter)

        val actual = repository.getCharacter(characterId = character.id)

        Truth.assertThat(actual).isEqualTo(uiCharacter)
    }
}