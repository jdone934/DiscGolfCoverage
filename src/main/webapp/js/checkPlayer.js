const init = () => {
    document.querySelector("#searchForCourses").addEventListener("click", searchCourses);
}

const searchCourses = async() => {
    let resultsSection = document.querySelector("#searchResults")
    resultsSection.innerHTML = "Loading...";

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
        let newCourse = courseLinkBuilder(course, "add");

        resultsSection.appendChild(newCourse);
    })
}

const addCourse = course => {
    if (courseNotInList(course)) {
        let courseToAdd = courseLinkBuilder(course, "remove");

        let addedCourses = document.querySelector("#addedCourses");
        addedCourses.appendChild(courseToAdd);
    }
}

const deleteCourse = event => {
    let courseGroup = event.target.parentNode;
    let parent = courseGroup.parentNode;
    parent.removeChild(courseGroup);
}

const courseNotInList = course => {
    let notInList = true;
    let addedCourses = document.querySelectorAll(".addedCourse");

    addedCourses.forEach(testCourse => {
        if (testCourse.value == course.courseId) {
            notInList = false;
        }
    })

    return notInList;
}

const courseLinkBuilder = (course, addOrRemove) => {
    let courseToBuild = document.createElement("label");
    courseToBuild.setAttribute("class", "courseResult d-flex justify-content-center checkbox-label");

    let input = document.createElement("input");
    input.setAttribute("type", "checkbox");

    let courseLink = document.createElement("a");
    courseLink.setAttribute("href", `/DiscGolfCoverage/viewCourse?id=${course.courseId}`);
    courseLink.setAttribute("target", "_blank");
    courseLink.innerHTML = course.name;

    let addOrRemoveButton = document.createElement("i");
    addOrRemoveButton.setAttribute("class", "material-icons addOrRemoveButton");

    if (addOrRemove === "add"){
        addOrRemoveButton.innerHTML = "add_circle_outline";
        addOrRemoveButton.addEventListener("click", function () {
            addCourse(course);
        });
    } else {
        addOrRemoveButton.innerHTML = "delete";
        addOrRemoveButton.addEventListener("click", deleteCourse);

        input.setAttribute("name", "coursesForTournament");
        input.setAttribute("value", course.courseId);
        input.setAttribute("class", "addedCourse")
    }

    courseToBuild.appendChild(input);
    courseToBuild.appendChild(courseLink);
    courseToBuild.appendChild(addOrRemoveButton);

    return courseToBuild;
}

window.onload = init;