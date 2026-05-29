<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>契約情報入力</title>
    
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    
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
            border-radius: 8px; /* 角を丸く */
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
        }

        h2 {
            color: #0056b3;
            border-bottom: 2px solid #e9ecef;
            padding-bottom: 15px;
            margin-top: 0;
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            color: #495057;
        }

        input[type="text"], select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
            background-color: #ffffff;
        }

        input[type="text"]:focus, select:focus {
            border-color: #80bdff;
            outline: 0;
            box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
        }

        input.input-age {
            width: 80px;
            display: inline-block;
        }

        .input-with-btn {
            display: table;
            width: 100%;
        }
        .input-cell {
            display: table-cell;
            width: 100%;
        }
        .btn-cell {
            display: table-cell;
            white-space: nowrap;
            padding-left: 10px;
            vertical-align: top;
        }

        .btn {
            padding: 10px 20px;
            font-size: 14px;
            font-weight: bold;
            border-radius: 4px;
            cursor: pointer;
            border: none;
            transition: background-color 0.2s;
        }

        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
        }

        .btn-primary {
            background-color: #007bff;
            color: white;
            width: 100%;
            font-size: 16px;
            margin-top: 15px;
        }
        .btn-primary:hover {
            background-color: #0069d9;
        }

        .message-span {
            display: block;
            margin-top: 6px;
            font-size: 13px;
        }

        .error-container {
            background-color: #f8d7da;
            color: #721c24;
            padding: 12px;
            border-radius: 4px;
            margin-bottom: 20px;
            border: 1px solid #f5c6cb;
            font-size: 14px;
        }

        .nav-link {
            display: inline-block;
            margin-bottom: 20px;
            color: #007bff;
            text-decoration: none;
        }
        .nav-link:hover {
            text-decoration: underline;
        }
    </style>
    
    <script>
    function checkDuplicateName() {
        // 1. 入力値の取得
        var nameValue = $("#contractorNameField").val();
        var $messageArea = $("#nameCheckMessage"); // 変数の頭に$を付けるとjQueryオブジェクトだと分かりやすい
        
        if(!nameValue) {
            $messageArea.text("⚠ 名前を入力してください");
            $messageArea.css("color", "#dc3545");
            return;
        }

        $messageArea.text("⏳ チェック中...");
        $messageArea.css("color", "#6c757d");

        // 2. Ajax通信の実行
        $.ajax({
            url: "checkName.do",                 // 送信先のURL
            type: "GET",                        // HTTP通信の種類
            data: { name: nameValue },          // サーバーに送るデータ（自動でエンコードされる）
            dataType: "text"                    // サーバーから返ってくるデータの形式（文字列）
        })
        .done(function(result) {
            // 通信成功時の処理
            // ※Java側から改行コードなどの余計な空白が返るバグを防ぐため、.trim() を挟む
            if (result.trim() === "NG") {
                $messageArea.text("❌ この名前はすでに登録されています！");
                $messageArea.css("color", "#dc3545");
            } else {
                $messageArea.text("⭕ 登録可能な名前です。");
                $messageArea.css("color", "#28a745");
            }
        })
        .fail(function(error) {
            // 通信エラー時の処理
            console.error("通信エラー:", error);
            $messageArea.text("❌ 通信エラーが発生しました");
            $messageArea.css("color", "#dc3545");
        });
    }
    </script>
</head>
<body>

    <div class="container">
        <h2>契約情報入力</h2>
        
        <a href="contractList.do" class="nav-link">◀ 契約情報一覧</a>

        <html:messages id="err" property="ageError">
            <div class="error-container">
                <strong>入力エラー：</strong> <html:errors property="ageError" />
            </div>
        </html:messages>

        <html:form action="/contract">
            
            <div class="form-group">
                <label for="contractorNameField">契約者名</label>
                <div class="input-with-btn">
                    <div class="input-cell">
                        <html:text property="contractorName" styleId="contractorNameField" />
                    </div>
                    <div class="btn-cell">
                        <button type="button" class="btn btn-secondary" onclick="checkDuplicateName()">重複チェック</button>
                    </div>
                </div>
                <span id="nameCheckMessage" class="message-span"></span>
            </div>
            
            <div class="form-group">
                <label>年齢</label>
                <html:text property="age" size="3" styleClass="input-age" /> 歳
            </div>
            
            <div class="form-group">
                <label>プラン</label>
                <html:select property="plan">
                    <html:option value="standard"><bean:message key="plan.standard" /></html:option>
                    <html:option value="premium"><bean:message key="plan.premium" /></html:option>
                    <html:option value="economy"><bean:message key="plan.economy" /></html:option>
                </html:select>
            </div>
            
            <html:submit value="この内容で送信する" styleClass="btn btn-primary" />
            
        </html:form>
    </div>

</body>
</html>