const init = () => {
    let favoriteButtons = document.querySelectorAll(".favoriteButton");
    favoriteButtons.forEach(button => {
        button.addEventListener("click", toggleFavorite);
    })
}

const toggleFavorite = async (event) => {
    let target = event.target;
    let searchParams = parseTargetId(target.id);
    let searchType = searchParams["type"];
    let id = searchParams["id"];

    let url = `toggleFavorite?${searchType}Id=${id}`;
    let togglePromise = fetch(url);

    await togglePromise.then(() => {
        if (target.innerHTML === "favorite_border") {
            target.innerHTML = "favorite";
        } else if (target.innerHTML === "favorite") {
            target.innerHTML = "favorite_border";
        }
    })
}

const parseTargetId = id => {
    let sliceIndex;
    for (let i = 0; i < id.length; i++) {
        if (!isNaN(id.charAt(i))) {
            sliceIndex = i;
            break;
        }
    }

    let params = {
        "type" : id.slice(0, sliceIndex),
        "id" : id.slice(sliceIndex)
    }

    return params;
}

window.onload = init;