<?php

include 'dbCon.php';

if (isset($_GET['mother'])) {
    $mother = $_GET['mother'];

    $sql = "SELECT id from users where users.username = '$mother'";

    $result = pg_query($dbconn, $sql);

    $echoArray = array();

    while ($row = pg_fetch_assoc($result)) {
        $id = $row['id'];
        $parentssql = "SELECT familyrelations.mother from familyrelations where familyrelations.userid = $id";
        $result2 = pg_query($dbconn, $parentssql);
        if ($row2 = pg_fetch_assoc($result2)) {
            array_push($echoArray, $row2['mother']);
            $newmother = $row2['mother'];
            $sql3 = "SELECT id from users where users.username = '$newmother'";
            $result = pg_query($dbconn, $sql3);
        } else {
            echo json_encode($echoArray);
            exit();
        }

    }

    echo json_encode($echoArray);



} else {
    echo json_encode(['error' => ' Father or mother not set']);
    exit();
}

pg_close($dbconn);

?>