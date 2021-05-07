<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>

<body>

    <div class="jumbotron">
        <h2>Gestión de clientes (Spring Security)</h2>
    </div>

    <div class="row">

        <div class="col-4 offset-4">
            <div class="card card-primary">
                <div class="card-header">Login</div>
                <div class="card-body">

                    <g:form controller="login" action="authenticate" method="POST">

                        <div class="form-horizontal" id="formulario">
                            <div class="form-group">
                                <label class="control-label col-xs-2" for="username">Login</label>
                                <div class="col-xs-10">
                                    <input name="username" type="text" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2" for="password">Pw</label>
                                <div class="col-xs-10">
                                    <input name="password" type="text" class="form-control"/>
                                </div>
                            </div>
                        </div>

                        <div id="error">
                            ${flash.message}
                        </div>

                        <div class="text-center">
                            <input type="submit" id="btnLogin" class="btn mr-1 btn-primary" style="width:110px" value="Login" />
                        </div>

                    </g:form>

                </div>
            </div>
        </div>
    </div>

</body>
</html>