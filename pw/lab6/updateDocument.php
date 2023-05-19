<?php
require_once "connection.php";
global $connection;
$id = "";
$author = "";
$title = "";
$number_of_pages = "";
$type = "";
$format = "";
if (isset($_GET['id']) && !empty(trim($_GET['id']))) {
    $id = trim($_GET['id']);
    $sql_query = "select * from documents.document where id = $id;";
    $result = mysqli_query($connection, $sql_query);
    if ($result) {
        $number_of_rows = mysqli_num_rows($result);
        if ($number_of_rows == 1) {
            $row = mysqli_fetch_array($result);
            $author = $row['author'];
            $title = $row['title'];
            $number_of_pages = $row['number_of_pages'];
            $type = $row['type'];
            $format = $row['format'];
        } else {
            die("Invalid id");
        }
    } else {
        die("Invalid id");
    }
    mysqli_close($connection);
} else
    die("Invalid id");
?>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Update User</title>
</head>

<body>
    <div class="container">
        <h1>Update Document</h1>
        <form action="updateDocumentScript.php" method="post">
            <input type="hidden" name="id" value="<?php echo trim($_GET['id']); ?>">
            <input type="text" name="author" placeholder="Author" value="<?php echo $author ?>"> <br>
            <input type="text" name="title" placeholder="Title" value="<?php echo $title ?>"> <br>
            <input type="number" name="number_of_pages" placeholder="Number of pages"
                value="<?php echo $number_of_pages ?>"> <br>
            <input type="text" name="type" placeholder="Type" value="<?php echo $type ?>"> <br>
            <input type="text" name="format" placeholder="Format" value="<?php echo $format ?>"> <br>
            <div class="button_container">
                <button type="submit">Update document</button>
                <button type="reset" onclick="window.location.href='showAllDocuments.html'">Cancel</button>
            </div>
        </form>
    </div>
</body>

</html>