<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/
Route::post('register', 'API\RegisterController@register');
Route::post('login', 'API\LoginController@login');
Route::post('user/edit','API\APIUserController@editUser');


Route::group(['prefix' => 'buku'], function(){
   // Route::get('/dijualbiji', 'API\JualBukuController@spesificBukuKategori');
    Route::get('/ongkoskirim', 'API\JualBukuController@ongkir');
    Route::get('/dijual', 'API\JualBukuController@bukumobile');
    Route::post('/search', 'API\JualBukuController@searchBuku');
    Route::post('/detailbuku', 'API\JualBukuController@spesificBuku');

});

Route::group(['prefix' => 'cart'], function(){
    Route::post('/','API\KeranjangController@cart');
    Route::post('/list','API\KeranjangController@getCart');
    Route::post('/carthold','API\KeranjangController@carthold');
    Route::post('/deleteCart','API\KeranjangController@deleteCart');
});

Route::group(['prefix' => 'transaksi'], function(){    
    Route::post('/', 'API\APITransaksiController@transaksi');
    Route::get('/list', 'API\APITransaksiController@getAllTransaksi');
    Route::post('/list/detail', 'API\APITransaksiController@getDetailTransaksi');
    Route::post('/onDelivery', 'API\APITransaksiController@onDelivery');
    Route::post('/complete', 'API\APITransaksiController@complete');
    Route::post('/editTransaksi', 'API\APITransaksiController@editTransaksi');
    Route::post('/confirm', 'API\APITransaksiController@datang');
    Route::post('/getOngkir', 'API\APIUserController@getOngkir');
   // Route::post('/editnoresi', 'API\APITransaksiController@editnoResi');
});

Route::middleware('auth:api')->group( function() {
    Route::get('bukushow', 'API\APIBukuController@show');
    Route::post('buku/tambah-buku', 'API\APIBukuController@adminTambahBuku');
    Route::post('buku/delete', 'API\JualBukuController@delete');
    Route::post('buku/detail', 'API\JualBukuController@spesificBuku');
    Route::post('buku/edit', 'API\APIBukuController@editBuku');
    Route::post('buku/gudang/search', 'API\APIBukuController@searchBuku');
    Route::get('user', 'API\APIUserAdminController@listUser');
    Route::post('user/delete', 'API\APIUserAdminController@deleteUser');
    Route::post('user/detail', 'API\APIUserAdminController@detailUser');
    Route::post('user/search', 'API\APIUserAdminController@searchUser');
    Route::post('transaksi/editnoresi', 'API\APITransaksiController@editnoResi');
});
