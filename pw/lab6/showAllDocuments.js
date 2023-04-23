$(function () {
  function populateWithDocumentData() {
    $.getJSON("showAllDocuments.php", function (json) {
      console.log(json);
      json.forEach(function (document) {
        $("table").append(`<tr>
        <td>${document[1]}</td>
        <td>${document[2]}</td>
        <td>${document[3]}</td>
        <td>${document[4]}</td>
        <td>${document[5]}</td>
        <td>
            <a href=deleteDocument.php?id=${document[0]}>Delete</a>
            <br/>
            <a href=updateDocument.php?id=${document[0]}>Update</a>
            <br/>
        </td>
        `);
      });
    });
  }

  populateWithDocumentData();
});
