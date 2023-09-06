---
toc: true
comments: true
layout: post
title: Nitro Type
permalink: /nitrotype/
courses: {csa: {week: 3} }
type: hacks
---

<html>
<head>
  <style>
    /* styling */
    body {
      text-align: center;
    }
    #game-container {
      width: 400px;
      margin: 0 auto;
    }
    #paragraph-display {
      font-size: 24px;
      margin-bottom: 20px;
    }
    #input-field {
      font-size: 18px;
      padding: 5px;
      width: 100%;
      box-sizing: border-box;
    }
    #timer {
      font-size: 18px;
      margin-top: 20px;
    }
    .result {
      border-radius: 12px;
      border: 1px solid black;
      padding: 20px;
      max-width: 300px;
      flex-shrink: 0;
    }
  </style>
</head>
<body>
  <!-- div for the game. Includes the paragraph being displayed, the inputs, and the timer -->
  <div id="game-container">
    <p id="paragraph-display">Start typing...</p>
    <input type="text" id="input-field" autofocus>
    <p id="timer"></p>
  </div>

  <script>
    // This is the paragraph bank
    var paragraphs = [
      "Determine retiree thought improve truth active",
      "Polish curve stun addicted extreme affect present",
      "Certain dramatic greeting order twin fade",
      "Relevance glimpse grain debt tell morning",
      "Genetic suggest reduce demonstrate lift make",
      "Entry circulation supply accountant admire spot",
      "Assignment bracket satellite agony equal afford",
      "Wash throw mistreat measure competition education",
      "Tolerate"
    ];

    // This is the counter for how many paragraphs have been completed
    var paragraphsComplete = 0;

    // This generates a random integer from 0 to the number of paragraphs - 1
    var currentParagraphIndex = Math.floor(Math.random() * paragraphs.length);

    // This uses the random integer from above as an index for a random paragraph from the paragraph bank
    var currentParagraph = paragraphs[currentParagraphIndex];

    // Split the current paragraph into an array of words
    var currentWords = currentParagraph.split(" ");

    // Track the current word index within the paragraph
    var currentWordIndex = 0;

    // Track the current letter index within the current word
    var currentLetterIndex = 0;

    // This sets the startTime and the timerInterval to undefined values
    var startTime = null;
    var timerInterval = null;

    // This is the code that replaces the previous paragraph
    var paragraphDisplay = document.getElementById("paragraph-display");
    // This gets the input from the text box
    var inputField = document.getElementById("input-field");
    // This is the code that allows the timer to update
    var timer = document.getElementById("timer");

    // This displays the random paragraph
    paragraphDisplay.textContent = currentParagraph;

    // Function starts as soon as it detects an input
    inputField.addEventListener("input", function(event) {
      var enteredText = event.target.value;

      // Starts the timer after the user inputs something into the textbox
      if (!startTime) {
        startTime = new Date();
        startTimer();
      }

      // Verify if the entered letter is correct
      if (enteredText === currentWords[currentWordIndex][currentLetterIndex]) {
        // Highlight the correct letter in yellow
        var paragraphText = paragraphDisplay.textContent;
        paragraphText = paragraphText.substring(0, currentLetterIndex) + '<span style="background-color: yellow;">' + currentWords[currentWordIndex][currentLetterIndex] + '</span>' + paragraphText.substring(currentLetterIndex + 1);
        paragraphDisplay.innerHTML = paragraphText;

        currentLetterIndex++;

        // If all letters in the current word have been typed correctly
        if (currentLetterIndex === currentWords[currentWordIndex].length) {
          // Move to the next word
          currentWordIndex++;
          currentLetterIndex = 0;

          // Clear the text box
          inputField.value = "";

          // If the user has completed the entire paragraph
          if (currentWordIndex === currentWords.length) {
            paragraphsComplete++;

            // Display a "You Win!" message
            paragraphDisplay.textContent = "You Win!";
            // Hide the text box
            inputField.style.display = "none";
            // Stop the timer
            stopTimer();
          }
        }
      }
    });

    // Starts repeated action (timer) that updates every 10 milliseconds (0.01)
    function startTimer() {
      timerInterval = setInterval(updateTimer, 10);
    }

    // Stops the timer when it is called. It is called after the user has typed the entire paragraph
    function stopTimer() {
      // Makes the action above (timer) stop
      clearInterval(timerInterval);

      // Waits 1 second after the game is complete. Then it asks for the user's name.
      setTimeout(()=> {
         var username = prompt('Congratulations! You completed the paragraph in ' + actualTime + ' seconds! Enter your name:');
         // Save or process the username as needed
         // Then reload the page
         location.reload();
      }, 1000);
    }

    // This function updates the timer every millisecond
    function updateTimer() {
      var currentTime = new Date();
      // Subtract the currentTime from the startTime to calculate the elapsed time in hundredths of a second
      var elapsedTime = Math.floor((currentTime - startTime) / 10);
      // Converts the elapsed time to seconds with two decimal places
      var actualTime = (elapsedTime / 100).toFixed(2);
      // Displays on the frontend
      timer.textContent = "Time: " + actualTime + " seconds";
    }
  </script>
</body>
</html>
