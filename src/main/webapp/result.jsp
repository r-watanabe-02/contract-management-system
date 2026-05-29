<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登録結果</title>
    
    <style>
        body {
            font-family: "Helvetica Neue", Arial, "Hiragino Kaku Gothic ProN", "Segoe UI", sans-serif;
            background-color: #f4f6f9;
            color: #333333;
            line-height: 1.6;
            margin: 40px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px; /* 角丸を統一 */
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05); /* 軽い立体感の影 */
        }

        h2 {
            color: #0056b3;
            border-bottom: 2px solid #e9ecef;
            padding-bottom: 15px;
            margin-top: 0;
            margin-bottom: 20px;
        }

        .success-banner {
            background-color: #d4edda;
            color: #155724;
            padding: 14px;
            border-radius: 6px;
            margin-bottom: 25px;
            border: 1px solid #c3e6cb;
            font-size: 15px;
            font-weight: bold;
        }

        .result-box {
            background-color: #f8fafc;
            border: 1px solid #e2e8f0;
            border-radius: 6px;
            padding: 10px 20px;
            margin-bottom: 30px;
        }

        .result-row {
            padding: 12px 0;
            display: flex;
            justify-content: space-between;
            border-bottom: 1px solid #e2e8f0;
        }
        .result-row:last-child {
            border-bottom: none;
        }

        .result-label {
            font-weight: bold;
            color: #64748b;
        }

        .result-value {
            font-weight: 500;
            color: #1e293b;
        }

        .nav-link {
            display: inline-block;
            color: #007bff;
            text-decoration: none;
            font-size: 14px;
        }
        .nav-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="container">
        
        <h2>登録完了</h2>
        
        <div class="success-banner">
            ✓ 以下の内容で正常に受け付けました。
        </div>
        
        <div class="result-box">
            
            <div class="result-row">
                <span class="result-label">契約者名</span>
                <span class="result-value">
                    <strong><bean:write name="contractForm" property="contractorName" /></strong>
                </span>
            </div>
            
            <div class="result-row">
                <span class="result-label">年齢</span>
                <span class="result-value">
                    <bean:write name="contractForm" property="age" /> 歳
                </span>
            </div>
            
            <div class="result-row">
                <span class="result-label">選択プラン</span>
                <span class="result-value">
                    <bean:write name="planName" />
                </span>
            </div>
            
        </div>
        
        <a href="input.jsp" class="nav-link">◀ 契約情報入力画面</a>
        
    </div>

</body>
</html>