CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS Products (
    id UUID DEFAUT gen_random_uuid() PRIMARY KEY,
    codigo INT NOT NULL ,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    custo DOUBLE PRECISION(2) NOT NULL,
    preco DOUBLE PRECISION(2) NOT NULL,
    estoqueMin INT NOT NULL,
    estoque INT NOT NULL
);