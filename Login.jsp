
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <script type = "text/javascript" src = "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
    </head>
    <body bgcolor="#6dbac0">
        <%@include file="Header.jsp"%>
        <script>
            jQuery.validator.addMethod("checkemail", function(value, element) {
                return /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(value) || /^[0-9]{10}$/.test(value);
            }, "Please enter the email format as abc@gmail.com");

            jQuery(document).ready(function($) {
                $("#login").validate({
                    rules: {
                        email: {
                            required: true,
                            checkemail: true
                        },
                        pw: {
                            required: true,
                            minlength: 6
                        },
                    },
                    messages: {
                        email: {
                            required: "Please enter the email.",
                        },
                        pw: {
                            required: "Please enter the password.",
                            minlength: "Please enter the password greater than or equal to 6.",
                        },
                    }
                });
            });



        </script>
        <style>
        .login{
            border:1px solid black;
            width: 400px;
            height: 450px;
            background: url('') ;
            color: white;
            border-radius: 20px;
            box-shadow: 0px 0px 20px rgba(0,0,0,0.75);
            background-size: cover;
            background-position: center;
            overflow: hidden;
            margin-top:40px ;
         form{
            display: block;
           /*  box-sizing: border-box; */
            padding: 20px;
            width: 100%;
            height: 100%;
            backdrop-filter: brightness(40%);
            flex-direction: column;
            display: flex;
            gap: 5px;
        }
        #msg{
                background: green;
                color:black;
                border: 1px solid green;
                width:100%;
                font-weight: bold;
                font-size: 25px;
                padding: 5px;
            }
        h1{
            font-weight: normal;
            font-size: 40px;
            text-shadow: 0px 0px 2px rgba(0,0,0,0.5);
            margin-bottom: 30px;
        }
        label{
            color: rgba(255,255,255,0.8);
            text-transform: uppercase;
            font-size: 15px;
            letter-spacing: 2px;
            padding-left: 10px;
        }
        input{
            background: rgba(183, 165, 165, 0.759);
            height: 30px;
            line-height: 40px;
            border-radius: 20px;
            padding: 0px 10px;
            border: none;
            margin-bottom: 20px;
            color: white;
        
        }
        button{
            background: rgb(45,126,231);
            height: 40px;
            line-height: 40px;
            border-radius: 40px;
            border: none;
            margin: 10px 0px;
            box-shadow: 0px 0px 5px rgba(0,0,0,0.3);
            color: white;
            font-size: 12px;
            text-transform: uppercase;

        }
           /*  .error{
                color:red;
            }
            .button{
                padding: 5px;
                width: 6%;
                background: cornflowerblue;
                color: white;
            }

            #msg
            {
                background: green;
                color:black;
                border: 1px solid green;
                width:24%;
                font-weight: bold;
                font-size: 25px;
                padding: 5px;
            }
            td input{
                display:block;
            } */
        </style>

    <center>

        
       
    <div class="login">
    <% if (request.getAttribute("status") != null) {%>
        <div id="msg">  <%= request.getAttribute("status")%></div>
        <%}%>
    <form method="POST" id="login" action="register">
        <h1>Login</h1>
        <label>Email</label>
        <input type="email" placeholder="Enter your mail" name="email"/>
        <label>Password</label>
        <input type="text" placeholder="Enter your password" name="pw"/>
        <button type="submit" name="login">Login</button>
        
   
</form>
</div>

        <%-- <% if (request.getAttribute("status") != null) {%>
        <div id="msg">  <%= request.getAttribute("status")%></div>
        <%}%>

        <form method="POST" id="login" action="register">
            <font color="blue" size="4">
            <h2> Login  </h2>
            </font>
            <br>
            <div class="container ">
                <div class="form-group col-md-4">
                    <label >Email address</label>
                    <input type="email" class="form-control" placeholder="Enter email"  name="email">
                </div>
                <div class="form-group col-md-4">
                    <label >Password</label>
                    <input type="password" class="form-control"  placeholder="Enter Password" name="pw">
                </div>
                <button type="submit" class="btn btn-primary" name="login">Submit</button>
                <!-- <div style="text-align: center; margin-top: 15px;">
                    <a href="forgotpass.jsp">Forgot password?</a>
                </div> -->
            </div>
        </form>
 --%>
    </center>

    <%@include file="Footer.jsp"%>
</body>
</html>
    