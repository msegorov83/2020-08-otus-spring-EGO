 $(function () {
    $.get('/api/books').done(function (books) {
        books.forEach(function (book) {

            $("#books").find('tbody').append(`
                 <tr>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.genre}</td>
                    <td>
                         <a id="${book.id}" href="#" class="edit-launch">Edit</a>
                    </td>
                    <td>
                        <form id="delete-form" value="${book.id}" th:method="post" action="books.html" >
                            <button type="submit" class="delete-launch" value="${book.id}">-</button>
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
        url: '/edit',
        data: {
            id: id
        },
        success: function(data) {

            window.location = '/edit?id='+id;
       }
    });
});

$(document).on('click', '.delete-launch', function () {
    let id =  $(this).val();

    $.ajax({
        type: 'POST',
        url: '/api/delete',
        data: {
            id: id
        },
        success: function(data) {
            console.log('Delete book');
			window.location = '/';
       }
    });
});
