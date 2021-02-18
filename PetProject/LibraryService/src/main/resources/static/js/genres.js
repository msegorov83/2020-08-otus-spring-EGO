 $(function () {
        $.get('/api/genres').done(function (genres) {
            genres.forEach(function (genre) {

                $("#genres").find('tbody').append(`
                     <tr>
                        <td>${genre.id}</td>
                        <td>${genre.gName}</td>
                        <td>
                              <a id="${genre.id}" href="#" class="edit-launch">Edit</a>
                        </td>
                        <td>
                            <form id="delete-form" value="${genre.id}" th:method="post" action="genres.html" >
                                 <button type="submit" class="delete-launch"
                                                        value="${genre.id}">-</button>


                            </form>
                        </td>
                    </tr>
                `)
            });
        })
    });

$(document).on('click', '.edit-launch', function () {
    let id =  $(this).attr('id');

    $.ajax({
        type: 'get',
        url: '/genres/edit',
        data: {
            id: id
        },
        success: function(data) {

            window.location = '/genres/edit?id='+id;
       }
    });
});

$(document).on('click', '.delete-launch', function () {
    let id =  $(this).val();

    $.ajax({
        type: 'POST',
        url: '/api/genres/delete',
        data: {
            id: id
        },
        success: function(data) {
            console.log('Delete genre');
			window.location = '/genres';
       }
    });
});