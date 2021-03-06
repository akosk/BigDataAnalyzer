<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="hu-HU">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- bower:css -->
    <link rel="stylesheet" href="vendor/bower/bootstrap-sweetalert/lib/sweet-alert.css" />
    <link rel="stylesheet" href="vendor/bower/fontawesome/css/font-awesome.css" />
    <link rel="stylesheet" href="vendor/bower/animate.css/animate.css" />
    <link rel="stylesheet" href="vendor/bower/bootstrap/dist/css/bootstrap.css" />
    <link rel="stylesheet" href="vendor/bower/angular-dragula/dist/dragula.css" />
    <!-- endbower -->

    <link rel="stylesheet" href="css/site.css"/>
    <link rel="stylesheet" href="css/header.css"/>
    <link rel="stylesheet" href="css/navbar.css"/>
</head>

<body>


<div class="wrap">

<div class="pageHeader" id="pageHeader">
    <div class="container">
        <div class="row">
            <div class="headerLogo headerGeoTeam col-md-2 col-xs-4">
                <a href="http://geoteam.innocenter.hu/koszonto">
                    <img id="geoTeamLogo" src="http://geoteam.innocenter.hu/template/geoteam/image/geoTeamLogo.png">
                </a>
            </div>
            <div class="col-md-5 hidden-xs">
                <p>Interdiszciplináris kutatói teamek létrehozása és felkészítése a nemzetközi programokban való
                    részvételre környezetbiztonsági kutatási területeken</p>

                <p>TÁMOP-4.2.2.D-15/1/KONV-2015-0030</p>
            </div>
            <div class="headerLogo col-md-2 col-xs-4">
                <a href="http://www.uni-miskolc.hu">
                    <img src="http://geoteam.innocenter.hu/template/geoteam/image/uniMiskolcLogo.jpg"
                         id="headerUniMiskolc">
                </a>

                <a href="http://www.innocenter.hu">
                    <img id="innocenterLogo"
                         src="http://geoteam.innocenter.hu/template/geoteam/image/innocenterLogo.png">
                </a>
            </div>

            <div class="headerLogo headerLogoSz2020 col-md-3 col-xs-4">
                <img src="http://geoteam.innocenter.hu/template/geoteam/image/sz2020.png">

            </div>
        </div>
    </div>
</div>

<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/data-manager.html"><i class="fa fa-cloud-upload"></i> Adatfeltöltés</a></li>
                <li><a href="/calculation-config.html"><i class="fa fa-cog"></i> Számítási konfigurációk</a></li>
                <li><a href="/tasks.html"><i class="fa fa-tasks"></i> Feladatok</a></li>
            </ul>
        </div>
    </div>
</nav>