$(document).ready(function () {
  $("#loginForm").submit(function (e) {
    e.preventDefault();

    $.ajax({
      type: "POST",
      url: "backend/login.php",
      data: $(this).serialize(),
    }).then(function (response) {
      let data = JSON.parse(response);
      console.log(data);

      if (data.error) {
        alert(data.error);
        return;
      }

      // REPLACE HERE WITH INDEX.HTML
      location.href = "index.html";

      // http://localhost/schelet-php/login.html
    });
  });
});
