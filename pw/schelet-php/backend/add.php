<?php

include 'dbCon.php';

if (isset($_POST['name']) && isset($_POST['date'])) {
    $name = $_POST['name'];
    $date = $_POST['date'];

    // --- check if it already exists - depends on the case - uncomment if necessary
    //$sql = "SELECT * FROM entities WHERE `Key` = '$key';";

    // $result = pg_query($con, $sql);

    // $id = -1;

    // if ($row = pg_fetch_array($result)) {
    //     $id = $row['id'];
    // }

    // if ($id == -1) {
    $sql = "INSERT INTO entities(name, date) VALUES ('$name','$date');";
    //}

    if (pg_query($con, $sql)) {
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