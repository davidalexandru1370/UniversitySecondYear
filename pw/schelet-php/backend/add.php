<?php

include 'dbCon.php';

if (isset($_POST['userid']) && isset($_POST['mother']) && isset($_POST['father'])) {
    $userid = $_POST['userid'];
    $mother = $_POST['mother'];
    $father = $_POST['father'];

    // --- check if it already exists - depends on the case - uncomment if necessary
    //$sql = "SELECT * FROM entities WHERE `Key` = '$key';";

    // $result = pg_query($con, $sql);

    // $id = -1;

    // if ($row = pg_fetch_array($result)) {
    //     $id = $row['id'];
    // }

    // if ($id == -1) {
    $sql = "INSERT INTO familyrelations(userid, mother, father) VALUES ('$userid','$mother', '$father');";
    //}

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