package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.remote.ApiResponse
import com.example.moviecatalogue.data.source.remote.response.*

object DataDummy {

    fun generateDummyMovie(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                id = "740985",
                voteAverage = 6.8,
                title = "Borat Subsequent Moviefilm",
                releaseDate = "2020-10-23",
                originalTitle = "Borat Subsequent Moviefilm",
                backdropPath = "/3Ysvp4ODDC6sucdQ9quHWkMiKED.jpg",
                posterPath = "/6agKYU5IQFpuDyUYPu39w7UCRrJ.jpg",
                overview = "14 years after making a film about his journey across the USA, Borat risks life and limb when he returns to the United States with his young daughter, and reveals more about the culture, the COVID-19 pandemic, and the political elections."
            )
        )
        movies.add(
            MovieEntity(
                id = "531219",
                voteAverage = 7.2,
                title = "Roald Dahl's The Witches",
                releaseDate = "2020-10-26",
                originalTitle = "Roald Dahl's The Witches",
                backdropPath = "/v5uzuxtegkel1nNHdk5SHot6dlT.jpg",
                posterPath = "/betExZlgK0l7CZ9CsCBVcwO1OjL.jpg",
                overview = "In late 1967, a young orphaned boy goes to live with his loving grandma in the rural Alabama town of Demopolis. As the boy and his grandmother encounter some deceptively glamorous but thoroughly diabolical witches, she wisely whisks him away to a seaside resort. Regrettably, they arrive at precisely the same time that the world's Grand High Witch has gathered."
            )
        )
        movies.add(
            MovieEntity(
                id = "105",
                voteAverage = 8.3,
                title = "Back to the Future",
                releaseDate = "1985-07-03",
                originalTitle = "Back to the Future",
                backdropPath = "/fq3wyOs1RHyz2yfzsb4sck7aWRG.jpg",
                posterPath = "/xlBivetfrtF84Yx0zISShnNtHYe.jpg",
                overview = "Eighties teenager Marty McFly is accidentally sent back in time to 1955, inadvertently disrupting his parents' first meeting and attracting his mother's romantic interest. Marty must repair the damage to history by rekindling his parents' romance and - with the help of his eccentric inventor friend Doc Brown - return to 1985."
            )
        )
        movies.add(
            MovieEntity(
                id = "590223",
                voteAverage = 7.6,
                title = "Love and Monsters",
                releaseDate = "2020-10-16",
                originalTitle = "Love and Monsters",
                backdropPath = "/lA5fOBqTOQBQ1s9lEYYPmNXoYLi.jpg",
                posterPath = "/r4Lm1XKP0VsTgHX4LG4syAwYA2I.jpg",
                overview = "Seven years after the Monsterpocalypse, Joel Dawson, along with the rest of humanity, has been living underground ever since giant creatures took control of the land. After reconnecting over radio with his high school girlfriend Aimee, who is now 80 miles away at a coastal colony, Joel begins to fall for her again. As Joel realizes that there’s nothing left for him underground, he decides against all logic to venture out to Aimee, despite all the dangerous monsters that stand in his way."
            )
        )
        movies.add(
            MovieEntity(
                id = "625568",
                voteAverage = 6.4,
                title = "Unhinged",
                releaseDate = "2020-07-02",
                originalTitle = "Unhinged",
                backdropPath = "/gvEAq0gaEqWZ2ifPW7j8jGwF69M.jpg",
                posterPath = "/6WC3cIhuaLZSq3SbtdOp2ZTSTe.jpg",
                overview = "A divorced mother honks impatiently at a deranged middle-aged stranger at a red light while running late on her way to work. His road rage escalates to horrifyingly psychotic proportions as he becomes single-mindedly determined to teach her a deadly lesson for provoking him."
            )
        )
        movies.add(
            MovieEntity(
                id = "560050",
                voteAverage = 7.9,
                title = "Over the Moon",
                releaseDate = "2020-10-16",
                originalTitle = "Over the Moon",
                backdropPath = "/htBUhLSS7FfHtydgYxUWjL3J1Q1.jpg",
                posterPath = "/lQfdytwN7eh0tXWjIiMceFdBBvD.jpg",
                overview = "A girl builds a rocket to travel to the moon in hopes of meeting the legendary Moon Goddess."
            )
        )
        movies.add(
            MovieEntity(
                id = "556984",
                voteAverage = 7.8,
                title = "The Trial of the Chicago 7",
                releaseDate = "2020-09-25",
                originalTitle = "The Trial of the Chicago 7",
                backdropPath = "/v8Nf6Y1qL1Q3PWTBezXNPPaXqza.jpg",
                posterPath = "/ahf5cVdooMAlDRiJOZQNuLqa1Is.jpg",
                overview = "What was intended to be a peaceful protest at the 1968 Democratic National Convention turned into a violent clash with police and the National Guard. The organizers of the protest — including Abbie Hoffman, Jerry Rubin, Tom Hayden and Bobby Seale — were charged with conspiracy to incite a riot and the trial that followed was one of the most notorious in history."
            )
        )
        movies.add(
            MovieEntity(
                id = "505379",
                voteAverage = 6.3,
                title = "Rebecca",
                releaseDate = "2020-10-16",
                originalTitle = "Rebecca",
                backdropPath = "/mY8oTSMlVHgkBtKsvanKnvT8rY9.jpg",
                posterPath = "/bSKVKcCXdKxkbgf0LL8lBTPG02e.jpg",
                overview = "After a whirlwind romance with a wealthy widower, a naïve bride moves to his family estate but can't escape the haunting shadow of his late wife."
            )
        )
        movies.add(
            MovieEntity(
                id = "613504",
                voteAverage = 6.9,
                title = "After We Collided",
                releaseDate = "2020-09-02",
                originalTitle = "After We Collided",
                backdropPath = "/6hgItrYQEG33y0I7yP2SRl2ei4w.jpg",
                posterPath = "/kiX7UYfOpYrMFSAGbI6j1pFkLzQ.jpg",
                overview = "Tessa finds herself struggling with her complicated relationship with Hardin; she faces a dilemma that could change their lives forever."
            )
        )
        movies.add(
            MovieEntity(
                id = "575417",
                voteAverage = 6.5,
                title = "On the Rocks",
                releaseDate = "2020-10-02",
                originalTitle = "On the Rocks",
                backdropPath = "/7FQHLt4iNh2TZ58cAAYrZK0xogg.jpg",
                posterPath = "/fcijRCmB7yTtloh4Pumy9b1rkwU.jpg",
                overview = "Faced with sudden doubts about her marriage, a young New York mother teams up with her larger-than-life playboy father to tail her husband."
            )
        )

        return movies
    }

    fun generateDummyTvShow(): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                backdropPath = "/iU955nBcsc5Zr5e31eWU2xKVqHk.jpg",
                firstAirDate = "2020-10-23",
                id = "93785",
                name = "Barbarians",
                originalName = "Barbaren",
                overview = "Three people's fates are interwoven in the Battle of the Teutoburg Forest in 9 A.D., during which Germanic warriors halt the spread of the Roman Empire.",
                posterPath = "/trfHliK2hhw2xOJVztHqKylyzrD.jpg",
                voteAverage = 7.3
            )
        )
        tvShows.add(
            TvShowEntity(
                originalName = "The Haunting of Bly Manor",
                id = "109958",
                name = "The Haunting of Bly Manor",
                voteAverage = 7.8,
                firstAirDate = "2020-10-09",
                posterPath = "/vIXQ8UymmQ7zJEPrKJP3s3fSbhR.jpg",
                backdropPath = "/isevY1UDcpaAYlYo7IqoSAU9s81.jpg",
                overview = "After an au pair’s tragic death, Henry Wingrave hires a young American nanny to care for his orphaned niece and nephew who reside at Bly Manor with the estate’s chef Owen, groundskeeper Jamie and housekeeper, Mrs. Grose. But all is not as it seems at the manor, and centuries of dark secrets of love and loss are waiting to be unearthed in this chilling gothic romance. At Bly Manor, dead doesn’t mean gone."
            )
        )
        tvShows.add(
            TvShowEntity(
                originalName = "The Queen's Gambit",
                id = "87739",
                name = "The Queen's Gambit",
                voteAverage = 9.2,
                firstAirDate = "2020-10-23",
                posterPath = "/zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
                backdropPath = "/34OGjFEbHj0E3lE2w0iTUVq0CBz.jpg",
                overview = "In a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction."
            )
        )
        tvShows.add(
            TvShowEntity(
                originalName = "The Boys",
                id = "76479",
                name = "The Boys",
                voteAverage = 8.4,
                firstAirDate = "2019-07-25",
                posterPath = "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                backdropPath = "/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg",
                overview = "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty."
            )
        )
        tvShows.add(
            TvShowEntity(
                originalName = "The Walking Dead: World Beyond",
                id = "94305",
                name = "The Walking Dead: World Beyond",
                voteAverage = 7.7,
                firstAirDate = "2020-10-04",
                posterPath = "/z31GxpVgDsFAF4paZR8PRsiP16D.jpg",
                backdropPath = "/pLVrN9B750ehwTFdQ6n3HRUERLd.jpg",
                overview = "A heroic group of teens sheltered from the dangers of the post-apocalyptic world leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world."
            )
        )
        tvShows.add(
            TvShowEntity(
                originalName = "Star Trek: Discovery",
                id = "67198",
                name = "Star Trek: Discovery",
                voteAverage = 7.0,
                firstAirDate = "2017-09-24",
                posterPath = "/98RYSYsRNKWgrAAFBn0WfploUG7.jpg",
                backdropPath = "/p3McpsDNTNmpbkNBKdNxOFZJeKX.jpg",
                overview = "Follow the voyages of Starfleet on their missions to discover new worlds and new life forms, and one Starfleet officer who must learn that to truly understand all things alien, you must first understand yourself."
            )
        )
        tvShows.add(
            TvShowEntity(
                originalName = "Fear the Walking Dead",
                id = "62286",
                name = "Fear the Walking Dead",
                voteAverage = 7.3,
                firstAirDate = "2015-08-23",
                posterPath = "/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
                backdropPath = "/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg",
                overview = "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question."
            )
        )
        tvShows.add(
            TvShowEntity(
                originalName = "Helstrom",
                id = "88987",
                name = "Helstrom",
                voteAverage = 6.6,
                firstAirDate = "2020-10-16",
                posterPath = "/8fPIcaRIZgsoBcgA5yAm3GNz61M.jpg",
                backdropPath = "/qCReQONjNVaW4QFN2V5vCOzPvex.jpg",
                overview = "Daimon and Ana Helstrom are the son and daughter of a mysterious and powerful serial killer. The siblings have a complicated dynamic as they track down the terrorizing worst of humanity — each with their attitude and skills."
            )
        )
        tvShows.add(
            TvShowEntity(
                originalName = "The Undoing",
                id = "83851",
                name = "The Undoing",
                voteAverage = 4.0,
                firstAirDate = "2020-10-25",
                posterPath = "/3tDbJxobPN3EI2bBebL6zmusmw5.jpg",
                backdropPath = "/tJjwEzTglpxoWJXwEbZlKLppMhr.jpg",
                overview = "Grace Fraser is a successful therapist on the brink of publishing her first book with a devoted husband and young son who attends an elite private school in New York City. Weeks before her book is published, a chasm opens in her life: a violent death, a missing husband, and, in the place of a man Grace thought she knew, only a chain of terrible revelations."
            )
        )
        tvShows.add(
            TvShowEntity(
                originalName = "La Révolution",
                id = "99807",
                name = "La Révolution",
                voteAverage = 7.2,
                firstAirDate = "2020-10-16",
                posterPath = "/7OXN9nUI5sV1AkBViSKeehoePoG.jpg",
                backdropPath = "/lpRROknTUlvJ8gPgmRUlIoOVCdA.jpg",
                overview = "1787, France. While investigating a series of mysterious murders, Joseph Guillotin - the future inventor of the world famous ‘Guillotine’ - uncovers an unknown virus: the Blue Blood. The disease quickly spreads amongst the French aristocracy, driving them to murder ordinary people and soon leads to a rebellion."
            )
        )

        return tvShows
    }

    fun generateRemoteDummyTopMovie(): List<TopMovieResultsItem> {
        val movies = ArrayList<TopMovieResultsItem>()

        movies.add(
            TopMovieResultsItem(
                id = "740985",
                voteAverage = 6.8,
                voteCount = 468,
                title = "Borat Subsequent Moviefilm",
                releaseDate = "2020-10-23",
                originalTitle = "Borat Subsequent Moviefilm",
                backdropPath = "/3Ysvp4ODDC6sucdQ9quHWkMiKED.jpg",
                posterPath = "/6agKYU5IQFpuDyUYPu39w7UCRrJ.jpg",
                overview = "14 years after making a film about his journey across the USA, Borat risks life and limb when he returns to the United States with his young daughter, and reveals more about the culture, the COVID-19 pandemic, and the political elections."
            )
        )
        movies.add(
            TopMovieResultsItem(
                id = "531219",
                voteAverage = 7.2,
                voteCount = 4628,
                title = "Roald Dahl's The Witches",
                releaseDate = "2020-10-26",
                originalTitle = "Roald Dahl's The Witches",
                backdropPath = "/v5uzuxtegkel1nNHdk5SHot6dlT.jpg",
                posterPath = "/betExZlgK0l7CZ9CsCBVcwO1OjL.jpg",
                overview = "In late 1967, a young orphaned boy goes to live with his loving grandma in the rural Alabama town of Demopolis. As the boy and his grandmother encounter some deceptively glamorous but thoroughly diabolical witches, she wisely whisks him away to a seaside resort. Regrettably, they arrive at precisely the same time that the world's Grand High Witch has gathered."
            )
        )
        movies.add(
            TopMovieResultsItem(
                id = "105",
                voteAverage = 8.3,
                voteCount = 88,
                title = "Back to the Future",
                releaseDate = "1985-07-03",
                originalTitle = "Back to the Future",
                backdropPath = "/fq3wyOs1RHyz2yfzsb4sck7aWRG.jpg",
                posterPath = "/xlBivetfrtF84Yx0zISShnNtHYe.jpg",
                overview = "Eighties teenager Marty McFly is accidentally sent back in time to 1955, inadvertently disrupting his parents' first meeting and attracting his mother's romantic interest. Marty must repair the damage to history by rekindling his parents' romance and - with the help of his eccentric inventor friend Doc Brown - return to 1985."
            )
        )
        movies.add(
            TopMovieResultsItem(
                id = "590223",
                voteAverage = 7.6,
                voteCount = 123,
                title = "Love and Monsters",
                releaseDate = "2020-10-16",
                originalTitle = "Love and Monsters",
                backdropPath = "/lA5fOBqTOQBQ1s9lEYYPmNXoYLi.jpg",
                posterPath = "/r4Lm1XKP0VsTgHX4LG4syAwYA2I.jpg",
                overview = "Seven years after the Monsterpocalypse, Joel Dawson, along with the rest of humanity, has been living underground ever since giant creatures took control of the land. After reconnecting over radio with his high school girlfriend Aimee, who is now 80 miles away at a coastal colony, Joel begins to fall for her again. As Joel realizes that there’s nothing left for him underground, he decides against all logic to venture out to Aimee, despite all the dangerous monsters that stand in his way."
            )
        )
        movies.add(
            TopMovieResultsItem(
                id = "625568",
                voteAverage = 6.4,
                voteCount = 9,
                title = "Unhinged",
                releaseDate = "2020-07-02",
                originalTitle = "Unhinged",
                backdropPath = "/gvEAq0gaEqWZ2ifPW7j8jGwF69M.jpg",
                posterPath = "/6WC3cIhuaLZSq3SbtdOp2ZTSTe.jpg",
                overview = "A divorced mother honks impatiently at a deranged middle-aged stranger at a red light while running late on her way to work. His road rage escalates to horrifyingly psychotic proportions as he becomes single-mindedly determined to teach her a deadly lesson for provoking him."
            )
        )
        movies.add(
            TopMovieResultsItem(
                id = "560050",
                voteAverage = 7.9,
                voteCount = 27,
                title = "Over the Moon",
                releaseDate = "2020-10-16",
                originalTitle = "Over the Moon",
                backdropPath = "/htBUhLSS7FfHtydgYxUWjL3J1Q1.jpg",
                posterPath = "/lQfdytwN7eh0tXWjIiMceFdBBvD.jpg",
                overview = "A girl builds a rocket to travel to the moon in hopes of meeting the legendary Moon Goddess."
            )
        )
        movies.add(
            TopMovieResultsItem(
                id = "556984",
                voteAverage = 7.8,
                voteCount = 738,
                title = "The Trial of the Chicago 7",
                releaseDate = "2020-09-25",
                originalTitle = "The Trial of the Chicago 7",
                backdropPath = "/v8Nf6Y1qL1Q3PWTBezXNPPaXqza.jpg",
                posterPath = "/ahf5cVdooMAlDRiJOZQNuLqa1Is.jpg",
                overview = "What was intended to be a peaceful protest at the 1968 Democratic National Convention turned into a violent clash with police and the National Guard. The organizers of the protest — including Abbie Hoffman, Jerry Rubin, Tom Hayden and Bobby Seale — were charged with conspiracy to incite a riot and the trial that followed was one of the most notorious in history."
            )
        )
        movies.add(
            TopMovieResultsItem(
                id = "505379",
                voteAverage = 6.3,
                voteCount = 2352,
                title = "Rebecca",
                releaseDate = "2020-10-16",
                originalTitle = "Rebecca",
                backdropPath = "/mY8oTSMlVHgkBtKsvanKnvT8rY9.jpg",
                posterPath = "/bSKVKcCXdKxkbgf0LL8lBTPG02e.jpg",
                overview = "After a whirlwind romance with a wealthy widower, a naïve bride moves to his family estate but can't escape the haunting shadow of his late wife."
            )
        )
        movies.add(
            TopMovieResultsItem(
                id = "613504",
                voteAverage = 6.9,
                voteCount = 987,
                title = "After We Collided",
                releaseDate = "2020-09-02",
                originalTitle = "After We Collided",
                backdropPath = "/6hgItrYQEG33y0I7yP2SRl2ei4w.jpg",
                posterPath = "/kiX7UYfOpYrMFSAGbI6j1pFkLzQ.jpg",
                overview = "Tessa finds herself struggling with her complicated relationship with Hardin; she faces a dilemma that could change their lives forever."
            )
        )
        movies.add(
            TopMovieResultsItem(
                id = "575417",
                voteAverage = 6.5,
                voteCount = 33,
                title = "On the Rocks",
                releaseDate = "2020-10-02",
                originalTitle = "On the Rocks",
                backdropPath = "/7FQHLt4iNh2TZ58cAAYrZK0xogg.jpg",
                posterPath = "/fcijRCmB7yTtloh4Pumy9b1rkwU.jpg",
                overview = "Faced with sudden doubts about her marriage, a young New York mother teams up with her larger-than-life playboy father to tail her husband."
            )
        )

        return movies
    }

    fun generateRemoteDummyTopTvShow(): List<TopTvShowResultsItem> {
        val tvShows = ArrayList<TopTvShowResultsItem>()

        tvShows.add(
            TopTvShowResultsItem(
                backdropPath = "/iU955nBcsc5Zr5e31eWU2xKVqHk.jpg",
                firstAirDate = "2020-10-23",
                id = "93785",
                name = "Barbarians",
                voteCount = 345,
                originalName = "Barbaren",
                overview = "Three people's fates are interwoven in the Battle of the Teutoburg Forest in 9 A.D., during which Germanic warriors halt the spread of the Roman Empire.",
                posterPath = "/trfHliK2hhw2xOJVztHqKylyzrD.jpg",
                voteAverage = 7.3
            )
        )
        tvShows.add(
            TopTvShowResultsItem(
                originalName = "The Haunting of Bly Manor",
                id = "109958",
                name = "The Haunting of Bly Manor",
                voteCount = 32,
                voteAverage = 7.8,
                firstAirDate = "2020-10-09",
                posterPath = "/vIXQ8UymmQ7zJEPrKJP3s3fSbhR.jpg",
                backdropPath = "/isevY1UDcpaAYlYo7IqoSAU9s81.jpg",
                overview = "After an au pair’s tragic death, Henry Wingrave hires a young American nanny to care for his orphaned niece and nephew who reside at Bly Manor with the estate’s chef Owen, groundskeeper Jamie and housekeeper, Mrs. Grose. But all is not as it seems at the manor, and centuries of dark secrets of love and loss are waiting to be unearthed in this chilling gothic romance. At Bly Manor, dead doesn’t mean gone."
            )
        )
        tvShows.add(
            TopTvShowResultsItem(
                originalName = "The Queen's Gambit",
                id = "87739",
                name = "The Queen's Gambit",
                voteAverage = 9.2,
                voteCount = 3453,
                firstAirDate = "2020-10-23",
                posterPath = "/zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
                backdropPath = "/34OGjFEbHj0E3lE2w0iTUVq0CBz.jpg",
                overview = "In a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction."
            )
        )
        tvShows.add(
            TopTvShowResultsItem(
                originalName = "The Boys",
                id = "76479",
                name = "The Boys",
                voteAverage = 8.4,
                voteCount = 235,
                firstAirDate = "2019-07-25",
                posterPath = "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                backdropPath = "/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg",
                overview = "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty."
            )
        )
        tvShows.add(
            TopTvShowResultsItem(
                originalName = "The Walking Dead: World Beyond",
                id = "94305",
                name = "The Walking Dead: World Beyond",
                voteAverage = 7.7,
                voteCount = 59,
                firstAirDate = "2020-10-04",
                posterPath = "/z31GxpVgDsFAF4paZR8PRsiP16D.jpg",
                backdropPath = "/pLVrN9B750ehwTFdQ6n3HRUERLd.jpg",
                overview = "A heroic group of teens sheltered from the dangers of the post-apocalyptic world leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world."
            )
        )
        tvShows.add(
            TopTvShowResultsItem(
                originalName = "Star Trek: Discovery",
                id = "67198",
                name = "Star Trek: Discovery",
                voteAverage = 7.0,
                voteCount = 90,
                firstAirDate = "2017-09-24",
                posterPath = "/98RYSYsRNKWgrAAFBn0WfploUG7.jpg",
                backdropPath = "/p3McpsDNTNmpbkNBKdNxOFZJeKX.jpg",
                overview = "Follow the voyages of Starfleet on their missions to discover new worlds and new life forms, and one Starfleet officer who must learn that to truly understand all things alien, you must first understand yourself."
            )
        )
        tvShows.add(
            TopTvShowResultsItem(
                originalName = "Fear the Walking Dead",
                id = "62286",
                name = "Fear the Walking Dead",
                voteAverage = 7.3,
                voteCount = 34523,
                firstAirDate = "2015-08-23",
                posterPath = "/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
                backdropPath = "/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg",
                overview = "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question."
            )
        )
        tvShows.add(
            TopTvShowResultsItem(
                originalName = "Helstrom",
                id = "88987",
                name = "Helstrom",
                voteAverage = 6.6,
                voteCount = 40,
                firstAirDate = "2020-10-16",
                posterPath = "/8fPIcaRIZgsoBcgA5yAm3GNz61M.jpg",
                backdropPath = "/qCReQONjNVaW4QFN2V5vCOzPvex.jpg",
                overview = "Daimon and Ana Helstrom are the son and daughter of a mysterious and powerful serial killer. The siblings have a complicated dynamic as they track down the terrorizing worst of humanity — each with their attitude and skills."
            )
        )
        tvShows.add(
            TopTvShowResultsItem(
                originalName = "The Undoing",
                id = "83851",
                name = "The Undoing",
                voteAverage = 4.0,
                voteCount = 10,
                firstAirDate = "2020-10-25",
                posterPath = "/3tDbJxobPN3EI2bBebL6zmusmw5.jpg",
                backdropPath = "/tJjwEzTglpxoWJXwEbZlKLppMhr.jpg",
                overview = "Grace Fraser is a successful therapist on the brink of publishing her first book with a devoted husband and young son who attends an elite private school in New York City. Weeks before her book is published, a chasm opens in her life: a violent death, a missing husband, and, in the place of a man Grace thought she knew, only a chain of terrible revelations."
            )
        )
        tvShows.add(
            TopTvShowResultsItem(
                originalName = "La Révolution",
                id = "99807",
                name = "La Révolution",
                voteAverage = 7.2,
                voteCount = 19,
                firstAirDate = "2020-10-16",
                posterPath = "/7OXN9nUI5sV1AkBViSKeehoePoG.jpg",
                backdropPath = "/lpRROknTUlvJ8gPgmRUlIoOVCdA.jpg",
                overview = "1787, France. While investigating a series of mysterious murders, Joseph Guillotin - the future inventor of the world famous ‘Guillotine’ - uncovers an unknown virus: the Blue Blood. The disease quickly spreads amongst the French aristocracy, driving them to murder ordinary people and soon leads to a rebellion."
            )
        )

        return tvShows
    }

//    fun generateRemoteDummyMovie(): ApiResponse<MovieResponse> {
//        val listGenres = ArrayList<MovieGenresItem>()
//        listGenres.add(MovieGenresItem("Action", 1))
//        listGenres.add(MovieGenresItem("Comedy", 2))
//        listGenres.add(MovieGenresItem("Horror", 3))
//
//        val anu = MovieResponse(
//            id = "575417",
//            voteAverage = 6.5,
//            voteCount = 33,
//            title = "On the Rocks",
//            releaseDate = "2020-10-02",
//            originalTitle = "On the Rocks",
//            backdropPath = "/7FQHLt4iNh2TZ58cAAYrZK0xogg.jpg",
//            posterPath = "/fcijRCmB7yTtloh4Pumy9b1rkwU.jpg",
//            overview = "Faced with sudden doubts about her marriage, a young New York mother teams up with her larger-than-life playboy father to tail her husband.",
//            runtime = 90,
//            tagline = "Faced with sudden doubts about her marriage",
//            genres = listGenres
//        )
//
//        return ApiResponse.success(anu)
//    }

    fun generateRemoteDummyMovie(): MovieResponse {
        val listGenres = ArrayList<MovieGenresItem>()
        listGenres.add(MovieGenresItem("Action", 1))
        listGenres.add(MovieGenresItem("Comedy", 2))
        listGenres.add(MovieGenresItem("Horror", 3))

        return MovieResponse(
            id = "575417",
            voteAverage = 6.5,
            voteCount = 33,
            title = "On the Rocks",
            releaseDate = "2020-10-02",
            originalTitle = "On the Rocks",
            backdropPath = "/7FQHLt4iNh2TZ58cAAYrZK0xogg.jpg",
            posterPath = "/fcijRCmB7yTtloh4Pumy9b1rkwU.jpg",
            overview = "Faced with sudden doubts about her marriage, a young New York mother teams up with her larger-than-life playboy father to tail her husband.",
            runtime = 90,
            tagline = "Faced with sudden doubts about her marriage",
            genres = listGenres
        )
    }

    fun generateRemoteDummyTvShow(): TvShowResponse {
        val listGenres = ArrayList<TvShowGenresItem>()
        listGenres.add(TvShowGenresItem("Action", 1))
        listGenres.add(TvShowGenresItem("Comedy", 2))
        listGenres.add(TvShowGenresItem("Horror", 3))

        return TvShowResponse(
            originalName = "The Boys",
            id = "76479",
            name = "The Boys",
            voteAverage = 8.4,
            voteCount = 235,
            firstAirDate = "2019-07-25",
            posterPath = "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
            backdropPath = "/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg",
            overview = "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
            numberOfEpisodes = 12,
            numberOfSeasons = 2,
            genres = listGenres
        )
    }

    fun generateDummyFavorite(): List<FavoriteEntity> {
        val favorite = ArrayList<FavoriteEntity>()

        favorite.add(
            FavoriteEntity(
                id = "740985",
                title = "Borat Subsequent Moviefilm",
                releaseDate = "2020-10-23",
                originalTitle = "Borat Subsequent Moviefilm",
                posterPath = "/6agKYU5IQFpuDyUYPu39w7UCRrJ.jpg",
                type = "Movie"
            )
        )
        favorite.add(
            FavoriteEntity(
                id = "590223",
                title = "Love and Monsters",
                releaseDate = "2020-10-16",
                originalTitle = "Love and Monsters",
                posterPath = "/r4Lm1XKP0VsTgHX4LG4syAwYA2I.jpg",
                type = "Movie"
            )
        )
        favorite.add(
            FavoriteEntity(
                originalTitle = "The Haunting of Bly Manor",
                id = "109958",
                title = "The Haunting of Bly Manor",
                releaseDate = "2020-10-09",
                posterPath = "/vIXQ8UymmQ7zJEPrKJP3s3fSbhR.jpg",
                type = "TV Show"
            )
        )
        favorite.add(
            FavoriteEntity(
                originalTitle = "Helstrom",
                id = "88987",
                title = "Helstrom",
                releaseDate = "2020-10-16",
                posterPath = "/8fPIcaRIZgsoBcgA5yAm3GNz61M.jpg",
                type = "TV Show"
            )
        )
        favorite.add(
            FavoriteEntity(
                id = "625568",
                title = "Unhinged",
                releaseDate = "2020-07-02",
                originalTitle = "Unhinged",
                posterPath = "/6WC3cIhuaLZSq3SbtdOp2ZTSTe.jpg",
                type = "Movie"
            )
        )

        return favorite
    }
}