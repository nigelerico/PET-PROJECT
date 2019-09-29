@extends('layout')

@section('title', 'Tambah Pengguna')

@section('content')
    <div style="margin-bottom: 20px;" class="all-header">
        <div class="container">
            <h2 style="margin-top: 20px; margin-left: 15px;">Tambah Pengguna</h2>
        </div>
    </div>
    <div class="section-content">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-12 col-12">
                    <form rule="form"  method="POST" action="/manage-user/tambah-user/simpan">
                        {{ csrf_field() }}
                        
                        <div class="form-group form{{ $errors->has('name') ? ' has-error' : '' }}">
                            <input id="name" type="text" class="form-control input-lg" required autofocus placeholder="Nama" name="name"
                                value="{{ old('name') }}">
                
                            @if ($errors->has('name'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('name') }}</strong>
                                </span>
                            @endif
                        </div>
                
                        <div class="form-group form{{ $errors->has('email') ? ' has-error' : '' }}">
                            <input id="email" type="email" class="form-control input-lg" required autofocus placeholder="E-mail" name="email"
                                value="{{ old('email') }}">
                        
                            @if ($errors->has('email'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('email') }}</strong>
                                </span>
                            @endif
                        </div>
                
                        <div class="form-group form{{ $errors->has('password') ? ' has-error' : '' }}">
                            <input id="password" type="password" class="form-control input-lg" required placeholder="Password" name="password">
                        </div>
                
                        <div class="form-group form{{ $errors->has('password') ? ' has-error' : '' }}">
                            <input id="password" type="password" class="form-control input-lg" required placeholder="Konfirmasi Password" 
                                name="password_confirmation">
                        
                            @if ($errors->has('password'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('password') }}</strong>
                                </span>
                            @endif
                        </div>
                
                        <div class="form-group form{{ $errors->has('nomor_telepon') ? ' has-error' : '' }}">
                            <input id="nomor_telepon" type="text" class="form-control input-lg" required placeholder="Nomor Telepon" 
                                name="nomor_telepon">
                
                            @if ($errors->has('nomor_telepon'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('nomor_telepon') }}</strong>
                                </span>
                            @endif
                        </div>
                
                        <div class="form-group{{ $errors->has('tipe') ? ' has-error' : '' }}">
                            <select class="form-control input-lg tipe-select" name="tipe">
                                <option selected value="" disabled>Pilih Tipe Pengguna</option>
                                <option value="admin" >Admin</option>
                                <option value="user" >User</option>
                            </select>
                
                            @if ($errors->has('tipe'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('tipe') }}</strong>
                                </span>
                            @endif
                        </div>
                
                        <div style="text-align: center; width: 100%;" class="form-group">
                            <button style="width: 200px;" type="submit" class="btn btn-default btn-lg btn-modif">Tambah</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    @if(session()->has('addUser'))
            <input type="hidden" id="addUser" value="1">
        @else
            <input type="hidden" id="addUser" value="0">
        @endif
    </div>
    <div id="modal-add-user" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 style="text-align: center;" class="modal-title">Pemberitahuan</h3>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <p>Pengguna berhasil ditambahkan</p>
                </div>
                <div style="text-align: center;" class="modal-footer">      
                    <button type="button" class="btn btn-primary btn-modif" data-dismiss="modal">Oke</button>
                </div>
            </div>
        </div>
    </div>
@endsection