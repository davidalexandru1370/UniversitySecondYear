<?php

include 'dbCon.php';

if (isset($_GET['keywords'])) {
    $keywords = $_GET['keywords'];

    $sql = "SELECT * FROM documents";

    $result = pg_query($dbconn, $sql);

    $echoArray = array();

    while ($row = pg_fetch_assoc($result)) {
        $templates = explode(";", $keywords);
        $dbKeywords = array();
        array_push($dbKeywords, $row['keyword1']);
        array_push($dbKeywords, $row['keyword2']);
        array_push($dbKeywords, $row['keyword3']);
        array_push($dbKeywords, $row['keyword4']);
        array_push($dbKeywords, $row['keyword5']);
        foreach ($templates as $val) {
            array_push($dbKeywords, $val);
        }
        $initialLength = count($dbKeywords);
        $counter = $initialLength - count(array_unique($dbKeywords));
        if ($counter == 3) {
            array_push($echoArray, $row);
        }

    }
    echo json_encode($echoArray);


} else {
    echo json_encode(['error' => 'Document id is nor set!']);
    exit();
}

pg_close($con);

?>