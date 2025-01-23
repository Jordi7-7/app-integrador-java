-- Crear tabla de Autores
CREATE TABLE lib_autor (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    nacionalidad VARCHAR(100)
);

-- Crear tabla de Libros
CREATE TABLE lib_libro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    genero VARCHAR(100),
    anio_publicacion INT,
    id_autor INT REFERENCES lib_autor(id) ON DELETE SET NULL
);

-- Crear tabla de Usuarios
CREATE TABLE lib_usuario (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) UNIQUE NOT NULL
);

-- Crear tabla de Préstamos
CREATE TABLE lib_prestamo (
    id SERIAL PRIMARY KEY,
    id_libro INT REFERENCES lib_libro(id) ON DELETE CASCADE,
    id_usuario INT REFERENCES lib_usuario(id) ON DELETE CASCADE,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE
);

-- INSERCIONES AUTORES
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Gabriel García Márquez', 'Británica');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Isabel Allende', 'Italiana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('J.K. Rowling', 'Japonesa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Stephen King', 'Japonesa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('George R.R. Martin', 'Japonesa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Ernest Hemingway', 'Británica');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Jane Austen', 'Rusa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Mark Twain', 'Francesa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Charles Dickens', 'Colombiana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Virginia Woolf', 'Española');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('F. Scott Fitzgerald', 'Colombiana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Haruki Murakami', 'Alemana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Toni Morrison', 'Española');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('William Faulkner', 'Española');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Leo Tolstoy', 'Británica');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Homer', 'Alemana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Dante Alighieri', 'Chilena');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('J.R.R. Tolkien', 'Estadounidense');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Agatha Christie', 'Italiana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Edgar Allan Poe', 'Británica');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Victor Hugo', 'Italiana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Mary Shelley', 'Colombiana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Franz Kafka', 'Española');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('James Joyce', 'Alemana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Kurt Vonnegut', 'Francesa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Oscar Wilde', 'Británica');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Emily Brontë', 'Francesa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Herman Melville', 'Española');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Fyodor Dostoevsky', 'Colombiana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Anton Chekhov', 'Estadounidense');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Sylvia Plath', 'Alemana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('J.D. Salinger', 'Estadounidense');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('George Orwell', 'Rusa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Aldous Huxley', 'Italiana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Ray Bradbury', 'Francesa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('John Steinbeck', 'Española');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Margaret Atwood', 'Rusa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Philip K. Dick', 'Británica');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Isaac Asimov', 'Española');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Arthur C. Clarke', 'Italiana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('H.G. Wells', 'Italiana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Douglas Adams', 'Rusa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Terry Pratchett', 'Chilena');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Neil Gaiman', 'Japonesa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('C.S. Lewis', 'Francesa');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Rick Riordan', 'Española');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Suzanne Collins', 'Española');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Veronica Roth', 'Chilena');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Dan Brown', 'Colombiana');
INSERT INTO lib_autor (nombre, nacionalidad) VALUES ('Nicholas Sparks', 'Colombiana');

-- INSERCIONES LIBROS
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El misterio de Gabriel', 'Fantasía', 1972, 27);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La historia de Isabel', 'Novela', 1914, 24);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El misterio de J.K.', 'Misterio', 1963, 17);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('Las aventuras de Stephen', 'Misterio', 1936, 24);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El legado de George', 'Poesía', 2022, 11);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El enigma de Ernest', 'Ciencia Ficción', 1911, 11);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El enigma de Jane', 'Misterio', 1965, 3);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El enigma de Mark', 'Misterio', 1961, 29);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La historia de Charles', 'Misterio', 1900, 35);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('Las aventuras de Virginia', 'Poesía', 1928, 24);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El legado de F.', 'Ciencia Ficción', 1946, 38);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('Las aventuras de Haruki', 'Romance', 1948, 37);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La historia de Toni', 'Historia', 1942, 36);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('Las aventuras de William', 'Fantasía', 1984, 50);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El misterio de Leo', 'Fantasía', 1926, 13);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El enigma de Homer', 'Fantasía', 1938, 22);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La magia de Dante', 'Horror', 1961, 48);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El misterio de J.R.R.', 'Poesía', 1946, 42);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La magia de Agatha', 'Misterio', 1945, 39);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El legado de Edgar', 'Historia', 1911, 41);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La magia de Victor', 'Misterio', 1946, 18);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La historia de Mary', 'Historia', 1999, 21);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El legado de Franz', 'Ciencia Ficción', 1904, 15);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La magia de James', 'Romance', 1941, 17);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('Las aventuras de Kurt', 'Romance', 1983, 2);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El enigma de Oscar', 'Romance', 2012, 6);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La historia de Emily', 'Poesía', 2007, 30);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El legado de Herman', 'Romance', 2013, 50);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El enigma de Fyodor', 'Ciencia Ficción', 1995, 28);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El legado de Anton', 'Misterio', 2014, 23);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El legado de Sylvia', 'Romance', 1999, 11);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('Las aventuras de J.D.', 'Fantasía', 1956, 42);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('Las aventuras de George', 'Misterio', 1911, 20);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El misterio de Aldous', 'Poesía', 2010, 11);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La historia de Ray', 'Misterio', 1920, 36);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El legado de John', 'Misterio', 1909, 8);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La historia de Margaret', 'Ciencia Ficción', 1935, 20);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El legado de Philip', 'Misterio', 1967, 25);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El misterio de Isaac', 'Romance', 1931, 3);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('Las aventuras de Arthur', 'Fantasía', 1954, 19);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El legado de H.G.', 'Poesía', 1969, 5);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('Las aventuras de Douglas', 'Historia', 1996, 10);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El enigma de Terry', 'Misterio', 1979, 24);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La magia de Neil', 'Ciencia Ficción', 1988, 26);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La magia de C.S.', 'Poesía', 1951, 48);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La magia de Rick', 'Romance', 2014, 39);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La magia de Suzanne', 'Romance', 1952, 44);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La historia de Veronica', 'Misterio', 1900, 3);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('El enigma de Dan', 'Romance', 1939, 17);
INSERT INTO lib_libro (titulo, genero, anio_publicacion, id_autor) VALUES ('La magia de Nicholas', 'Misterio', 2022, 19);