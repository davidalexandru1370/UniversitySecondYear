<?php

include 'dbCon.php';

if (isset($_GET['father']) || isset($_GET['mother'])) {
    $father = $_GET['father'];
    $mother = $_GET['mother'];

    $sql = "SELECT users.username, familyrelations.mother, familyrelations.father FROM familyrelations left join users on users.id = familyrelations.userid WHERE familyrelations.father = '$father' or familyrelations.mother = '$mother';";

    $result = pg_query($dbconn, $sql);

    $echoArray = array();

    while ($row = pg_fetch_assoc($result)) {

        array_push($echoArray, $row);
    }

    echo json_encode($echoArray);



} else {
    echo json_encode(['error' => ' Father or mother not set']);
    exit();
}

pg_close($dbconn);

?>