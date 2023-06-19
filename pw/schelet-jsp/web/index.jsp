<%--
  Created by IntelliJ IDEA.
  User: forest
  Date: 4/29/2021
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Welcome</title>

    <link rel="stylesheet" href="style.css">

    <script type="text/javascript" src="js/schelet-script.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
  </head>
  <body>

    <h2>Select</h2>
    <!-- <button onclick="get()">Get!</button> -->
    <section id="get-section" style="display: none;">
      <h3>Normal table</h3>
      <table id="table" class="table"></table>
      <h3>Paginated table</h3>
      <table id="table-paginated" class="table"></table>
      <button id="previousButton" onclick="prev()">Prev</button>
      <button id="nextButton" onclick="next()">Next</button>
      <h3>List</h3>
      <ul id="list"></ul>
    </section>

    <h2>Add</h2>
    <section id="add-section">
      <form id="add-form">
        <label for="name">Name: </label>
        <input id="name" type="text">
        <br>
        <label for="date">Date: </label>
        <input id="date" type="date">
        <br>
        <button onclick="add()">Add!</button>
      </form>
    </section>

    <h2>Delete</h2>
    <section id="delete-section">
      <select id="dropdown"></select>
      <br>
      <button onclick="myDelete()">Delete!</button>
    </section>

    <h2>Update</h2>
    <section id="update">
      <select id="dropdown2"></select>
      <br>
      <button onclick="showUpdateForm()">Select for update!</button>
      <section id="update-section" style="display: none;">
        <p>Selected Id: <span id="id"></span></p>

        <label for="name2">Name: </label>
        <input id="name2" type="text">
        <br>
        <label for="date2">Date: </label>
        <input id="date2" type="date">
        <br>
        <button onclick="update()">Update!</button>
      </section>
    </section>

    <h2>Filter</h2>
    <section id="filter-section">
      <table id="table-filter" class="table"></table>
      <input id="filter-input" type="text">
      <button onclick="filter()">Filter!</button>
    </section>
  </body>
</html>
