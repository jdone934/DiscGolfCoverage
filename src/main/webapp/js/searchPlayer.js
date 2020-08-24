const playerInit = () => {
    document.querySelector("#searchForPlayers").addEventListener("click", searchPlayers);
}

const searchPlayers = async() => {
    let resultsSection = document.querySelector("#playerSearchResults")
    resultsSection.innerHTML = "Loading...";

    let playerFirstName = document.querySelector("#playerFirstName").value;
    let playerLastName = document.querySelector("#playerLastName").value;
    let players = await getPlayers(playerFirstName, playerLastName);
    printPlayers(players);
}

const getPlayers = async(firstName, lastName)=> {
    let url = `searchPlayer?firstName=${firstName}&lastName=${lastName}`;
    let searchPromise = fetch(url);
    let players = await searchPromise.then(results => {
        return results.json();
    })

    return players;
}

const printPlayers = players => {
    let resultsSection = document.querySelector("#playerSearchResults")
    resultsSection.innerHTML = "";
    players.forEach(player => {
        let newPlayer = playerLinkBuilder(player, "add");

        resultsSection.appendChild(newPlayer);
    })
}

const addPlayer = player => {
    if (playerNotInList(player)) {
        let playerToAdd = playerLinkBuilder(player, "remove");

        let addedPlayers = document.querySelector("#addedPlayers");
        addedPlayers.appendChild(playerToAdd);
    }
}

const deletePlayer = event => {
    let playerGroup = event.target.parentNode;
    let parent = playerGroup.parentNode;
    parent.removeChild(playerGroup);
}

const playerNotInList = player => {
    let notInList = true;
    let addedPlayer = document.querySelectorAll(".addedPlayer");

    addedPlayer.forEach(testPlayer => {
        if (testPlayer.value == player.playerId) {
            notInList = false;
        }
    })

    return notInList;
}

const playerLinkBuilder = (player, addOrRemove) => {
    let playerToBuild = document.createElement("label");
    playerToBuild.setAttribute("class", "playerResult d-flex justify-content-center checkbox-label");

    let input = document.createElement("input");
    input.setAttribute("type", "checkbox");

    let playerLink = document.createElement("a");
    playerLink.setAttribute("href", `/DiscGolfCoverage/playerProfile?id=${player.playerId}`);
    playerLink.setAttribute("target", "_blank");
    playerLink.innerHTML = `${player.firstName} ${player.lastName}`;

    let addOrRemoveButton = document.createElement("i");
    addOrRemoveButton.setAttribute("class", "material-icons addOrRemoveButton");

    if (addOrRemove === "add"){
        addOrRemoveButton.innerHTML = "add_circle_outline";
        addOrRemoveButton.addEventListener("click", function () {
            addPlayer(player);
        });
    } else {
        addOrRemoveButton.innerHTML = "delete";
        addOrRemoveButton.addEventListener("click", deletePlayer);

        input.setAttribute("name", "playersInRound");
        input.setAttribute("value", player.playerId);
        input.setAttribute("class", "addedPlayer")
        input.checked = true;
    }

    playerToBuild.appendChild(input);
    playerToBuild.appendChild(playerLink);
    playerToBuild.appendChild(addOrRemoveButton);

    return playerToBuild;
}

window.onload = () => {
    tournamentInit();
    playerInit();
};