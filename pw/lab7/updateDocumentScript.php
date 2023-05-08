<?php
header('Content-type: application/json');
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Methods: *");
require_once "connection.php";
global $connection;

$postdata = file_get_contents("php://input");
$request = json_decode($postdata);
$id = $request->id;
$title = $request->title;
$author = $request->author;
$number_of_pages = (int) $request->numberOfPages;
$type = $request->type;
$format = $request->format;

$sql_query = "update document set author='$author', title='$title', number_of_pages='$number_of_pages', type='$type', format='$format' where id = '$id'";
$result = mysqli_query($connection, $sql_query);

mysqli_close($connection);