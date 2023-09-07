---
toc: true
comments: true
layout: post
title: Pair Programming
type: tangibles
courses: { csa: {week: 3} }
---

## Feedback (ChatGPT)
Your code appears to be a web page for a typing game. I'll review it for you:

1. HTML Structure:
- The HTML structure is clean and well-organized.
- You have included the necessary elements like head, body, and various div elements with appropriate IDs and classes.

2. CSS Styling:
- The CSS styles are defined in the head section. This is good for keeping the styles separate from the HTML content.
- The styles are appropriately named and organized. Each rule is easy to understand.
- The use of IDs and classes for styling is clear and makes the code easy to maintain.

3. External Dependencies:
- You're using external resources like jQuery and DataTables. This is fine, but be aware that relying on external resources can introduce dependencies and potential points of failure.

4. JavaScript:
- The JavaScript code is well-structured and organized.
- You have used async/await correctly for fetching random words.
- Functions are appropriately named and follow a logical flow.
- You've handled potential errors when fetching a new word.

5. Variable Declarations:
- You've declared variables using var, which is perfectly fine. However, in modern JavaScript, you might use const and let instead of var for better scoping.

6. Error Handling:
- You've added error handling for failed fetch requests, which is a good practice.

7. Event Handling:
- You've attached event listeners for user input. The logic for updating the timer, highlighting text, and checking for accuracy seems correct.

8. Comments:
- You have added a front-matter section at the beginning, which is typical for Jekyll or similar static site generators. This helps in specifying metadata for the page.
- You haven't included any comments within the JavaScript code. Adding comments can help others (and yourself in the future) understand the code's functionality.

9. Security Concerns:
- Be cautious about exposing API keys directly in the code, especially if the code will be publicly accessible. It's better to handle API keys server-side or through environment variables.

10. Overall:
- The code is well-written, organized, and appears to work as intended.

Remember, this review is based solely on the provided code snippet, and I don't have context on how this code interacts with other parts of your project. Always ensure you're following best practices and security measures, especially when deploying code on the web.

---

## Draft
Nitro Type - a game where the user is given a string of random words and they have to correctly type them as fast as possible. Based on the time it takes them, we then calculate how many words per minute they'd be able to type and maybe save their own high scores locally in a leaderboard(?)

---

CSA Code, Code, Coding assignment.

Review. Teacher posts for JS Itunes API, JS Input, JS Output w/jquery, JS Output w/API.

Option 1. Add to the lessons and making code cells and hacks for better breakdown and understanding
Option 2. Combine all the concepts into something unique and you feel is awesome.

- [x] Make a plan on what you and your Pair plan to do, this must exist by Monday night. You should have proof in live review of a draft in GitHub Issues history.
- [ ] Ask me questions and interact with me on Slack or live on Mon, Tue…. particularly interact with me when you have posted a draft of your plan. This would be considered Collaboration. Try to get feedback from me on Plan.
- [ ] Code Must have Data!!!
- [x] Must have Input.
- [x] Must have Output.
- [x] Input must impact Output.
- [x] Must be focused on JavaScript.
- [x] Bring these into GitHub pages repo as Hacks or Tangibles on you Time Box page.
- [x] Review code with ChatGPT, perhaps ask "Can you provide a review of this code?" Then paste code. Currate response into page(s).
- [ ] Plan your Pair Showcase, know exactly what you will present. Try to do it within 3 minutes.
- [ ] Build a review ticket in Time Box page to accelerate discussion.
- [ ] Be ready for Live review on Wed through Friday

---