const pictures = document.getElementById('carousel').getElementsByTagName("img");
const rightBtn = document.getElementById('right-btn');
const leftBtn = document.getElementById('left-btn');

let position = 0;

if (pictures.length > 1) {
  for (let i = 1; i < pictures.length; i++) {
    pictures[i].hidden = true;
  }
}

const moveRight = () => {
    pictures[position].hidden = true;
    if (position >= pictures.length - 1) {
        position = 0
        pictures[position].hidden = false;
        return;
    }

    pictures[position + 1].hidden = false;
    position++;
}

const moveLeft = () => {
    pictures[position].hidden = true;
    if (position < 1) {
        position = pictures.length - 1;
        pictures[position].hidden = false;
        return;
    }
    pictures[position - 1].hidden = false;
    position--;
}

rightBtn.addEventListener("click", moveRight);
leftBtn.addEventListener("click", moveLeft);
