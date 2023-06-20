$(document).ready(function () {
  $("#add-form").submit(function (e) {
    e.preventDefault();
    $.ajax({
      type: "POST",
      url: "backend/add.php",
      data: $(this).serialize(),
    }).then(function (response) {
      let data = JSON.parse(response);
      console.log(data);

      if (data.error) {
        alert(data.error);
        return;
      }

      // http://localhost/schelet-php/login.html
    });
  });
});

$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "backend/getSiblings.php",
    data: {
      mother: localStorage.getItem("mother"),
      father: localStorage.getItem("father"),
    },
    success: (response) => {
      response = JSON.parse(response);

      console.log(response);

      if (response.error) {
        alert(response.error);
      } else {
        for (const relation of response) {
          const innerHtml = "<p>" + relation.username + "</p>";
          if (
            relation.username.localeCompare(localStorage.getItem("user")) !== 0
          ) {
            $("#siblings").append(innerHtml);
          }
        }
      }
    },
  }).fail(console.error);
});
