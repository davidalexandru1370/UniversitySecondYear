<?php
require_once "connection.php";
global $connection;
$id = $_POST['id'];
$author = $_POST['author'];
$title = $_POST['title'];
$number_of_pages = $_POST['number_of_pages'];
$type = $_POST['type'];
$format = $_POST['format'];
$sql_query = "update documents.document set author='$author', title='$title', number_of_pages='$number_of_pages', type='$type', format='$format' where id = $id";
$result = mysqli_query($connection, $sql_query);
if ($result) {
    echo "Updated successfully!";
    header("Location: showAllDocuments.html");
} else {
    echo "Failed";
}
header("Location: showAllDocuments.html");

mysqli_close($connection);