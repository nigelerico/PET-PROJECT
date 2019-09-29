<?php

namespace App;

use Laravel\Passport\HasApiTokens;
use Illuminate\Notifications\Notifiable;
use Illuminate\Foundation\Auth\User as Authenticatable;

class User extends Authenticatable
{
    use HasApiTokens, Notifiable;

    protected $fillable = [
        'name', 'email', 'nomor_telepon','alamat_user','id_kota','password', 'foto', 'tipe', 'token', 
    ];

    protected $hidden = [
        'password', 'remember_token',
    ];

    public function isAdmin()
    {
        return $this->tipe == 'admin';
    }

    public function foto()
    {
        return '/img/' . $this->foto;
    }

    public function karanjang()
    {
        return $this->hasMany(Keranjang::class);
    }

    public function pembayaran()
    {
        return $this->hasMany(Pembayaran::class);
    }

    public function pengiriman()
    {
        return $this->hasMany(Pengiriman::class);
    }

    public function chatting()
    {
        return $this->hasMany(Chatting::class);
    }
}
