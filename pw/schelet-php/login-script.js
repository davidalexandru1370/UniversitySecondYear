$(document).ready(function () {
  $("#loginForm").submit(function (e) {
    e.preventDefault();
    $.ajax({
      type: "POST",
      url: "backend/login2.php",
      data: $(this).serialize(),
    }).then(function (response) {
      let data = JSON.parse(response);
      console.log(data);

      if (data.error) {
        alert(data.error);
        return;
      } else {
        localStorage.setItem("mother", $("#mother").val());
        localStorage.setItem("father", $("#father").val());
        localStorage.setItem("user", $("#username").val());
      }

      // REPLACE HERE WITH INDEX.HTML
      location.href = "schelet-index.html";

      // http://localhost/schelet-php/login.html
    });
  });
});
