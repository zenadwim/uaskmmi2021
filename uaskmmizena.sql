-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 19, 2021 at 04:57 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uaskmmizena`
--

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `nip_guru` int(15) NOT NULL,
  `username` char(30) NOT NULL,
  `nama_guru` varchar(60) NOT NULL,
  `jk` char(20) NOT NULL,
  `hp` char(13) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`nip_guru`, `username`, `nama_guru`, `jk`, `hp`, `alamat`) VALUES
(1234710, 'agus', 'agus dwi', 'laki-laki', '089998753760', 'depok'),
(1234789, 'fatimah', 'fatimah azzahra', 'perempuan', '089998753760', 'bandung'),
(1234888, 'syabilla', 'syabilla anindya', 'perempuan', '081137822769', 'tangerang'),
(2244789, 'taqy', 'taqy malik', 'laki-laki', '089998753666', 'jakarta');

-- --------------------------------------------------------

--
-- Table structure for table `hafalan`
--

CREATE TABLE `hafalan` (
  `id_hafalan` int(11) NOT NULL,
  `surat` varchar(30) NOT NULL,
  `ayat` varchar(30) NOT NULL,
  `nisn` int(15) NOT NULL,
  `tanggal` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hafalan`
--

INSERT INTO `hafalan` (`id_hafalan`, `surat`, `ayat`, `nisn`, `tanggal`) VALUES
(2, 'At-Tin', '1-4', 19829099, '19-08-2021'),
(10, 'Al-Kafh', '1-4', 19829002, '19-09-2021'),
(11, 'Al-Kafh', '5-8', 19829002, '19-09-2021'),
(13, 'At-Tin', '1-4', 19829022, '19-09-2021'),
(14, 'At-Tin', '5-7', 19829022, '19-09-2021'),
(15, 'Al-Kafh', '9-12', 19829002, '19-09-2021');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `id_kelas` int(11) NOT NULL,
  `nama_kelas` char(20) NOT NULL,
  `nip_guru` int(15) NOT NULL,
  `tahun_ajaran` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`id_kelas`, `nama_kelas`, `nip_guru`, `tahun_ajaran`) VALUES
(1, 'X - MIA 1', 1234789, '2020/2021'),
(2, 'X - MIA 2', 1234710, '2020/2021'),
(3, 'XI - MIA 1', 2244789, '2020/2021'),
(4, 'XI - MIA 2', 1234888, '2020/2021'),
(5, 'XII - MIA 1', 1234888, '2020/2021'),
(6, 'XII - MIA 2', 1234710, '2020/2021');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `nisn` int(11) NOT NULL,
  `nama_siswa` varchar(60) NOT NULL,
  `jk` char(20) NOT NULL,
  `hp` char(13) NOT NULL,
  `id_kelas` int(11) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `semester` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`nisn`, `nama_siswa`, `jk`, `hp`, `id_kelas`, `alamat`, `semester`) VALUES
(19829000, 'zain javvad', 'laki-laki', '089998333666', 3, 'jakarta selatan', 1),
(19829001, 'refal hadityo', 'laki-laki', '081117822769', 4, 'jakarta barat', 1),
(19829002, 'gio gino', 'laki-laki', '081137822000', 1, 'tangerang', 2),
(19829003, 'maman sutarman', 'laki-laki', '087737822769', 2, 'bogor', 2),
(19829004, 'zhea zihara', 'perempuan', '089998770000', 3, 'pondok indah', 2),
(19829022, 'zena dwi mentari', 'perempuan', '089937822769', 1, 'jakarta timur', 1),
(19829028, 'zendaya', 'perempuan', '088998753760', 2, 'joglo', 1),
(19829099, 'kevin wijaya', 'laki-laki', '081137822700', 5, 'bali', 2),
(19829444, 'marcus mario', 'laki-laki', '088998753711', 6, 'bandung', 1),
(19829905, 'shenna sinera', 'perempuan', '089900333666', 4, 'jogja', 2);

-- --------------------------------------------------------

--
-- Table structure for table `surat`
--

CREATE TABLE `surat` (
  `id_surat` int(11) NOT NULL,
  `nama_surat` char(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `surat`
--

INSERT INTO `surat` (`id_surat`, `nama_surat`) VALUES
(1, 'At-Tin'),
(2, 'Al-Kafh');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `nip_guru` char(30) NOT NULL,
  `password` char(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`nip_guru`, `password`) VALUES
('1234710', '123'),
('1234789', '0011'),
('1234888', '444'),
('2244789', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`nip_guru`);

--
-- Indexes for table `hafalan`
--
ALTER TABLE `hafalan`
  ADD PRIMARY KEY (`id_hafalan`);

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`id_kelas`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nisn`);

--
-- Indexes for table `surat`
--
ALTER TABLE `surat`
  ADD PRIMARY KEY (`id_surat`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`nip_guru`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hafalan`
--
ALTER TABLE `hafalan`
  MODIFY `id_hafalan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
