<?php

require 'bbdd.php'; // Incluimos fichero en la que está la coenxión con la BBDD

$arrMensaje = array();  // Este array es el codificaremos como JSON tanto si hay resultado como si hay error



$query = "SELECT * FROM mascotas";

$result = $conn->query($query);

if (isset($result) && $result) { // Si pasa por este if, la query está está bien y se obtiene resultado

    if ($result->num_rows > 0) { // Aunque la query esté bien puede no obtenerse resultado (tabla vacía). Comprobamos antes de recorrer

        while ($row = $result->fetch_assoc()) {

            $arrMascota = array();
            $arrMascota['id'] = $row['ID'];
            $arrMascota['nombre'] = $row['Nombre'];
            $arrMascota['especie'] = $row['Especie'];
            $arrMensaje[] = $arrMascota;
        }
    }
}

$mensajeJSON = json_encode($arrMensaje, JSON_PRETTY_PRINT);

//echo "<pre>";  // Descomentar si se quiere ver resultado "bonito" en navegador. Solo para pruebas 
echo $mensajeJSON;
//echo "</pre>"; // Descomentar si se quiere ver resultado "bonito" en navegador

$conn->close();
?>