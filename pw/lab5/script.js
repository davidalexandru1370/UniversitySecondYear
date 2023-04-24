class Helper {
  static index = 0;
}

$(function () {
  $("#buttonNext").on("click", () => {
    if (Helper.index === 4) {
      return;
    }
    $(`#desktop${Helper.index}`).slideUp("slow");
    $(`#desktop${Helper.index + 1}`).slideDown("slow");
    Helper.index += 1;
  });
  $("#buttonPrevious").on("click", () => {
    if (Helper.index === 1) {
      return;
    }

    $(`#desktop${Helper.index - 1}`).slideDown("slow");
    $(`#desktop${Helper.index}`).slideUp("slow");

    Helper.index -= 1;
  });
  $("#buttonNext").hover(() => {
    if (Helper.index === 4) {
      return;
    }
    $(`#desktop${Helper.index}`).slideUp("slow");
    $(`#desktop${Helper.index + 1}`).slideDown("slow");
    Helper.index += 1;
  });
});
