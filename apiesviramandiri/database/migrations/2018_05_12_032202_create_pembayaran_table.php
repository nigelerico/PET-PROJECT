<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePembayaranTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('pembayaran', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('user_id')->unsigned();
            $table->string('jenis');//ots atau transfer
            $table->string('kode')->nullable();
            $table->integer('total_harga')->unsigned();
            $table->string('foto_transaksi')->nullable();//foto untuk jika metode pembayarannya lewat transfer
            $table->boolean('status')->default(0);//status untuk sudah di bayar atau belum
            $table->boolean('isDeliver')->default(0);//status sudah terkirim atau belum
            $table->string('kode_belanja');
            $table->timestamps();

            $table->foreign('user_id')->references('id')->on('users')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('pembayaran');
    }
}
