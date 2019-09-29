<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>@yield('title') - {{ config('app.name') }}</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
<script>
// Add active class to the current button (highlight it)
$(document).ready(function(){
    $('menu').click(function(){
        $('menu').removeClass("active");
        $(this).addClass("active");
    });
  });
</script>
    <link rel="stylesheet" href="{{ asset('/template/bower_components/font-awesome/css/font-awesome.min.css') }}">
    <link rel="stylesheet" href="{{ asset('/template/bower_components/Ionicons/css/ionicons.min.css') }}">
    <link href="https://fonts.googleapis.com/css?family=Cabin:400,600|Raleway:400,700" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <link rel="stylesheet" href="{{ asset('/css/bootstrap.min.css') }}">
    <link rel="stylesheet" href="{{ asset('/css/style.css') }}">
    
<body>
    <header>
        <div class="row">
            <div class="col-lg-2 icon">
                <a style="text-decoration: none" href="/"><h5 style="color: white; margin-top:27px; margin-left: 35px;">ADMIN ESVIRA</h5></a>
            </div>
            <div class="col-lg-7"></div>
            <div class="col-lg-3 icon">
                {{-- <a href="/chat"><img src="{{ asset('img/message-white.png') }}" style="width: 45px; margin: 20px 10px 20px 100px; float: left;" ></a> --}}
                <a href="/"><img src="{{ asset('img/admin.png') }}" style="width: 36px; margin: 19px 20px 20px 5px; float: left; pointer-events: none;cursor: default; "></a> 
            <div class="btn-group" style="margin-top: 20px;">
                <button type="button" class="btn btn-logout dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: white;">
                    Hello,{{ Auth::User()->name }}
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="/logout">Logout</a>
                </div>
            </div>

            </div>
            
        </div>  
</div>
    </header>
    <section class="sidebar">
        <div class="row">
            <div class="col-lg-2" style="background-color: #222d32">
                <div id="myMenu" class="section-left-side ">
                    <a class="menu" href="/manage-buku"><h6 id="manage-buku-txt"><i class="fa fa-book"></i>  Manage Buku</h6></a>
                    <hr>
                    <a class="menu" href="/manage-transaksi"><h6 id="manage-transaksi-txt"><i class="fa fa-edit"></i>  Manage Transaksi </h6></a>
                    <hr>
                    <a class="menu" href="/manage-user"><h6 id="manage-user-txt"><i class="fa fa-user"></i>  Manage User</h6></a>
                </div>
            </div>
            <div class="col-lg-10">
                <div class="section-core">
                    @yield('content')
                </div>
            </div>
        </div>
    </section>
    <footer>
        <img src="{{ asset('img/logoesvira.png') }}" style="height:40px ; float: left;">
        <h5 style="color: black; padding-top: 10px; margin-left: 50px;">Copyright CV Esvira Mandiri 2019</h5>
    </footer>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script src="{{ asset('/js/manage-buku.js') }}"></script>
<script src="{{ asset('/js/manage-user.js') }}"></script>

</html>