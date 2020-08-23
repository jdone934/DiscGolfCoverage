const init = () => {
    document.querySelector("#searchForCourses").addEventListener("click", searchCourses);
}

const searchCourses = async() => {
    let courseName = document.querySelector("#courseSearch").value;
    let courses = await getCourses(courseName);
    printCourses(courses);
}

const getCourses = async(searchTerm)=> {
    let url = `searchCourse?courseName=${searchTerm}`;
    let searchPromise = fetch(url);
    let courses = await searchPromise.then(results => {
        return results.json();
    })

    return courses;
}

const printCourses = courses => {
    let resultsSection = document.querySelector("#searchResults")
    resultsSection.innerHTML = "";
    courses.forEach(course => {
        let newCourse = document.createElement("div");
        newCourse.setAttribute("class", "courseResult d-flex justify-content-center");

        let courseLink = document.createElement("a");
        courseLink.setAttribute("href", `/DiscGolfCoverage/viewCourse?id=${course.courseId}`);
        courseLink.setAttribute("target", "_blank");
        courseLink.innerHTML = course.name;

        newCourse.appendChild(courseLink);
        resultsSection.appendChild(newCourse);
    })
}

window.onload = init;