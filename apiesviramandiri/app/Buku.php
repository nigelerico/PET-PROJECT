<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Buku extends Model
{
    protected $table = 'buku';
    protected $fillable = [
        'nama', 'jumlah', 'penulis', 'harga', 'foto','kategori','ukuranbuku','kulit','tahunterbit','jmlhalaman','bahanisi','bahankulit','deskripsi',
    ];

    public function foto()
    {
        return '/img/' . $this->foto;
    }
}
