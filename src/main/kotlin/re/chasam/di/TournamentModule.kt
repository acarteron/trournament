package re.chasam.di

import org.koin.dsl.module
import re.chasam.models.impl.TournamentImpl

val tournamentModule = module {
    single { TournamentImpl() }
}