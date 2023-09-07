---
toc: true
comments: true
layout: post
title: Nitro Type
permalink: /nitrotype/
courses: {csa: {week: 3} }
type: tangibles
---

<html>
<head>
  <style>
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
    #accuracy-counter {
      font-size: 18px;
      margin-top: 20px;
    }
    #wpm-counter {
      font-size: 18px;
      margin-top: 20px;
    }
    #result {
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
  <!-- Importing table and sorting code -->
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
  <script type="text/javascript" language="javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>var define = null;</script>
  <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
</head>
<body>
  <div id="game-container">
    <p id="paragraph-display"></p>
    <textarea id="input-field" rows="4"></textarea>
    <p id="timer"></p>
    <p id="accuracy-counter">Accuracy: 0%</p>
    <p id="wpm-counter">Words per Minute: 0</p>
    <p id="result"></p>
  </div>

  <script>
    var totalWords = 0;

    function calculateWPM(elapsedTime, totalLetters) {
      // Assuming an average word length of 5 characters
      var totalWordsTyped = totalLetters / 5;

      // Calculate words per minute
      var wpm = Math.round((totalWordsTyped / (elapsedTime / 60)));

      return wpm;
    }

    function updateWPM(elapsedTime) {
      var wpm = calculateWPM(elapsedTime, inputField.value.length);
      document.getElementById("wpm-counter").textContent = "Words per Minute: " + wpm;
    }

    async function fetchRandomWord() {
        const url = 'https://free-random-word-generator-api.p.rapidapi.com/random-word';
        const options = {
            method: 'GET',
            headers: {
                'X-RapidAPI-Key': '8402362dd5mshc2923fbd8d7b266p1b0f84jsn4229cc0ec9ac',
                'X-RapidAPI-Host': 'free-random-word-generator-api.p.rapidapi.com'
            }
        };

        try {
            const response = await fetch(url, options);
            const result = await response.text();
            return result;
        } catch (error) {
            console.error(error);
            return null;
        }
    }

    async function loadNewParagraph() {
      var newWord = await fetchRandomWord() + await fetchRandomWord() + await fetchRandomWord() + await fetchRandomWord() + await fetchRandomWord();
      newWord = newWord.replaceAll("\"", "");
      if (newWord) {
        currentParagraph = newWord.trim();
        currentCharIndex = 0;
        startTime = null;
        timer.textContent = "Time: 0.00 seconds";
        inputField.value = "";
        paragraphDisplay.innerHTML = currentParagraph;
        inputField.style.display = "block";
        document.getElementById("accuracy-counter").textContent = "Accuracy: 0%";
        resultElement.textContent = "";
      } else {
        alert("Failed to fetch a new word. Please try again later.");
      }
    }

    var currentParagraph = '';
    var currentCharIndex = 0;
    var startTime = null;
    var timerInterval = null;

    var paragraphDisplay = document.getElementById("paragraph-display");
    var inputField = document.getElementById("input-field");
    var timer = document.getElementById("timer");
    var resultElement = document.getElementById("result");

    // Function to randomly select a paragraph
    function selectRandomParagraph() {
      return paragraphs[Math.floor(Math.random() * paragraphs.length)];
    }

    function startTimer() {
      timerInterval = setInterval(updateTimer, 10);
    }

    function stopTimer() {
      clearInterval(timerInterval);

      setTimeout(()=> {
         var username = prompt('Congratulations! You completed the paragraph in ' + actualTime + ' seconds! Enter your name:');
         location.reload();
      }, 1000);
    }

    function updateTimer() {
      var currentTime = new Date();
      var elapsedTime = Math.floor((currentTime - startTime) / 10);
      var actualTime = (elapsedTime / 100).toFixed(2);
      timer.textContent = "Time: " + actualTime + " seconds";
    }

    function highlightText(enteredText) {
      var paragraphText = currentParagraph.slice(0, enteredText.length);

      var highlightedText = '';
      for (var i = 0; i < enteredText.length; i++) {
        if (enteredText[i] === paragraphText[i]) {
          highlightedText += '<span style="background-color: yellow;">' + enteredText[i] + '</span>';
        } else {
          highlightedText += enteredText[i];
        }
      }

      paragraphDisplay.innerHTML = highlightedText + currentParagraph.slice(enteredText.length);
    }


    inputField.addEventListener("input", function(event) {
      var enteredText = event.target.value;
      var totalLetters = enteredText.length;
      var highlightedLetters = 0;

      if (!startTime) {
        startTime = new Date();
        startTimer();
      }

      highlightText(enteredText);

      var paragraphText = currentParagraph.slice(0, totalLetters);

      for (var i = 0; i < totalLetters; i++) {
        if (enteredText[i] === paragraphText[i]) {
          highlightedLetters++;
        }
      }

      var accuracy = Math.round((highlightedLetters / totalLetters) * 100);
      document.getElementById("accuracy-counter").textContent = "Accuracy: " + accuracy + "%";

      if (enteredText === currentParagraph || enteredText.length >= currentParagraph.length) {
        paragraphDisplay.textContent = "You Win!";
        inputField.style.display = "none";
        stopTimer();
        setTimeout(loadNewParagraph, 1000);
        
        var currentTime = new Date();
        var elapsedTime = (currentTime - startTime) / 1000; // Convert to seconds
        updateWPM(elapsedTime);
      }
    });
    loadNewParagraph();
  </script>
</body>
</html>
