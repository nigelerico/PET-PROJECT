<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Transaksi;
use DB;
use App\User;
use App\BukuMobile;
use App\CartTransaksi;

class TransaksiController extends Controller
{    
    public function __construct()
    {
        $this->middleware(['auth', 'admin']);
    }

    public function manageTransaksi()
    {
        $belumdibayar = count(Transaksi::where('status_transaksi', 0)->orWhere('status_transaksi', 1)->get());
        $sukses = count(Transaksi::where('status_transaksi', 2)->orWhere('status_transaksi', 3)->get());
        return view('manage-transaksi', [
            'belumdibayar'      => $belumdibayar,
            'sukses'            => $sukses,
        ]);
    }

    public function detilTransaksi($id)
    {
        $transaksi = Transaksi::findOrFail($id)
                    ->where('transaksi.id', $id)
                    ->leftJoin('users','transaksi.user_id','=','users.id')
                    ->leftJoin('cart_transaksi','transaksi.id','=','cart_transaksi.transaksi_id')
                    ->leftJoin('bukumobile','cart_transaksi.buku_id','=','bukumobile.id')
                    ->get();
        
        $totalHarga = 0;
        foreach($transaksi as $t){
            $totalHarga += $t->total_harga; 
        }
        $totalHarga = $totalHarga + $transaksi[0]->ongkir;
        
        return view('detail-transaksi', [
            'transaksi' => $transaksi,
            'totalHarga' => $totalHarga
        ]);

        // return response()->json($transaksi, 200);
    }

    public function belumBayar()
    {
        $belumdibayar = DB::table('transaksi')
        ->where('transaksi.status_transaksi', 0)
        ->orWhere('transaksi.status_transaksi', 1)
        ->leftJoin('cart_transaksi','transaksi.id','=','cart_transaksi.transaksi_id')
        ->leftJoin('users','transaksi.user_id','=','users.id')
        ->select('transaksi.*', 'cart_transaksi.transaksi_id', 'cart_transaksi.jumlah_buku', 'cart_transaksi.total_harga', 'users.name')
        ->get();
        //header('Content-Type: application/json');
        return response()->json($belumdibayar, 200);
    }

    public function sudahBayar()
    {
        $sudahdibayar = DB::table('transaksi')
        ->where('transaksi.status_transaksi', 2)
        ->orWhere('transaksi.status_transaksi', 3)
        ->leftJoin('cart_transaksi','transaksi.id','=','cart_transaksi.transaksi_id')
        ->leftJoin('users','transaksi.user_id','=','users.id')
        ->select('transaksi.*', 'cart_transaksi.transaksi_id', 'cart_transaksi.jumlah_buku', 'cart_transaksi.total_harga', 'users.name')
        ->get();
        ///header('Content-Type: application/json');
        return response()->json($sudahdibayar, 200);
    }

    public function ubahStatus(Request $request)
    {
        $transaksi = Transaksi::findOrFail($request->transaksi_id);
        $transaksi->status_transaksi = $request->status; 
        $transaksi->save();
        return redirect()->back();
    }

  public function editStatus(Request $request)
    {
        $transaksi = Transaksi::findOrFail($request->transaksi_id);
        $validation = [
            'status_transaksi'          =>  'numeric',
            'no_resi'                   =>  'required|string|max:191',
         
         ];

        $this->validate($request, $validation);
        
        $data = $request->all();
      
            $transaksi->status_transaksi             = $data['status_transaksi'];
            $transaksi->no_resi                      = $data['no_resi'];
         

        $transaksi->save();

        return redirect('/manage-transaksi');
    }

}
