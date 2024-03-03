package github.vodianov.hoursignal.repository.sound

import java.io.FileDescriptor
import java.io.InputStream

interface SoundRepository {

    fun getAllSoundUri() : List<FileDescriptor>
    fun getSoundUriByName(name: String) : FileDescriptor
}