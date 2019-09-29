package com.skripsi.nigel.esvira;

import com.skripsi.nigel.esvira.Model.Buku;

public interface Callbacks {

    void updateCart(Buku buku, int status, int jumlah);
    void hapuscart (int Position);
    void updateJumlah (int jumlah, int status);
    void updateharga (int harga, int status);
}