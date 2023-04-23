<?php
require_once "connection.php";
global $connection;

$author = $_POST['author'];
$title = $_POST['title'];
$number_of_pages = $_POST['number_of_pages'];
$type = $_POST['type'];
$format = $_POST['format'];

$query = "INSERT INTO documents.document(author,title,number_of_pages,type,format) values('$author','$title','$number_of_pages','$type','$format')";


$result = mysqli_query($connection, $query);

if($result){
    echo "Document added succesfully";
}
else{
    echo "Failed adding document";
}

mysqli_close($connection);

?>