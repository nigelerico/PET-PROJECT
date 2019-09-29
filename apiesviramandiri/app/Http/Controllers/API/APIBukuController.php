<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\API\BaseController as BaseController;
use App\Buku;
use App\BukuMobile;
use Illuminate\Support\Facades\Auth;
use Validator;

class APIBukuController extends BaseController
{
    public function __construct()
    {
        $this->middleware(['auth', 'admin']);
    }

    public function show()
    {
        $buku = Buku::all();
        return $this->sendResponse($buku);
    }

    
    public function adminTambahBuku(Request $request)
    {
        $buku = Buku::findOrFail($request->id);

        $bukumobile = BukuMobile::create([
            'nama'          => $buku->nama,
            'jumlah'        => $buku->jumlah,
            'penulis'       => $buku->penulis,
            'harga'         => $buku->harga,
            'foto'          => $buku->foto,
            'kategori'      => $buku->kategori,
            'ukuranbuku'    => $buku->ukuranbuku,
            'kulit'         => $buku->kulit,
            'tahunterbit'   => $buku->tahunterbit,
            'jmlhalaman'    => $buku->jmlhalaman,
            'bahanisi'      => $buku->bahanisi,
            'bahankulit'    => $buku->bahankulit,
            'deskripsi'     => $buku->deskripsi,

        ]);

        $buku->delete();

        return $this->sendResponse($buku);
    }

    public function editBuku(Request $request)
    {
        $buku = BukuMobile::findOrFail($request->id);
        $data = $request->all();
        $random = $this->random_word(20);
        $pathFile = $random.".png";

        if($buku->foto != $data['foto']){
            $file = base64_decode($request->foto);
        }

         $validation = Validator::make($request->all(), [
            'nama'          => 'required|string|max:191',
            'penulis'       => 'required|string|max:191',
            'harga'         => 'required|numeric',
            'kategori'      => 'required|string|max:191',
        ]);   

        if($validation->fails()){
            $response['status'] = "failed";
            return $this->sendResponse($response); 
        }else{
            $buku->nama = $data['nama'];
            $buku->penulis = $data['penulis'];
            $buku->harga = $data['harga'];
            $buku->kategori = $data['kategori'];
            if($data['foto'] != null){
                if($buku->foto != $data['foto']){
                    $img[0] = imagecreatefromstring($file);
                    $pathDatabase = 'buku/'.$pathFile;
                    $path = public_path() . '/img/'.$pathDatabase;
                    imagepng($img[0], $path);
                    unlink(public_path() . '/img/'.$buku->foto);
                    $buku->foto = $pathDatabase;
                }
            }
            
            $buku->save();
            $response['status'] = "success";
            return $this->sendResponse($response); 
        }   
    }

    public function random_word($id = 20){
        $pool = '1234567890abcdefghijkmnpqrstuvwxyz';
        
        $word = '';
        for ($i = 0; $i < $id; $i++){
            $word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
        }
        return $word; 
    }

    public function searchBuku(Request $request)
    {
        $keyword = $request->keyword;
        $search = Buku::where('nama',"LIKE","%$keyword%")->paginate(20);
        return $this->sendResponse($search);
    }

  

}
