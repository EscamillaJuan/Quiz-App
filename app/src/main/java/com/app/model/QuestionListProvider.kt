package com.app.model

import com.app.R
import com.app.model.entity.Question

object QuestionListProvider {
    val items = listOf(
        Question(
            "¿Cuál es el nombre del actor que interpreta a Neo en la película The Matrix?",
            "Cine",
            R.drawable.clapperboard,
            listOf(
                "Tom Cruise",
                "Leonardo DiCaprio",
                "Will Smith"
            ),
            "Keanu Reeves"
        ),
        Question(
            "¿Cuál es la película ganadora del Óscar a la Mejor Película en 1994 dirigida por Steven Spielberg y protagonizada por Tom Hanks?",
            "Cine",
            R.drawable.clapperboard,
            listOf(
                "Rescatando al soldado Ryan",
                "La terminal",
                "Náufrago"
            ),
            "Forrest Gump"
        ),
        Question(
            "¿Cuál era el nombre original de Gollum en El Señor de los Anillos?",
            "Cine",
            R.drawable.clapperboard,
            listOf(
                "Isildur",
                "Galadriel",
                "Elrond"
            ),
            "Smeagol"
        ),
        Question(
            "¿Cuántos son los espectros del anillo (Nazgul)?",
            "Cine",
            R.drawable.clapperboard,
            listOf(
                "5",
                "8",
                "10"
            ),
            "9"
        ),
        Question(
            "¿Cuál es el nombre de la nave espacial protagonista en la saga Star Wars?",
            "Cine",
            R.drawable.clapperboard,
            listOf(
                "Enterprise",
                "Serenity",
                "Discovery"
            ),
            "Millennium Falcon"
        ),
        Question(
            "¿Qué famoso pintor fue conocido por su estilo surrealista y sus pinturas como La persistencia de la memoria?",
            "Arte",
            R.drawable.palette,
            listOf(
                "Pablo Picasso",
                "Vincent van Gogh",
                "Claude Monet"
            ),
            "Salvador Dalí"
        ),
        Question(
            "¿Quién pintó la famosa obra La noche estrellada?",
            "Arte",
            R.drawable.palette,
            listOf(
                "Leonardo da Vinci",
                "Michelangelo Buonarroti",
                "Johannes Vermeer"
            ),
            "Vincent van Gogh"
        ),
        Question(
            "¿Cuál es el nombre de la pintura renacentista creada por Leonardo da Vinci que representa a una mujer misteriosa con una sonrisa enigmática?",
            "Arte",
            R.drawable.palette,
            listOf(
                "La última cena",
                "El nacimiento de Venus",
                "La Venus del espejo"
            ),
            "La Gioconda (Mona Lisa)"
        ),
        Question(
            "¿Cuál de estas obras es una escultura del artista griego clásico Fidias?",
            "Arte",
            R.drawable.palette,
            listOf(
                "La Venus de Milo",
                "El David",
                "El Moisés"
            ),
            "El Discóbolo"
        ),
        Question(
            "¿Qué movimiento artístico se caracteriza por su enfoque en la representación de la vida cotidiana y la clase trabajadora?",
            "Arte",
            R.drawable.palette,
            listOf(
                "Impresionismo",
                "Cubismo",
                "Surrealismo"
            ),
            "Realismo"
        ),
        Question(
            "¿Quién fue el primer presidente de Estados Unidos?",
            "Historia",
            R.drawable.book,
            listOf(
                "Abraham Lincoln",
                "Thomas Jefferson",
                "John F. Kennedy"
            ),
            "George Washington"
        ),
        Question(
            "¿Cuál de las siguientes civilizaciones antiguas construyó las pirámides de Giza?",
            "Historia",
            R.drawable.book,
            listOf(
                "Griega",
                "Egipcia",
                "Persa"
            ),
            "Babilonia"
        ),
        Question(
            "¿Quién fue el líder de la Revolución Rusa en 1917?",
            "Historia",
            R.drawable.book,
            listOf(
                "Joseph Stalin",
                "Leon Trotsky",
                "Nicholas II"
            ),
            "Vladimir Lenin"
        ),
        Question(
            "¿Qué evento histórico marcó el final de la Segunda Guerra Mundial en Europa?",
            "Historia",
            R.drawable.book,
            listOf(
                "La batalla de Stalingrado",
                "El desembarco de Normandía",
                "La rendición de Alemania nazi"
            ),
            "El bombardeo de Hiroshima y Nagasaki"
        ),
        Question(
            "¿Cuál de las siguientes civilizaciones es conocida por su código de leyes grabado en piedra?",
            "Historia",
            R.drawable.book,
            listOf(
                "Grecia",
                "China",
                "Egipto"
            ),
            "Babilonia"
        ),
        Question(
            "¿Cuál de estos instrumentos es de viento?",
            "Música",
            R.drawable.music,
            listOf(
                "Guitarra",
                "Piano",
                "Batería"
            ),
            "Trompeta"
        ),
        Question(
            "¿Qué banda británica es conocida como los Cuatro Fabulosos?",
            "Música",
            R.drawable.music,
            listOf(
                "The Rolling Stones",
                "Led Zeppelin",
                "Queen"
            ),
            "The Beatles"
        ),
        Question(
            "¿Quién es conocido como el Rey del Pop?",
            "Música",
            R.drawable.music,
            listOf(
                "Elvis Presley",
                "Prince",
                "Stevie Wonder"
            ),
            "Michael Jackson"
        ),
        Question(
            "¿Cuál es el género musical que se originó en Jamaica y se caracteriza por su ritmo distintivo y letras sociales o políticas?",
            "Música",
            R.drawable.music,
            listOf(
                "Salsa",
                "Hip-hop",
                "Blues"
            ),
            "Reggae"
        ),
        Question(
            "¿Quién es el compositor de la famosa obra El Bolero de Ravel?",
            "Música",
            R.drawable.music,
            listOf(
                "Ludwig van Beethoven",
                "Wolfgang Amadeus Mozart",
                "Johann Sebastian Bach"
            ),
            "Maurice Ravel"
        ),
        Question(
            "¿Cuál es la fórmula química del agua?",
            "Ciencia",
            R.drawable.flask_vial,
            listOf(
                "CO2",
                "NaCl",
                "C6H12O6"
            ),
            "H2O"
        ),
        Question(
            "¿Cuál es la unidad básica de la estructura de los seres vivos?",
            "Ciencia",
            R.drawable.flask_vial,
            listOf(
                "Átomo",
                "ADN",
                "Gen"
            ),
            "Célula"
        ),
        Question(
            "¿Cuál de las siguientes es una ley fundamental de la física que describe la relación entre la masa y la energía?",
            "Ciencia",
            R.drawable.flask_vial,
            listOf(
                "Ley de Boyle",
                "Ley de la gravedad",
                "Ley de la relatividad"
            ),
            "Ley de la relatividad"
        ),
        Question(
            "¿Qué científico propuso la teoría de la evolución por selección natural?",
            "Ciencia",
            R.drawable.flask_vial,
            listOf(
                "Gregor Mendel",
                "Albert Einstein",
                "Louis Pasteur"
            ),
            "Charles Darwin"
        ),
        Question(
            "¿Cuál es la capa más externa de la Tierra?",
            "Ciencia",
            R.drawable.flask_vial,
            listOf(
                "Núcleo",
                "Manto",
                "Atmósfera"
            ),
            "Corteza"
        )
    )
}
