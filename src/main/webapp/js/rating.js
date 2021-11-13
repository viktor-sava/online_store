const card = document.getElementsByClassName('card__rating');
const empty_star = getContextPath() + "/img/empty_star.svg";
const filled_star = getContextPath() + "/img/filled_star.svg";

function getContextPath() {
  const href = window.document.location.href;
  const pathName = window.document.location.pathname;
  const pos = href.indexOf(window.document.location.pathname);
  const localhostPart = href.substring(0, pos);
  const projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
  return localhostPart + projectName;
}

const set_rating = (value, i) => {
  for (let k = 0; k < value; k++) {
    let image = new Image(24, 24);
    image.src = filled_star;
    image.alt = "filled_star";
    card[i].appendChild(image);
  }
  for (let k = 0; k < 5 - value; k++) {
    let image = new Image(24, 24);
    image.src = empty_star;
    image.alt = "empty_star";
    card[i].appendChild(image);
  }
}

for (let i = 0; i < card.length; i++) {
  if (card[i].classList.length > 1) {
    if (card[i].classList.contains('r-0')) {
      set_rating(0, i);
    } else if (card[i].classList.contains('r-1')) {
      set_rating(1, i);
    } else if (card[i].classList.contains('r-2')) {
      set_rating(2, i);
    } else if (card[i].classList.contains('r-3')) {
      set_rating(3, i);
    } else if (card[i].classList.contains('r-4')) {
      set_rating(4, i);
    } else if (card[i].classList.contains('r-5')) {
      set_rating(5, i);
    }
  }
}
