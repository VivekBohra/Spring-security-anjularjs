<%-- 
    Document   : index
    Created on : Apr 17, 2015, 1:55:12 PM
    Author     : Vivek
--%>
<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html ng-app="taskManagerApp">
   
    <!-- BEGIN HEAD -->
    <head>



        <meta charset="utf-8"/>

        <title>CMS</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
        <meta content="" name="description"/>
        <meta content="" name="author"/>
        <meta name="MobileOptimized" content="320">
        <!-- BEGIN GLOBAL MANDATORY STYLES -->

        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
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

        <script data-require="angular.js@*" data-semver="1.2.13" src="${pageContext.request.contextPath}/resources/js/angular.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/angular-animate.js"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom_app.js"></script>

        <!-- END THEME STYLES -->
        <!--<link rel="shortcut icon" href="favicon.ico"/>-->
    </head>
    <!-- END HEAD -->
    <!-- BEGIN BODY -->
    <body class="page-header-fixed">
        <!-- BEGIN HEADER -->
        <div class="header navbar navbar-inverse navbar-fixed-top">
            <!-- BEGIN TOP NAVIGATION BAR -->
            <div class="header-inner">
                <!-- BEGIN LOGO -->
                <a class="navbar-brand" href="javascript:void(0);" style="padding:15px">
                    SPRING + ANGULAR JS
                </a>		
                <!-- END LOGO -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <img src="${pageContext.request.contextPath}/resources/img/menu-toggler.png" alt=""/>
                </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN TOP NAVIGATION MENU -->
                <ul class="nav navbar-nav pull-right">

                    <!-- BEGIN USER LOGIN DROPDOWN -->
                    <li class="dropdown user">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                            <img alt="" src="${pageContext.request.contextPath}/resources/img/avatar1_small.jpg"/>
                            <span class="username">
                                ${pageContext.request.userPrincipal.name}
                            </span>
                            <i class="fa fa-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu">															
                            <li>
                                <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                                    <!-- For login user -->
                                    <c:url value="/j_spring_security_logout" var="logoutUrl" />
                                    <form action="${logoutUrl}" method="post" id="logoutForm">
                                        <input type="hidden" name="${_csrf.parameterName}"
                                               value="${_csrf.token}" />
                                    </form>
                                    <script>
                                        function formSubmit() {
                                            document.getElementById("logoutForm").submit();
                                        }
                                    </script>

                                    <c:if test="${pageContext.request.userPrincipal.name != null}">

                                        <a
                                            href="javascript:formSubmit()"> <i class="fa fa-key"></i>Logout</a>

                                    </c:if>


                                </sec:authorize>
                                <!--<a href="login.html"><i class="fa fa-key"></i> Log Out</a>-->
                            </li>
                        </ul>
                    </li>
                    <!-- END USER LOGIN DROPDOWN -->
                </ul>
                <!-- END TOP NAVIGATION MENU -->
            </div>
            <!-- END TOP NAVIGATION BAR -->
        </div>
        <!-- END HEADER -->

        <!-- END HEAD -->
        <!-- BEGIN BODY -->
        <div>

        </div>
        <div class="clearfix">
        </div>

        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <div class="page-content" style="margin-left:0px;min-height:700px">

                    <!-- BEGIN PAGE HEADER-->
                    <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                            <ul class="page-breadcrumb breadcrumb">						
                                <li>
                                    <i class="fa fa-home"></i>

                                    Content Management System 
                                    <i class="fa fa-angle-right"></i>
                                </li>
                               
                            </ul>
                            <!-- END PAGE TITLE & BREADCRUMB-->
                        </div>
                    </div>
                    <!-- END PAGE HEADER-->




                    <hr>

                    <!-- Manage Contact CONTENT-->			
                    <div class="row" ng-controller="AppController" >
                        <!--<div class="hide col-md-12" ng-show="toggle">-->
                        <div id="grid" class="col-md-12" >
                            <!-- BEGIN EXAMPLE TABLE PORTLET-->
                            <div class="portlet box light-grey">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="fa fa-globe"></i>{{test}} Managed Users Contacts
                                    </div>
                                    <div class="tools">
                                        <a href="javascript:;" class="collapse"></a>
                                        <!-- <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                        <a href="javascript:;" class="reload"></a> -->
                                        <a href="javascript:;" class="remove"></a>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="table-toolbar">
                                        <div class="btn-group">
                                            <button id="sample_editable_1_new" class="btn green" ng-click="enableaddTask('Add');">
                                                <!--  <button id="sample_editable_1_new" class="btn green" ng-click="enableaddTask();toggle = !toggle">-->
                                                Add New <i class="fa fa-plus"></i>
                                            </button>									
                                        </div>	

                                    </div>

                                    <table class="table table-striped table-bordered table-hover user_tb" >
                                        <thead>
                                            <tr>
                                                <th class="table-checkbox">#</th>							
                                                <th>First Name</th>
                                                <th>Last Name</th>
                                                <th>Mobile Number</th>
                                                <th>Email</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>

                                        <tbody ng-repeat="task in tasks"  >

                                            <tr>
                                                <td>{{task.id}}</td>
                                                <td>{{task.first_name}}</td>
                                                <td>{{task.last_name}}</td>
                                                <td>{{task.mobile_number}}</td>
                                                <td>{{task.email}}</td>

                                                <td> 

                                                    <sec:authorize access="hasAnyRole('ROLE_ADMIN')"> <button id="{{task.id}}" value="{{task.id}}" ng-click="deleteUser(task.id)" class="btn btn-sm red" >Delete</button></sec:authorize>
                                                        <!--<button id="{{task.id}}" value="{{task.id}}" ng-click="deleteUser(task.id)" class="btn btn-sm red" >Delete</button>-->
                                                    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">   <button id="{{task.id}}"  value="{{task.id}}" ng-click="getTask(task.id)" class="btn btn-sm blue" >Edit</button></sec:authorize>

                                                    </td>
                                                </tr>


                                            </tbody>

                                        </table>
                                    </div>
                                </div>
                                <!-- END EXAMPLE TABLE PORTLET-->
                            </div>		


                            <!-- add user1 -->
                            <div ng-show="divEmployee">
                                <p class="divHead"> Contacts</p>
                                <div class="col-md-offset-3 col-md-6">
                                    <!-- BEGIN SAMPLE FORM PORTLET-->
                                    <div class="portlet box light-grey">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-globe"></i>{{Action}} User
                                            </div>
                                            <div class="tools">
                                                <a href="" class="collapse"></a>								
                                                <a href="" class="remove"></a>
                                            </div>
                                        </div>
                                        <div class="portlet-body">
                                            <p>&nbsp;</p>
                                            <form class="form-horizontal" name="myForm" role="form" novalidate >


                                                <div class="form-group">


                                                    <input type="hidden" disabled="disabled" ng-model="id" class="form-control" id="ids" >

                                                </div>
                                                <div class="form-group">
                                                    <label for="first_name" class="col-md-4 control-label">First Name</label>
                                                    <div class="col-md-6">
                                                        <input type="text" ng-model="first_name"  name="first_name"  ng-minlength="10" placeholder="first Name" class="form-control" required>


                                                        <span style="color:red" ng-show="myForm.first_name.$dirty && myForm.first_name.$invalid">
                                                            <span ng-show="myForm.first_name.$dirty && myForm.first_name.$error.required">first name is required</span>
                                                            <span class="error" ng-show="myForm.first_name.$error.minlength"> less that 10 char.</span>


                                                        </span>
                                                    </div>
                                                </div>								
                                                <div class="form-group">
                                                    <label for="last_name" class="col-md-4 control-label">Last Name</label>
                                                    <div class="col-md-6">
                                                        <input type="text" ng-model="last_name" class="form-control" id="last_name" placeholder="Last Name">
                                                    </div>
                                                </div>								

                                                <div class="form-group">
                                                    <label for="mobile_number" class="col-md-4 control-label">Mobile Number</label>
                                                    <div class="col-md-6">
                                                        <!--<input type="text" ng-model="mobile_number" class="form-control" id="mobile_number" placeholder="Mobile Number">-->

                                                        <div>
                                                            <input type="text" class="form-control" placeholder="+91-636-78658" name="phone" ng-pattern="phoneNumbr" ng-model="mobile_number" />
                                                            <span class="error" ng-show="myForm.phone.$error.required">Required!</span>
                                                            <span class="error" ng-show="myForm.phone.$error.minlength">Phone no not less that 10 char.</span>
                                                            <span class="error" ng-show="myForm.phone.$error.maxlength">Phone no not more than 11 char.</span>
                                                            <br><span class="error" ng-show="myForm.phone.$error.pattern">Please match pattern [+91-036-78658 || 91-036-78658]</span>
                                                        </div>
                                                    </div>
                                                </div>								
                                                <div class="form-group">
                                                    <label for="email" class="col-md-4 control-label">Email</label>
                                                    <div class="col-md-6">
                                                        <!--<input type="text" ng-model="email" class="form-control" id="email" placeholder="Email">-->
                                                        <input type="email"  class="form-control" placeholder="Email" name="email" ng-model="email" >
                                                        <span style="color:red" ng-show="myForm.email.$dirty && myForm.email.$invalid">
                                                            <span ng-show="myForm.email.$error.required">Email is required.</span>
                                                            <span ng-show="myForm.email.$error.email">Invalid email address.</span>
                                                        </span>
                                                    </div>
                                                </div>			
                                                <hr>					
                                                <div class="form-group">
                                                    <div class="col-md-offset-4 col-md-10">
                                                        <button ng-click="addTask()" class="btn green" ng-disabled="myForm.first_name.$dirty && myForm.first_name.$invalid || myForm.email.$dirty && myForm.email.$invalid" >Submit</button>

                                                        <button type="button" class="btn default" ng-click="disabledaddTask()">Cancel</button>
                                                    </div>
                                                </div>
                                            </form>							
                                        </div>
                                    </div>
                                    <!-- END SAMPLE FORM PORTLET-->
                                </div>
                            </div>
                            <!-- add user1end -->




                        </div>


                        <hr>




                    </div>
                </div>
                <!-- END CONTENT -->
            </div>
            <!-- END CONTAINER -->
            <!-- BEGIN FOOTER -->
            <div class="footer">
                <div class="footer-inner">
                    2015 &copy; CMS by Technosoft Ltd.
                </div>
                <div class="footer-tools">
                    <span class="go-top">
                        <i class="fa fa-angle-up"></i>
                    </span>
                </div>
            </div>
            <!-- END FOOTER -->
            <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
            <!-- BEGIN CORE PLUGINS -->
            <!--[if lt IE 9]>
            <script src="assets/plugins/respond.min.js"></script>
            <script src="assets/plugins/excanvas.min.js"></script> 
            <![endif]-->

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
        <script>
                                                            var token = $("meta[name='_csrf']").attr("content");
                                                            if (!token)
                                                                token = "";
                                                            var header = $("meta[name='_csrf_header']").attr("content");
                                                            jQuery(document).ready(function() {
                                                                var test = "dfgd";
                                                                App.init();
                                                                TableManaged.init();
                                                            });
        </script>
    </body>
    <!-- END BODY -->
</html>
