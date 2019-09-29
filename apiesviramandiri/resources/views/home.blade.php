@extends('layout')

@section('title', 'Home')

@section('content')
    <div class="all-header">
        <div class="container">
            <h2 style="margin-left: 20px; margin-top: 20px;">Halaman utama</h2>
        </div>
    </div>
    <div class="section-content">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <a style="color: black; text-decoration: none" href="/manage-transaksi">
                        <div class="status-pesanan-container">
                            <img src="{{ asset('img/buy.png') }}" style="width: 60px; float: left; margin: 0px 5px 10px 5px;">
                            <h4>{{ $belumdibayar }} Pesanan 
                                <br>
                            belum selesai</h4>
                        </div>
                    </a>
                </div>
                <div class="col-lg-4">
                    <a style="color: black; text-decoration: none;" href="/manage-transaksi">
                        <div class="status-pesanan-container">
                            <img src="{{ asset('img/contract.png') }}" style="width: 60px; float: left; margin: 0px 5px 10px 5px;">
                            <h4>{{ $sukses }} 
                                <br>
                            Transaksi sukses</h4>
                        </div>
                    </a>
                </div>
            </div>
            <h3 style="margin-top: 30px; margin-bottom: 15px;">Buku yang tersedia</h3>
            <div class="row">
                @foreach($buku as $s)
                <div class="col-lg-3">
                    <a href="/manage-buku" style="text-decoration: none">
                        <div class="buku-home-container">
                            <h4 style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis; text-align: center; color: #000000; font-family: arial;">{{ $s->nama }}</h4>
                            <div class="buku-home-img-home" style="background-image: url({{ $s->foto() }})"></div>
                            <table class="table table-borderless" style="text-align: center; color: #000000" >
                                <thead>
                                    <tr>
                                        <th scope="col">Stok</th>
                                        <th scope="col">Kategori</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr >
                                        <td>{{ $s->jumlah }}</td>
                                        <td>{{ $s->berat }} {{ $s->kategori }}</td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td colspan="2">Rp. {{ $s->harga }} ,-</td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </a>
                </div>
                @endforeach
            </div>
            <h3 style="margin-top: 30px; margin-bottom: 15px;">Pengguna Aplikasi Mobile</h3>
            <div class="row">
                @foreach($user as $u)
                <div class="col-lg-3">
                    <a href="/manage-user" style="text-decoration: none">
                        <div class="user-home-container">
                            <div class="user-home-img" style="background-image: url({{ $u->foto() }})"></div>
                            <hr>
                            <h5 style="margin-top: 15px;">{{ $u->name }}</h5>
                            <hr>
                            <h6 style="margin-top: 15px;">{{ $u->email }}</h6>
                        </div>
                    </a>
                </div>
                @endforeach
            </div>
        </div>
    </div>
@endsection