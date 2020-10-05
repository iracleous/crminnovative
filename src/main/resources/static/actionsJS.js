function submitProduct(){


    var	actionMethod = "POST"
    var	actionUrl = "http://localhost:1002/product"


    console.log("This is a nameee: "+$('#name').val());
    sendData = {"name":$('#name').val(),
        "inventoryQuantity":$('#inventoryQuantity').val(),
        "price":$('#price').val(),
    }

    $.ajax({
        beforeSend: function (xhr) {
        },
        url: actionUrl,
        dataType: 'json',
        type: actionMethod,
        contentType: 'application/json',
        data: JSON.stringify( sendData ),
        processData: false,
        success: function( data, textStatus, jQxhr ){
            alert( JSON.stringify( data ) );
        },
        error: function( jqXhr, textStatus, errorThrown ){
            console.log( "errorThrown" );
        }
    });

}