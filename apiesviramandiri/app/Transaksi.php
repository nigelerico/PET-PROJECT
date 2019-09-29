<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Transaksi extends Model
{
    protected $table = 'transaksi';
    protected $fillable = [
        'user_id', 'kode_transaksi', 'buku_id', 'jumlah', 'total_harga', 'alamat', 'note_alamat', 'status_transaksi', 'metode_transaksi', 'foto_transaksi', 'waktu_pengiriman', 'ongkir','kode_unik','no_resi','status_kedatangan',
    ];
    public function foto_transaksi()
    {
      return '/img/' . $this->foto_transaksi;
    }
}
