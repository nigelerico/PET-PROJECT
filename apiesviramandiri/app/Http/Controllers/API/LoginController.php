<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\API\BaseController as BaseController;
use App\User; 
use Illuminate\Support\Facades\Auth; 
use Validator;

class LoginController extends BaseController
{
    public function login(Request $request)
    {
        $data = $request->all();

        if(Auth::attempt(['email' => $data['email'], 'password' => $data['password']]))
        {
            $user = Auth::user();
            $response['status'] = "success";
            $response['id'] = $user->id;
            $response['name'] = $user->name;
            $response['nomor_telepon'] = $user->nomor_telepon;
            $response['alamat_user'] = $user->alamat_user;
            $response['id_kota'] = $user->id_kota;
            $response['foto'] = $user->foto;
            $response['tipe'] = $user->tipe;
            $response['token'] = $user->createToken('sayurin')->accessToken; 

            return $this->sendResponse($response);
        }else{
            $response['status'] ="failed";
            return $this->sendResponse($response);
        }
    }
}
