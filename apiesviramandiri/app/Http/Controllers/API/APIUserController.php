<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\API\BaseController as BaseController;
use App\User;
use App\Ongkir;
use Illuminate\Support\Facades\Auth;
use Validator;
use DB;


class APIUserController extends BaseController
{
    public function editUser(Request $request)
    {
        $data = $request->all();
        $user = User::findOrFail($request->id);
        $random = $this->random_word(20);
        $pathFile = $random.".png";

        if($user->foto != $data['foto']){
        	$file = base64_decode($request->foto);
        }

        $validation = Validator::make($request->all(), [
            'name'              => 'required|string|max:191',
            'nomor_telepon'     => 'required',
            'alamat_user'     	=> 'required',
            'id_kota'           => 'required',

        ]);    

        if($user->email != $data['email']){
        	$validation = Validator::make($request->all(), [
            'email'              => 'required|string|email|max:255|unique:users'
        		]);   
        }

        if($validation->fails()){
            $response['status'] = "failed";
            $failed = $validation->failed();
            if(isset($failed['email'])){
                $response['field'] = "Email telah digunakan oleh pengguna yang lain";
            }

            return $this->sendResponse($response); 
        }else{
            $user->name = $data['name'];
            $user->email = $data['email'];
            $user->nomor_telepon = $data['nomor_telepon'];
            $user->alamat_user = $data['alamat_user'];
            $user->id_kota = $data['id_kota'];
            if($data['foto'] != null){
                if($user->foto != $data['foto']){
                    $img[0] = imagecreatefromstring($file);
                    $pathDatabase = 'user/'.$pathFile;
                    $path = public_path() . '/img/'.$pathDatabase;
                    imagepng($img[0], $path);
                    if($user->foto != "user/user-icon.png"){
                        unlink(public_path() . '/img/'. $user->foto);
                    }
                    $user->foto = $pathDatabase;
                }
            }
            $user->save();
            $user_response = User::findOrFail($request->id);
            return $this->sendResponse($user_response); 
        }   

    }
    
    public function random_word($id = 20){
        $pool = '1234567890abcdefghijkmnpqrstuvwxyz';
        
        $word = '';
        for ($i = 0; $i < $id; $i++){
            $word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
        }
        return $word; 
    }


   public function getOngkir(Request $request)
    {
        $data = DB::table('users')
                ->where('users.id', $request->id)
                ->leftJoin('ongkir','users.id_kota','=','ongkir.id')
                ->get();
            return $this->sendResponse($data);
    }
       
}
