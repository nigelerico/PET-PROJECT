<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Auth::routes();
Route::get('/logout', 'Auth\LoginController@logout');

Route::get('/', 'HomeController@home');

Route::group(['prefix' => 'manage-buku'], function(){
    Route::get('/', 'BukuController@manageBuku');
    Route::get('/tambah-buku', 'BukuController@tambahBuku');
    Route::post('/tambah-buku/tambah', 'BukuController@save');
    Route::get('/search', 'BukuController@searchBuku');
    Route::get('/json', 'BukuController@BukuJson');
    Route::get('/detail-buku/{id}', 'BukuController@detailBuku');
    Route::get('/edit-buku/{id}', 'BukuController@editBuku');
    Route::post('/edit-buku/simpan', 'BukuController@simpanPerbaharuan');
    Route::post('/delete/{id}', 'BukuController@delete');

});

Route::group(['prefix' => 'manage-transaksi'], function(){
    Route::get('/', 'TransaksiController@manageTransaksi');
    Route::get('/detail-transaksi/{id}', 'TransaksiController@detilTransaksi');
    Route::get('/belumbayar', 'TransaksiController@belumBayar');
    Route::get('/sudahbayar', 'TransaksiController@sudahBayar');
    Route::post('/edit-t/simpan', 'TransaksiController@editStatus');


});

Route::group(['prefix' => 'manage-user'], function(){
    Route::get('/', 'UserController@manageUser');
    Route::get('/search', 'UserController@search');
    Route::post('/delete/{id}', 'UserController@delete');
    Route::get('/tambah-user', 'UserController@tambahUser');
    Route::post('/tambah-user/simpan', 'UserController@save');
});

Route::get('/chat', 'UserController@chatUser');


