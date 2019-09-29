<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Chatting extends Model
{
    protected $table = 'chatting';
    protected $fillable = [
        'pengirim_id', 'penerima_id', 'pesan',
    ];

    public function user()
    {
        return $this->belongsTo(User::class);
    }
}
