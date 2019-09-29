<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\API\BaseController as BaseController;
use App\BukuMobile;
use App\Ongkir;
use DB;


class JualBukuController extends BaseController
{
    public function bukumobile()
    {
        $bukumobile = BukuMobile::all();
        return $this->sendResponse($bukumobile);
    }


public function ongkir()
    {
        $ongkir = Ongkir::all();
        return $this->sendResponse($ongkir);
    }


    public function delete(Request $request)
    {
    	$data = BukuMobile::findOrFail($request->id);
    	$data->delete();
    	return "sukses";
    }

    public function spesificBuku(Request $request)
    {
     // $data = DB::select( DB::raw("
     //        SELECT a.*, (a.jumlah_buku * SUM(total_harga)) as total from (
     //        SELECT bukumobile.*, SUM(jumlah_buku)as jumlah_buku, total_harga,buku_id,user_id FROM `keranjang` 
     //        RIGHT JOIN bukumobile on keranjang.buku_id = bukumobile.id
     //        WHERE bukumobile.id = '".$request->id."' 
     //        GROUP BY buku_id) as a group by nama "));

        $data = BukuMobile::findOrFail($request->id);
        return $this->sendResponse($data);
    	
    }

    public function searchBuku(Request $request)
    {
        $keyword = $request->keyword;
        $search = BukuMobile::where('nama',"LIKE","%$keyword%")->paginate(20);
        return $this->sendResponse($search);
    }

     public function spesificBukuKategori(Request $request)
    {
        $data = BukuMobile::where('kategori','Biji')->get();
        return $this->sendResponse($data);
    }


}
