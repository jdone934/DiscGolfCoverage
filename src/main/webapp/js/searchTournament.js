const init = () => {
    document.querySelector("#searchForTournaments").addEventListener("click", searchTournament);
}

const searchTournament = async() => {
    let tournamentResults = document.querySelector("#tournamentSearchResults");
    tournamentResults.innerHTML = "Loading...";

    let tournamentName = document.querySelector("#tournamentSearch").value;
    let tournaments = await getTournaments(tournamentName);
    printTournaments(tournaments);
}

const getTournaments = async(searchTerm) => {
    let url = `searchTournament?tournamentName=${searchTerm}`;
    let searchPromise = fetch(url);
    let tournaments = await searchPromise.then(results => {
        return results.json();
    })

    return tournaments;
}

const printTournaments = tournaments => {
    let tournamentResults = document.querySelector("#tournamentSearchResults");
    tournamentResults.innerHTML = "";

    tournaments.forEach(tournament => {
        let newTournament = tournamentLinkBuilder(tournament, "add");
        tournamentResults.appendChild(newTournament)
    })
}

const addTournament = tournament => {
    let tournamentToAdd = tournamentLinkBuilder(tournament, "remove");

    let addedTournament = document.querySelector("#addedTournament");
    while (addedTournament.childElementCount > 1) {
        let lastChild = addedTournament.lastChild
        addedTournament.removeChild(lastChild);
    }

    addedTournament.appendChild(tournamentToAdd);
}

const tournamentLinkBuilder = (tournament, addOrRemove) => {
    let tournamentToBuild = document.createElement("label");
    tournamentToBuild.setAttribute("class", "tournamentResult d-flex justify-content-center checkbox-label");

    let input = document.createElement("input");
    input.setAttribute("type", "checkbox");

    let tournamentLink = document.createElement("a");
    tournamentLink.setAttribute("href", `/DiscGolfCoverage/viewTournament?id=${tournament.tournamentId}`);
    tournamentLink.setAttribute("target", "_blank");
    tournamentLink.innerHTML = tournament.name;

    let addOrRemoveButton = document.createElement("i");
    addOrRemoveButton.setAttribute("class", "material-icons addOrRemoveButton");

    if (addOrRemove === "add"){
        addOrRemoveButton.innerHTML = "add_circle_outline";
        addOrRemoveButton.addEventListener("click", function () {
            addTournament(tournament);
        });

        tournamentToBuild.appendChild(input);
        tournamentToBuild.appendChild(tournamentLink);
        tournamentToBuild.appendChild(addOrRemoveButton);
    } else {
        input.setAttribute("name", "tournamentForRound");
        input.setAttribute("value", tournament.tournamentId);
        input.setAttribute("class", "addedCourse")
        input.checked = true;

        tournamentToBuild.appendChild(input);
        tournamentToBuild.appendChild(tournamentLink);
    }

    return tournamentToBuild;
}

window.onload = init;