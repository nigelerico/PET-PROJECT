@extends('layout')

@section('title', 'Manage Transaksi')

@section('content')
    <div class="all-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <h2 style=" padding-top: 20px;">Manage Transaksi</h2>
                </div>
                <div class="col-lg-6"></div>
            </div>
        </div>
    </div>
    <div class="section-content">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div id="belumdibayar-container" class="status-pesanan-container">
                        <img src="{{ asset('img/buy.png') }}" style="width: 60px; float: left; margin: 0px 5px 10px 5px;">
                        <h4>{{ $belumdibayar }} Pesanan 
                            <br>
                        belum selesai</h4>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div id="telahdibayar-container" class="status-pesanan-container">
                        <img src="{{ asset('img/contract.png') }}" style="width: 60px; float: left; margin: 0px 5px 10px 5px;">
                        <h4>{{ $sukses }} 
                            <br>
                        Transaksi sukses</h4>
                    </div>
                </div>
            </div>
            <div id="tabel-belum-dibayar-container" style="display:none; margin-top: 50px;" class="datatable-home-container">
                <table style="text-align: center;" id="table_id_blm_byr" class="display">
                    <thead>
                        <tr class="table-primary">
                            <th scope="col">Nama</th>
                            <th scope="col">Jumlah Pembelian</th>
                            <th scope="col">Total Harga</th>
                            <th scope="col">No Pesanan</th>
                            <th scope="col">Status</th>
                            <th scope="col">Aksi</th>
                        </tr>
                    </thead>
                    <tbody id="tabel-belum-dibayar">
                       
                    </tbody>
                </table>
            </div>
            <div id="tabel-telah-dibayar-container" style="display:none; margin-top: 50px;" class="datatable-home-container">
                <table style="text-align: center;" id="table_id_telahdibayar" class="display">
                    <thead>
                        <tr class="table-primary">
                            <th scope="col">Nama</th>
                            <th scope="col">Jumlah Pembelian</th>
                            <th scope="col">Total Harga</th>
                            <th scope="col">No Pesanan</th>
                            <th scope="col">Status</th>
                            <th scope="col">Aksi</th>
                        </tr>
                    </thead>
                    <tbody id="tabel-telah-dibayar">
                      
                    </tbody>
                </table>
            </div>
        </div>
        <input type="hidden" id="link-blm-byr" value="/manage-transaksi/belumbayar">
        <input type="hidden" id="link-sdh-byr" value="/manage-transaksi/sudahbayar">
    </div> 

    <script>
        var temp_blm_byr = 0, temp_sdh_byr = 0;

        $(document).ready( function () {
            $('#manage-transaksi-txt').css('color', '#ffffff');
        } );

        $('#belumdibayar-container').click(function(){
            $.ajax({
                type: "GET",
                url: $('#link-blm-byr').val(),
                success: function(data){
                    var htmlblmbyr;
                    var tempidtransaksi = 0;
                    
                    if(data.length == 0){
                        htmlblmbyr += '<tr>';
                        htmlblmbyr += '<td></td>';
                        htmlblmbyr += '<td></td>';
                        htmlblmbyr += '<td></td>';
                        htmlblmbyr += '<td></td>';
                        htmlblmbyr += '<td></td>';
                        htmlblmbyr += '<td></td>';
                        htmlblmbyr += '</tr>';
                    }else{
                        for(var i = 0; i < data.length; i++){
                            if(tempidtransaksi != data[i].id){
                                htmlblmbyr += '<tr>';
                                htmlblmbyr += '<td>'+ data[i].name +'</td>';
                                htmlblmbyr += '<td>'+ hitungJumlahPembelian(data[i].id, data) +'</td>';
                                htmlblmbyr += '<td>'+ hitungTotalHarga(data[i].id, data) +'</td>';
                                htmlblmbyr += '<td>'+ data[i].kode_transaksi +'</td>';
                                if(data[i].status_transaksi == 0){
                                    htmlblmbyr += '<td> Dalam proses </td>';
                                }
                                if(data[i].status_transaksi == 1){
                                    htmlblmbyr += '<td> Sedang dikirim </td>';
                                }
                                htmlblmbyr += '<td><a href="/manage-transaksi/detail-transaksi/'+ data[i].id +'" class="btn btn-default hapus-user-btn" style="background-color: #5a95f4; color: white;">'+
                                        'Lihat'+
                                    '</a></td>';
                                htmlblmbyr += '</tr>';
                                
                                tempidtransaksi = data[i].id
                            }
                        }
                    }

                    $('#tabel-belum-dibayar').empty();
                    $('#tabel-belum-dibayar').append(htmlblmbyr);
                    $("#tabel-telah-dibayar-container").hide("slow");
                    $("#tabel-belum-dibayar-container").show("slow");  
                    if(temp_blm_byr == 0){
                        $('#table_id_blm_byr').DataTable({
                            "ordering": false,
                            "language": {
                                "emptyTable": "Tidak ada data"
                            },
                        });
                        temp_blm_byr = 1;
                    }    
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert(errorThrown);
                }   
            }); 
        });

        $('#telahdibayar-container').click(function(){
            $.ajax({
                type: "GET",
                url: $('#link-sdh-byr').val(),
                success: function(data){
                    var htmlsdhbyr;
                    var tempidtransaksi = 0;
                        
                    if(data.length == 0){
                        htmlsdhbyr += '<tr>';
                        htmlsdhbyr += '<td></td>';
                        htmlsdhbyr += '<td></td>';
                        htmlsdhbyr += '<td></td>';
                        htmlsdhbyr += '<td></td>';
                        htmlsdhbyr += '<td></td>';
                        htmlsdhbyr += '<td></td>';
                        htmlsdhbyr += '</tr>';
                    }else{
                        for(var a = 0; a < data.length; a++){
                            if(tempidtransaksi != data[a].id){
                                htmlsdhbyr += '<tr>';
                                htmlsdhbyr += '<td>'+ data[a].name +'</td>';
                                htmlsdhbyr += '<td>'+ hitungJumlahPembelian(data[a].id, data) +'</td>';
                                htmlsdhbyr += '<td>'+ hitungTotalHarga(data[a].id, data) +'</td>';
                                htmlsdhbyr += '<td>'+ data[a].kode_transaksi +'</td>';

                                // htmlsdhbyr += '<td> Sukses </td>';
                                if(data[a].status_transaksi == 2){
                                    htmlsdhbyr += '<td> Completed </td>';
                                }
                                if(data[a].status_transaksi == 3){
                                    htmlsdhbyr += '<td> Pesanan Dibatalkan </td>';
                                }
                                htmlsdhbyr += '<td><a href="/manage-transaksi/detail-transaksi/'+ data[a].id +'" class="btn btn-default hapus-user-btn" style="background-color: #5a95f4; color: white;">'+
                                        'Lihat'+
                                    '</a></td>';
                                htmlsdhbyr += '</tr>';
                                
                                tempidtransaksi = data[a].id
                            }
                        }
                    }
                    
                    $('#tabel-telah-dibayar').empty();
                    $('#tabel-telah-dibayar').append(htmlsdhbyr);
                    $("#tabel-belum-dibayar-container").hide("slow");
                    $("#tabel-telah-dibayar-container").show("slow");  
                    if(temp_sdh_byr == 0){
                        $('#table_id_telahdibayar').DataTable({
                            "ordering": false,
                            "language": {
                                "emptyTable": "Tidak ada data"
                            },
                        });
                        temp_sdh_byr = 1;
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert(errorThrown);
                }   
            }); 
        });

        function hitungTotalHarga(idtransaksi, data){
            var jumlah = 0;
            for(var j = 0; j < data.length; j++){
                if(data[j].transaksi_id == idtransaksi){
                    jumlah += parseInt(data[j].total_harga);
                }
            }

            return jumlah;
        }

        function hitungJumlahPembelian(idtransaksi, data){
            var jumlah = 0;
            for(var k = 0; k < data.length; k++){
                if(data[k].transaksi_id == idtransaksi){
                    jumlah += parseInt(data[k].jumlah_buku);
                }
            }
            
            return jumlah;
        }

        
    </script>
@endsection