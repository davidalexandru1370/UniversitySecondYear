class Helper {
  static index = 0;
}

function changeBackgroundImage() {
  const backgroundImages = [
    "linear-gradient(to bottom right, #f54ea2, #e100ff)",
    "linear-gradient(to bottom right, #fce38a, #f38131)",
    "linear-gradient(to bottom right, #17ead9, #6078ea)",
    "linear-gradient(to bottom right, #42e965, #3bb2b8)",
    "linear-gradient(to bottom right, #65799b, #5e2563)",
  ];

  document.body.style.backgroundImage = backgroundImages[Helper.index];
  Helper.index = (Helper.index + 1) % backgroundImages.length;
}

function generateHexaColor() {
  const elements = [
    "0",
    "1",
    "2",
    "3",
    "4",
    "5",
    "6",
    "7",
    "8",
    "9",
    "A",
    "B",
    "C",
    "D",
    "E",
    "F",
  ];
  let result = "#";

  for (let i = 0; i < 6; i++) {
    let index = Math.round(Math.random() * 100) % elements.length;
    let element = elements[index];
    result = result.concat(element);
  }
  return result;
}

function changeLinksColor() {
  const links = document.querySelectorAll("a");
  for (let link of links) {
    let color = generateHexaColor();
    link.style.color = color;
  }
}
