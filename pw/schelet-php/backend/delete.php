<?php

include 'dbCon.php';

if (isset($_POST['id'])) {
    $id = $_POST['id'];

    $sql = "DELETE FROM entities WHERE id = $id";
    if (pg_query($con, $sql)) {
        echo json_encode(['message' => "Deleted successfully!"]);
    } else {
        echo json_encode(['message' => "Opps...it was not deleted..."]);
    }

} else {
    echo json_encode(['message' => 'Input is wrong!']);
    exit();
}

pg_close($con);
exit()
    ?>