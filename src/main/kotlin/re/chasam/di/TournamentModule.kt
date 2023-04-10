package re.chasam.di

import org.koin.dsl.module
import re.chasam.connector.Connector
import re.chasam.connector.impl.Mongo
import re.chasam.models.Tournament
import re.chasam.models.impl.TournamentImpl

val tournamentModule = module {
    single<Connector> { Mongo() }
    single<Tournament> { TournamentImpl() }
}