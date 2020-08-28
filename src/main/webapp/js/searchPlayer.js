const playerInit = () => {
    document.querySelector("#searchForPlayers").addEventListener("click", searchPlayers);
    document.querySelector("#searchForCommentators").addEventListener("click", searchCommentators);
    nextPageInit();
}

const searchPlayers = async() => {
    let resultsSection = document.querySelector("#playerSearchResults")
    resultsSection.innerHTML = "Loading...";

    let playerFirstName = document.querySelector("#playerFirstName").value;
    let playerLastName = document.querySelector("#playerLastName").value;
    let players = await getPlayers(playerFirstName, playerLastName);
    printPlayers(players);
}

const searchCommentators = async() => {
    let resultsSection = document.querySelector("#commentatorSearchResults")
    resultsSection.innerHTML = "Loading...";

    let commentatorFirstName = document.querySelector("#playerFirstName").value;
    let commentatorLastName = document.querySelector("#playerLastName").value;
    let commentators = await getPlayers(commentatorFirstName, commentatorLastName);
    printCommentators(commentators);
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
        let newPlayer = playerLinkBuilder(player, "add", "player");

        resultsSection.appendChild(newPlayer);
    })
}

const printCommentators = commentators => {
    let resultsSection = document.querySelector("#commentatorSearchResults")
    resultsSection.innerHTML = "";
    commentators.forEach(player => {
        let newCommentator = playerLinkBuilder(player, "add", "commentator");

        resultsSection.appendChild(newCommentator);
    })
}

const addPlayer = (player, playerOrCommentator) => {
    if (playerNotInList(player, playerOrCommentator)) {
        let playerToAdd = playerLinkBuilder(player, "remove", playerOrCommentator);

        if (playerOrCommentator === "player") {
            let addedPlayers = document.querySelector("#addedPlayers");
            addedPlayers.appendChild(playerToAdd);
        } else {
            let addedCommentators = document.querySelector("#addedCommentators");
            addedCommentators.appendChild(playerToAdd);
        }
    }
}

const deletePlayer = event => {
    let playerGroup = event.target.parentNode;
    let parent = playerGroup.parentNode;
    parent.removeChild(playerGroup);
}

const playerNotInList = (player, playerOrCommentator) => {
    let notInList = true;
    let addedPlayer;
    if (playerOrCommentator == "player") {
        addedPlayer = document.querySelectorAll(".addedPlayer");
    } else {
        addedPlayer = document.querySelectorAll(".addedCommentator");
    }

    addedPlayer.forEach(testPlayer => {
        if (testPlayer.value == player.playerId) {
            notInList = false;
        }
    })

    return notInList;
}

const playerLinkBuilder = (player, addOrRemove, playerOrCommentator) => {
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
            addPlayer(player, playerOrCommentator);
        });
    } else {
        addOrRemoveButton.innerHTML = "delete";
        addOrRemoveButton.addEventListener("click", deletePlayer);

        if (playerOrCommentator === "player"){
            input.setAttribute("name", "playersInRound");
            input.setAttribute("class", "addedPlayer")
        } else {
            input.setAttribute("name", "commentatorsInRound");
            input.setAttribute("class", "addedCommentator")
        }

        input.setAttribute("value", player.playerId);
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
    editRoundInit();
};