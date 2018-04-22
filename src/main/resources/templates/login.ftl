<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- <link rel="icon" href="../../../../favicon.ico"> -->

    <title>兴趣使然的创意</title>

    <!-- Bootstrap core CSS -->
    <link href="/trd/bootstrap-4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/login/floating-labels.css" rel="stylesheet">
  </head>

  <body>
    <form class="form-signin" action="/login" method="POST">
      <div class="text-center mb-4">
        <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">兴趣使然的</h1>
        <p>这是一个兴趣使然的网站，想到什么点子，就会兴冲冲的加上去    ε=ε=(ノ≧∇≦)ノ</a></p>
      </div>

<!--       <div class="form-label-group">
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputEmail">邮箱</label>
      </div> -->
      
      <div class="form-label-group">
        <input type="text" id="inputText" class="form-control" name="username" placeholder="Text" required autofocus>
        <label for="inputText">账号</label>
      </div>

      <div class="form-label-group">
        <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Password" required>
        <label for="inputPassword">密码</label>
      </div>
      <!-- csrf防止跨域的token -->
      <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" id="remember-me" name="remember-me" checked="true"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">登    录</button>
      <p class="mt-5 mb-3 text-muted text-center">&copy; 2017-2018</p>
    </form>
  </body>
</html>
