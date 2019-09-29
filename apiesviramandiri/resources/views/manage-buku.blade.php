@extends('layout')

@section('title', 'Manage Buku')

@section('content')
    <div style="margin-bottom: 20px;" class="all-header">
        <div class="container">
            <div class="row">
                <div style="margin-bottom: 20px;"class="col-lg-6">
                    <h2 style=" padding-top: 20px;">Manage Buku</h2>
                </div>
                <div class="col-lg-2"></div>
                    <div class="col-lg-4" style="margin-top: 20px">
                        <form action="/manage-buku/search" method="GET">
                                 <div class="input-group mb-3">
                                  <input type="text" class="form-control" name="search" id="search" placeholder="Cari Buku..." aria-label="Recipient's username" aria-describedby="button-addon2">
                                  <div class="input-group-success">
                                    <button class="btn btn-outline-secondary" type="submit"><i class="fa fa-search"></i></button>
                                  </div>
                                </div>
                        </form> 
                    </div>  
                <div class="col-lg-2">
                     <a href="/manage-buku/tambah-buku"><button class="btn btn-default btn-lg btn-modif"><i class="fa fa-plus"></i>
                        Tambah Buku
                    </button></a>
                </div>
             
            </div>
        </div>
    </div>
    <div class="section-content">
        <div class="container">
            <div class="row">
                @foreach($buku as $s)
                <div class="col-lg-3">
                    <div class="manage-buku-container">
                        <a href="/manage-buku/detail-buku/{{ $s->id }}" style="text-decoration: none;" >
                        <h4 style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis; text-align: center; font-family: arial; color: #000000; ">{{ $s->nama }}</h4>
                        <div class="buku-home-img-home" style="background-image: url({{ $s->foto() }})"></div>
                        <table class="table" style="text-align: center; color: #000000" >
                            <thead >
                                <tr>
                                    <th>Stok</th>
                                    <th>Kategori</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>{{ $s->jumlah }}</td>
                                    <td>{{ $s->kategori }}</td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">Rp. {{ $s->harga }} ,-</td>
                                </tr>
                            </tfoot>

                        </table>
                        </a>
                        
                    </div>
                </div>
                @endforeach
            </div>
        </div>
    </div>  
    <script>
        $('#manage-buku-txt').css('color', '#ffffff');
    </script>
@endsection