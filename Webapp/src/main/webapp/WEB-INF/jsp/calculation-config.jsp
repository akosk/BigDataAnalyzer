<%@ page contentType="text/html; charset=UTF-8" %>

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

<jsp:include page="_footer.jsp"></jsp:include>
