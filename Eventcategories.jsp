<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /*<!-- categories-->*/

            .categories
            {
                margin:40px 0;
            }
            .small-container
            {
                max-width: 1080px;
                margin:auto;
                padding-left: 25px;
                padding-right: 25px;
            }

            .title{
                text-align: center;
                margin: 0 auto 80px;
                position: relative;
                line-height: 60px;
                color: #555;
            }

            .title::after{
                content: '';
                background: orangered;
                width: 100px;
                height: 5px;
                border-radius: 15px;
                position: absolute;
                bottom: 0;
                left: 50%;
                transform: translateX(-50%);
            }
            .row{
                display: flex;
                align-items:center;
                flex-wrap: wrap;
                justify-content:space-around;
            }

            .categories .col-3{
                flex-basis: 30%;
                min-width: 250px;
                margin-bottom: 0px;

                text-align: center;
                padding: 40px 10px;
                /*    box-shadow: 0 0 20px 0px rgba(0,0,0,0.1);*/
                cursor: pointer;
                transition: transform 0.5s;

            }

            .categories .col-3 img
            {
                width: 150px;
                height:150px;
                margin-top: 20px;
                border-radius: 50%;
            }

            .categories .col-3:hover
            {
                transform: translateY(-10px);
            }

        </style>
    </head>
    <body>
    <%@include file="Header.jsp"%>
     <div class="categories">
            <div class="small-container">
                <h2 class="title">Events</h2>
                <div class="row">
                    <div class="col-3">
                        <a href="Events.jsp?event_category=Birthday"> <img src="images/Event1.jpeg"><br>Birthday</a>
                    </div>
                    <div class="col-3">
                        <a href="Events.jsp?event_category=Wedding"> <img src="images/Beach1wedd.jpg"><br>Wedding</a>
                    </div>

                    <div class="col-3">
                        <a href="Events.jsp?event_category=Corporate"> <img src="images/Partyphoto.jpg"><br>Corporate Events</a>
                    </div>

                </div>
            </div>
        </div>
      <%@include file="Footer.jsp"%>  
    </body>
</html>
