<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\API\BaseController as BaseController;
use App\Keranjang;
use App\BukuMobile;
use DB;

class KeranjangController extends BaseController
{
    public function cart(Request $request)
    {
        $data_cart = Keranjang::all();
        $user_id = $request->user_id;
        $buku_cart = $request->buku_id;
        $jumlah = $request->jumlah;

        for($i = 0; $i < count($data_cart); $i++){
            if($data_cart[$i]->user_id == $user_id){
                $data_cart[$i]->delete();
            }
        }


        for($i = 0; $i < count($buku_cart); $i++){
            $buku = BukuMobile::findOrFail($buku_cart[$i]);
            $total_harga[$i] = $jumlah[$i] * $buku->harga;

            Keranjang::create([
                'user_id'           => $user_id,
                'buku_id'           => $buku->id,
                'jumlah_buku'       => $jumlah[$i],
                'total_harga'       => $total_harga[$i],
            ]);
        }

        return "success";
    }

    public function getCart(Request $request)
    {
        // $data = DB::table('keranjang')
        //         ->where('user_id', $request->id)
        //         ->leftJoin('bukumobile','bukumobile.id','=','keranjang.buku_id')
        //         ->get();


        $data = DB::select( DB::raw("
            SELECT a.*, (a.jumlah_buku * SUM(total_harga)) as total from (
            SELECT bukumobile.*, SUM(jumlah_buku)as jumlah_buku, total_harga,buku_id,user_id FROM `keranjang` 
            LEFT JOIN bukumobile on keranjang.buku_id = bukumobile.id
            WHERE keranjang.user_id = '".$request->id."' 
            GROUP BY buku_id) as a group by nama "));
        return $this->sendResponse($data);
    }


    public function deleteCart(Request $request)
    {
       //$keranjang = Keranjang::findOrFail($request->buku_id);
        $data_cart = Keranjang::where('buku_id',$request->id)->where('user_id', $request->user_id);
       // $user_id = $request->user_id;

        //if($data_cart->user_id == $user_id){
        $data_cart->delete();
          //  }

       //  DB::table('keranjang')

       //  ->where('buku_id',$request->id)->delete();
       // $keranjang->delete();
       return "sukses";
    }

  public function carthold(Request $request)
    {
        $data_cart = Keranjang::all();
        $user_id = $request->user_id;
        $buku_id = $request->buku_id;
        $jumlah_buku = $request->jumlah_buku;
        $total_harga = $request->total_harga;
  
   
            Keranjang::create([
                'user_id'           => $user_id,
                'buku_id'           => $buku_id,
                'jumlah_buku'       => $jumlah_buku,
                'total_harga'       => $total_harga,
            ]);
        

        return "success insert cart";
    }

  

}
