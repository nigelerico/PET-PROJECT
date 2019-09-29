<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\API\BaseController as BaseController;
use App\Http\Controllers\API\KeranjangController as KeranjangController;
use App\Transaksi;
use App\Keranjang;
use App\CartTransaksi;
use DB;
use Validator;

class APITransaksiController extends BaseController
{
    public function transaksi(Request $request)
    {
        $keranjang = new KeranjangController();
        $keranjang->cart($request);
        $user_id = $request->user_id;
        $random = 'EV-'.$this->random_word2(15);
        $randomKodeUnik = $this->random_kode_unik();
        $data_cart = Keranjang::where('user_id',$user_id)->get();
        date_default_timezone_set('Asia/Jakarta');

        $transaksi = Transaksi::create([
            'user_id'           => $user_id,
            'kode_transaksi'    => $random,
            'alamat'            => $request->alamat,
            'note_alamat'       => $request->note_alamat,
            'metode_transaksi'  => $request->metode_transaksi,
            'waktu_pengiriman'  => $request->waktu_pengiriman,
            'foto_transaksi'    => 'user/bukti.png',
            'ongkir'            => $request->ongkir,
            'kode_unik'         => $randomKodeUnik,
            'no_resi'           => 'Menunggu Pengiriman',
            'status_kedatangan' => 'belum sampai',

        ]);

        for($i = 0; $i < count($data_cart); $i++){
            CartTransaksi::create([
                'transaksi_id'  => $transaksi->id,
                'buku_id'       => $data_cart[$i]->buku_id,
                'jumlah_buku'   => $data_cart[$i]->jumlah_buku,
                'total_harga'   => $data_cart[$i]->total_harga,
            ]);

            $data_cart[$i]->delete();
        }

        return 'success';
    }

    public function getDetailTransaksi(Request $request)
    {
        $transaksi = DB::table('transaksi')
                    ->where('transaksi.id', $request->id)
                    ->leftJoin('users','transaksi.user_id','=','users.id')
                    ->leftJoin('cart_transaksi','transaksi.id','=','cart_transaksi.transaksi_id')
                    ->leftJoin('bukumobile','cart_transaksi.buku_id','=','bukumobile.id')
                    ->get();
        return $this->sendResponse($transaksi);
    }

    public function getAllTransaksi()
    {
        $transaksi = DB::table('users')
                    ->rightJoin('transaksi','transaksi.user_id','=','users.id')
                    ->get();
        return $this->sendResponse($transaksi);
    }

    public function onDelivery(Request $request)
    {
        $transaksi = Transaksi::findOrFail($request->transaksi_id);
        $transaksi->status_transaksi = 1; //sedang dikirim
        $transaksi->save();
        return "success";
    }


    public function complete(Request $request)
    {
        $transaksi = Transaksi::findOrFail($request->transaksi_id);
        $transaksi->status_transaksi = 2; //terkirim
        $transaksi->save();
        return "success";
    }


     public function datang(Request $request)
    {
        $transaksi = Transaksi::findOrFail($request->transaksi_id);
        $transaksi->status_kedatangan= "confirmed"; //terkirim
        $transaksi->save();
        return "success";
    }



     public function editTransaksi(Request $request)
    {
        $transaksi = Transaksi::findOrFail($request->id);
        $data = $request->all();
        $random = $this->random_word(20);
        $pathFile = $random.".png";

       if($transaksi->foto_transaksi != $data['foto_transaksi']){
            $file = base64_decode($request->foto_transaksi);
        }

            if($data['foto_transaksi'] != null){
                if($transaksi->foto_transaksi != $data['foto_transaksi']){
                    $img[0] = imagecreatefromstring($file);
                    $pathDatabase = 'transaksi/'.$pathFile;
                    $path = public_path() . '/img/'.$pathDatabase;
                    imagepng($img[0], $path);
                    //unlink(public_path() . '/img/'.$transaksi->foto);
                    $transaksi->foto_transaksi = $pathDatabase;
                }
            }
            
            $transaksi->save();
            $response['status'] = "success";
            return $this->sendResponse($response); 
          
    }

     public function random_word($id = 20){
        $pool = '1234567890abcdefghijkmnpqrstuvwxyz';
        
        $word = '';
        for ($i = 0; $i < $id; $i++){
            $word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
        }
        return $word; 
    }

       public function random_word2($id = 15){
        $pool = '1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        
        $word = '';
        for ($i = 0; $i < $id; $i++){
            $word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
        }
        return $word; 
    }


      public function random_kode_unik(){
        $word = rand(1,200);
        return $word; 
    }


     public function editnoResi(Request $request)
    {
        $transaksi = Transaksi::findOrFail($request->id);
        $data = $request->all();
     

         $validation = Validator::make($request->all(), [
            'no_resi'          => 'required|string|max:191',
            
        ]);   

        if($validation->fails()){
            $response['status'] = "failed";
            return $this->sendResponse($response); 
        }else{
            $transaksi->no_resi = $data['no_resi'];
            $transaksi->save();
            $response['status'] = "success";
            return $this->sendResponse($response); 
        }   
    }

     public function getOngkir(Request $request)
    {
        $data = DB::select( DB::raw("
            SELECT a.*, (a.jumlah_buku * SUM(total_harga)) as total from (
            SELECT bukumobile.*, SUM(jumlah_buku)as jumlah_buku, total_harga,buku_id,user_id FROM `keranjang` 
            LEFT JOIN bukumobile on keranjang.buku_id = bukumobile.id
            WHERE keranjang.user_id = '".$request->id."' 
            GROUP BY buku_id) as a group by nama "));
        return $this->sendResponse($data);
    }



}
