<?php

include 'dbCon.php';
session_start();

if (isset($_POST['username']) && isset($_POST['mother']) && isset($_POST['father'])) {
    $username = $_POST['username'];
    $father = $_POST['father'];
    $mother = $_POST['mother'];

    //$sql = "SELECT users.username, familyrelations.mother, familyrelations.mother FROM users left join familyrelations on users.id = familyrelations.userid where users.username = $username";
    $sql = "SELECT users.username, familyrelations.mother, familyrelations.father FROM users left join familyrelations on users.id = familyrelations.userid where users.username = '$username'";
    $res = pg_query($dbconn, $sql);

    if ($row = pg_fetch_assoc($res)) {
        // set cookie with the username
        if ($row['username'] == $username && ($row['mother'] == $mother || $row['father'] == $father)) {
            $cookie_name = "username";
            $cookie_value = $username;
            setcookie($cookie_name, $cookie_value, time() + (86400 * 1), "/"); // 86400 = 1 day
            echo json_encode([$row['username'], $row['mother'], $row['father']]);
        }
    }
} else {
    echo json_encode(['error' => 'No data inserted']);
}

pg_close($con);

?>