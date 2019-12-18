<?php
require 'bbdd.php';
$parameters = file_get_contents("php://input");
if (isset($parameters)) {
    $mascotaRecibida = json_decode($parameters, true);
    $arrMMensaje = array();
    switch ($mascotaRecibida['operacion']) {
        case 'insertar':
            $arrMensaje['resultado'] = insertar($mascotaRecibida);
            break;
        case 'borrar':
            $arrMensaje['resultado'] = borrar($mascotaRecibida);
            break;
        case 'editar':
            $arrMensaje['resultado'] = editar($mascotaRecibida);
            break;
        case 'sustituir':
            $arrMensaje['resultado'] = sustituir($mascotaRecibida);
            break;
        default:
            break;
    }


    $mensajeJSON = json_encode($arrMensaje, JSON_PRETTY_PRINT);

    echo $mensajeJSON;
}

$conn->close();

die();

function insertar($var)
{
    global $conn;
    $id = $var['id'];
    $nombre = $var['nombre'];
    $especie = $var['especie'];
    $query = "INSERT INTO mascotas (ID, Nombre, Especie) ";
    $query .= "VALUES ('$id','$nombre','$especie')";

    $result = $conn->query($query);
    if (isset($result) && $result) {
        return "true";
    } else {
        return $conn->error;
    }
}

function borrar($var)
{
    global $conn;
    $id = intval($var['id']);

    $query = "DELETE FROM mascotas WHERE ID=$id";
    $result = $conn->query($query);
    if (isset($result) && $result) {
        return "true";
    } else {
        return $conn->error;
    }
}

function editar($var)
{
    global $conn;
    $id = intval($var['id']);
    $nombre = $var['nombre'];
    $especie = $var['especie'];
    $query = "UPDATE mascotas ";
    $query .= "SET Nombre = '$nombre', Especie = '$especie' ";
    $query .= "WHERE ID = $id";

    $result = $conn->query($query);
    if (isset($result) && $result) {
        return "true";
    } else {
        return $conn->error;
    }
}

function sustituir($var)
{
    global $conn;
    $arrMascotas = $var['mascotas'];
    $query = "DELETE FROM mascotas";
    $conn->query($query);
    $result = "true";

    for ($i = 0; $i < sizeof($arrMascotas); $i++) {
        $mascota = $arrMascotas[$i];
        if (insertar($mascota) != "true") {
            $result = "Some Pet was unsuccsefully added";
        }
    }
    return $result;
}
