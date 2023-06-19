<?php

include 'dbCon.php';

if (isset($_GET['name'])) {
    $name = $_GET['name'];

    $sql = "SELECT * FROM entities WHERE `name` LIKE '%$name%';";

    $result = pg_query($con, $sql);
    $echoArray = array();

    while ($row = pg_fetch_assoc($result)) {
        array_push($echoArray, $row);
    }

    echo json_encode($echoArray);


} else {
    echo json_encode(['error' => 'Input is wrong!']);
    exit();
}

pg_close($con);

?>