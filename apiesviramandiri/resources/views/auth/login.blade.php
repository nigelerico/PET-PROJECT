<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>@yield('title') - {{ config('app.name') }}</title>
    <link href="https://fonts.googleapis.com/css?family=Cabin:400,600|Raleway:400,700" rel="stylesheet">
    <link rel="stylesheet" href="{{ asset('/css/bootstrap.min.css') }}">
    <link rel="stylesheet" href="{{ asset('/css/auth.css') }}">
</head>
<body>
    <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-2"></div>
        <div class="col-lg-4 col-md-4 col-sm-8">
            <div class="box" style="background-color: #F0F0ED;">
                <div class="web-logo">
                    <a href="/"><img src="{{ asset('img/logoesvira.png') }}" style="width: 40%;"></a>
                </div>
                <hr>
                <div class="content">
                    <h1>LOGIN</h1>
                        <form rule="form" method="POST" action="{{ route('login') }}">
                        {{ csrf_field() }}

                            <div class="form-group{{ $errors->has('email') ? ' has-error' : '' }}">
                                <input id="email" type="email" class="form-control input-lg" required autofocus placeholder="E-mail" 
                                    name="email" value="{{ old('email') }}">

                                @if ($errors->has('email'))
                                    <span class="help-block">
                                        <strong>{{ $errors->first('email') }}</strong>
                                    </span>
                                @endif
                            </div>

                            <div class="form-group{{ $errors->has('password') ? ' has-error' : '' }}">  
                                <input id="password" type="password" class="form-control input-lg" required placeholder="Password" 
                                    name="password">

                                @if ($errors->has('password'))
                                    <span class="help-block">
                                        <strong>{{ $errors->first('password') }}</strong>
                                    </span>
                                @endif
                            </div>
                        
                            <div class="form-group">
                                <button type="submit" class="btn btn-default btn-lg btn-modif">
                                    LOGIN
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-2"></div>
    </div>

<script src="{{ asset('/js/jquery.min.js') }}"></script>
<script src="{{ asset('/js/bootstrap.min.js') }}"></script>

</body>
</html>