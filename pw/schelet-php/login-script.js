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
        localStorage.setItem("user", data[0]);
        localStorage.setItem("mother", data[1]);
        localStorage.setItem("father", data[2]);
      }

      location.href = "schelet-index.html";
    });
  });
});
