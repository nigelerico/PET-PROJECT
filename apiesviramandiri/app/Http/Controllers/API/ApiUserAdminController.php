<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\API\BaseController as BaseController;
use App\User;
use Illuminate\Support\Facades\Auth;
use Validator;


class ApiUserAdminController extends BaseController
{
    public function __construct()
    {
        $this->middleware(['auth', 'admin']);
    }

    public function listUser()
    {
    	$user = User::all();
    	return $this->sendResponse($user);
    }

    public function deleteUser(Request $request)
    {
    	$user = User::findOrFail($request->id);
    	$user->delete();
    	return "sukses";
    }

    public function detailUser(Request $request)
    {
    	$user = User::findOrFail($request->id);
    	return $this->sendResponse($user);
    }
    
    public function searchUser(Request $request)
    {
        $keyword = $request->keyword;
        $search = User::where('tipe', 'user')->where('name',"LIKE","%$keyword%")->paginate(20);
        return $this->sendResponse($search);
    }

}
