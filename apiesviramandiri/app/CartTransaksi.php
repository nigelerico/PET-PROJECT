<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class CartTransaksi extends Model
{
    protected $table = 'cart_transaksi';
    protected $fillable = [
        'transaksi_id', 'buku_id', 'jumlah_buku', 'total_harga'
    ];
}
