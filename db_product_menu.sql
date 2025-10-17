-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 17, 2025 at 05:03 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_product_menu`
--

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(12,2) NOT NULL,
  `category` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `category`) VALUES
('P001', 'Kopi Hitam', 15000.00, 'Minuman'),
('P002', 'Roti Coklat', 12000.00, 'Makanan'),
('P003', 'Susu Segar', 18000.00, 'Minuman'),
('P004', 'Nasi Goreng', 25000.00, 'Makanan');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `kategori` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id`, `nama`, `harga`, `kategori`) VALUES
('KOO08438', 'Kopiko', 34385, 'Arabica'),
('KOP002', 'Kopi Toraja', 30000, 'Arabica'),
('KOP003', 'Kopi Lampung', 25000, 'Robusta'),
('KOP0034', 'Kopi Luwak', 230000, 'Arabica'),
('KOP0045', 'Kopi Kapal Api', 2300000, 'Arabica'),
('KOP005', 'Kopi Kintamani', 32000, 'Arabica'),
('KOP006', 'Kopi Flores Bajawa', 37000, 'Arabica'),
('KOP007', 'Kopi Aceh Ulee Kareng', 28000, 'Robusta'),
('KOP008', 'Kopi Sidikalang', 26000, 'Robusta'),
('KOP009', 'Kopi Mandailing', 34000, 'Arabica'),
('KOP010', 'Kopi Bali Pupuan', 36000, 'Arabica'),
('KOP011', 'Kopi Jawa Preanger', 33000, 'Arabica'),
('KOP012', 'Kopi Sumatera Lintong', 31000, 'Arabica'),
('KOP013', 'Kopi Bengkulu Curup', 24000, 'Robusta'),
('KOP014', 'Kopi Enrekang Kalosi', 38000, 'Arabica'),
('KOP015', 'Kopi Temanggung', 27000, 'Robusta'),
('KOP016', 'Kopi Wamena Papua', 42000, 'Arabica'),
('KOP017', 'Kopi Kerinci', 39000, 'Arabica'),
('KOP018', 'Kopi Bangka Belitung', 26000, 'Robusta'),
('KOP019', 'Kopi Garut', 30000, 'Arabica'),
('KOP020', 'Kopi Rangsang Meranti', 41000, 'Liberica');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
