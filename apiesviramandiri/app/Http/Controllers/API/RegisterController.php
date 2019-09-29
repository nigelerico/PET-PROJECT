<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\API\BaseController as BaseController;
use App\User;
use App\Ongkir;
use Illuminate\Support\Facades\Auth;
use Validator;
use Illuminate\Support\Facades\Hash;

class RegisterController extends BaseController
{
    public function register(Request $request)
    {
        
      


        $validator = Validator::make($request->all(), [
            'name' => 'required|string|max:191',
            'email' => 'required|string|email|max:255|unique:users',
            'nomor_telepon' => 'required',
            'alamat_user' => 'required',
            'id_kota' => 'required',
            'password' => 'required|confirmed',
            'tipe' => 'required',
        ]);

        if($validator->fails()){
            $response['status'] = "failed"; 
            $failed = $validator->failed();
            if(isset($failed['email'])){
                $response['field'] = "email";
            }
            if(isset($failed['password'])){
               $response['field'] = "password";    
            }
            if(isset($failed['email'], $failed['password'])){
                $response['field'] = "emaildanpassword";
            }

            return $this->sendResponse($response);
        }else{
            $data = $request->all();

            $user = User::create([
                'name'          => $data['name'],
                'email'         => $data['email'],
                'nomor_telepon' => $data['nomor_telepon'],
                'alamat_user'   => $data['alamat_user'],
                'id_kota'       => $data['id_kota'],
                'password'      => Hash::make($data['password']),
                'foto'          => "user/user-icon.png",
                'tipe'          => $data['tipe'],
            ]);
            
            $response['status'] = "success";
            return $this->sendResponse($response);
        }
        // $success['token'] =  $user->createToken('sayurin')->accessToken;
    }
    
}
