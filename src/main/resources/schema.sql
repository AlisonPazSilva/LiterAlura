CREATE TABLE IF NOT EXISTS autor (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    ano_nacimiento INTEGER,
    ano_fallecimiento INTEGER
);

CREATE TABLE IF NOT EXISTS libro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    idioma VARCHAR(10),
    numero_descargas INTEGER,
    autor_id INTEGER NOT NULL,
    CONSTRAINT fk_autor FOREIGN KEY(autor_id) REFERENCES autor(id)
);
