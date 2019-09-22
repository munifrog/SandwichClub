## Introduction

Android application that parses a JavaScript Object Notation (JSON) file that contains details
for various sandwiches and displays details for each sandwich. It was originally meant as a
demonstration for building layouts and populating and manipulating recurring fields.

## Overview

This app parses a hard-coded (shipped with the code) JSON file that contains details for various
sandwiches. A list of all sandwiches is displayed using a linear recycler view. When the user
clicks on a sandwich name, more specific details are displayed, including an image, alternate names,
ingredients, place of origin, and description. The app also trims out any information sections for
which the details are not provided, such as alternate names for some sandwiches.

## Getting started / Usage

The files for this project were built using Android Studio, so you will likely have the easiest
time duplicating the original behavior using the same.

 1. Clone this repository
    * `git clone https://github.com/munifrog/SandwichClub.git`
 1. Open this directory using Android Studio
 1. Launch the app on an emulator or device

## License
This project started as a task within the [Udacity Android Developer Nanodegree Course](https://www.udacity.com/course/android-developer-nanodegree-by-google--nd801)
and Udacity (or Google) owns the rights for the project idea.

While I personally prefer indirect sources for finding solutions to puzzles, you may learn from
what I have done, if that helps. Just be careful to avoid plagiarism if you are also taking this
course and looking for answers.

If, on the other hand, you want to build on what I have done, that is also welcome.
