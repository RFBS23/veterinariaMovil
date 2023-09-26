<?php
require_once '../models/clientes.php';

if (isset($_POST['operacion'])){
    $cliente = new Cliente();

    if($_POST['operacion'] == 'login'){
        $datosObtenidos = $cliente->login($_POST['cliente']);
        $resultado = [
            "status" => false,
            "nombres" => "",
            "apellidos" => "",
            "mensaje" => ""
        ];
        if ($datosObtenidos){
            $claveEncriptada = $datosObtenidos['claveacceso'];
            if (password_verify($_POST['claveIngresada'], $claveEncriptada)){
                $resultado["status"] = true;
                $resultado["nombres"] = $datosObtenidos["nombres"];
                $resultado["apellidos"] = $datosObtenidos["apellidos"];
            } else {
                $resultado["mensaje"] = "Contraseña incorrecta";
            }
        } else {
            $resultado["mensaje"] = "No se encontro al usuario";
        }
        echo  json_encode($resultado);
    }

    //agregar
    if ($_POST['operacion'] == 'agregar') {
        $registro = [
            "nombres" => $_POST['nombres'],
            "apellidos" => $_POST['apellidos'],
            "dni" => $_POST['dni'],
            "claveacceso" => $_POST['claveacceso'],
        ];
        $cliente->agregarClientes($registro);
    }

    //eliminar
    if ($_POST['operacion'] == 'eliminar') {
        $cliente->eliminarCliente(
            [
                'idcliente' => $_POST['idcliente']
            ]
        );
    }

    //actualizar
    if ($_POST['operacion'] == 'actualizar') {
        $registro = [
            "idcliente" => $_POST['idcliente'],
            "nombres" => $_POST['nombres'],
            "apellidos" => $_POST['apellidos'],
            "dni" => $_POST['dni'],
            "claveacceso" => $_POST['claveacceso'],
        ];
        $cliente->actualizarCliente($registro);
    }
}

//listar
if (isset($_GET['operacion'])){
    if ($_GET['operacion'] == 'listar') {
        echo json_encode($cliente->listarClientes());
    }
    if ($_GET['operacion'] == 'obtenerDatos'){
        echo json_encode($cliente->obtenerCliente(["idcliente" => $_GET['idcliente']]));
    }
}

?>