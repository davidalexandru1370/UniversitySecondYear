<?php
header('Content-type: application/json');
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Methods: *");
require_once "connection.php";
global $connection;

$postdata = file_get_contents("php://input");
$request = json_decode($postdata);
$title = $request->title;
$author = $request->author;
$number_of_pages = (int) $request->numberOfPages;
$type = $request->type;
$format = $request->format;

$query = "INSERT INTO document(author,title,number_of_pages,type,format) values('$author','$title','$number_of_pages','$type','$format')";
// $query = $connection->prepare("INSERT INTO document(author, title, number_of_pages, type, format) values (?, ?, ?, ?, ?)");
// $query->bind_param("sssss", $author, $title, $number_of_pages, $type, $format);
// $query->execute();
$result = mysqli_query($connection, $query);

mysqli_close($connection);

?>