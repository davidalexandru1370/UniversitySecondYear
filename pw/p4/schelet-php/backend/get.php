<?php

include 'dbCon.php';

$sql = "SELECT websites, count(*) as documentsNumber FROM websites left join documents on documents.idwebsite = websites.id group by websites.id";
$result = pg_query($dbconn, $sql);

$echoArray = array();

while ($row = pg_fetch_assoc($result)) {
    array_push($echoArray, $row);
}

echo json_encode($echoArray);


pg_close($con);
exit()
    ?>