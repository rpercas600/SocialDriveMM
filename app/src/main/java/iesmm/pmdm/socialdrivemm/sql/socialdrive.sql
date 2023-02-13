DROP DATABASE IF EXISTS socialdrivemm;
CREATE DATABASE socialdrivemm CHARACTER SET utf8mb4;
USE socialdrivemm;

CREATE TABLE `marcador` (
  `id` int(5) NOT NULL,
  `hora` varchar(20) NOT NULL,
  `ubi` varchar(20) NOT NULL,
  `descripcion` varchar(20) NOT NULL,
  `via` varchar(10) NOT NULL,
  `user` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `usuario` (
  `user` varchar(15) NOT NULL,
  `pass` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `usuario` (`user`, `pass`) VALUES
('Ale', '1234'),
('Rafa', '1234');

ALTER TABLE `marcador`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_loggued` (`user`);

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`user`);

ALTER TABLE `marcador`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT;

ALTER TABLE `marcador`
  ADD CONSTRAINT `user_loggued` FOREIGN KEY (`user`) REFERENCES `usuario` (`user`);
COMMIT;