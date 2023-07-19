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

$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "backend/getFatherDescendents.php",
    data: {
      father: localStorage.getItem("father"),
    },
    success: (response) => {
      response = JSON.parse(response);

      console.log(response);

      if (response.error) {
        alert(response.error);
      } else {
        let descendants = "<p>" + localStorage.getItem("father");
        for (const element of response) {
          descendants += " -> " + element;
        }
        descendants += "</p>";
        $("#fatherdescendents").append(descendants);
      }
    },
  }).fail(console.error);
});

$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "backend/getMotherDescendents.php",
    data: {
      mother: localStorage.getItem("mother"),
    },
    success: (response) => {
      response = JSON.parse(response);

      console.log(response);

      if (response.error) {
        alert(response.error);
      } else {
        let descendants = "<p>" + localStorage.getItem("mother");
        for (const element of response) {
          descendants += " -> " + element;
        }
        descendants += "</p>";
        $("#motherdescendents").append(descendants);
      }
    },
  }).fail(console.error);
});
