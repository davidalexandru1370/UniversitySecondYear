<?php
header('Content-type: application/json');
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Methods: GET, POST, DELETE");

require_once "connection.php";
global $connection;

$postdata = file_get_contents("php://input");
$request = json_decode($postdata);
$id = $request->id;
$sql_query = "delete from document where id = '$id';";
$result = mysqli_query($connection, $sql_query);
mysqli_close($connection);

?>
