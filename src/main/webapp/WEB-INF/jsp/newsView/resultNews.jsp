<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/doc.css">
    <style>
    body {
    font-family: 'Montserrat', sans-serif;
    background-color: #edf1f5;
    }
    .news {
    margin: auto;
    text-align: center;

    color: rgb(31, 11, 6);
    }

    .main-cont{
    display: flex;
    flex-direction: column-reverse;
    display: flex;
    flex-direction: row-reverse;
    }

    .img-section{
    width: 1200px;
    background-color: rgb(226, 223, 240);
    margin: 10px 10px auto 10px;
    color: rgb(4, 16, 29);
    text-align: left;

    }
    .menu{
    background-color: rgb(8, 2, 36);
    margin: 10px 5px 150px 10px;
    color: rgb(188, 191, 194);
    display: flex;
    padding: 0 100px 640px 17px;
    }
    .color1{
    color: rgb(15, 6, 32);

    }

    .color-news{
    text-align: center;
    margin: 1em 0 0.5em 0;
    font-weight: 600;
    font-family: 'Titillium Web', sans-serif;
    position:relative;
    font-size: 40px;
    line-height: 30px;
    padding: 15px 15px 15px 15%;
    color: #061429;
    box-shadow:
    inset 0 0 0 1px rgba(176, 180, 185, 0.4),
    inset 0 0 5px rgba(155, 158, 161, 0.5);
    border-radius : 0 10px 0 10px;


    }
    .color{
    color: rgb(188, 191, 194);
    }
    .color2{
    color: rgb(188, 191, 194);
    margin: 10px 5px 10px 12px;

    }
    </style>
</head>
<body>
<!--News section-->
<div class="news">
    <h1 class="color-news">News</h1>
</div>

<!--Main cont-->

<div class="main-cont">
    <!--img section-->
    <div class="img-section">
            <h3>${currentNews.infoMessage}</h3>
        <c:forEach var="currentArticle" items="${currentNews.articleList}">
            <h3 class="color1">${currentArticle.title}</h3>
            <img width="300" src="${currentArticle.urlToImage}" alt="foto">
            <p> ${currentArticle.description}</p>
            <a href="${currentArticle.url}">"Link to original source"</a>
        </c:forEach>

    </div>
    <!--menu-->
    <aside class="menu">
        <div>
            <h1 class="color">Choose news:</h1>
            <form:form action="checkNews" method="post">
                <c:forEach var="country" items="${currentNews.country}">
                    <h3>Choose category of News</h3>
                                <select name="country" id="country">
                                    <option country=ua></option>
                                    <option country=ua>ua</option>
                                    <option country=us>us</option>
                                    <option country=ru>ru</option>
                                </select>

                                <select name="category" id="category">
                                    <option category=one></option>
                                    <option category=science>science</option>
                                    <option category=sports>sports</option>
                                </select>
                    <h3></h3>
                </c:forEach>

                <input type="submit" value="Save">
            </form:form>
        </div>
    </aside>
</div>
</body>
</html>