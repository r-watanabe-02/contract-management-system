<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>契約情報一覧</title>
    
    <style>
        body {
            font-family: "Helvetica Neue", Arial, "Hiragino Kaku Gothic ProN", "Segoe UI", sans-serif;
            background-color: #f4f6f9;
            color: #333333;
            line-height: 1.6;
            margin: 40px;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
        }

        h2 {
            color: #0056b3;
            border-bottom: 2px solid #e9ecef;
            padding-bottom: 15px;
            margin-top: 0;
            margin-bottom: 25px;
        }

        .nav-link {
            display: inline-block;
            margin-bottom: 20px;
            color: #007bff;
            text-decoration: none;
            font-size: 14px;
        }
        .nav-link:hover {
            text-decoration: underline;
        }

        .modern-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
            font-size: 14px;
            border: 1px solid #ced4da; 
        }
        .modern-table th, .modern-table td {
            padding: 12px 14px;
            text-align: left;
            border: 1px solid #ced4da; 
        }
        .modern-table th {
            background-color: #f8fafc;
            color: #495057;
            font-weight: bold;
        }

        .modern-table tr:hover {
            background-color: #f8f9fa;
        }

        .btn-delete {
            color: #dc3545;
            text-decoration: none;
            font-weight: bold;
        }
        .btn-delete:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="container">
        
        <h2>契約情報一覧</h2>
        
        <a href="input.jsp" class="nav-link">▶ 契約情報入力画面</a>

        <table class="modern-table">
            <tr>
                <th style="width: 12%;">ID</th>
                <th style="width: 35%;">契約者名</th>
                <th style="width: 15%;">年齢</th>
                <th style="width: 23%;">プラン</th>
                <th style="width: 15%;">操作</th>
            </tr>
            <logic:iterate id="contract" name="contractList">
                <tr>
                    <td><bean:write name="contract" property="id" /></td>
                    <td><strong><bean:write name="contract" property="name" /></strong></td>
                    <td><bean:write name="contract" property="age" /> 歳</td>
                    <td><bean:write name="contract" property="planName" /></td>
                    <td>
                        <a href="contractDelete.do?id=<bean:write name="contract" property="id" />" 
                           class="btn-delete"
                           onclick="return confirm('本当に削除しますか？');">削除</a>
                    </td>
                </tr>
            </logic:iterate>
        </table>
        
    </div>

</body>
</html>