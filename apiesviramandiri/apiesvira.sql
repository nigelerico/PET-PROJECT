-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2019 at 07:26 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `apiesvira`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id` int(10) UNSIGNED NOT NULL,
  `nama` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `jumlah` int(11) NOT NULL,
  `penulis` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `harga` int(11) NOT NULL,
  `foto` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `kategori` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ukuranbuku` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `kulit` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tahunterbit` int(11) NOT NULL,
  `jmlhalaman` int(11) NOT NULL,
  `bahanisi` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bahankulit` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `deskripsi` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id`, `nama`, `jumlah`, `penulis`, `harga`, `foto`, `kategori`, `ukuranbuku`, `kulit`, `tahunterbit`, `jmlhalaman`, `bahanisi`, `bahankulit`, `deskripsi`, `created_at`, `updated_at`) VALUES
(15, 'aqua', 12, 'dr putra', 250000, 'sayur/mYq1vu93qHpyFefKqvssI5vz2UxU0YoFAsVPxxdl.png', 'sosial', '17,7 x 25 cm', 'Full Colour (6/0)', 2018, 100, 'hvs 80 gram', 'ggwp', 'aqua sama :3', '2019-04-23 23:47:40', '2019-05-02 20:29:02'),
(17, 'tes', 90000, 'nigel erico p', 250000, 'sayur/ky4k1WsH0A55pA5ZoJIcpCco5tNhQDArQKntDBJj.jpeg', 'agama', '17,6 x 25 cm', 'kulit', 1998, 178, 'hvs 70 gram', 'art cartoon', 'bagus', '2019-04-24 23:50:33', '2019-04-24 23:50:33'),
(18, 'shaltear', 123, '123', 56666, 'sayur/QkGvnNSPMeSNGocsiDR6FC70jOhVnhSrtyScJLmf.jpeg', 'sosial', '123', '12', 1989, 123, '12', '123', '123', '2019-05-04 06:57:54', '2019-05-04 06:57:54');

-- --------------------------------------------------------

--
-- Table structure for table `bukumobile`
--

CREATE TABLE `bukumobile` (
  `id` int(10) UNSIGNED NOT NULL,
  `nama` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `jumlah` int(11) NOT NULL,
  `penulis` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `harga` int(11) NOT NULL,
  `foto` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `kategori` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ukuranbuku` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `kulit` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tahunterbit` int(11) NOT NULL,
  `jmlhalaman` int(11) NOT NULL,
  `bahanisi` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bahankulit` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `deskripsi` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `bukumobile`
--

INSERT INTO `bukumobile` (`id`, `nama`, `jumlah`, `penulis`, `harga`, `foto`, `kategori`, `ukuranbuku`, `kulit`, `tahunterbit`, `jmlhalaman`, `bahanisi`, `bahankulit`, `deskripsi`, `created_at`, `updated_at`) VALUES
(5, 'Belajar Dari Pertunjukan Seni tradisional', 100, 'Agung Putra S.pd', 30400, 'sayur/4XDYK6gvkKghKgLHcDtEPf0IMRzHpp7aAkKhyaea.png', 'agama', '17,6 x 21 cm', 'Full Colour (4/0)', 2018, 76, 'hvs 80 gram', 'art cartoon 210 gr', 'Buku ini adalah ...', '2018-12-02 06:07:26', '2019-05-14 00:02:01'),
(7, 'Seri Revoulusi Mental : Ayo Kerja', 100, 'Siti Hanifah', 34000, 'sayur/IkhdCetGRNIyx7edhcZvtqrkicvd5RVoDRXTgc6E.png', 'sosial', '17,6 x 25 cm', 'Full Colour (4/0)', 2016, 68, 'hvs 80 gram', 'art cartoon 210 gr', 'Buku ini adalah..', '2018-12-02 06:07:36', '2019-05-14 01:08:14'),
(8, 'Mencegah perilaku menyimpang dan sikap anti sosial', 10, 'Agung Putra S.pd', 80000, 'sayur/7cDbdfmkVlSEU9zsQANPKvM8hsera9ih3Q4hygMV.jpeg', 'Agama', '17,5 x 21 cm', 'Full Colour (4/0)', 2011, 100, 'hvs 100 gram', 'art cartoon ', 'buku ini adalah...', '2019-05-13 21:49:42', '2019-05-13 23:43:06');

-- --------------------------------------------------------

--
-- Table structure for table `cart_transaksi`
--

CREATE TABLE `cart_transaksi` (
  `id` int(10) UNSIGNED NOT NULL,
  `transaksi_id` int(10) UNSIGNED NOT NULL,
  `buku_id` int(10) UNSIGNED NOT NULL,
  `jumlah_buku` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cart_transaksi`
--

INSERT INTO `cart_transaksi` (`id`, `transaksi_id`, `buku_id`, `jumlah_buku`, `total_harga`, `created_at`, `updated_at`) VALUES
(18, 16, 5, 1, 30400, '2019-05-19 17:21:27', '2019-05-19 17:21:27'),
(19, 16, 7, 1, 34000, '2019-05-19 17:21:27', '2019-05-19 17:21:27'),
(20, 17, 5, 1, 30400, '2019-05-19 17:23:19', '2019-05-19 17:23:19');

-- --------------------------------------------------------

--
-- Table structure for table `chatting`
--

CREATE TABLE `chatting` (
  `id` int(10) UNSIGNED NOT NULL,
  `pengirim_id` int(10) UNSIGNED NOT NULL,
  `penerima_id` int(10) UNSIGNED NOT NULL,
  `pesan` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `keranjang`
--

CREATE TABLE `keranjang` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `buku_id` int(10) UNSIGNED NOT NULL,
  `jumlah_buku` int(11) NOT NULL DEFAULT '0',
  `total_harga` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2018_05_12_031558_create_sayur_table', 1),
(8, '2016_06_01_000001_create_oauth_auth_codes_table', 2),
(9, '2016_06_01_000002_create_oauth_access_tokens_table', 2),
(10, '2016_06_01_000003_create_oauth_refresh_tokens_table', 2),
(11, '2016_06_01_000004_create_oauth_clients_table', 2),
(12, '2016_06_01_000005_create_oauth_personal_access_clients_table', 2),
(13, '2018_07_17_015017_create_sayurmobile_table', 2),
(25, '2018_05_12_031825_create_keranjang_table', 6),
(26, '2018_08_07_060953_create_transaksi_table', 7),
(27, '2018_08_14_015419_create_cart_transaksi_table', 7);

-- --------------------------------------------------------

--
-- Table structure for table `oauth_access_tokens`
--

CREATE TABLE `oauth_access_tokens` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `scopes` text COLLATE utf8mb4_unicode_ci,
  `revoked` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `expires_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `oauth_access_tokens`
--

INSERT INTO `oauth_access_tokens` (`id`, `user_id`, `client_id`, `name`, `scopes`, `revoked`, `created_at`, `updated_at`, `expires_at`) VALUES
('03f64002716a965e676770134cfdeeccdcc7172d325f8c34f713e29eff689a77972ac0d952bdc96c', 1, 1, 'sayurin', '[]', 0, '2018-09-08 08:29:41', '2018-09-08 08:29:41', '2019-09-08 15:29:41'),
('059b889b5a94e454cdc6377598f738218b4c9f2b59497b0c81510ec94e8fbf2d3248c913ea0c16da', 22, 3, 'sayurin', '[]', 0, '2019-05-13 22:37:34', '2019-05-13 22:37:34', '2020-05-14 05:37:34'),
('076fcafa7d787b9074a00b8709b0232d03870df50951f635d424e3009c79805b0d4b8af3fe2dd301', 1, 3, 'sayurin', '[]', 0, '2019-05-13 02:55:47', '2019-05-13 02:55:47', '2020-05-13 09:55:47'),
('08fb47f373b8e2f84f180550850f934fc489c207ccb1fe770e131d6104b960bcea72ffc81036fe28', 18, 3, 'sayurin', '[]', 0, '2019-05-13 21:49:33', '2019-05-13 21:49:33', '2020-05-14 04:49:33'),
('09b3988927defea63b3c96ca05b699062bc258f9f999635808c2b5476dd62d6c0456092b1ed932ef', 21, 3, 'sayurin', '[]', 0, '2019-05-12 00:02:43', '2019-05-12 00:02:43', '2020-05-12 07:02:43'),
('0c0b44e719703d3898997a595d9dfe00585a855c8de3c657d4acf650b1a774a9d3a3c57eee778e31', 1, 1, 'sayurin', '[]', 0, '2018-08-08 07:55:07', '2018-08-08 07:55:07', '2019-08-08 14:55:07'),
('114ae685da2882746f5cb4daf28968e32a2a76e0267e320d974c4d7f95225dbe8a01b9ac08008862', 9, 3, 'sayurin', '[]', 0, '2018-11-29 06:07:52', '2018-11-29 06:07:52', '2019-11-29 13:07:52'),
('123610f19392071ef18fe7b13a865379aa7855e91dead7bcc83c465ba0725a4277e5634c3f3f90db', 21, 3, 'sayurin', '[]', 0, '2019-05-13 02:24:45', '2019-05-13 02:24:45', '2020-05-13 09:24:45'),
('12489a51cda8c5ba2c4c2fcb35ec80a4580796b8661412f890991ff708c495bb011635d24a5f5955', 21, 3, 'sayurin', '[]', 0, '2019-05-12 00:02:43', '2019-05-12 00:02:43', '2020-05-12 07:02:43'),
('16dea153209a365f9c4f7d1610b70cb8fe4f0ccec6c2b0685f08cd905127e74110c40afdf1690c4e', 21, 3, 'sayurin', '[]', 0, '2019-05-13 02:24:45', '2019-05-13 02:24:45', '2020-05-13 09:24:45'),
('17482058e02b8e1c46a09d95b2b4c011cde5a0a721f7beb86d0fa85545d16585633caee17da42fc7', 1, 3, 'sayurin', '[]', 0, '2019-05-11 23:18:42', '2019-05-11 23:18:42', '2020-05-12 06:18:42'),
('18a656b831fd1d815100d252d8674262b981772710cbb4db445f1b14d5a28859dde9984f2823fbb1', 1, 1, 'sayurin', '[]', 0, '2018-09-07 07:43:05', '2018-09-07 07:43:05', '2019-09-07 14:43:05'),
('18a87b85cdacd3b7e862233e000660f103a91293bfe7418ac1ba4a2fcda3098bada9f16c0f2788a0', 18, 3, 'sayurin', '[]', 0, '2019-05-14 00:28:38', '2019-05-14 00:28:38', '2020-05-14 07:28:38'),
('19266309b657859bcce50c454785f7bd52f039ddcd727b73e41492241ad7e106baa7fd4556c79d7f', 2, 1, 'sayurin', '[]', 0, '2018-09-07 07:39:47', '2018-09-07 07:39:47', '2019-09-07 14:39:47'),
('1a19a1ffd3382193bffbf024287423525d053e0f7eec7987f3a29a9bc6ccb188e3adc6a2a7b95249', 1, 1, 'sayurin', '[]', 0, '2018-10-17 14:36:59', '2018-10-17 14:36:59', '2019-10-17 21:36:59'),
('1a2cf5af579d6e08f366107ccd47ed648b71abe90d096615ec479cdb3dfc74ad5f1f6a6de745ff18', 1, 1, 'sayurin', '[]', 0, '2018-09-08 09:15:43', '2018-09-08 09:15:43', '2019-09-08 16:15:43'),
('1cedeccb5215556259e9615d7f8765d6aa552ec0398791b9981284daeea750679e9746cbd505658b', 9, 3, 'sayurin', '[]', 0, '2018-11-30 06:22:08', '2018-11-30 06:22:08', '2019-11-30 13:22:08'),
('1eda92a9394cd1a7b19426d4ba36d380df7c8ffafda662a2d2d79095f77187d5148d52c65caeca1a', 9, 3, 'sayurin', '[]', 0, '2018-12-02 08:44:29', '2018-12-02 08:44:29', '2019-12-02 15:44:29'),
('21d3c9fe2b5e26ada7b6144e12a23cc8fb4994969a159501e2f0dd6cbb262ad4bdf865e9be41bad5', 17, 3, 'sayurin', '[]', 0, '2018-12-02 08:41:23', '2018-12-02 08:41:23', '2019-12-02 15:41:23'),
('227558622427b7ef40554535aef35ea941b74ac44e92e4cd0ba34bf049f6c81d48fdea7270270cc3', 1, 3, 'sayurin', '[]', 0, '2019-05-11 23:18:42', '2019-05-11 23:18:42', '2020-05-12 06:18:42'),
('26a998a392b0d20e433e54339b2122a4ffaa2b417399160662d18570c139459eedc1bb448b1b4ac1', 1, 1, 'sayurin', '[]', 0, '2018-08-06 10:01:30', '2018-08-06 10:01:30', '2019-08-06 17:01:30'),
('2842468d4ace7e7f248fcead5acddbea06ef1f614d1097f1ac078ee0fda7de13c1ddf1468ce47341', 1, 1, 'sayurin', '[]', 0, '2018-09-07 07:35:52', '2018-09-07 07:35:52', '2019-09-07 14:35:52'),
('2933268d6d89011346ee4a33768083e4f958e0d881a7d7df459edc59216bbb80a11895f29030fa70', 1, 1, 'sayurin', '[]', 0, '2018-09-07 15:58:21', '2018-09-07 15:58:21', '2019-09-07 22:58:21'),
('2da64646a9f0dec8d3fc1613092adb59f376b07cf098515b175e89ceab80651d7438d53333f80a14', 9, 3, 'sayurin', '[]', 0, '2018-11-29 06:27:29', '2018-11-29 06:27:29', '2019-11-29 13:27:29'),
('2e1ed8ae0d272bcc97a24b5fdae52758d02d7e9b283c47bb441d199307c7a137af7fc326cb027155', 17, 3, 'sayurin', '[]', 0, '2018-12-02 07:49:33', '2018-12-02 07:49:33', '2019-12-02 14:49:33'),
('31d3911556071ae0f52c1103491d07737274bd3fbc9d98d857f252b92e02788e9168eb6b5ae31db7', 1, 1, 'sayurin', '[]', 0, '2018-09-08 00:57:39', '2018-09-08 00:57:39', '2019-09-08 07:57:39'),
('334570456c21c8d8cca508beb202b739ee8f14f2db6118c64659553cab501ef0c92b02daf52aca40', 9, 3, 'sayurin', '[]', 0, '2018-12-02 08:41:42', '2018-12-02 08:41:42', '2019-12-02 15:41:42'),
('36ec19d5306cb73b04ae8aeffdc778b43728e4de34a20cceb62772b2bc31fa21a3c359730bd042b8', 1, 1, 'sayurin', '[]', 0, '2018-09-07 07:35:52', '2018-09-07 07:35:52', '2019-09-07 14:35:52'),
('37ca29742162a40abab09288eafe1a8ea1baa364c5f8d0c297f2dfa8a48d3a8c540693d2c720973a', 1, 3, 'sayurin', '[]', 0, '2019-05-12 08:06:46', '2019-05-12 08:06:46', '2020-05-12 15:06:46'),
('3841340e36406821b1d24f3a16cce376cc498fc8cfa99a903806e5168099f560f9243005afc02d36', 21, 3, 'sayurin', '[]', 0, '2019-05-04 06:36:12', '2019-05-04 06:36:12', '2020-05-04 13:36:12'),
('3a792810d859c9bdf4bf94d9d8d5950759840e5520323a2e8b107571436fcb85830381804b98c9df', 1, 1, 'sayurin', '[]', 0, '2018-09-08 01:22:25', '2018-09-08 01:22:25', '2019-09-08 08:22:25'),
('3e62e2847932417633cf105c6504452c0cd1befc801abbbde9a5baf7c774c91684754e0b74b6912d', 1, 3, 'sayurin', '[]', 0, '2018-11-29 05:52:30', '2018-11-29 05:52:30', '2019-11-29 12:52:30'),
('416428f60651a5fcaf05d8a9b7ea1c3d31fee1ac5e97e288db44699ff434793f538592b7219f2daf', 9, 3, 'sayurin', '[]', 0, '2018-12-02 06:54:10', '2018-12-02 06:54:10', '2019-12-02 13:54:10'),
('41e7512940322bcb8abee06c229d5945ce9c863222c2d059ff0ee18fb739d7da1af25bd026b93c7b', 21, 3, 'sayurin', '[]', 0, '2019-05-12 00:03:03', '2019-05-12 00:03:03', '2020-05-12 07:03:03'),
('420a35524ce06f296ff9962bc4e64593c78129e2370c9297bfc0b08372b76696e3c677a9179ea671', 22, 3, 'sayurin', '[]', 0, '2019-05-15 18:50:39', '2019-05-15 18:50:39', '2020-05-16 01:50:39'),
('4258c2a6c03b36de6417e7e0d57c0a9b2950ab06b999aea98d9ae7fe552e80e3a3331e7d5da9241d', 1, 3, 'sayurin', '[]', 0, '2018-11-29 03:48:51', '2018-11-29 03:48:51', '2019-11-29 10:48:51'),
('460f3448412ae91677f63d69c4275cc69a54ccf17c96876ffd6808cd37600af31a2851c6ce90a1a6', 1, 3, 'sayurin', '[]', 0, '2019-05-13 02:55:46', '2019-05-13 02:55:46', '2020-05-13 09:55:46'),
('47f6c20e339d52e939b4e4277d6cbb792dd9d194f20a46c97d5e21d473a14a12c9d390a6d5580a14', 1, 1, 'sayurin', '[]', 0, '2018-09-08 08:43:53', '2018-09-08 08:43:53', '2019-09-08 15:43:53'),
('48035c312c4e5ed2fc7e0768c7a7acffaf15076c0b839d8ac20ae5d3372737e872b78ec95cfdac4e', 1, 1, 'sayurin', '[]', 0, '2018-09-08 08:45:14', '2018-09-08 08:45:14', '2019-09-08 15:45:14'),
('5101e894b30b84bbac2c0fbdb27cfbea3ee13b7355a268ad61160e106fb6b7e14e776a5c1406d14f', 1, 1, 'sayurin', '[]', 0, '2018-09-07 16:01:17', '2018-09-07 16:01:17', '2019-09-07 23:01:17'),
('526b13a3825f2b7ecfe0c2109aa585863cf744e02d368705e5c6a61be06c5bc9984b05eb6abe1bd4', 21, 3, NULL, '[]', 0, '2019-05-04 06:36:11', '2019-05-04 06:36:11', '2020-05-04 13:36:11'),
('555992206c6fec0dcabd24c22a7c5e7dea84be328a5ece6b48b743aa637553338c63a1d18c467285', 1, 3, 'sayurin', '[]', 0, '2018-11-29 05:55:18', '2018-11-29 05:55:18', '2019-11-29 12:55:18'),
('588f60932c8d85ab9d341c88e92285cd030c17cf54dcc35234098962d29d3af11fe46dfd00af1105', 22, 3, 'sayurin', '[]', 0, '2019-05-19 10:23:45', '2019-05-19 10:23:45', '2020-05-19 17:23:45'),
('5b92d36bedb6b815272ed4c88eda15125fe7edbaf025ac8ebe234d27eee741d8f0673a170f59d732', 22, 3, 'sayurin', '[]', 0, '2019-05-12 00:04:11', '2019-05-12 00:04:11', '2020-05-12 07:04:11'),
('5c8fa828edb573ae8943120b064c1edc8358674f60424e4f2d2d549cece1fa235e5b68ccd3eee447', 9, 3, 'sayurin', '[]', 0, '2018-12-02 08:29:52', '2018-12-02 08:29:52', '2019-12-02 15:29:52'),
('5e5a76f3810dfcdb91be265f8f20a99171c9b73a106826b64c3e946d452941ecaf94d054290558a0', 9, 3, 'sayurin', '[]', 0, '2018-11-29 06:11:12', '2018-11-29 06:11:12', '2019-11-29 13:11:12'),
('5f5939787b283799b1dea12fa5bfb369e92e2c9ccae0820a23169eb4e93decd63cff79e0653e65f6', 22, 3, 'sayurin', '[]', 0, '2019-05-13 22:40:31', '2019-05-13 22:40:31', '2020-05-14 05:40:31'),
('5f86ed27352a064a4b6f5038f903031437945e42ae9401310dfd85e0219dd5a6f7187ab66262b2cf', 9, 3, 'sayurin', '[]', 0, '2018-11-29 06:43:37', '2018-11-29 06:43:37', '2019-11-29 13:43:37'),
('62ad493b147224805a03a53c147564505ce8bea30a4f25fbbaca3eaeb9aff43be92b87f5f583a405', 1, 1, 'sayurin', '[]', 0, '2018-08-08 07:59:31', '2018-08-08 07:59:31', '2019-08-08 14:59:31'),
('6baa6ad6af22c04e754ff6c88a18142dcf976742c72d56b59cea69b241b87755f00ee0c728dc2cae', 1, 3, 'sayurin', '[]', 0, '2019-05-12 06:55:51', '2019-05-12 06:55:51', '2020-05-12 13:55:51'),
('6ea8812616fdb074f34e4c1ae2f4cc17b6e0b90ca05b976f8b6241b5f9e07ab4dc5a32a3ecadbd75', 1, 3, 'sayurin', '[]', 0, '2019-05-12 06:55:50', '2019-05-12 06:55:50', '2020-05-12 13:55:50'),
('6f8990cd97975dd312373b9800a5085d2c0aeb1097ed3e5e8f2828b4d3c48f0a309a46520e4b5abb', 1, 1, 'sayurin', '[]', 0, '2018-10-17 14:51:40', '2018-10-17 14:51:40', '2019-10-17 21:51:40'),
('702517400afded2e39614fa642f28d23a024e698de0913267682aed572451a68bb9ee5e8d6b5cb05', 17, 3, 'sayurin', '[]', 0, '2018-12-02 07:18:16', '2018-12-02 07:18:16', '2019-12-02 14:18:16'),
('70606440fa572a5e14083e353a3665b190b7e286455a2aeb7cc1b666e85191c6dce1e790e21c32a5', 9, 3, 'sayurin', '[]', 0, '2018-12-02 08:47:32', '2018-12-02 08:47:32', '2019-12-02 15:47:32'),
('731421677b8aa1f88af84d620c28ecb198ef98215a2e47951623429c8b4330ac15b3eaa366df1101', 22, 3, 'sayurin', '[]', 0, '2019-05-15 18:50:40', '2019-05-15 18:50:40', '2020-05-16 01:50:40'),
('752a2102725fb6f2c52f49f6e37787342a99bdb86328f31f51e95a59da72a2a489eeb79100c5d651', 1, 1, 'sayurin', '[]', 0, '2018-09-07 15:43:39', '2018-09-07 15:43:39', '2019-09-07 22:43:39'),
('75915b3f9edff00ca8af50ce41baaf9134dc83c8736607195004236af103d96ce24f99cce3b0de76', 17, 3, 'sayurin', '[]', 0, '2018-12-02 08:39:11', '2018-12-02 08:39:11', '2019-12-02 15:39:11'),
('7840c4449f01483b4b71e4dd4b77678e6c0071e0e6eff63704f33d6ca86a146bb324ff81e504a303', 1, 1, 'sayurin', '[]', 0, '2018-09-08 08:46:28', '2018-09-08 08:46:28', '2019-09-08 15:46:28'),
('7bf960a80b7d88ee51eb5a56e733bff5256a659bb89a36e5464d4e9a4b55eaf63617d96795f1da48', 22, 3, 'sayurin', '[]', 0, '2019-05-14 01:04:12', '2019-05-14 01:04:12', '2020-05-14 08:04:12'),
('7c3d75bbc1b1879707ba6799a29223272f87ecdeeb1786bf8ae365b8b199dcfd9ae5326e719a1908', 9, 3, 'sayurin', '[]', 0, '2018-11-29 06:27:29', '2018-11-29 06:27:29', '2019-11-29 13:27:29'),
('7dfa43fa436aab15f6a199945f887e67d783df26ecb15e72076553abce655cbd9b18c6ce6c8821f0', 1, 1, 'sayurin', '[]', 0, '2018-08-08 10:37:57', '2018-08-08 10:37:57', '2019-08-08 17:37:57'),
('805f8ba97c32d170ddb8dde4df187e65b6f35f5663d09815a075214c0a2fa9a473af8394c6c13cb4', 18, 3, 'sayurin', '[]', 0, '2019-05-14 00:28:37', '2019-05-14 00:28:37', '2020-05-14 07:28:37'),
('809a5e3fe0b585142f3f62ac53a8ec2f97f5dc1594a66a29915b6be603c1ec36f153afbc8feabb6b', 17, 3, 'sayurin', '[]', 0, '2018-12-02 06:09:01', '2018-12-02 06:09:01', '2019-12-02 13:09:01'),
('861c2f990c02e32f0cf11ebf3cdcaa93f6bd6507323cbcd4b22eb536398b1f07778d04f79c301d45', 9, 3, 'sayurin', '[]', 0, '2018-11-29 06:43:37', '2018-11-29 06:43:37', '2019-11-29 13:43:37'),
('87d54a927b8df585946b6e243bc67906cb1c8decb3d212f7459e2d37bd3129087e8d60a781619b3b', 1, 1, 'sayurin', '[]', 0, '2018-10-17 14:31:39', '2018-10-17 14:31:39', '2019-10-17 21:31:39'),
('8e1cf071fd49d84f71ed060515c71d4a321c6c2e2b6d9fa808c3d7b0ab969429d8b122a279b29129', 1, 1, 'sayurin', '[]', 0, '2018-09-08 08:56:02', '2018-09-08 08:56:02', '2019-09-08 15:56:02'),
('8e929a3480555a001119895142e1e7e612353dd58bfeb514eb6bd5af8cce6a2e7df57dae6aaee3b2', 1, 1, 'sayurin', '[]', 0, '2018-09-08 09:38:07', '2018-09-08 09:38:07', '2019-09-08 16:38:07'),
('9072b325037aa76ddee062a36ee57edd6751beca110472785a483f99f6ae3b8c4b3ef4f47fddead6', 2, 3, 'sayurin', '[]', 0, '2018-11-29 07:23:22', '2018-11-29 07:23:22', '2019-11-29 14:23:22'),
('90ed583463b9bd73a72ceb42ec4281ce55bac94deb0b592589f2520f1c9fc5552acdd861fc4bb838', 22, 3, 'sayurin', '[]', 0, '2019-05-13 03:08:45', '2019-05-13 03:08:45', '2020-05-13 10:08:45'),
('913dd2af93c5c3ff52b1e4046024b4a8e8f33fd05dd1a813c76ae97394cc7273bdef0b555196fc50', 18, 3, 'sayurin', '[]', 0, '2019-05-13 23:41:59', '2019-05-13 23:41:59', '2020-05-14 06:41:59'),
('91df8539d2cae35a07d0310f09b9b47a6f8b72d40cb8a206260f5e25d2a9e1943096e12d47e8ef6a', 9, 3, 'sayurin', '[]', 0, '2018-12-02 08:49:53', '2018-12-02 08:49:53', '2019-12-02 15:49:53'),
('9334e6af1a158ed7d4da6772bc2e64955965cdf33c3375d53d497a6651bd6c3d73ac10d5744e82a9', 22, 3, 'sayurin', '[]', 0, '2019-05-13 21:54:31', '2019-05-13 21:54:31', '2020-05-14 04:54:31'),
('9780c8cf03a60d27e68fa7c9a3c6cf2a6c03311e7c71709846b7b395f14e4bf6c1655625cce240d0', 9, 3, 'sayurin', '[]', 0, '2018-12-02 08:39:39', '2018-12-02 08:39:39', '2019-12-02 15:39:39'),
('98226531ad4460a5894ee37be66a0c211e6dc17ed36fa7db1b60fca5edd9f703aa984f06ec19d601', 1, 1, 'sayurin', '[]', 0, '2018-09-08 01:14:19', '2018-09-08 01:14:19', '2019-09-08 08:14:19'),
('9951d72eb701de61d46b0dc8323fc81bbbda7545547b179c4ff9f24757888f67fd474bfa102f1c6c', 1, 1, 'sayurin', '[]', 0, '2018-09-07 15:56:24', '2018-09-07 15:56:24', '2019-09-07 22:56:24'),
('9a3a57e55b89715465edf50cd4d1a8316520fa6a82e12dbabfaa7bbbefe8a552ba1b8f79f15215a0', 22, 3, 'sayurin', '[]', 0, '2019-05-12 09:13:50', '2019-05-12 09:13:50', '2020-05-12 16:13:50'),
('9aa3192988d369122c38e2a4b24d2ec6f603df7c3df4875d14fb7c469ac39d7dae695a6d8f999a18', 22, 3, 'sayurin', '[]', 0, '2019-05-14 10:51:32', '2019-05-14 10:51:32', '2020-05-14 17:51:32'),
('9c740242a1f7cfc19a4984ee50431d28ca69cb459bf9e9c1100cca5ab75cf2ecf9cd5f100c47a283', 9, 3, 'sayurin', '[]', 0, '2018-11-29 06:30:21', '2018-11-29 06:30:21', '2019-11-29 13:30:21'),
('9e06449b0ea3cafee737ecc82cda858c28ed05359fe515991311fd7837f434ce53d0cc2efc208f37', 9, 3, 'sayurin', '[]', 0, '2018-11-29 06:01:01', '2018-11-29 06:01:01', '2019-11-29 13:01:01'),
('9e068fa6b0ccf36fc4f5bb0c7980e34ccb9b6966d7e7d7397709ec6d0541d9ec3b70351eaca0eb2c', 22, 3, 'sayurin', '[]', 0, '2019-05-13 02:59:06', '2019-05-13 02:59:06', '2020-05-13 09:59:06'),
('9f3345072a5e989570ebb21c7a737f244e956d79de85e8aa963373da94b892af22a724f4cc6e2d0e', 1, 1, 'sayurin', '[]', 0, '2018-09-07 07:36:47', '2018-09-07 07:36:47', '2019-09-07 14:36:47'),
('9f4e77384ad7bcfd74204c1e21b9a44625d8b0606983e52a7801c13a69c764c879b86747cc78ce5b', 9, 3, 'sayurin', '[]', 0, '2018-12-02 07:47:42', '2018-12-02 07:47:42', '2019-12-02 14:47:42'),
('a0337dd315e78713c4ef39e27a6ac168d6a9d720544ed9f6aca0e5551bf728d80669e539e2f37c6b', 2, 3, 'sayurin', '[]', 0, '2018-11-29 07:25:45', '2018-11-29 07:25:45', '2019-11-29 14:25:45'),
('a093215568106186d40f2ff8d1d1cadd983589fa3a6cf4ceabdc2f5b3c4567f2a632a0631b40cade', 1, 3, 'sayurin', '[]', 0, '2019-05-12 08:04:11', '2019-05-12 08:04:11', '2020-05-12 15:04:11'),
('a174e28b52dc7cbbff3342c1f70cd62b6f24bdae995315162d1e62d9e2ef356f43e8f978af05d6ff', 1, 1, 'sayurin', '[]', 0, '2018-10-17 14:31:39', '2018-10-17 14:31:39', '2019-10-17 21:31:39'),
('a28b017e6e49165f685887066a7be4d173abcde4b6d7c599aa550ce65b3f8fa7e05be2b56926f8f2', 18, 3, 'sayurin', '[]', 0, '2019-05-13 21:49:33', '2019-05-13 21:49:33', '2020-05-14 04:49:33'),
('a566edc9192dffbb547dc53e19618d942d46d8b463ec8be454c27516670b8863725eaf2d45e77b9d', 1, 1, 'sayurin', '[]', 0, '2018-08-06 10:12:24', '2018-08-06 10:12:24', '2019-08-06 17:12:24'),
('a5e0970fea7bf74f37131a351d35df549284c8ab631b85ff120d00080294007dba1b38e4cc30629b', 17, 3, 'sayurin', '[]', 0, '2018-12-02 07:45:24', '2018-12-02 07:45:24', '2019-12-02 14:45:24'),
('a6f95614f672c8e691c9de8c121cb2cf222af3afd99e14132be13f25d750ebb8b84b772c0d9c0526', 1, 1, 'sayurin', '[]', 0, '2018-09-07 15:51:07', '2018-09-07 15:51:07', '2019-09-07 22:51:07'),
('a9a39c41be5f2d7534c231eeb45a11e982e19bcf7da8477281a1e31b83994b7ae6706e9b7671f8b9', 9, 3, 'sayurin', '[]', 0, '2018-12-02 06:07:14', '2018-12-02 06:07:14', '2019-12-02 13:07:14'),
('aa605a54a1ca230cb87caec4b8a5543d73cc4bced57c2362fcbe1f813174ab542cb715cb3a2c9af6', 22, 3, 'sayurin', '[]', 0, '2019-05-14 10:51:32', '2019-05-14 10:51:32', '2020-05-14 17:51:32'),
('abae5626273c25c114fbb3ba5d2198e4c383c2b7c6dcb11e9a2b84a064c86ef69b342657bb4506e5', 1, 3, 'sayurin', '[]', 0, '2019-05-12 07:08:33', '2019-05-12 07:08:33', '2020-05-12 14:08:33'),
('abbdea4da529f0f9a533cbb5d55e8f0b7a5d115bb280f10a1d0c1de09c5ad1a9ae6a4b8b94989ab8', 22, 3, 'sayurin', '[]', 0, '2019-05-11 23:48:12', '2019-05-11 23:48:12', '2020-05-12 06:48:12'),
('afdb7ce52f2e0015cdbb8baf83b6fb53e9b3d818770378544ce19b1cc2b4f863cecd257fcd9509eb', 22, 3, 'sayurin', '[]', 0, '2019-05-11 23:49:58', '2019-05-11 23:49:58', '2020-05-12 06:49:58'),
('b133f10183e80b417ae9db3a4f31211b55e1f200d0989588a5ce4caff22482948577c93d64957797', 1, 1, 'sayurin', '[]', 0, '2018-09-07 15:56:04', '2018-09-07 15:56:04', '2019-09-07 22:56:04'),
('b335ff2f2092ba5786e3c86e24d39b6c83565846ebf7f2bd7912f483ac1a32f54db91cd7fe9761e6', 1, 1, 'sayurin', '[]', 0, '2018-10-25 03:07:39', '2018-10-25 03:07:39', '2019-10-25 10:07:39'),
('b7fb83dba9fc05906646483100eb30af7809f67c9455933fdb2ed66d3d7e01f2b8d20139d88c4be1', 1, 3, 'sayurin', '[]', 0, '2019-05-12 07:14:59', '2019-05-12 07:14:59', '2020-05-12 14:14:59'),
('b87ab90e037db4464b6ff1d9879c71287d7f4961eef8d507708335331d749448e5c65876c2268e82', 22, 3, 'sayurin', '[]', 0, '2019-05-12 09:13:50', '2019-05-12 09:13:50', '2020-05-12 16:13:50'),
('ba6587748c5a460d250af6ab688b034092c2b274e79e51ef1266c35cd927a5b28a4f3dba23d953b2', 9, 3, 'sayurin', '[]', 0, '2018-12-02 02:58:49', '2018-12-02 02:58:49', '2019-12-02 09:58:49'),
('bb6f0a349ad6c4de77d3917afd28caceee74dbaefd11bf394e74f27abfebe3e966d79e3d845fd6ac', 9, 3, 'sayurin', '[]', 0, '2018-11-29 06:58:38', '2018-11-29 06:58:38', '2019-11-29 13:58:38'),
('bf922f15016c923dc7f969c920221d35d1af9782a69a4896c17354db88775a930f9ec80d77283633', 1, 3, 'sayurin', '[]', 0, '2018-11-29 05:55:18', '2018-11-29 05:55:18', '2019-11-29 12:55:18'),
('c1641488f0d732270a6fbf3d1b2619ae47dd9fef40185db98a9b519d092337f75f1c990fe3710a10', 1, 1, 'sayurin', '[]', 0, '2018-09-07 07:55:41', '2018-09-07 07:55:41', '2019-09-07 14:55:41'),
('c2917ecb6844a6b0fd626bdaf8e6e6fa4e933169e128c0ccdc67d40362b37bd88c7f696dda60bba4', 1, 1, 'sayurin', '[]', 0, '2018-09-08 08:40:56', '2018-09-08 08:40:56', '2019-09-08 15:40:56'),
('c536ef48dab7f177de434083e45f6d766e0c2c52b89ba88addc1a1f89abea09f18a46456b34631c7', 1, 1, 'sayurin', '[]', 0, '2018-09-08 09:37:33', '2018-09-08 09:37:33', '2019-09-08 16:37:33'),
('c59f8e3b49f433a4b5b39f1c78c1188966d599742b0d451cc0537baf6fc518d062ccaff32dd84865', 22, 3, 'sayurin', '[]', 0, '2019-05-14 01:04:12', '2019-05-14 01:04:12', '2020-05-14 08:04:12'),
('c7e66f02bcfef3c4fa8f3665c476bf629db51f91727752af9c79606b99aef3a1bc4a9c3e1d5f2902', 1, 1, 'sayurin', '[]', 0, '2018-10-17 14:36:54', '2018-10-17 14:36:54', '2019-10-17 21:36:54'),
('cabaf3dc4e53cbc2de904d2c10f36010432733ff75f87033b009c0a75493e15a34076d6b94fe042c', 1, 1, 'sayurin', '[]', 0, '2018-09-07 16:07:16', '2018-09-07 16:07:16', '2019-09-07 23:07:16'),
('cafb14c8d060fb19ce7dae39900a110a16e7090bbfa11d74382f375b310ffdb38e836d8cf6cbe9c8', 22, 3, 'sayurin', '[]', 0, '2019-05-13 23:40:00', '2019-05-13 23:40:00', '2020-05-14 06:40:00'),
('cc1a35cb1c4e22dbd34b7ce802d69a0e6a960551287c7f124b13ae75d08facff045b60949a731b05', 1, 1, 'sayurin', '[]', 0, '2018-10-17 14:36:53', '2018-10-17 14:36:53', '2019-10-17 21:36:53'),
('ccd0564da9b3d2e8b6c25689ee786a151f3efebb293f493320666db07497f267d902b0cb8b1a2afb', 1, 1, 'sayurin', '[]', 0, '2018-10-17 14:36:53', '2018-10-17 14:36:53', '2019-10-17 21:36:53'),
('cd2fd0c45757a1116f137098b8ea477618dd2d76906564b5d51a05c583a59b8f1a531cc99c99f414', 22, 3, 'sayurin', '[]', 0, '2019-05-13 21:54:31', '2019-05-13 21:54:31', '2020-05-14 04:54:31'),
('cf858f8c7e31a32fecc3d0ee53a0b3c40ecc9601f3f9dfd5cff260c0a146644ed51db7355d87d450', 22, 3, 'sayurin', '[]', 0, '2019-05-19 10:23:45', '2019-05-19 10:23:45', '2020-05-19 17:23:45'),
('d209f5ef693370f6ea485727ba569984bd5eef357f405ec802298e373a2a2694b7c12ca16c528517', 1, 1, 'sayurin', '[]', 0, '2018-09-08 08:29:42', '2018-09-08 08:29:42', '2019-09-08 15:29:42'),
('d306811db0ec21b5538d53e4761ef49215fa08996b9d499db5add05c688900bfcf86e8c5dfcfdef3', 9, 3, 'sayurin', '[]', 0, '2018-11-29 07:27:03', '2018-11-29 07:27:03', '2019-11-29 14:27:03'),
('d38d1dce9f07509f40123b9f36634a3ec8b1c7738d71862d5116a65be4a2a9352e9846722dcc091a', 1, 1, 'sayurin', '[]', 0, '2018-09-07 15:38:16', '2018-09-07 15:38:16', '2019-09-07 22:38:16'),
('d793e97d7062da15224a18a06b03063cfcfe7f7fd55efe6df73ceb53bf42776b7cb742095c6f2b0e', 1, 1, 'sayurin', '[]', 0, '2018-09-08 01:26:15', '2018-09-08 01:26:15', '2019-09-08 08:26:15'),
('d7aa67ea0f981c4d51c8573374ad2b1bff562b394b4cb05738ed7c1b32aff7fd0edcf44f7287df07', 1, 1, 'sayurin', '[]', 0, '2018-09-08 08:34:30', '2018-09-08 08:34:30', '2019-09-08 15:34:30'),
('d8caa9b41c252a48f936ae5885516db13ca7c973990172fb2653405daafaf09af8bfb484b1f376af', 22, 3, 'sayurin', '[]', 0, '2019-05-13 22:37:34', '2019-05-13 22:37:34', '2020-05-14 05:37:34'),
('dbc59ebdd166999f538e2b97a9f9c4e55eeafbb2e66718afed9c5763f3d035727d8a9cfe275438c1', 1, 1, 'sayurin', '[]', 0, '2018-08-07 01:04:42', '2018-08-07 01:04:42', '2019-08-07 08:04:42'),
('e075347c8dc7ea5d6382654bd110dd222da014f7d76ed2f74049a6466951f5df77935ea7e0a87d93', 22, 3, 'sayurin', '[]', 0, '2019-05-13 03:08:45', '2019-05-13 03:08:45', '2020-05-13 10:08:45'),
('e221be61d178c08ccd618b7be6bd17ba6512803a9192761e376db9c598c514d92a719d86a8127fd1', 1, 1, 'sayurin', '[]', 0, '2018-09-08 01:28:38', '2018-09-08 01:28:38', '2019-09-08 08:28:38'),
('e4437d03dc39f1967809d41ca07e8d43c86257939893f7b235d1237d3482056560149df9fc88698b', 1, 3, 'sayurin', '[]', 0, '2019-05-12 09:15:06', '2019-05-12 09:15:06', '2020-05-12 16:15:06'),
('e4cf435b6d6abddc3a1526151c45ecdbd493a3ea916e3fa92832c939df2add30d5977fb56e56b7dc', 2, 1, 'sayurin', '[]', 0, '2018-09-08 09:44:14', '2018-09-08 09:44:14', '2019-09-08 16:44:14'),
('e555b482e391287e3532f1179c5407d1dc98f2c2f6b21851a366a6601d4a236a031899b8dac2a6a5', 1, 3, 'sayurin', '[]', 0, '2019-05-10 21:50:46', '2019-05-10 21:50:46', '2020-05-11 04:50:46'),
('e6eb669a4de849d5e9e7e4acb0793eb9617cc1347d38b39cf00adf3bfc174617468f648735282651', 22, 3, 'sayurin', '[]', 0, '2019-05-14 00:47:33', '2019-05-14 00:47:33', '2020-05-14 07:47:33'),
('ed4c70afcd2e484e333a91405d22d28366e94fd24bc1bf4d27898c14601369574e19a3556b87e970', 22, 3, 'sayurin', '[]', 0, '2019-05-14 11:32:06', '2019-05-14 11:32:06', '2020-05-14 18:32:06'),
('f22d8f6473a3f55d8dd994fd031c5383c0c34d2d19aa96d7384df1742233f03c0cb604d5958a256c', 1, 3, 'sayurin', '[]', 0, '2018-11-29 05:52:30', '2018-11-29 05:52:30', '2019-11-29 12:52:30'),
('f38589104c8fe4c1c66426b736ba7e5ecf0d99af52a9d9293d35e2fc4330e4cd6b8b67386f080249', 22, 3, 'sayurin', '[]', 0, '2019-05-14 00:47:33', '2019-05-14 00:47:33', '2020-05-14 07:47:33'),
('f932c0ce816521aae9c499399b993b1ff6dd1d77b4183cedc80d12ff747471aa14816f1cda3aa494', 17, 3, 'sayurin', '[]', 0, '2018-12-02 08:48:40', '2018-12-02 08:48:40', '2019-12-02 15:48:40'),
('f9ec264b8f3e6ebfc3b06c1a3970707860269249b79ad988e547cc7ba01e6840e59becaa1e78458a', 1, 1, 'sayurin', '[]', 0, '2018-10-25 03:07:39', '2018-10-25 03:07:39', '2019-10-25 10:07:39'),
('fa69bfa11c04e829fb15e358ebc26573873ae98e41b05d1ff5116a49ff7a4658cd1cab12d6f84891', 9, 3, 'sayurin', '[]', 0, '2018-11-29 07:24:32', '2018-11-29 07:24:32', '2019-11-29 14:24:32'),
('fc4197e926737f0432f28178c09dfdd75ecea2a3dcf8f900d35304b47f219de520f14ebe266e707d', 9, 3, 'sayurin', '[]', 0, '2018-12-02 07:43:35', '2018-12-02 07:43:35', '2019-12-02 14:43:35'),
('fe56bf85a316c10e66a0e2d781ad8f2fe960409a8b706b097e1d1f05f1a0a307d102ebbd9a1e218b', 1, 1, 'sayurin', '[]', 0, '2018-09-08 00:57:39', '2018-09-08 00:57:39', '2019-09-08 07:57:39');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_auth_codes`
--

CREATE TABLE `oauth_auth_codes` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `scopes` text COLLATE utf8mb4_unicode_ci,
  `revoked` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_clients`
--

CREATE TABLE `oauth_clients` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `secret` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `redirect` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `personal_access_client` tinyint(1) NOT NULL,
  `password_client` tinyint(1) NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `oauth_clients`
--

INSERT INTO `oauth_clients` (`id`, `user_id`, `name`, `secret`, `redirect`, `personal_access_client`, `password_client`, `revoked`, `created_at`, `updated_at`) VALUES
(1, NULL, 'Sayurin Personal Access Client', 'BDL82nOfOuXqnIQW0sPaNdk3FWNZCJk3D6bl7GKw', 'http://localhost', 1, 0, 0, '2018-08-06 08:22:05', '2018-08-06 08:22:05'),
(2, NULL, 'Sayurin Password Grant Client', 'mriRFJfahTjCURE0KYt6I5YMQPZqysjGGn8LDWHJ', 'http://localhost', 0, 1, 0, '2018-08-06 08:22:06', '2018-08-06 08:22:06'),
(3, NULL, 'Sayurin Personal Access Client', '4LLblVjvjhGXV6x0r5sOQDLKolUk6sQHyMuZ7vBq', 'http://localhost', 1, 0, 0, '2018-11-29 03:48:06', '2018-11-29 03:48:06'),
(4, NULL, 'Sayurin Password Grant Client', 's7s4bRzaMoaxtzs7obtGfvG5p5RysvyOPpKTKTBQ', 'http://localhost', 0, 1, 0, '2018-11-29 03:48:06', '2018-11-29 03:48:06');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_personal_access_clients`
--

CREATE TABLE `oauth_personal_access_clients` (
  `id` int(10) UNSIGNED NOT NULL,
  `client_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `oauth_personal_access_clients`
--

INSERT INTO `oauth_personal_access_clients` (`id`, `client_id`, `created_at`, `updated_at`) VALUES
(1, 1, '2018-08-06 08:22:06', '2018-08-06 08:22:06'),
(2, 3, '2018-11-29 03:48:06', '2018-11-29 03:48:06');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_refresh_tokens`
--

CREATE TABLE `oauth_refresh_tokens` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `access_token_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `jenis` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `kode` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `total_harga` int(10) UNSIGNED NOT NULL,
  `foto_transaksi` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `isDeliver` tinyint(1) NOT NULL DEFAULT '0',
  `kode_belanja` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pengiriman`
--

CREATE TABLE `pengiriman` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `pembayaran_id` int(10) UNSIGNED NOT NULL,
  `alamat` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `kode_transaksi` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `alamat` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `note_alamat` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status_transaksi` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `metode_transaksi` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `waktu_pengiriman` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `foto_transaksi` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `user_id`, `kode_transaksi`, `alamat`, `note_alamat`, `status_transaksi`, `metode_transaksi`, `waktu_pengiriman`, `foto_transaksi`, `created_at`, `updated_at`) VALUES
(16, 22, 'EV-16O95FI4ZGOOTO4', 'Jl. Teluk Pacitan No.14, Arjosari, Kec. Blimbing, Kota Malang, Jawa Timur 65126, Indonesia', 'tes', '0', 'TRANSFER MANDIRI', '23-05-2019, 09.00 WIB', 'transaksi/x4fgqf9shrpenanstfuw.png', '2019-05-19 17:21:27', '2019-05-19 10:22:37'),
(17, 22, 'EV-43WLM04X4DD9EUD', 'Amphitheatre Pkwy, Mountain View, CA 94043, USA', 'tes', '0', 'CASH ON DELIVERY', '22-05-2019, 09.00 WIB', 'user/bukti.png', '2019-05-19 17:23:19', '2019-05-19 17:23:19');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nomor_telepon` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `foto` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tipe` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `nomor_telepon`, `password`, `foto`, `tipe`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'suastikadinata', 'suastikaadinata@gmail.com', '0851112222', '$2y$10$TYVQWdegvScWVekFzVCq5ubGyOuOJSM59pmEZMwqWEOnJLOQTa8cy', 'user/user-icon.png', 'admin', 'iUp5mZOJlL4IGv3rNX3NTABlAnkPpSHvnpd0E0Kk0UTMN1pncPKsDmrjoim8', '2018-06-02 04:15:41', '2018-06-02 04:15:41'),
(9, 'halo edit', 'halo@gmail.com', '44444', '$2y$10$ZRmdy8reY9dtkgYxr0fe0.fnLfH0xicje2r9FNbmlsUucYhvd1GH6', 'user/4f01rt27xcq1pv9vwz9b.png', 'admin', NULL, '2018-11-21 14:35:37', '2018-12-02 08:50:34'),
(16, 'adi', 'adi@gmail.com', '123', '$2y$10$lH.E849tyqmio0qF20dVzOxG6o.wWlV26rhuayy4O7a6pZHidBQNa', 'user/user-icon.png', 'admin', NULL, '2018-11-21 15:00:16', '2018-11-21 15:00:16'),
(17, 'budi', 'budi@gmail.com', '1234567', '$2y$10$eNg2X39vdmIW//PuqZYlLOHPoVZsvDOJETLQEZIAUECB5nzOpoC.u', 'user/y2qi1e44hsgyh446nitn.png', 'user', NULL, '2018-12-02 06:08:39', '2018-12-02 08:48:49'),
(18, 'nigel erico pangestu', 'nigelerico1@gmail.com', '089654252938', '$2y$10$i56MUp/Vo.RmTgkk.ejSO.MHmSMn4afM236M2sRm966K1TxgskSyO', 'user/user-icon.png', 'admin', 'LQGjJhRTWkpetyriCSIuQNq7p40UZaKF1ASfswWF1bGNu2ztcmSMJl4nDWvu', '2019-04-14 20:22:15', '2019-04-14 20:22:15'),
(19, 'husen', 'husen@gmail.com', '54535534', '$2y$10$vidJtVfJVjZwjQ3PJTLCmez.FH0RWOd/JEK6wWaOqDngKlEdm0lSK', 'user/user-icon.png', 'user', NULL, '2019-04-24 06:36:39', '2019-04-24 06:36:39'),
(20, 'nigel2', 'erico@yahoo.com', '9034809248209', '$2y$10$.KJdewLcbK8GiIJW60nK1u8V5scG3wNErrIEEP6LsQ4uV0wAPOivm', 'user/user-icon.png', 'admin', 'XPzPFlRAmrD7mC8TlbDr1iEaNIH24eBzROoG4fssY9vHC82q5hvAC7FEdAXI', '2019-04-24 06:37:11', '2019-04-24 06:37:11'),
(21, 'gel@gmail.com', 'gel@gmail.com', '123', '$2y$10$rGktykcf44iYgqynvtDLOuf9uBdHcIZqGfWEmWANBN97jrXB8GaEW', 'user/user-icon.png', 'user', NULL, '2019-05-04 06:36:00', '2019-05-04 06:36:00'),
(22, 'putra', 'put@gmail.com', '08721123123', '$2y$10$1TIvt7v7/km28vVQhaKaCOLgdRDgVHVYkRLAvGi0yJEYANE.RYah.', 'user/user-icon.png', 'user', NULL, '2019-05-11 23:47:47', '2019-05-14 11:10:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bukumobile`
--
ALTER TABLE `bukumobile`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart_transaksi`
--
ALTER TABLE `cart_transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cart_transaksi_transaksi_id_foreign` (`transaksi_id`),
  ADD KEY `cart_transaksi_buku_id_foreign` (`buku_id`) USING BTREE;

--
-- Indexes for table `chatting`
--
ALTER TABLE `chatting`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chatting_pengirim_id_foreign` (`pengirim_id`),
  ADD KEY `chatting_penerima_id_foreign` (`penerima_id`);

--
-- Indexes for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `keranjang_user_id_foreign` (`user_id`),
  ADD KEY `keranjang_buku_id_foreign` (`buku_id`) USING BTREE;

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `oauth_access_tokens`
--
ALTER TABLE `oauth_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_access_tokens_user_id_index` (`user_id`);

--
-- Indexes for table `oauth_auth_codes`
--
ALTER TABLE `oauth_auth_codes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `oauth_clients`
--
ALTER TABLE `oauth_clients`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_clients_user_id_index` (`user_id`);

--
-- Indexes for table `oauth_personal_access_clients`
--
ALTER TABLE `oauth_personal_access_clients`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_personal_access_clients_client_id_index` (`client_id`);

--
-- Indexes for table `oauth_refresh_tokens`
--
ALTER TABLE `oauth_refresh_tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_refresh_tokens_access_token_id_index` (`access_token_id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pembayaran_user_id_foreign` (`user_id`);

--
-- Indexes for table `pengiriman`
--
ALTER TABLE `pengiriman`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pengiriman_user_id_foreign` (`user_id`),
  ADD KEY `pengiriman_pembayaran_id_foreign` (`pembayaran_id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaksi_user_id_foreign` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buku`
--
ALTER TABLE `buku`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `bukumobile`
--
ALTER TABLE `bukumobile`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `cart_transaksi`
--
ALTER TABLE `cart_transaksi`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `chatting`
--
ALTER TABLE `chatting`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `keranjang`
--
ALTER TABLE `keranjang`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `oauth_clients`
--
ALTER TABLE `oauth_clients`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `oauth_personal_access_clients`
--
ALTER TABLE `oauth_personal_access_clients`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pengiriman`
--
ALTER TABLE `pengiriman`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart_transaksi`
--
ALTER TABLE `cart_transaksi`
  ADD CONSTRAINT `cart_transaksi_sayur_id_foreign` FOREIGN KEY (`buku_id`) REFERENCES `bukumobile` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `cart_transaksi_transaksi_id_foreign` FOREIGN KEY (`transaksi_id`) REFERENCES `transaksi` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `chatting`
--
ALTER TABLE `chatting`
  ADD CONSTRAINT `chatting_penerima_id_foreign` FOREIGN KEY (`penerima_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `chatting_pengirim_id_foreign` FOREIGN KEY (`pengirim_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD CONSTRAINT `keranjang_sayur_id_foreign` FOREIGN KEY (`buku_id`) REFERENCES `bukumobile` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `keranjang_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD CONSTRAINT `pembayaran_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `pengiriman`
--
ALTER TABLE `pengiriman`
  ADD CONSTRAINT `pengiriman_pembayaran_id_foreign` FOREIGN KEY (`pembayaran_id`) REFERENCES `pembayaran` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `pengiriman_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
