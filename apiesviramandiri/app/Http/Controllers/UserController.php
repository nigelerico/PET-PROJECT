<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use Illuminate\Support\Facades\Input;
use App\Http\Controllers\Controller;
use Validator;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Auth;

class UserController extends Controller
{    
    public function __construct()
    {
        $this->middleware(['auth', 'admin']);
    }

    public function manageUser()
    {
        $user = User::get();
        return view('manage-user', [
            'user' => $user
        ]);
    }
    
    public function chatUser()
    {
        return view('chat');
    }

    public function search()
    {
        $keyword = Input::get('search');
        $search = User::where('tipe', 'user')->where('name',"LIKE","%$keyword%")->paginate(20);

        return view('manage-user', [            
            'user' => $search
        ]);
    }

    public function delete($id)
    {
        $users = User::findOrFail($id);
        $users->delete();
        return redirect()->back();
    }

    public function tambahUser()
    {
        return view('tambah-user');
    }

    public function save(Request $request)
    {
        $validation = [
            'name'          => 'required|string|max:191',
            'email'         => 'required|string|email|max:255|unique:users',
            'nomor_telepon' => 'required',
            'password'      => 'required|confirmed',
            'tipe'          => 'required',
        ];
        
        $this->validate($request, $validation);

        $data = $request->all();

        $user = User::create([
            'name'          => $data['name'],
            'email'         => $data['email'],
            'nomor_telepon' => $data['nomor_telepon'],
            'password'      => Hash::make($data['password']),
            'foto'          => "user/user-icon.png",
            'tipe'          => $data['tipe'],
        ]);
        
        // return redirect('/manage-user/tambah-user')->with('addUser',['tambah']);
        return redirect('/manage-user');
    }
}
