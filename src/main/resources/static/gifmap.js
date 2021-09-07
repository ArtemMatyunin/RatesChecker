
$( document ).ready(function(){


    $('.check_button').on('click', function(){


        var userInput = $('#symbol').val().trim();


        userInput = userInput.replace(/ /g, "+");

        var queryURL = '/gif?symbols=' + userInput;


        $.ajax({url: queryURL, method: 'GET'}).done(function(response){


            console.log(response.gifData.data.images.fixed_height);
            console.log(response.gifData.data.latestRate);
            var giphyURL = response.gifData;
            console.log(giphyURL)

            $('#here_is_gif').attr('src', giphyURL.data.images.fixed_height.url);
            $('#rate').attr('value', giphyURL.historicalRate);
            ``
        });

        return false;
    })


});