<?php

namespace App\Http\Controllers;

use App\BukuMobile;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Input;

class BukuController extends Controller
{
    public function __construct()
    {
        $this->middleware(['auth', 'admin']);
    }

    public function manageBuku()
    {
        $buku = BukuMobile::all();
        return view('manage-buku',[
            'buku' => $buku
        ]);
    }

    public function tambahBuku()
    {
        return view('tambah-buku');
    }

    public function save(Request $request)
    {
        $validation = [
            'gambar-buku'   =>  'required|image|max:2000',
            'nama'          =>  'required|string|max:191',
            'harga'         =>  'required|numeric',
            'stok'          =>  'required|numeric',
            'penulis'       =>  'required|string|max:191',
            'kategori'      =>  'required|string|max:191',
            'ukuranbuku'    =>  'required|string|max:191',
            'kulit'         =>  'required|string|max:191',
            'tahunterbit'   =>  'required|numeric',
            'jmlhalaman'    =>  'required|numeric',
            'bahanisi'      =>  'required|string|max:191',
            'bahankulit'    =>  'required|string|max:191',
            'halisi'        =>  'required|string|max:191',
            'deskripsi'     =>  'required|string|max:191',

        ];

        $this->validate($request, $validation);
        
        $data = $request->all();

        if($request->hasFile('gambar-buku')){
            $files = $request->file('gambar-buku');
            $path = $files->store('buku', 'uploads');
        }

        $buku = BukuMobile::create([
            'nama'          => $data['nama'],
            'jumlah'        => $data['stok'],
            'penulis'       => $data['penulis'],
            'harga'         => $data['harga'],
            'foto'          => $path,
            'kategori'      => $data['kategori'],
            'ukuranbuku'    => $data['ukuranbuku'],
            'kulit'         => $data['kulit'],
            'tahunterbit'   => $data['tahunterbit'],
            'jmlhalaman'    => $data['jmlhalaman'],
            'bahanisi'      => $data['bahanisi'],
            'bahankulit'    => $data['bahankulit'],
            'halisi'        => $data['halisi'],
            'deskripsi'     => $data['deskripsi']

        ]);

        // return redirect('/manage-buku/tambah-buku')->with('addBuku',['tambah']);
        return redirect('/manage-buku');
    }

    public function searchBuku()
    {
        $keyword = Input::get('search');
        $search = BukuMobile::where('nama',"LIKE","%$keyword%")->paginate(20);

        return view('manage-buku',[
            'buku' => $search
        ]);
    }

    public function BukuJson()
    {
        $buku = BukuMobile::all();
        return response()->json($buku,200);
    }

    public function detailBuku($id)
    {
        $buku = BukuMobile::findOrFail($id);
        return view('detail-buku', [
            'buku' => $buku
        ]);
    }

    public function editBuku($id)
    {
        $buku = BukuMobile::findOrFail($id);
        return view('edit-buku', [
            'buku' => $buku
        ]);
    }

    public function delete($id)
    {
        $buku = BukuMobile::findOrFail($id);
        $buku->delete();
        return redirect('/manage-buku');
    }

    public function simpanPerbaharuan(Request $request)
    {
        $buku = BukuMobile::findOrFail($request->buku_id);
        $validation = [
            'nama'          =>  'required|string|max:191',
            'harga'         =>  'required|numeric',
            'stok'          =>  'required|numeric',
            'penulis'       =>  'required|string|max:191',
            'kategori'      =>  'required|string|max:191',
            'ukuranbuku'    =>  'required|string|max:191',
            'kulit'         =>  'required|string|max:191',
            'tahunterbit'   =>  'required|numeric',
            'jmlhalaman'    =>  'required|numeric',
            'bahanisi'      =>  'required|string|max:191',
            'bahankulit'    =>  'required|string|max:191',
            'halisi'        =>  'required|string|max:191',
            'deskripsi'     =>  'required|string|max:191',
        ];

        $this->validate($request, $validation);
        
        $data = $request->all();

        if($request->hasFile('gambar-buku')){
            $files = $request->file('gambar-buku');
            $path = $files->store('buku', 'uploads');
            unlink(public_path() . '/img/'.$buku->foto);
        }else{
            $path = $buku->foto;
        }

      
            $buku->nama          = $data['nama'];
            $buku->jumlah        = $data['stok'];
            $buku->penulis       = $data['penulis'];
            $buku->harga         = $data['harga'];
            $buku->foto          = $path;
            $buku->kategori      = $data['kategori'];
            $buku->ukuranbuku    = $data['ukuranbuku'];
            $buku->kulit         = $data['kulit'];
            $buku->tahunterbit   = $data['tahunterbit'];
            $buku->jmlhalaman    = $data['jmlhalaman'];
            $buku->bahanisi      = $data['bahanisi'];
            $buku->bahankulit    = $data['bahankulit'];
            $buku->halisi        = $data['halisi'];
            $buku->deskripsi     = $data['deskripsi'];

        $buku->save();

        return redirect('/manage-buku');
    }

}
