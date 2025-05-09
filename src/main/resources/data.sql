-- Tabela Time
INSERT INTO time (id, nome, data) VALUES
(1, 'Real Madrid', '2020-08-01'),
(2, 'Barcelona', '2020-08-01'),
(3, 'Juventus', '2021-01-15'),
(4, 'Bayern de Munique', '2020-08-01'),
(5, 'Paris Saint-Germain', '2022-03-10'),
(6, 'Manchester City', '2022-01-01'),
(7, 'Liverpool', '2021-01-01'),
(8, 'Manchester United', '2021-08-01'),
(9, 'Tottenham Hotspur', '2021-03-01'),
(10, 'Juventus', '2020-09-01'),
(11, 'Atlético de Madrid', '2021-01-01');

-- Tabela Integrante (Jogadores)
INSERT INTO integrante (id, franquia, nome, funcao) VALUES
(1, 'Espanha', 'Karim Benzema', 'Atacante'),
(2, 'Espanha', 'Luka Modric', 'Meio-campista'),
(3, 'Espanha', 'Gerard Piqué', 'Zagueiro'),
(4, 'Inglaterra', 'Mohamed Salah', 'Atacante'),
(5, 'Alemanha', 'Manuel Neuer', 'Goleiro'),
(6, 'Alemanha', 'Thomas Müller', 'Meio-campista'),
(7, 'Brasil', 'Gabriel Barbosa', 'Atacante'),
(8, 'Brasil', 'Arrascaeta', 'Meio-campista'),
(9, 'Inglaterra', 'Kevin De Bruyne', 'Meio-campista'),
(10, 'Inglaterra', 'Erling Haaland', 'Atacante'),
(11, 'Brasil', 'Neymar Jr.', 'Atacante'),
(12, 'Argentina', 'Lionel Messi', 'Atacante'),
(13, 'Portugal', 'Cristiano Ronaldo', 'Atacante'),
(14, 'França', 'Kylian Mbappé', 'Atacante'),
(15, 'Alemanha', 'Joshua Kimmich', 'Meio-campista'),
(16, 'Inglaterra', 'Harry Kane', 'Atacante'),
(17, 'Brasil', 'Casemiro', 'Volante'),
(18, 'Espanha', 'Sergio Ramos', 'Zagueiro'),
(19, 'Croácia', 'Ivan Perišić', 'Meio-campista'),
(20, 'Uruguai', 'Luis Suárez', 'Atacante');

-- Composições de Times (Relacionamento entre Time e Integrante)
-- Real Madrid
INSERT INTO composicao_time (id, time_id, integrante_id, funcao_no_time) VALUES
(1, 1, 1, 'Centroavante'),
(2, 1, 2, 'Armador'),
(3, 2, 3, 'Defensor Central'),
(4, 3, 4, 'Ponta Direita'),
(5, 4, 5, 'Goleiro Titular'),
(6, 4, 6, 'Meio Ofensivo'),
(7, 6, 7, 'Atacante'),
(8, 6, 8, 'Meia Criativo'),
(9, 5, 9, 'Meio Criativo'),
(10, 5, 10, 'Centroavante'),
(11, 4, 1, 'Atacante'),
(12, 2, 4, 'Ponta Direita'),
(13, 1, 7, 'Reserva'),
(14, 2, 10, 'Atacante'),
(15, 1, 9, 'Armador'),
(16, 3, 3, 'Zagueiro'),
(17, 2, 2, 'Meio-campista'),
(18, 5, 8, 'Meia'),
(19, 5, 6, 'Meio Ofensivo'),
(20, 5, 5, 'Goleiro');

-- PSG
INSERT INTO composicao_time (id, time_id, integrante_id, funcao_no_time) VALUES
(21, 7, 11, 'Ponta Esquerda'),
(22, 7, 12, 'Falso 9'),
(23, 7, 14, 'Ponta Direita');

-- Manchester United
INSERT INTO composicao_time (id, time_id, integrante_id, funcao_no_time) VALUES
(24, 8, 13, 'Centroavante'),
(25, 8, 17, 'Volante'),
(26, 8, 18, 'Zagueiro');

-- Tottenham Hotspur
INSERT INTO composicao_time (id, time_id, integrante_id, funcao_no_time) VALUES
(27, 9, 16, 'Centroavante');

-- Juventus
INSERT INTO composicao_time (id, time_id, integrante_id, funcao_no_time) VALUES
(28, 10, 13, 'Centroavante'),
(29, 10, 19, 'Meia Ofensivo');

-- Atlético de Madrid
INSERT INTO composicao_time (id, time_id, integrante_id, funcao_no_time) VALUES
(30, 11, 20, 'Centroavante'),
(31, 11, 18, 'Zagueiro');
