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

    <jsp:include page="_header.jsp" />




    <div class="container">
        <ul class="breadcrumb"><li><a href="/">Főoldal</a></li>
            <li class="active">Számítási konfigurációk</li>
        </ul>

        <div ng-app="app" ng-view>
        </div>

        <div class="row">
            <div id='spinner' class="text-center col-md-12">
                <i class="fa fa-circle-o-notch fa-spin fa-5x"></i>
            </div>
        </div>

    </div>

</div>

<footer class="footer">
    <div class="container">
        <p class="pull-left">&copy; Innocenter Kft. 2015</p>

        <p class="pull-right"></p>
    </div>

</footer>


<!-- bower:js -->
<script src="vendor/bower/jquery/dist/jquery.js"></script>
<script src="vendor/bower/angularjs/angular.js"></script>
<script src="vendor/bower/bootstrap-sweetalert/lib/sweet-alert.js"></script>
<script src="vendor/bower/underscore/underscore.js"></script>
<script src="vendor/bower/angular/angular.js"></script>
<script src="vendor/bower/angular-route/angular-route.js"></script>
<script src="vendor/bower/angular-animate/angular-animate.js"></script>
<script src="vendor/bower/angular-sanitize/angular-sanitize.js"></script>
<script src="vendor/bower/wow/dist/wow.js"></script>
<script src="vendor/bower/jquery-easing/jquery.easing.js"></script>
<script src="vendor/bower/jquery-easing/jquery.easing.min.js"></script>
<script src="vendor/bower/bootstrap/dist/js/bootstrap.js"></script>
<script src="vendor/bower/angular-dragula/dist/angular-dragula.js"></script>
<script src="vendor/bower/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="vendor/bower/moment/moment.js"></script>
<!-- endbower -->

<!-- inject:js -->
<script src="/app/calculation-configuration/app.js"></script>
<script src="/app/calculation-configuration/config.js"></script>
<script src="/app/calculation-configuration/config.route.js"></script>
<script src="/app/calculation-configuration/controller.js"></script>
<script src="/app/calculation-configuration/directives/columnSelector.js"></script>
<script src="/app/calculation-configuration/directives/dsCard.js"></script>
<script src="/app/calculation-configuration/services/dataService.js"></script>
<!-- endinject -->


</body>
</html>
