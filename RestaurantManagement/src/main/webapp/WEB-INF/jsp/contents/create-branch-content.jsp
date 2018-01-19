<%-- 
    Document   : create-branch-content
    Created on : Nov 8, 2017, 6:29:14 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="#">Branch managament</a>
    </li>
    <li class="breadcrumb-item active">New branch</li>
</ol>

<div class="card mb-3">
    <div class="card-header">
        <i class="fa fa-table"></i> New branch</div>
    <div class="card-body">
        <form>
            <div class="form-group">
                <label for="branchNameInput">Tên chi nhánh </label>
                <input class="form-control" id="branchNameInput" type="text" aria-describedby="...">
            </div>
            <div class="form-group">
                <label for="branchAddressInput">Địa chỉ</label>
                <input class="form-control" id="branchAddressInput" type="text">
            </div>
            <div class="form-group">
                <label for="branchPhoneInput">Số điện thoại</label>
                <input class="form-control" id="branchPhoneInput" type="text">
            </div>
            <div class="form-group">
                <label for="branchAreaInput">Tỉnh / Thành phố</label>
                <input class="form-control" id="branchAreaInput" type="text">
            </div>
            <div class="form-group">
                <label for="branchTableCountInput">Số lượng bàn</label>
                <input class="form-control" id="branchTableCountInput" type="number">
            </div>
            <a class="btn btn-primary btn-block" href="/RestaurantManagement/home">Thêm</a>
        </form>
    </div>
</div>