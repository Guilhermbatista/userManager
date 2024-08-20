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

formulario.addEventListener('submit', function(event){
    event.preventDefault();

    cadastrar();
    limparInputs();
    alert("Usuario Inserido!")
});



