<?php
require_once "connection.php";
global $connection;
$get_all_query="SELECT * FROM documents.document";
$result = mysqli_query($connection, $get_all_query);
$documents = array();
if($result->num_rows > 0){
    while($row = $result->fetch_assoc()){
        array_push($documents,array($row['id'],$row['author'],$row['title'],$row['number_of_pages'],$row['type'],$row['format']));
    }


}
mysqli_close($connection);
echo json_encode($documents);

?>