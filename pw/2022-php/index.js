$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "backend/getAll.php",

    success: (response) => {
      response = JSON.parse(response);
      console.log(response);
      if (response.error) {
        alert(response.error);
      }
    },
  }).fail(console.error);
});

$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "backend/getsecond.php",
    data: {
      name: "david",
    },

    success: (response) => {
      response = JSON.parse(response);
      console.log(response);
      if (response.error) {
        alert(response.error);
      }
    },
  }).fail(console.error);
});
