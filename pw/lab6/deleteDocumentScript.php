<?php
require_once "connection.php";
global $connection;
if (isset($_POST['id']) && !empty(trim($_POST['id']))) {
    $id = $_POST['id'];
    $sql_query = "delete from documents.document where id = '$id';";
    $result = mysqli_query($connection, $sql_query);
    if ($result) {
        echo "Deleted successfully!";
        header("Location: showAllDocuments.html");
    } else {
        echo "Invalid id";
    }
}
mysqli_close($connection);
?>