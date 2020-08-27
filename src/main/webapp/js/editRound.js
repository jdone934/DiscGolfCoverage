const editRoundInit = () => {
    let addOrRemoveButtons = document.querySelectorAll(".addOrRemoveButton");
    addOrRemoveButtons.forEach(button => button.addEventListener("click", deletePlayer));

    document.querySelector(".deleteRound").addEventListener("click", deleteRound);
}

const deleteRound = () => {
    let confirmStatus = confirm("Are you sure?");
    if (confirmStatus == true) {
        //console.log("Yes I'm sure");
        let queryString = window.location.search;
        let urlParams = new URLSearchParams(queryString);


        window.location.replace(`deleteRound?roundId=${document.querySelector("#roundId").value}`);
    }
}