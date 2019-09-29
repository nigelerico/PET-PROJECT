$('.hapus-user-btn').click(function () {
    var form = $('.hapus-user form');
    form.attr('action', form.data('url') + '/' + $(this).data('id'));
});

var addUser = $('#addUser').val();
if(addUser == 1){
    $('#modal-add-user').trigger('focus');
}

console.log('add user = '+ addUser);