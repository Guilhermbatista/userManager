
document.addEventListener('DOMContentLoaded', () => {
    getAllUser()
});

const formulario = document.querySelector("form")
const Inome = document.querySelector(".name")
const Iemail = document.querySelector(".email")
const Isenha = document.querySelector(".password")
const Itel = document.querySelector(".telefone")


function cadastrar(){

    fetch("http://localhost:8080/usuario",{
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({ 
            nome: Inome.value,
            email: Iemail.value,
            senha: Isenha.value,
            telefone: Itel.value})
    })
    .then(function(res){console.log(res)})
    .catch(function(res){console.log(res)})

};
function limparInputs() {
    document.getElementById('nome').value = '';
    document.getElementById('email').value = '';
    document.getElementById('senha').value = '';
    document.getElementById('telefone').value = '';
}

function getAllUser() {
    fetch('http://localhost:8080/usuario')
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById('usuariosTable').querySelector('tbody');

            data.forEach(item => {
                const row =
                    `<tr>
                        <td>${item.id}</td>
                        <td>${item.nome}</td>
                        <td>${item.email}</td>
                        <td>${item.telefone}</td>
                        
                        <td>
                            <button type="button" class="btn btn-warning" onclick="abrirModalEdicao(${item.id})" data-bs-toggle="modal" data-bs-target="#exampleModal">
                            Editar
                            </button>
                            <button type="button" class="btn btn-danger" onclick="deletarUsuario(${item.id})"">Excluir</button>
                        </td>
                    </tr>`;
                tbody.innerHTML += row;
            });
        });
}

async function getUserByName(userName) {
    const apiUrl = `http://localhost:8080/usuario/busca?nome=${encodeURIComponent(userName)}`;

    try {
        const response = await fetch(apiUrl, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.status}`);
        }

        const user = await response.json();
        return user;

    } catch (error) {
        console.log("Erro ao buscar usuário: ", error);
        return null;
    }
}

document.getElementById('searchButton').addEventListener('click', async () => {
    const userName = document.getElementById('userNameInput').value;

    if (userName.trim() === '') {
        getAllUser()
        return;
    }

    const user = await getUserByName(userName);
    console.log(user.nome)
    if (user) {
        const tbody = document.getElementById('usuariosTable').querySelector('tbody');
        tbody.innerHTML = '';  // Limpa a tabela antes de exibir o resultado filtrado

        user.forEach(item => {
            const row =
                `<tr data-bs-toggle="modal" data-bs-target="#exampleModal">
                    <td>${item.id}</td>
                    <td>${item.nome}</td>
                    <td>${item.email}</td>
                    <td>${item.telefone}</td>
                </tr>`;
            tbody.innerHTML += row;
        });

    } else {
        alert('Usuário não encontrado.');
    }
});

// Deletar usuario

function deletarUsuario(idUsuario) {
    fetch(`http://localhost:8080/usuario/${idUsuario}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                alert('Usuário deletado com sucesso!');
                location.reload();

            } else {
                alert('Falha ao deletar o usuário.');
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao tentar deletar o usuário.');
        });
}

//UPDATE

function abrirModalEdicao(idUsuario) {
    fetch(`http://localhost:8080/usuario/${idUsuario}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            // Preenche os campos de edição com os dados atuais do usuário
            document.getElementById('id').value = data.id;
            document.getElementById('nome').value = data.nome;
            document.getElementById('email').value = data.email;
            document.getElementById('senha').value = data.senha;
            document.getElementById('telefone').value = data.telefone;

            // Armazena o id do usuário em um atributo data do botão de salvar
            document.getElementById('salvarBtn').setAttribute('data-id', idUsuario);


        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao tentar buscar os dados do usuário.');
        });
}
//part2

function editarUsuario() {

    // Captura os valores dos inputs
    const IdString = document.querySelector(".id").value;
    const Inome = document.querySelector(".name").value;
    const Iemail = document.querySelector(".email").value;
    const Isenha = document.querySelector(".password").value;
    const Itel = document.querySelector(".telefone").value;

    const Id = Number(IdString);
    //Cria o objeto usuário atualizado com as propriedades corretas
    const usuarioAtualizado = {
        id: Id,
        nome: String(Inome),
        email: String(Iemail),
        senha: String(Isenha),
        telefone: String(Itel)
    };
    

    // Faz a requisição PUT para atualizar o usuário na API
    fetch('http://localhost:8080/usuario', {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'

        },
        body: JSON.stringify(usuarioAtualizado)
    })
       .then(response => {
        console.log(response)
            if (response.ok) {
                alert('Usuário atualizado com sucesso!');
                location.reload(); // Recarrega a página após a atualização bem-sucedida
            } else {
                alert('Falha ao atualizar o usuário.');
                console.log(usuarioAtualizado);
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao tentar atualizar o usuário.');
        });
}


