$(function() {

    var password = $( "#password" ),
    password1 = $( "#password1" ),
    allFields = $( [] ).add( password ),
    tips = $( "#errorMessagePassword" );

    function checkPasswords(o1,o2){
        if(o1.val()!=o2.val()){
            o1.addClass("error_input");
            o2.addClass("error_input");
            updateTips("Пароли не совпадают");
            return false;
        }
        else{
            o1.removeClass( "error_input");
            o2.removeClass( "error_input");
            return true;
        }
    }

    function updateTips( t ) {
        tips.text(t);
    }

    function checkLength( o, n, min, max ) {
        if ( o.val().length > max || o.val().length < min ) {
            o.addClass( "error_input" );
            updateTips( "Длина " + n + " должна быть между " +
                min + " и " + max + "." );
            return false;
        } else {
            o.removeClass( "error_input");
            return true;
        }
    }

     function checkRegexp( o, regexp, n ) {
        if ( !( regexp.test( o.val() ) ) ) {
            o.addClass( "error_input" );
            updateTips( n );
            return false;
        } else {
            o.removeClass( "error_input");
            return true;
        }
    }


    $( "#form" ).submit(function() {
        var bValid = true;
        allFields.removeClass("error_input");
        bValid = bValid && checkLength( password, "пароля", 4, 50);
        bValid = bValid && checkRegexp( password, /^([0-9a-z_])+$/i, "Пароль должен состоять из букв английского алфавита и цифр" );
        bValid = bValid && checkPasswords(password,password1);
        if ( bValid ) {
            $("#errorMessagePassword").text("");
        }
        return bValid;
    });
});



