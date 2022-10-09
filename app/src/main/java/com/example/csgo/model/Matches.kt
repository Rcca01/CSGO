package com.example.csgo.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

data class Matches (
    @SerializedName("detailed_stats")
    val detailedStats: Boolean,

    @SerializedName("modified_at")
    val modifiedAt: String,

    @SerializedName("live_embed_url")
    val liveEmbedURL: String? = null,

    @SerializedName("winner_type")
    val winnerType: Type,

    @SerializedName("game_advantage")
    val gameAdvantage: Long? = null,

    val draw: Boolean,
    val live: Live,

    @SerializedName("league_id")
    val leagueID: Long,

    val tournament: Tournament,

    @SerializedName("original_scheduled_at")
    val originalScheduledAt: String,

    val videogame: Videogame,

    @SerializedName("begin_at")
    val beginAt: String,

    val id: Long,
    val results: List<Result>,
    val league: League,
    val name: String,
    val serie: Serie,

    @SerializedName("number_of_games")
    val numberOfGames: Long,

    val forfeit: Boolean,
    val status: Status,

    @SerializedName("streams_list")
    val streamsList: List<StreamsList>,

    val rescheduled: Boolean,
    val games: List<Game>,

    @SerializedName("serie_id")
    val serieID: Long,

    val streams: Streams,
    val slug: String,

    @SerializedName("scheduled_at")
    val scheduledAt: String,

    val winner: OpponentClass? = null,

    @SerializedName("match_type")
    val matchType: MatchType,

    @SerializedName("videogame_version")
    val videogameVersion: String? = null,

    @SerializedName("tournament_id")
    val tournamentID: Long,

    val opponents: List<Opponent>,

    @SerializedName("end_at")
    val endAt: String? = null,

    @SerializedName("official_stream_url")
    val officialStreamURL: String? = null,

    @SerializedName("winner_id")
    val winnerID: Long? = null
)

data class Game (
    @SerializedName("begin_at")
    val beginAt: String? = null,

    val complete: Boolean,

    @SerializedName("detailed_stats")
    val detailedStats: Boolean,

    @SerializedName("end_at")
    val endAt: String? = null,

    val finished: Boolean,
    val forfeit: Boolean,
    val id: Long,
    val length: Long? = null,

    @SerializedName("match_id")
    val matchID: Long,

    val position: Long,
    val status: Status,

    @SerializedName("video_url")
    val videoURL: String? = null,

    val winner: GameWinner,

    @SerializedName("winner_type")
    val winnerType: Type
)

@Serializable
enum class Status(val value: String) {
    Finished("finished"),
    NotStarted("not_started"),
    Running("running");

    companion object : KSerializer<Status> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.Status", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): Status = when (val value = decoder.decodeString()) {
            "finished"    -> Finished
            "not_started" -> NotStarted
            "running"     -> Running
            else          -> throw IllegalArgumentException("Status could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: Status) {
            return encoder.encodeString(value.value)
        }
    }
}


data class GameWinner (
    val id: Long? = null,
    val type: Type
)


enum class Type(val value: String) {
    Team("Team");

    companion object : KSerializer<Type> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.Type", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): Type = when (val value = decoder.decodeString()) {
            "Team" -> Team
            else   -> throw IllegalArgumentException("Type could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: Type) {
            return encoder.encodeString(value.value)
        }
    }
}


data class League (
    val id: Long,

    @SerializedName("image_url")
    val imageURL: String? = null,

    @SerializedName("modified_at")
    val modifiedAt: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("url")
    val url: String? = null
)



data class Live (
    @SerializedName("opens_at")
    val opensAt: String? = null,

    val supported: Boolean,
    val url: String? = null
)


enum class MatchType(val value: String) {
    BestOf("best_of");

    companion object : KSerializer<MatchType> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.MatchType", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): MatchType = when (val value = decoder.decodeString()) {
            "best_of" -> BestOf
            else      -> throw IllegalArgumentException("MatchType could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: MatchType) {
            return encoder.encodeString(value.value)
        }
    }
}


data class Opponent (
    val opponent: OpponentClass,
    val type: Type
)


data class OpponentClass (
    val acronym: String? = null,
    val id: Long,

    @SerializedName("image_url")
    val imageURL: String,

    val location: String? = null,

    @SerializedName("modified_at")
    val modifiedAt: String,

    val name: String,
    val slug: String
)


data class Result (
    val score: Long,

    @SerializedName("team_id")
    val teamID: Long
)


data class Serie (
    @SerializedName("begin_at")
    val beginAt: String,

    val description: String? = null,

    @SerializedName("end_at")
    val endAt: String? = null,

    @SerializedName("full_name")
    val fullName: FullName,

    val id: Long,

    @SerializedName("league_id")
    val leagueID: Long,

    @SerializedName("modified_at")
    val modifiedAt: String,

    val name: SerieName? = null,
    val season: String? = null,
    val slug: SerieSlug,
    val tier: Tier,

    @SerializedName("winner_id")
    val winnerID: String? = null,

    @SerializedName("winner_type")
    val winnerType: String? = null,

    val year: Long
)


enum class FullName(val value: String) {
    AdvancedEuropeSeason422022("Advanced Europe season 42 2022"),
    AmericanShowdownFall2022("American Showdown Fall 2022"),
    DywizjaMistrzowskaAutumn2022("Dywizja Mistrzowska Autumn 2022"),
    EuropeanShowdownFall2022("European Showdown Fall 2022"),
    Fall2022("Fall 2022"),
    GlobalFinals2022("Global Finals 2022");

    companion object : KSerializer<FullName> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.FullName", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): FullName = when (val value = decoder.decodeString()) {
            "Advanced Europe season 42 2022"  -> AdvancedEuropeSeason422022
            "American Showdown Fall 2022"     -> AmericanShowdownFall2022
            "Dywizja Mistrzowska Autumn 2022" -> DywizjaMistrzowskaAutumn2022
            "European Showdown Fall 2022"     -> EuropeanShowdownFall2022
            "Fall 2022"                       -> Fall2022
            "Global Finals 2022"              -> GlobalFinals2022
            else                              -> throw IllegalArgumentException("FullName could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: FullName) {
            return encoder.encodeString(value.value)
        }
    }
}


enum class SerieName(val value: String) {
    AdvancedEurope("Advanced Europe"),
    AmericanShowdown("American Showdown"),
    DywizjaMistrzowska("Dywizja Mistrzowska"),
    EuropeanShowdown("European Showdown"),
    GlobalFinals("Global Finals");

    companion object : KSerializer<SerieName> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.SerieName", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): SerieName = when (val value = decoder.decodeString()) {
            "Advanced Europe"     -> AdvancedEurope
            "American Showdown"   -> AmericanShowdown
            "Dywizja Mistrzowska" -> DywizjaMistrzowska
            "European Showdown"   -> EuropeanShowdown
            "Global Finals"       -> GlobalFinals
            else                  -> throw IllegalArgumentException("SerieName could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: SerieName) {
            return encoder.encodeString(value.value)
        }
    }
}

enum class SerieSlug(val value: String) {
    CSGoBlastPremierAmericanShowdownFall2022("cs-go-blast-premier-american-showdown-fall-2022"),
    CSGoBlastPremierEuropeanShowdownFall2022("cs-go-blast-premier-european-showdown-fall-2022"),
    CSGoEseaAdvancedEurope422022("cs-go-esea-advanced-europe-42-2022"),
    CSGoFireleagueGlobalFinals2022("cs-go-fireleague-global-finals-2022"),
    CSGoPolskaLigaEsportowaDywizjaMistrzowskaAutumn2022("cs-go-polska-liga-esportowa-dywizja-mistrzowska-autumn-2022"),
    CSGoSvenskaElitserienFall2022("cs-go-svenska-elitserien-fall-2022");

    companion object : KSerializer<SerieSlug> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.SerieSlug", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): SerieSlug = when (val value = decoder.decodeString()) {
            "cs-go-blast-premier-american-showdown-fall-2022"             -> CSGoBlastPremierAmericanShowdownFall2022
            "cs-go-blast-premier-european-showdown-fall-2022"             -> CSGoBlastPremierEuropeanShowdownFall2022
            "cs-go-esea-advanced-europe-42-2022"                          -> CSGoEseaAdvancedEurope422022
            "cs-go-fireleague-global-finals-2022"                         -> CSGoFireleagueGlobalFinals2022
            "cs-go-polska-liga-esportowa-dywizja-mistrzowska-autumn-2022" -> CSGoPolskaLigaEsportowaDywizjaMistrzowskaAutumn2022
            "cs-go-svenska-elitserien-fall-2022"                          -> CSGoSvenskaElitserienFall2022
            else                                                          -> throw IllegalArgumentException("SerieSlug could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: SerieSlug) {
            return encoder.encodeString(value.value)
        }
    }
}


enum class Tier(val value: String) {
    A("a"),
    C("c"),
    D("d");

    companion object : KSerializer<Tier> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.Tier", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): Tier = when (val value = decoder.decodeString()) {
            "a"  -> A
            "c"  -> C
            "d"  -> D
            else -> throw IllegalArgumentException("Tier could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: Tier) {
            return encoder.encodeString(value.value)
        }
    }
}


data class Streams (
    val english: English,
    val official: English,
    val russian: English
)

data class English (
    @SerializedName("embed_url")
    val embedURL: String? = null,

    @SerializedName("raw_url")
    val rawURL: String? = null
)


data class StreamsList (
    @SerializedName("embed_url")
    val embedURL: String,

    val language: Language,
    val main: Boolean,
    val official: Boolean,

    @SerializedName("raw_url")
    val rawURL: String
)


enum class Language(val value: String) {
    En("en"),
    Es("es"),
    Fr("fr"),
    Pl("pl"),
    Ru("ru"),
    Sv("sv");

    companion object : KSerializer<Language> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.Language", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): Language = when (val value = decoder.decodeString()) {
            "en" -> En
            "es" -> Es
            "fr" -> Fr
            "pl" -> Pl
            "ru" -> Ru
            "sv" -> Sv
            else -> throw IllegalArgumentException("Language could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: Language) {
            return encoder.encodeString(value.value)
        }
    }
}


data class Tournament (
    @SerializedName("begin_at")
    val beginAt: String,

    @SerializedName("end_at")
    val endAt: String? = null,

    val id: Long,

    @SerializedName("league_id")
    val leagueID: Long,

    @SerializedName("live_supported")
    val liveSupported: Boolean,

    @SerializedName("modified_at")
    val modifiedAt: String,

    val name: TournamentName,
    val prizepool: Prizepool? = null,

    @SerializedName("serie_id")
    val serieID: Long,

    val slug: String,
    val tier: Tier,

    @SerializedName("winner_id")
    val winnerID: String? = null,

    @SerializedName("winner_type")
    val winnerType: Type
)

enum class TournamentName(val value: String) {
    Playoffs("Playoffs"),
    RegularSeason("Regular Season");

    companion object : KSerializer<TournamentName> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.TournamentName", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): TournamentName = when (val value = decoder.decodeString()) {
            "Playoffs"       -> Playoffs
            "Regular Season" -> RegularSeason
            else             -> throw IllegalArgumentException("TournamentName could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: TournamentName) {
            return encoder.encodeString(value.value)
        }
    }
}


enum class Prizepool(val value: String) {
    The150000UnitedStatesDollar("150000 United States Dollar"),
    The67500UnitedStatesDollar("67500 United States Dollar");

    companion object : KSerializer<Prizepool> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.Prizepool", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): Prizepool = when (val value = decoder.decodeString()) {
            "150000 United States Dollar" -> The150000UnitedStatesDollar
            "67500 United States Dollar"  -> The67500UnitedStatesDollar
            else                          -> throw IllegalArgumentException("Prizepool could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: Prizepool) {
            return encoder.encodeString(value.value)
        }
    }
}

data class Videogame (
    val id: Long,
    val name: VideogameName,
    val slug: VideogameSlug
)


enum class VideogameName(val value: String) {
    CSGo("CS:GO");

    companion object : KSerializer<VideogameName> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.VideogameName", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): VideogameName = when (val value = decoder.decodeString()) {
            "CS:GO" -> CSGo
            else    -> throw IllegalArgumentException("VideogameName could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: VideogameName) {
            return encoder.encodeString(value.value)
        }
    }
}


enum class VideogameSlug(val value: String) {
    CSGo("cs-go");

    companion object : KSerializer<VideogameSlug> {
        override val descriptor: SerialDescriptor get() {
            return PrimitiveSerialDescriptor("quicktype.VideogameSlug", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): VideogameSlug = when (val value = decoder.decodeString()) {
            "cs-go" -> CSGo
            else    -> throw IllegalArgumentException("VideogameSlug could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: VideogameSlug) {
            return encoder.encodeString(value.value)
        }
    }
}
