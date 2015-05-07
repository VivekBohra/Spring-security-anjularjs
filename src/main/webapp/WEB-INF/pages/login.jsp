<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
    <head><link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/uniform.default.css" rel="stylesheet" type="text/css"/>
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL STYLES -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/DT_bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/select2_metro.css"/>
        <!-- END PAGE LEVEL STYLES -->
        <!-- BEGIN THEME STYLES -->
        <link href="${pageContext.request.contextPath}/resources/css/style-metronic.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/style-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/plugins.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
        <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet" type="text/css"/>
        <title>Login Page</title>
        <style>
            .error {
                padding: 15px;
                margin-bottom: 20px;
                border: 1px solid transparent;
                border-radius: 4px;
                color: #a94442;
                background-color: #f2dede;
                border-color: #ebccd1;
            }

            .msg {
                padding: 15px;
                margin-bottom: 20px;
                border: 1px solid transparent;
                border-radius: 4px;
                color: #31708f;
                background-color: #d9edf7;
                border-color: #bce8f1;
            }

            #login-box {
                width: 300px;
                padding: 20px;
                margin: 100px auto;
                background: #fff;
                -webkit-border-radius: 2px;
                -moz-border-radius: 2px;
                border: 1px solid #000;
            }
        </style>
    </head>
    <body onload='document.loginForm.username.focus();'>

        <h3>Spring Security Login Form (Database Authentication)</h3>




        <!-- Login Form -->
        <div class="row ">



            <div class="col-md-offset-3 col-md-6">
                <!-- BEGIN SAMPLE FORM PORTLET-->
                <div class="portlet box light-grey">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-globe"></i> Login Here
                        </div>
                        <!--<div class="tools">
                                <a href="" class="collapse"></a>								
                                <a href="" class="remove"></a>
                        </div>-->
                    </div>
                    <div class="portlet-body">

                        <div style="min-height: 30px">  <c:if test="${not empty error}">
                                <div class="error">  ${error}</div>
                            </c:if>
                            <c:if test="${not empty msg}">
                                ${msg}
                            </c:if>
                        </div>
                        <form class="form-horizontal" role="form" name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
                            <div class="form-group">
                                <label for="inputEmail1" class="col-md-4 control-label">Email</label>
                                <div class="col-md-6">
                                    <input type='text' class="form-control" id="inputEmail1" name='username' placeholder="username">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword12" class="col-md-4 control-label">Password</label>
                                <div class="col-md-6">
                                    <input type="password" class="form-control" id="inputPassword12" name='password' placeholder="Password">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-4">

                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-10">
                                    <button type="submit" value="submit" class="btn blue ">Sign in</button>
                                </div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                        </form>							
                    </div>
                </div>
                <!-- END SAMPLE FORM PORTLET-->
            </div>
        </div>
        <!-- Login Form End -->

        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.cokie.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.uniform.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/select2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular-animate.js"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/DT_bootstrap.js"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/table-managed.js"></script>

    </body>
</html>