package github.vodianov.hoursignal.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.vodianov.hoursignal.repository.base.SettingsRepository
import github.vodianov.hoursignal.repository.base.SoundRepository
import github.vodianov.hoursignal.repository.impl.AssetsJsonSettingRepository
import github.vodianov.hoursignal.repository.impl.AssetsSoundRepository
import github.vodianov.hoursignal.service.DeviceInfoService
import github.vodianov.hoursignal.service.signal.SignalService
import github.vodianov.hoursignal.service.signal.SignalServiceImpl

@Module
@InstallIn(SingletonComponent::class)
class HourSignalModule {

    @Provides
    fun soundRepository(@ApplicationContext context: Context) : SoundRepository {
        return AssetsSoundRepository(context)
    }

    @Provides
    fun settingsRepository(@ApplicationContext context: Context) : SettingsRepository {
        return AssetsJsonSettingRepository(context)
    }

    @Provides
    fun signalService(@ApplicationContext context: Context): SignalService {
        return SignalServiceImpl(context)
    }

    @Provides
    fun deviceInfoService(@ApplicationContext context: Context) : DeviceInfoService {
        return DeviceInfoService(context)
    }
}