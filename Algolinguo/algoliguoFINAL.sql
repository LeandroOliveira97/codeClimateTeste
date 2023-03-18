-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 03-Jul-2022 às 04:51
-- Versão do servidor: 10.4.22-MariaDB
-- versão do PHP: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `algoliguo`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluno_x_lista`
--

CREATE TABLE `aluno_x_lista` (
  `idAlunoLista` int(11) NOT NULL,
  `idAluno` int(11) NOT NULL,
  `idLista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `exercicios`
--

CREATE TABLE `exercicios` (
  `id` int(11) NOT NULL,
  `titulo` varchar(50) DEFAULT NULL,
  `resp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `exercicios`
--

INSERT INTO `exercicios` (`id`, `titulo`, `resp`) VALUES
(1, 'Calculadora', '321'),
(2, 'CalculaRaio', '4231'),
(3, 'PilhaPrato', '3421');

-- --------------------------------------------------------

--
-- Estrutura da tabela `listaex`
--

CREATE TABLE `listaex` (
  `id` int(11) NOT NULL,
  `nomeLista` varchar(30) NOT NULL,
  `exLista` varchar(20) DEFAULT NULL,
  `nomeAutor` varchar(50) NOT NULL,
  `alunosVinculados` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(250) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `tipoUsuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `senha`, `tipoUsuario`) VALUES
(11, 'admin', '21232f297a57a5a743894a0e4a801fc3', -1);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `aluno_x_lista`
--
ALTER TABLE `aluno_x_lista`
  ADD PRIMARY KEY (`idAlunoLista`),
  ADD KEY `fk_aluno` (`idAluno`),
  ADD KEY `fk_lista` (`idLista`);

--
-- Índices para tabela `exercicios`
--
ALTER TABLE `exercicios`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `listaex`
--
ALTER TABLE `listaex`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `aluno_x_lista`
--
ALTER TABLE `aluno_x_lista`
  MODIFY `idAlunoLista` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `listaex`
--
ALTER TABLE `listaex`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `aluno_x_lista`
--
ALTER TABLE `aluno_x_lista`
  ADD CONSTRAINT `fk_aluno` FOREIGN KEY (`idAluno`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `fk_lista` FOREIGN KEY (`idLista`) REFERENCES `listaex` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
