<?php

include 'dbCon.php';

if (isset($_POST['userid']) && isset($_POST['mother']) && isset($_POST['father'])) {
    $userid = $_POST['userid'];
    $mother = $_POST['mother'];
    $father = $_POST['father'];

    $sql = "INSERT INTO familyrelations(userid, mother, father) VALUES ('$userid','$mother', '$father');";

    if (pg_query($dbconn, $sql)) {
        echo json_encode(['message' => "Added successfully!"]);
    } else {
        echo json_encode(['message' => "Opps...it was not added..."]);
    }


} else {
    echo json_encode(['message' => 'Input is wrong!']);
    exit();
}

pg_close($con);

?>