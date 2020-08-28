const nextPageInit = () => {
    document.querySelector(".nextPage").addEventListener("click", goToNextPage);
}

const goToNextPage = event => {
    let nextPageServlet = event.target.id;
    let pagePath = `${nextPageServlet}`;
    window.location.replace(pagePath);
}