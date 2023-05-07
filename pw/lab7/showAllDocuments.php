<?php
header('Content-type: application/json');
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
require_once "connection.php";
global $connection;
$get_all_query = "SELECT * FROM documents.document";
$result = mysqli_query($connection, $get_all_query);
$documents = array();
$type = $_GET["type"];
$format = $_GET["format"];
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        if (str_contains($row["type"], $type) && str_contains($row["format"], $format)) {
            array_push($documents, array($row['id'], $row['author'], $row['title'], $row['number_of_pages'], $row['type'], $row['format']));
        }
    }
}
mysqli_close($connection);
echo json_encode($documents);

?>