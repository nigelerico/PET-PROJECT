@extends('layout')

@section('title', 'Detail Transaksi')

@section('content')
    <div style="margin-top: 20px;" class="all-header">
        <div class="container">
           <h1>Detail Transaksi</h1>
        </div>
    </div>
    <div class="section-content">
        <div class="container">
            <table class="table " style="text-align: left; color: #00000; border: none!important;" >
                <thead class="table-primary">
                    <tr>
                        <th scope="col">{{ $transaksi[0]->name}}</th>
                        <th scope="col">{{ $transaksi[0]->updated_at }}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td style="font-weight: bold;">{{ $transaksi[0]->nomor_telepon }}</td>
                        <td style="font-weight: bold">{{ $transaksi[0]->email }}</td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="2">{{ $transaksi[0]->alamat }}</td>
                    </tr>
                    <tr>
                        <td colspan="2">Note : {{ $transaksi[0]->note_alamat }}</td>

                    </tr>
                     <tr>
                        <td colspan="2">Waktu Pengiriman : {{ $transaksi[0]->waktu_pengiriman }}</td>
                    </tr>
                    <tr>
                        <td colspan="2">Status Kedatangan Barang : {{ $transaksi[0]->status_kedatangan }}</td>
                    </tr>
                </tfoot>
                
            </table>
            <div class="col-lg-3">
                <h3>Pesanan</h3>
            </div>
            <table style="text-align: center;" class="table table-bordered table-light table-striped">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">Buku</th>
                        <th scope="col">Jumlah</th>
                        <th scope="col">Total</th>
                    </tr>
                </thead>
                <tbody >
                @foreach($transaksi as $t)
                    <tr>
                        <td>{{ $t->nama }}</td>
                        <td>{{ $t->jumlah_buku }}</td>
                        <td>{{ $t->total_harga }}</td>

                    </tr>
                @endforeach
                </tbody>
                 <tbody>
                    <tr>
                        <td colspan="2">Ongkir</td>
                    <td scope="">{{ $transaksi[0]->ongkir }}</td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="2">Total</td>
                    <td scope="">{{ $totalHarga }}</td>
                    </tr>
                </tfoot>
            </table>
            <div class="row">
                <div class="col-lg-5">
                    <span style="font-weight: bold;">Metode pembayaran : </span>
                    <span>{{ $transaksi[0]->metode_transaksi }}</span>
                </div>
                <div class="col-lg-7">
                    @if($transaksi[0]->status_transaksi == 0)
                        <span style="font-weight: bold;">Status Transaksi : </span>
                        <span>Dalam Proses</span>
                    @endif
                    @if($transaksi[0]->status_transaksi == 1)
                        <span style="font-weight: bold;">Status Transaksi : </span>
                        <span>On Delivery</span>
                    @endif
                    @if($transaksi[0]->status_transaksi == 2)
                        <span style="font-weight: bold;">Status Transaksi : </span>
                        <span>Completed</span>
                    @endif
                     @if($transaksi[0]->status_transaksi == 3)
                        <span style="font-weight: bold;">Status Transaksi : </span>
                        <span>Pesanan Dibatalkan</span>
                    @endif



                </div>

                <div class="col-lg-3 mt-3">
                <h4>Bukti Transfer :</h4>
                        <div class="bukti-img" style="background-image: url({{ $transaksi[0]->foto_transaksi() }})"></div>
                </div>


              <div class="col-lg-5 mt-3" >
                    <form method="POST" rule="form" action="/manage-transaksi/edit-t/simpan" enctype="multipart/form-data" >
                        {{ csrf_field() }}
                        <input  type="hidden" name="transaksi_id" value="{{ $transaksi[0]->transaksi_id }}">
                        <div class="col-lg-8">  
                        <h4>Status Transaksi</h4>
                        <div class="form-group form{{ $errors->has('status_transaksi') ? ' has-error' : '' }}">
                        <input id="nama" type="text" class="form-control input-lg" value="{{ $transaksi[0]->status_transaksi }}" name="status_transaksi"
                            >
                             <h4>No Resi</h4>
                        <div class="form-group form{{ $errors->has('no_resi') ? ' has-error' : '' }}">
                        <input id="nama" type="text" class="form-control input-lg" value="{{ $transaksi[0]->no_resi }}" name="no_resi"
                            >

                            @if ($errors->has('no_resi'))
                                <span class="help-block">
                                    <strong>{{ $errors->first('no_resi') }}</strong>
                                </span>
                            @endif
                        </div>
                        </div>
                        <button class="btn btn-default btn-lg btn-modif" style="text-align: center;">
                           Edit
                        </button>

                    </form>
                </div> 
            </div>      
        </div>
    </div>
    <div class="modal fade" id="myModal" role="dialog" style="position: fixed; top: 50%;left: 50%;
  transform: translate(-50%, -50%);
">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"></h4>
        </div>
        <div class="modal-body">
          <p>anda yakin ingin menghapus </p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Yakin</button>
          <button type="button" class="btn btn-default btn-warning" data-dismiss="modal">Batal</button>
        </div>
      </div>
    </div>
  </div>
</div>
@endsection