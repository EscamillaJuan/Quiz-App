package com.app.utils

import com.app.R

class InsertionQueries {
    val topicInsertion = listOf(
        "INSERT INTO topic(_id, title, icon) VALUES(0, 'Cine', ${R.drawable.clapperboard})",
        "INSERT INTO topic(_id, title, icon) VALUES(1, 'Arte', ${R.drawable.palette})",
        "INSERT INTO topic(_id, title, icon) VALUES(2, 'Historia', ${R.drawable.book})",
        "INSERT INTO topic(_id, title, icon) VALUES(3, 'Música', ${R.drawable.music})",
        "INSERT INTO topic(_id, title, icon) VALUES(4, 'Ciencia', ${R.drawable.flask_vial})",
        "INSERT INTO topic(_id, title, icon) VALUES(5, 'Tecnología', ${R.drawable.baseline_build_24})"
    )
    val questionInsertion = listOf(
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(0, 0, '¿Cuál es el nombre del actor que interpreta a Neo en la película The Matrix?', 'Keanu Reeves', 'Tom Cruise', 'Leonardo DiCaprio', 'Will Smith')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(1, 0, '¿Cuál es la película ganadora del Óscar a la Mejor Película en 1994 dirigida por Steven Spielberg y protagonizada por Tom Hanks?', 'Forrest Gump', 'Rescatando al soldado Ryan', 'La terminal', 'Náufrago')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(2, 0, '¿Cuál era el nombre original de Gollum en El Señor de los Anillos?', 'Smeagol', 'Isildur', 'Galadriel', 'Elrond')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(3, 0, '¿Cuántos son los espectros del anillo (Nazgul)?', '9', '5', '8', '10')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(4, 0, '¿Cuál es el nombre de la nave espacial protagonista en la saga Star Wars?', 'Millennium Falcon', 'Enterprise', 'Serenity', 'Discovery')",

        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(5, 1, '¿Qué famoso pintor fue conocido por su estilo surrealista y sus pinturas como La persistencia de la memoria?', 'Salvador Dalí', 'Pablo Picasso', 'Vincent van Gogh', 'Claude Monet')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(6, 1, '¿Quién pintó la famosa obra La noche estrellada?', 'Vincent van Gogh', 'Leonardo da Vinci', 'Michelangelo Buonarroti', 'Johannes Vermeer')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(7, 1, '¿Cuál es el nombre de la pintura renacentista creada por Leonardo da Vinci que representa a una mujer misteriosa con una sonrisa enigmática?', 'La Gioconda (Mona Lisa)', 'La última cena', 'El nacimiento de Venus', 'La Venus del espejo')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(8, 1, '¿Cuál de estas obras es una escultura del artista griego clásico Fidias?', 'El Discóbolo', 'La Venus de Milo', 'El David', 'El Moisés')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(9, 1, '¿Qué movimiento artístico se caracteriza por su enfoque en la representación de la vida cotidiana y la clase trabajadora?', 'Realismo', 'Impresionismo', 'Cubismo', 'Surrealismo')",

        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(10, 2, '¿Quién fue el primer presidente de Estados Unidos?', 'George Washington', 'Abraham Lincoln', 'Thomas Jefferson', 'John F. Kennedy')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(11, 2, '¿Cuál de las siguientes civilizaciones antiguas construyó las pirámides de Giza?', 'Egipcia', 'Griega', 'Babilonia', 'Persa')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(12, 2, '¿Quién fue el líder de la Revolución Rusa en 1917?', 'Vladimir Lenin', 'Joseph Stalin', 'Leon Trotsky', 'Nicholas II')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(13, 2, '¿Qué evento histórico marcó el final de la Segunda Guerra Mundial en Europa?', 'El bombardeo de Hiroshima y Nagasaki', 'La batalla de Stalingrado', 'El desembarco de Normandía', 'La rendición de Alemania nazi')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(14, 2, '¿Cuál de las siguientes civilizaciones es conocida por su código de leyes grabado en piedra?', 'Babilonia', 'Grecia', 'China', 'Egipto')",

        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(15, 3, '¿Cuál de estos instrumentos es de viento?', 'Trompeta', 'Guitarra', 'Piano', 'Batería')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(16, 3, '¿Qué banda británica es conocida como los Cuatro Fabulosos?', 'The Beatles', 'The Rolling Stones', 'Led Zeppelin', 'Queen')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(17, 3, '¿Quién es conocido como el Rey del Pop?', 'Michael Jackson', 'Elvis Presley', 'Prince', 'Stevie Wonder')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(18, 3, '¿Cuál es el género musical que se originó en Jamaica y se caracteriza por su ritmo distintivo y letras sociales o políticas?', 'Reggae', 'Salsa', 'Hip-hop', 'Blues')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(19, 3, '¿Quién es el compositor de la famosa obra El Bolero de Ravel?', 'Maurice Ravel', 'Ludwig van Beethoven', 'Wolfgang Amadeus Mozart', 'Johann Sebastian Bach')",

        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(20, 4, '¿Cuál es la fórmula química del agua?', 'H2O', 'CO2', 'NaCl', 'C6H12O6')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(21, 4, '¿Cuál es la unidad básica de la estructura de los seres vivos?', 'Célula', 'Átomo', 'ADN', 'Gen')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(22, 4, '¿Cuál de las siguientes es una ley fundamental de la física que describe la relación entre la masa y la energía?', 'Ley de la relatividad', 'Ley de Boyle', 'Ley de la gravedad', 'Primera ley de Newton')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(23, 4, '¿Qué científico propuso la teoría de la evolución por selección natural?', 'Charles Darwin', 'Gregor Mendel', 'Albert Einstein', 'Louis Pasteur')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(24, 4, '¿Cuál es la capa más externa de la Tierra?', 'Corteza', 'Núcleo', 'Manto', 'Atmósfera')",

        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(25, 5, '¿Qué es el lenguaje de programación predominante para el desarrollo de aplicaciones móviles en Android?', 'Kotlin', 'Java', 'Python', 'Kotlin')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(26, 5, '¿Cuál de las siguientes es una tecnología de almacenamiento en la nube ofrecida por Amazon?', 'Amazon S3', 'Amazon EC2', 'Amazon RDS', 'Amazon S3')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(27, 5, '¿Qué empresa desarrolló el sistema operativo Windows?', 'Microsoft', 'Apple', 'Google', 'Microsoft')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(28, 5, '¿Cuál de las siguientes es una base de datos relacional ampliamente utilizada?', 'MySQL', 'PostgreSQL', 'SQLite', 'MySQL')",
        "INSERT INTO question(_id, topic_id, text, answer, option_1, option_2, option_3) VALUES(29, 5, '¿Qué protocolo se utiliza para transferir páginas web a través de Internet?', 'HTTP', 'HTTPS', 'FTP', 'HTTP')"
    )

    val gameSessionInsert =
        "INSERT INTO game_session(_id, mode, question_qty, hint_enable, hint_qty, finished, score, answered_questions_qty) VALUES(0, 'medium', 10, 1, 3, 1, 0, 0)"

    val gameOptionInsert =
        "INSERT INTO game_option(_id, mode, question_qty, hint, cine, arte, historia, musica, ciencia, tecnologia)" +
                " VALUES(0, 1, 5, 1, 1, 1, 1, 0, 0, 0)"
}

