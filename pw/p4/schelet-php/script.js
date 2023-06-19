$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "backend/getByKeywords.php",
    data: {
      keywords: "a;b;c;g;h",
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

$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "backend/get.php",

    success: (response) => {
      response = JSON.parse(response);
      console.log(response);
      if (response.error) {
        alert(response.error);
      }
    },
  }).fail(console.error);
});
