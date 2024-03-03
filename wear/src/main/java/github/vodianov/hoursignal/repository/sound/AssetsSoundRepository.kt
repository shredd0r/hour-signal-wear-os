package github.vodianov.hoursignal.repository.sound

import android.content.Context
import android.content.res.AssetManager
import java.io.FileDescriptor

class AssetsSoundRepository (context: Context) : SoundRepository {

    private val folderName = "sounds"
    private val assets: AssetManager

    init {
        assets = context.assets
    }

    override fun getAllSoundUri(): List<FileDescriptor> {
        return assets.list(folderName)!!.map { nameSound -> getSoundUriByName(nameSound) }
    }

    override fun getSoundUriByName(name: String): FileDescriptor {
        return assets.openFd(String.format("%s/%s", folderName, name)).fileDescriptor
    }
}