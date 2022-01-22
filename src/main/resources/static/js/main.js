let allRolesList;// список всех ролей

async function getAllRoles() {
    await fetch('/rest/allRoles').then(roles => roles.json()).then(rroles => {
        allRolesList = rroles;
    });

    allRolesList.map(function (role) {
        $('#roles').append(`<option value="${role.id}">${role.role}</option>`);
    });
}

getAllRoles();

$('document').ready(function () {


    let editModal = document.getElementById('modal-edit');

    editModal.addEventListener('show.bs.modal', function (event) {
        let button = event.relatedTarget;
        let tds = button.parentNode.parentNode.querySelectorAll('td');
        $('#editId').val(tds[0].textContent);
        $('#editName').val(tds[1].textContent);
        $('#editSurname').val(tds[2].textContent);
        $('#editNumber').val(tds[4].textContent);
        $('#editLogin').val(tds[3].textContent);
        $('#editRoles').empty();
        allRolesList.map(function (role) {
            if (role.role == tds[5].textContent) {
                $('#editRoles').append(`<option value="${role.id}" selected>${role.role}</option>`);
            } else {
                $('#editRoles').append(`<option value="${role.id}">${role.role}</option>`);
            }

        })

    });

    let deleteModal = document.getElementById('modal-delete');

    deleteModal.addEventListener('show.bs.modal', function (event) {
        let button = event.relatedTarget;
        let tds = button.parentNode.parentNode.querySelectorAll('td');
        $('#deleteId').val(tds[0].textContent);
        $('#deleteName').val(tds[1].textContent);
        $('#deleteSurname').val(tds[2].textContent);
        $('#deleteNumber').val(tds[4].textContent);
        $('#deleteLogin').val(tds[3].textContent);
        $('#deleteRoles').empty();
        $('#deleteRoles').append(`<option>${tds[5].textContent}</option>`)
    });

    // $('.table #deleteButton').on('click', function (event) {
    //     event.preventDefault();
    //     var href = $(this).attr('href');
    //     $('#deleteModal #delRef').attr('href', href);
    //     $('#deleteModal').modal();
    //
    // });

    //вешаем listers на кнопки submit
    $('#createUserForm').on('click', async function(event) {
        event.preventDefault();
        let newUser = {
            name: $('#name').val(),
            surname: $('#surname').val(),
            age: $('#age').val(),
            username: $('#login').val(),
            password: $('#pass').val(),
            roles: [{
                id: $('#roles').val()
            }]
        }
        $('#name').val('');
        $('#surname').val('');
        $('#age').val('');
        $('#login').val('');
        $('#pass').val('');

        await fetch('rest/add', {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Referer': null
            },
            body: JSON.stringify(newUser)
        });

        refreshTable();
        $('.nav-tabs a:first').tab('show');
    })

    $('#editUserForm').on('click', async function(event) {
        event.preventDefault();
        let editedUser = {
            id: $('#editId').val(),
            name: $('#editName').val(),
            surname: $('#editSurname').val(),
            age: $('#editNumber').val(),
            username: $('#editLogin').val(),
            password: $('#editPass').val(),
            roles: [{
                id: $('#editRoles').val()
            }]
        }

        $('#editPass').val('');

        await fetch(`rest/${editedUser.id}`, {
            method: 'PATCH',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Referer': null
            },
            body: JSON.stringify(editedUser)
        });

        refreshTable();
        $(editModal).modal('hide');
    })

    $('#deleteUserForm').on('click', async function(event) {
        event.preventDefault();
        let userId = $('#deleteId').val();
        await fetch(`rest/${userId}/delete`, {
            method: 'delete',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Referer': null
            },
            body: null
        });
        refreshTable();
        $(deleteModal).modal('hide');
    })

    //вешаем listers на кнопки submit


});

//обновление таблицы ----------------------
function refreshTable() {
    let table = $('#dataTable');
    $('#dataTable tbody').empty();

    fetch('/rest/allUsers').then(result => result.json()).then(jsonR => {
        let row = '';

        jsonR.map(function (user) {
            let userRolesString = '';
            for (let role of user.roles) {
                userRolesString += role.role;
            }
            row += `<tr><td>${user.id}</td>
<td>${user.name}</td>
<td>${user.surname}</td>
<td>${user.username}</td>
<td>${user.age}</td>
<td>${userRolesString}</td>
<td><button class="btn btn-primary" type="button" data-bs-toggle="modal" data-bs-target="#modal-edit">Изменить</button></td>
<td><button class="btn btn-danger" type="button" data-bs-toggle="modal" data-bs-target="#modal-delete">Удалить</button></td>
`;
        });

        table.append(row);
    });
}

//обновление таблицы ----------------------






