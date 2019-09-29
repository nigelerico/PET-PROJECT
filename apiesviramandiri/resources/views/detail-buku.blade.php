@extends('layout')

@section('title', 'Detail Buku')

@section('content')
    <div style="margin-bottom: 20px;" class="all-header">
        <div class="container">
           <h1 style="margin-top: 20px;">Detail Buku</h1>
        </div>
    </div>
    <div class="section-content">
        <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="buku-home-img" style="background-image: url({{ $buku->foto() }})"></div>
                    </div>
                    <div class="col-lg-5">  
                        <h4>Judul Buku</h4>
                        <div>
                            <h5 style="color: #5a95f4; text-align: justify; width: 400px; height: 50px;" >{{ $buku->nama }}</h5>
                        </div>
                            
                        <h4>Harga Buku</h4>
                        <div>
                            <h5 style="color: #5a95f4">{{ $buku->harga }}</h5>
                        </div>

                        <h4>Stok Buku</h4>
                        <div>
                            <h5 style="color: #5a95f4">{{ $buku->jumlah }}</h5>
                        </div>

                        <h4>Tahun Terbit</h4>
                        <div>
                            <h5 style="color: #5a95f4">{{ $buku->tahunterbit }}</h5>
                        </div>

                        <h4>Jumlah Halaman</h4>
                        <div>
                            <h5 style="color: #5a95f4">{{ $buku->jmlhalaman }}</h5>
                        </div>
                        <h4>Deskripsi</h4>
                        <div>
                            <h5 style="color: #5a95f4">{{ $buku->deskripsi }}</h5>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <h4>Kategori</h4>
                        <div>
                            <h5 style="color: #5a95f4"  >{{ $buku->kategori}}</h5>
                        </div>
                        <h4>Penulis</h4>
                        <div>
                            <h5 style="color: #5a95f4"  >{{ $buku->penulis}}</h5>
                        </div>
                        <h4>Ukuran Buku</h4>
                        <div>
                            <h5 style="color: #5a95f4"  >{{ $buku->ukuranbuku}}</h5>
                        </div>
                        <h4>Kulit</h4>
                        <div>
                            <h5 style="color: #5a95f4"  >{{ $buku->kulit}}</h5>
                        </div>
                        <h4>Bahan Isi</h4>
                        <div>
                            <h5 style="color: #5a95f4"  >{{ $buku->bahanisi}}</h5>
                        </div>
                        <h4>Bahan Kulit</h4>
                        <div>
                            <h5 style="color: #5a95f4"  >{{ $buku->bahankulit}}</h5>
                        </div>

                        <h4>Hal Isi</h4>
                        <div>
                            <h5 style="color: #5a95f4"  >{{ $buku->halisi}}</h5>
                        </div>

                        <button onclick="window.location.href='/manage-buku/edit-buku/{{ $buku->id }}'" class="btn btn-default btn-lg btn-modif btn-tambah-buku">
                            Edit
                        </button>
                        <button id="hapus-buku-btn" data-id="{{ $buku->id }}" style="margin-top: 10px;" class="btn btn-default btn-lg btn-modif-hapus" data-toggle="modal" data-target="#myModal">
                            Hapus
                        </button>
                    </div>
                </div>
        </div>
    </div>
    <div class="hapus-buku modal fade" id="myModal" role="dialog" style="position: fixed; top: 50%;left: 50%; transform: translate(-50%, -50%);">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body">
                    <p>anda yakin ingin menghapus {{ $buku->nama }}</p>
                </div>
                <div class="modal-footer">
                    <form data-url="{{ url('/manage-buku/delete') }}" method="POST" action="">
                        {{ csrf_field() }}
                        <button type="submit" class="btn btn-default">
                            Yakin
                        </button>
                        <button type="button" class="btn btn-default btn-warning" data-dismiss="modal">Batal</button>
                    </form>
                </div>
            </div>
        </div>
  </div>
</div>
@endsection