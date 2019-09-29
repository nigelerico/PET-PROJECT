@extends('layout')

@section('title', 'Tambah Buku')

@section('content')
    <div style="margin-bottom: 20px;" class="all-header">
        <div class="container" style="margin-top: 20px">
           <h1>Tambah Buku</h1>
        </div>
    </div>
    <div class="section-content">
        <div class="container">
            <form rule="form" method="POST" enctype="multipart/form-data" action="/manage-buku/tambah-buku/tambah">
                {{ csrf_field() }}
                
                <div class="row">
                    <div class="col-lg-3">
                        <div class="image-place-upload"></div>
                        <label class="btn btn-default btn-lg btn-modif image-upload-btn">
                            <input class="input-image" type="file" multiple id="uploadPhotoBuku" name="gambar-buku">
                            <div id="text-upload"></div>

                            @if ($errors->has('gambar-buku'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('gambar-buku') }}</strong>
                                </span>
                            @endif
                        </label>
                    </div>
                    <div class="col-lg-5">  
                        <h4>Judul Buku</h4>
                        <div class="form-group form{{ $errors->has('nama') ? ' has-error' : '' }}">
                            <input id="nama" type="text" class="form-control input-lg" required autofocus placeholder="Nama Buku" name="nama"
                            >

                            @if ($errors->has('nama'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('nama') }}</strong>
                                </span>
                            @endif
                        </div>
                            
                        <h4>Harga Buku</h4>
                        <div class="form-group form{{ $errors->has('harga') ? ' has-error' : '' }}">
                            <input id="harga" type="number" class="form-control input-lg" required autofocus placeholder="Harga Buku" name="harga"
                            >
                                
                            @if ($errors->has('harga'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('harga') }}</strong>
                                </span>
                            @endif
                        </div>

                        <h4>Stok Buku</h4>
                        <div class="form-group form{{ $errors->has('stok') ? ' has-error' : '' }}">
                            <input id="stok" type="number" class="form-control input-lg" required placeholder="Stok Buku"   name="stok" >
                            
                            @if ($errors->has('stok'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('stok') }}</strong>
                                </span>
                            @endif
                        </div>
                        <h4>Tahun Terbit</h4>
                        <div class="form-group form{{ $errors->has('tahunterbit') ? ' has-error' : '' }}">
                            <input id="tahunterbit" type="number" class="form-control input-lg" required placeholder="Tahun Terbit"
                                name="tahunterbit" >
                            
                            @if ($errors->has('tahunterbit'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('tahunterbit') }}</strong>
                                </span>
                            @endif
                        </div>

                        <h4>Jumlah Halaman</h4>
                        <div class="form-group form{{ $errors->has('jmlhalaman') ? ' has-error' : '' }}">
                            <input id="jmlhalaman" type="number" class="form-control input-lg" required placeholder="Jumlah Halaman"   name="jmlhalaman" >
                            
                            @if ($errors->has('jmlhalaman'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('jmlhalaman') }}</strong>
                                </span>
                            @endif
                        </div>


                        <h4>Deskripsi</h4>
                        <div class="form-group form{{ $errors->has('deskripsi') ? ' has-error' : '' }}">
                            <textarea id="deskripsi" type="text"  class="form-control input-lg" required placeholder="Deskripsi"   name="deskripsi" ></textarea>
                            
                            @if ($errors->has('deskripsi'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('deskripsi') }}</strong>
                                </span>
                            @endif
                        </div>


                    </div>
                    <div class="col-lg-4">
                        <h4>Kategori</h4>
                            <div class="form-group form{{ $errors->has('kategori') ? ' has-error' : '' }}">
                                <input id="kaategori" type="text"  class="form-control input-lg" required placeholder="Kategori"   name="kategori" ></input>
                                    
                               </select>
    
                            @if ($errors->has('kategori'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('kategori') }}</strong>
                                </span>
                            @endif
                        </div>

                        <h4>Penulis</h4>
                        <div class="form-group form{{ $errors->has('penulis') ? ' has-error' : '' }}">
                            <input id="penulis" type="text" class="form-control input-lg" required placeholder="penulis"
                                name="penulis" >
                            
                            @if ($errors->has('penulis'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('penulis') }}</strong>
                                </span>
                            @endif
                        </div>

                        <h4>Ukuran Buku</h4>
                        <div class="form-group form{{ $errors->has('ukuranbuku') ? ' has-error' : '' }}">
                            <input id="ukuranbuku" type="text" class="form-control input-lg" required placeholder="Ukuran buku"
                                name="ukuranbuku" >
                            
                            @if ($errors->has('ukuranbuku'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('ukuranbuku') }}</strong>
                                </span>
                            @endif
                        </div>


                        <h4>Kulit</h4>
                        <div class="form-group form{{ $errors->has('kulit') ? ' has-error' : '' }}">
                            <input id="kulit" type="text" class="form-control input-lg" required placeholder="Kulit"
                                name="kulit" >
                            
                            @if ($errors->has('kulit'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('kulit') }}</strong>
                                </span>
                            @endif
                        </div>

                          <h4>Bahan Isi</h4>
                        <div class="form-group form{{ $errors->has('bahanisi') ? ' has-error' : '' }}">
                            <input id="bahanisi" type="text" class="form-control input-lg" required placeholder="Bahan Isi"
                                name="bahanisi" >
                            
                            @if ($errors->has('bahanisi'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('bahanisi') }}</strong>
                                </span>
                            @endif
                        </div>


                          <h4>Bahan Kulit</h4>
                        <div class="form-group form{{ $errors->has('bahankulit') ? ' has-error' : '' }}">
                            <input id="bahankulit" type="text" class="form-control input-lg" required placeholder="Bahan Kulit"
                                name="bahankulit" >
                            
                            @if ($errors->has('bahankulit'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('bahankulit') }}</strong>
                                </span>
                            @endif
                        </div>

                          <h4>Halaman Isi</h4>
                        <div class="form-group form{{ $errors->has('halisi') ? ' has-error' : '' }}">
                            <input id="halisi" type="text" class="form-control input-lg" required placeholder="Halaman Isi"
                                name="halisi" >
                            
                            @if ($errors->has('halisi'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('halisi') }}</strong>
                                </span>
                            @endif
                        </div>



                        <button class="btn btn-default btn-lg btn-modif btn-tambah-buku">
                            Tambah
                        </button>
                    </div>
                </div>
            </form>
        </div>
        @if(session()->has('addBuku'))
            <input type="hidden" id="addBuku" value="1">
        @else
            <input type="hidden" id="addBuku" value="0">
        @endif
    </div>
    <div id="modal-add-buku" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 style="text-align: center;" class="modal-title">Pemberitahuan</h3>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <p>Data buku anda berhasil ditambahkan</p>
                </div>
                <div style="text-align: center;" class="modal-footer">      
                    <button type="button" class="btn btn-primary btn-modif" data-dismiss="modal">Oke</button>
                </div>
            </div>
        </div>
    </div>
@endsection