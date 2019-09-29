<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateSayurmobileTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('sayurmobile', function (Blueprint $table) {
            $table->increments('id');
            $table->string('nama');
            $table->integer('jumlah');
            $table->integer('berat');
            $table->integer('harga');
            $table->string('foto');
            $table->string('kuantitas');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('sayurmobile');
    }
}
