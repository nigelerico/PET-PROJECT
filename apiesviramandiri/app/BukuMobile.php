<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class BukuMobile extends Model
{
    protected $table = 'bukumobile';
    protected $fillable = [
       'id', 'nama', 'jumlah', 'penulis', 'harga', 'foto','berat','kategori','ukuranbuku','kulit','tahunterbit','jmlhalaman','bahanisi','bahankulit','halisi','deskripsi',
    ];
    public function foto()
    {
        return '/img/' . $this->foto;
    }
}
