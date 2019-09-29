<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Pembayaran extends Model
{
    protected $table = 'pembayaran';
    protected $fillable = [
        'user_id', 'jenis', 'kode', 'total_harga', 'foto_transaksi', 'status', 'isDeliver', 'kode_belanja',
    ];

    public function keranjang()
    {
        return $this->hasMany(Keranjang::class);
    }

    public function sayur()
    {
        return $this->hasOne(Sayur::class);
    }

    public function user(){
        return $this->belongsTo(User::class);
    }

    public function status()
    {
        $this->status = 1;
        $this->save();
    }

    public function isDeliver()
    {
        $this->isDeliver = 1;
        $this->save();
    }
}
