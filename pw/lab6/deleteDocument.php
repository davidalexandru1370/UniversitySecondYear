<html>

<head>
    <meta charset="UTF-8">
    <title>Delete Document</title>
    <link rel="stylesheet" href="deleteDocument.css" />
</head>

<body>
    <h1>Delete document</h1>

    <div class="container">
        <h3>Are you sure?</h3>
        <div class="inline">
            <form action="deleteUserBackend.php" method="post">
                <input type="hidden" name="id" value="<?php echo trim($_GET['id']); ?>">
                <button type="submit" class="button yesButton">Yes</button>
            </form>
            <button class="button cancelButton" onclick="window.location.href='showAllDocuments.html'">
                Cancel
            </button>
        </div>
    </div>
</body>

</html>