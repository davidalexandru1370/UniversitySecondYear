<?php

include 'dbcon.php';

$sql = "SELECT * FROM player";
$result = pg_query($dbconn, $sql);

$echoArray = array();

while ($row = pg_fetch_assoc($result)) {
    array_push($echoArray, $row);
}

echo json_encode($echoArray);

pg_close($dbconn);
exit()
    ?>