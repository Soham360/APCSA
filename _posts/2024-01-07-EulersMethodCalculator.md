---
toc: true
comments: true
layout: post
title: Euler's Method Calculator
permalink: /eulersmethodcalculator/
courses: {csa: {week: 18} }
type: tangibles
---

<html>
<head>
    <title>Euler's Method</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style.css">
    <style>
        body {
            overflow-x: hidden;
            font-size: 16px;
            font-family: 'Helvetica Neue', 'Helvetica', serif;
        }
        input {
            font-size: 14px;
            font-family: inherit;
            border: none;
            border-bottom: solid 2px #aaa;
        }
        input:focus {
            outline: 0;
            border-bottom-color: #333;
            transition: border-bottom-color 0.5s;
        }
        input.small {
            width: 60px;
        }
        #error {
            color: #ab1c1c;
        }
        table {
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            border: solid 1px #aaa;
            padding: 3px;
            height: 20px;
        }
        .banner {
            position: fixed;
            bottom: 40px;
            right: -50px;
            padding: 10px;
            border: 2px solid black;
            background: white;
            color: black;
            width: 200px;
            text-align: center;
            text-decoration: none;
            transform: rotate(-45deg);
        }
        canvas {
            border: 1px solid #000;
        }
    </style>
</head>
<body>
    <script type="text/x-mathjax-config">
        MathJax.Hub.Config({tex2jax: {inlineMath: [['$','$'], ['\\(','\\)']]}});
    </script>
    <script type="text/javascript" async src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.2/MathJax.js?config=TeX-MML-AM_CHTML"></script>
    <h1>Euler's Method</h1>
    Start: (<input id="xi" type="number" class="small" value=1>, <input id="yi" type="number" class="small" value=1>)
    <br>
    $\frac{dy}{dx}$: <input id="dydx" type="text" value="x^2">
    <br>
    $&Delta;x$: <input id="dx" type="number" class="small" value="0.25">
    <br>
    Steps: <input id="steps" type="number" class="small" value=3>
    <br>
    <button id="update">Update</button>
    <p id="error"></p>
    <table>
        <thead>
            <tr>
                <th>$(x, y)$</th>
                <th>$\frac{dy}{dx}$</th>
                <th>$&Delta;x$</th>
                <th>$&Delta;y = \frac{dy}{dx}&Delta;x$</th>
                <th>$(x+&Delta;x, y+&Delta;y)$</th>
            </tr>
        </thead>
        <tbody id="tbody"></tbody>
    </table>
    <!-- <canvas id="slopeFieldCanvas" width="400" height="400"></canvas> -->
    <script>
        var tbody = document.getElementById('tbody'),
            inputs = {
                xi: document.getElementById('xi'),
                yi: document.getElementById('yi'),
                dydx: document.getElementById('dydx'),
                dx: document.getElementById('dx'),
                steps: document.getElementById('steps'),
            },
            update = document.getElementById('update'),
            error = document.getElementById('error');
        oninput = function() {
            update.style.display = 'inline';
        };
        update.onclick = function() {
            if (/^[()xye\d+\-*/^]$/.test(inputs.dydx.value)) error.textContent = 'Invalid input data.';
            var x = parseFloat(inputs.xi.value),
                y = parseFloat(inputs.yi.value),
                seed = {
                    dydx: inputs.dydx.value.replace(/(\d)([a-z])/g, '$1*$2')
                                        .replace(/([a-z])([a-z])/g, '$1*$2')
                                        .replace(/([a-z])([a-z])/g, '$1*$2')
                                        .replace(/([a-z])(\d)/g, '$1*$2')
                                        .replace(/\^/g, '**')
                                        .replace(/([a-z])/g, 'parseFloat($1)'),
                    dx: inputs.dx.value,
                    steps: inputs.steps.value,
                };
            error.textContent = (seed.dx * seed.steps > 20) ? 'Warning: Linearization\'s efficacy may decrease with many steps or a large increment!' : '';
            while (tbody.firstChild) tbody.removeChild(tbody.firstChild);
            for (i = 0; i < seed.steps; i++) {
                var tr = document.createElement('tr');
                var tds = [];
                for (ii = 0; ii < 5; ii++) tds[ii] = document.createElement('td');
                tds[0].textContent = '(' + pretty(x) + ', ' + pretty(y) + ')';
                var deriv = eval(seed.dydx); // Yes. Yes, I know.
                tds[1].textContent = pretty(deriv);
                tds[2].textContent = seed.dx;
                var dy = deriv * seed.dx;
                tds[3].textContent = pretty(dy);
                x += parseFloat(seed.dx);
                y += dy;
                tds[4].textContent = '(' + pretty(x) + ', ' + pretty(y) + ')';
                for (ii = 0; ii < 5; ii++) tr.appendChild(tds[ii]);
                tbody.appendChild(tr);
            }
            this.style.display = 'none';
        };
        function pretty(n) {
            var D = 100000000000;
            return n.toString().length <= 10 ? n : Math.round(n * D) / D;
        }
        document.addEventListener('DOMContentLoaded', function () {
            const canvas = document.getElementById('slopeFieldCanvas');
            const ctx = canvas.getContext('2d');
            const equation = function (x, y) {
                const dydxExpression = document.getElementById('dydx').value;
                const dydx = eval(dydxExpression.replace(/x/g, x).replace(/y/g, y));
                return dydx;
            };
            const stepSize = 0.2;
            const xRange = canvas.width / 20;
            const yRange = canvas.height / 20;
            function drawSlopeField() {
                for (let x = 0; x <= xRange; x += stepSize) {
                    for (let y = 0; y <= yRange; y += stepSize) {
                        const slope = equation(x / 20, y / 20);
                        const angle = Math.atan(slope);
                        const lineLength = 10;
                        const dx = lineLength * Math.cos(angle);
                        const dy = lineLength * Math.sin(angle);
                        ctx.beginPath();
                        ctx.moveTo(x, y);
                        ctx.lineTo(x + dx, y + dy);
                        ctx.stroke();
                    }
                }
            }
            drawSlopeField();
            });
        update.click();
    </script>
</body>