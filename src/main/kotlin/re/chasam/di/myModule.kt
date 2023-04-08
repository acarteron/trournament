package re.chasam.di

import org.koin.dsl.module
import re.chasam.models.*

val tournamentModule = module {
    single { TournamentImpl() }
}