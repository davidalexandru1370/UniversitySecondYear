<?php

include 'dbcon.php';
if (isset($_GET['name'])) {
    $name = $_GET['name'];
    $sql = "SELECT distinct (Select player.name from player where player.id = teammembers.idplayer1) as name1 , (Select player.name from player where player.id = teammembers.idplayer2) as name2 FROM teammembers left join player on teammembers.idplayer1 = player.id or teammembers.idplayer2 = player.id";
    $result = pg_query($dbconn, $sql);
    $echoArray = array();


    while ($row = pg_fetch_assoc($result)) {
        if ($row['name1'] == $name || $row['name2'] == $name) {
            if ($row['name1'] != $name) {
                array_push($echoArray, $row['name1']);
            } else {
                array_push($echoArray, $row['name2']);
            }
        }
    }

    echo json_encode($echoArray);
}
pg_close($dbconn);
exit()
    ?>