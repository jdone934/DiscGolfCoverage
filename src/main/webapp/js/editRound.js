const editRoundInit = () => {
    let addOrRemoveButtons = document.querySelectorAll(".addOrRemoveButton");
    addOrRemoveButtons.forEach(button => button.addEventListener("click", deletePlayer));
}