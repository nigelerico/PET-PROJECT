@extends('layout')

@section('title', 'Menejemen User')

@section('content')
    <div style="margin-bottom: 20px;" class="all-header">
        <div class="container">
            <div class="row">
                <div style="margin-bottom: 20px;"class="col-lg-6">
                    <h2 style=" padding-top: 20px;">Menejemen User</h2>
                </div>
                <div class="col-lg-6"></div>
                <div class="col-lg-2">
                        <a href="/manage-user/tambah-user"><button class="btn btn-default btn-lg btn-modif"><i class="fa fa-plus"></i>
                        Tambah Pengguna
                    </button></a>
                </div>
            
            </div>
        </div>
    </div>
    <div class="section-content">
        <div class="datatable-home-container">
            <div class="table-responsive-lg">
                <table style="text-align: center;" id="table_id" class="display">
                    <thead>
                        <tr class="table-primary">
                            <th scope="col">Nama</th>
                            <th scope="col">Email</th>
                            <th scope="col">No Telp</th>
                            <th scope="col">Tipe</th>
                            <th scope="col">Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                    @foreach($user as $u)
                        @if($u->id != Auth::User()->id)
                            <tr>
                                <td>{{ $u->name }}</td>
                                <td>{{ $u->email }}</td>
                                <td>{{ $u->nomor_telepon }}</td>
                                <td>{{ $u->tipe }}</td>
                                <td><button data-id="{{ $u->id }}" class="btn btn-default hapus-user-btn" data-toggle="modal" 
                                    data-target=".hapus-user" style="background-color: #fc2828; color: white;">
                                    Hapus
                                </button></td>
                            </tr>
                        @endif
                    @endforeach
                    </tbody>
                </table>
                
                    <div class="modal fade hapus-user" role="dialog" style="position: fixed; top: 50%;left: 50%;
                        transform: translate(-50%, -50%);
                        ">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title"></h4>
                                </div>
                                <div class="modal-body">
                                    <p>anda yakin ingin menghapus user ini</p>
                                </div>
                                <div class="modal-footer">
                                    <form data-url="{{ url('/manage-user/delete') }}" method="POST" action="">
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
            </div>
        </div>
    </div>      
    <script>
        $(document).ready( function () {
            $('#table_id').DataTable();
            $('#manage-user-txt').css('color', '#ffffff');
        } );
    </script>
@endsection