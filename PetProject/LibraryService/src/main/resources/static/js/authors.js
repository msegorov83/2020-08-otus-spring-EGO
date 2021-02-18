 $(function () {
        $.get('/api/authors').done(function (authors) {
            authors.forEach(function (author) {

                $("#authors").find('tbody').append(`
                     <tr>
                        <td>${author.id}</td>
                        <td>${author.fullName}</td>
                        <td>
                              <a id="${author.id}" href="#" class="edit-launch">Edit</a>
                        </td>
                        <td>
                            <form id="delete-form" value="${author.id}" th:method="post" action="authors.html" >
                                 <button type="submit" class="delete-launch"
                                                        value="${author.id}">-</button>


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
        url: '/authors/edit',
        data: {
            id: id
        },
        success: function(data) {

            window.location = '/authors/edit?id='+id;
       }
    });
});

$(document).on('click', '.delete-launch', function () {
    let id =  $(this).val();

    $.ajax({
        type: 'POST',
        url: '/api/authors/delete',
        data: {
            id: id
        },
        success: function(data) {
            console.log('Delete author');
			window.location = '/authors';
       }
    });
});