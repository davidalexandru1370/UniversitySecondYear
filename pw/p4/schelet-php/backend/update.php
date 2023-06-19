<?php

include 'dbCon.php';

if (isset($_POST['name']) && isset($_POST['date']) && isset($_POST['id'])) {
    $name = $_POST['name'];
    $date = $_POST['date'];
    $id = $_POST['id'];

    // --- check if it exists
    $sql = "SELECT * FROM entities WHERE id = '$id';";

    if (pg_query($con, $sql)) {
        $sql = "UPDATE entities SET `name`='$name', `date`='$date' WHERE id=$id;";
        if (pg_query($con, $sql)) {
            echo json_encode(['message' => "Updated successfully!"]);
        } else {
            echo json_encode(['message' => "Opps...it was not updated..."]);
        }
    } else {
        echo json_encode(['message' => "Opps...the entity was not found..."]);
    }


} else {
    echo json_encode(['message' => 'Input is wrong!']);
    exit();
}

pg_close($con);

?>