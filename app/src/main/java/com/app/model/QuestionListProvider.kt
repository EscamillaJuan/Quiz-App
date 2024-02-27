package com.app.model

import com.app.R
import com.app.model.entity.Question

object QuestionListProvider {
    val items = listOf(
        Question(
            "¿Cuál es el nombre del actor que " +
                    "interpreta a Neo en la película The Matrix?",
            "Cine",
            R.drawable.clapperboard,
            listOf(
                mapOf("Tom Cruise" to false),
                mapOf("Leonardo DiCaprio" to false),
                mapOf("Will Smith" to false)
            ),
            mapOf("Keanu Reeves" to true)
        ),
        Question(
            "¿Cuál es la película ganadora del Óscar a la " +
                    "Mejor Película en 1994 dirigida por Steven Spielberg y protagonizada por Tom Hanks?",
            "Cine",
            R.drawable.clapperboard,
            listOf(
                mapOf("Rescatando al soldado Ryan" to false),
                mapOf("La terminal" to false),
                mapOf("Náufrago" to false)
            ),
            mapOf("Forrest Gump" to true)
        ),
        Question(
            "¿Cuál era el nombre original de Gollum en el señor de los anillos?",
            "Cine",
            R.drawable.clapperboard,
            listOf(
                mapOf("Isildur" to false),
                mapOf("Galadriel" to false),
                mapOf("Elrond" to false)
            ),
            mapOf("Smeagol" to true)
        ),
        Question(
            "¿Cuántos son los espectros del anillo (Nazgul)?",
            "Cine",
            R.drawable.clapperboard,
            listOf(
                mapOf("5" to false),
                mapOf("8" to false),
                mapOf("10" to false)
            ),
            mapOf("9" to true)
        ),
        Question(
            "¿Cuál es el nombre de la nave espacial protagonista en la saga Star Wars?",
            "Cine",
            R.drawable.clapperboard,
            listOf(
                mapOf("Enterprise" to false),
                mapOf("Serenity" to false),
                mapOf("Discovery" to false)
            ),
            mapOf("Millennium Falcon" to true)
        ),
        Question(
            "¿Qué famoso pintor fue conocido por su estilo surrealista y sus pinturas como La persistencia de la memoria?",
            "Arte",
            R.drawable.palette,
            listOf(
                mapOf("Pablo Picasso" to false),
                mapOf("Vincent van Gogh" to false),
                mapOf("Claude Monet" to false)
            ),
            mapOf("Salvador Dalí" to true)
        ),
        Question(
            "¿Quién pintó la famosa obra La noche estrellada?",
            "Arte",
            R.drawable.palette,
            listOf(
                mapOf("Leonardo da Vinci" to false),
                mapOf("Michelangelo Buonarroti" to false),
                mapOf("Johannes Vermeer" to false)
            ),
            mapOf("Vincent van Gogh" to true)
        ),
        Question(
            "¿Cuál es el nombre de la pintura renacentista creada por Leonardo da Vinci que representa a una mujer misteriosa con una sonrisa enigmática?",
            "Arte",
            R.drawable.palette,
            listOf(
                mapOf("La última cena" to false),
                mapOf("El nacimiento de Venus" to false),
                mapOf("La Venus del espejo" to false)
            ),
            mapOf("La Gioconda (Mona Lisa)" to true)
        ),
        Question(
            "¿Cuál de estas obras es una escultura del artista griego clásico Fidias?",
            "Arte",
            R.drawable.palette,
            listOf(
                mapOf("La Venus de Milo" to false),
                mapOf("El David" to false),
                mapOf("El Moisés" to false)
            ),
            mapOf("El Discóbolo" to true)
        ),
        Question(
            "¿Qué movimiento artístico se caracteriza por su enfoque en la representación de la vida cotidiana y la clase trabajadora?",
            "Arte",
            R.drawable.palette,
            listOf(
                mapOf("Impresionismo" to false),
                mapOf("Cubismo" to false),
                mapOf("Surrealismo" to false)
            ),
            mapOf("Realismo" to true)
        ),
        Question(
            "¿Quién fue el primer presidente de Estados Unidos?",
            "Historia",
            R.drawable.book,
            listOf(
                mapOf("Abraham Lincoln" to false),
                mapOf("Thomas Jefferson" to false),
                mapOf("John F. Kennedy" to false)
            ),
            mapOf("George Washington" to true)
        ),
        Question(
            "¿Cuál de las siguientes civilizaciones antiguas construyó las pirámides de Giza?",
            "Historia",
            R.drawable.book,
            listOf(
                mapOf("Griega" to false),
                mapOf("Egipcia" to false),
                mapOf("Persa" to false)
            ),
            mapOf("Babilonia" to true)
        ),
        Question(
            "¿Quién fue el líder de la Revolución Rusa en 1917?",
            "Historia",
            R.drawable.book,
            listOf(
                mapOf("Joseph Stalin" to false),
                mapOf("Leon Trotsky" to false),
                mapOf("Nicholas II" to false)
            ),
            mapOf("Vladimir Lenin" to true)
        ),
        Question(
            "¿Qué evento histórico marcó el final de la Segunda Guerra Mundial en Europa?",
            "Historia",
            R.drawable.book,
            listOf(
                mapOf("La batalla de Stalingrado" to false),
                mapOf("El desembarco de Normandía" to false),
                mapOf("La rendición de Alemania nazi" to false)
            ),
            mapOf("El bombardeo de Hiroshima y Nagasaki" to true)
        ),
        Question(
            "¿Cuál de las siguientes civilizaciones es conocida por su código de leyes grabado en piedra?",
            "Historia",
            R.drawable.book,
            listOf(
                mapOf("Grecia" to false),
                mapOf("China" to false),
                mapOf("Egipto" to false)
            ),
            mapOf("Babilonia" to true)
        ),
        Question(
            "¿Cuál de estos instrumentos es de viento?",
            "Música",
            R.drawable.music,
            listOf(
                mapOf("Guitarra" to false),
                mapOf("Piano" to false),
                mapOf("Batería" to false)
            ),
            mapOf("Trompeta" to true)
        ),
        Question(
            "¿Qué banda británica es conocida como los Cuatro Fabulosos?",
            "Música",
            R.drawable.music,
            listOf(
                mapOf("The Rolling Stones" to false),
                mapOf("Led Zeppelin" to false),
                mapOf("Queen" to false)
            ),
            mapOf("The Beatles" to true)
        ),
        Question(
            "¿Quién es conocido como el Rey del Pop?",
            "Música",
            R.drawable.music,
            listOf(
                mapOf("Elvis Presley" to false),
                mapOf("Prince" to false),
                mapOf("Stevie Wonder" to false)
            ),
            mapOf("Michael Jackson" to true)
        ),
        Question(
            "¿Cuál es el género musical que se originó en Jamaica y se caracteriza por su ritmo distintivo y letras sociales o políticas?",
            "Música",
            R.drawable.music,
            listOf(
                mapOf("Salsa" to false),
                mapOf("Hip-hop" to false),
                mapOf("Blues" to false)
            ),
            mapOf("Reggae" to true)
        ),
        Question(
            "¿Quién es el compositor de la famosa obra El Bolero de Ravel?",
            "Música",
            R.drawable.music,
            listOf(
                mapOf("Ludwig van Beethoven" to false),
                mapOf("Wolfgang Amadeus Mozart" to false),
                mapOf("Johann Sebastian Bach" to false)
            ),
            mapOf("Maurice Ravel" to true)
        ),
        Question(
            "¿Cuál es la fórmula química del agua?",
            "Ciencia",
            R.drawable.flask_vial,
            listOf(
                mapOf("CO2" to false),
                mapOf("NaCl" to false),
                mapOf("C6H12O6" to false)
            ),
            mapOf("H2O" to true)
        ),
        Question(
            "¿Cuál es la unidad básica de la estructura de los seres vivos?",
            "Ciencia",
            R.drawable.flask_vial,
            listOf(
                mapOf("Átomo" to false),
                mapOf("ADN" to false),
                mapOf("Gen" to false)
            ),
            mapOf("Célula" to true)
        ),
        Question(
            "¿Cuál de las siguientes es una ley fundamental de la física que describe la relación entre la masa y la energía?",
            "Ciencia",
            R.drawable.flask_vial,
            listOf(
                mapOf("Ley de Boyle" to false),
                mapOf("Ley de la gravedad" to false),
                mapOf("Ley de la relatividad" to false)
            ),
            mapOf("Ley de la relatividad" to true)
        ),
        Question(
            "¿Qué científico propuso la teoría de la evolución por selección natural?",
            "Ciencia",
            R.drawable.flask_vial,
            listOf(
                mapOf("Gregor Mendel" to false),
                mapOf("Albert Einstein" to false),
                mapOf("Louis Pasteur" to false)
            ),
            mapOf("Charles Darwin" to true)
        ),
        Question(
            "¿Cuál es la capa más externa de la Tierra?",
            "Ciencia",
            R.drawable.flask_vial,
            listOf(
                mapOf("Núcleo" to false),
                mapOf("Manto" to false),
                mapOf("Atmósfera" to false)
            ),
            mapOf("Corteza" to true)
        )
    )
}