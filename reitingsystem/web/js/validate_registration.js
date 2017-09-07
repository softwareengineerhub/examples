$(function() {

    var login = $( "#login" ),
    email = $( "#email" ),
    password = $( "#password" ),
    password1 = $( "#password1" ),
    allFields = $( [] ).add( name ).add( email ).add( password ),
    tips = $( "#errorMessage" );

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

    function checkRegexp( o, regexp, n ) {
        if ( !( regexp.test( o.val() ) ) ) {
            o.addClass("error_input");
            updateTips( n );
            return false;
        } else {
            return true;
        }
    }
    $( "#form" ).submit(function() {
        var bValid = true;
        allFields.removeClass("error_input");
        bValid = bValid && checkLength( login, "логин", 3, 50 );
        bValid = bValid && checkRegexp( login, /^([0-9a-z_])+$/i, "Логин должен состоять из букв английского алфавита и цифр" );
        bValid = bValid && checkLength( password, "пароль", 4, 50);
        bValid = bValid && checkRegexp( password, /^([0-9a-z_])+$/i, "Пароль должен состоять из букв английского алфавита и цифр" );
        bValid = bValid && checkPasswords(password,password1);
        bValid = bValid && checkLength( email, "e-mail", 6, 80 );
        bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "Введите корректный адрес" );
        if ( bValid ) {
            $("#errorMessage").text("");
        //alert("GOOD");
        }
        return bValid;
    });
});

