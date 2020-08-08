const init = () => {
    document.querySelector("#searchType").addEventListener("change", updateForm);
}

const updateForm = event => {
    let searchType = event.target.value;
    hideForm();
    showSearchTypeFields(searchType);
}

const hideForm = () => {
    let formFields = document.querySelectorAll(".searchField");

    for(i = 0; i < formFields.length; i++) {
        formFields[i].style.display = "none";
    }
}

const showSearchTypeFields = searchType => {
    let formFields = document.querySelectorAll("." + searchType);

    for(i = 0; i < formFields.length; i++) {
        formFields[i].style.display = "block";
    }
}

window.onload = init;