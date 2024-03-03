package github.vodianov.hoursignal.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.vodianov.hoursignal.condition.signaloff.SignalOffConditionFactory
import github.vodianov.hoursignal.condition.signaloff.impl.AirplaneModeEnableSignalOffCondition
import github.vodianov.hoursignal.condition.signaloff.impl.DNDEnableSignalOffCondition
import github.vodianov.hoursignal.condition.signaloff.impl.IsChargingSignalOffCondition
import github.vodianov.hoursignal.repository.settings.SettingsRepository
import github.vodianov.hoursignal.repository.sound.SoundRepository
import github.vodianov.hoursignal.repository.settings.AssetsJsonSettingRepository
import github.vodianov.hoursignal.repository.sound.AssetsSoundRepository
import github.vodianov.hoursignal.repository.valuestorage.KeyValueRepository
import github.vodianov.hoursignal.repository.valuestorage.SharedReferencesKeyValueRepositoryImpl
import github.vodianov.hoursignal.service.DeviceInfoService
import github.vodianov.hoursignal.service.signal.SignalService
import github.vodianov.hoursignal.service.signal.SignalServiceImpl
import github.vodianov.hoursignal.workflow.BootStartWorkflow
import github.vodianov.hoursignal.workflow.SignalWorkflow

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
    fun signalService(@ApplicationContext context: Context,
                      keyValueRepository: KeyValueRepository): SignalService {
        return SignalServiceImpl(context, keyValueRepository)
    }

    @Provides
    fun deviceInfoService(@ApplicationContext context: Context) : DeviceInfoService {
        return DeviceInfoService(context)
    }

    @Provides
    fun signalOffConditionFactory(deviceInfoService: DeviceInfoService) : SignalOffConditionFactory {
        return SignalOffConditionFactory(
            IsChargingSignalOffCondition(deviceInfoService),
            DNDEnableSignalOffCondition(deviceInfoService),
            AirplaneModeEnableSignalOffCondition(deviceInfoService)
        )
    }

    @Provides
    fun signalWorkflow(settingsRepository: SettingsRepository,
                       soundRepository: SoundRepository,
                       signalService: SignalService,
                       signalOffConditionFactory: SignalOffConditionFactory) : SignalWorkflow {
        return SignalWorkflow(settingsRepository, soundRepository, signalService, signalOffConditionFactory)
    }

    @Provides
    fun bootStartWorkflow(signalService: SignalService,
                          settingsRepository: SettingsRepository,
                          keyValueRepository: KeyValueRepository) : BootStartWorkflow {
        return BootStartWorkflow(signalService, settingsRepository, keyValueRepository)
    }

    @Provides
    fun sharedReferencesKeyValueRepositoryImpl(@ApplicationContext context: Context) : KeyValueRepository {
        return SharedReferencesKeyValueRepositoryImpl(context)
    }
}