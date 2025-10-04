# Jaiking

Jaiking is a Java Swing application that allows users to draw points on a canvas and smooth the resulting polyline using Chaikin's algorithm. The app animates the smoothing process iteratively, providing a visual demonstration of curve refinement.

---

## Features

- **Interactive Drawing:** Click on the canvas to add points.
- **Smooth Curve Generation:** Press **Enter** to start smoothing the drawn polyline using Chaikin's algorithm.
- **Animated Smoothing:** The smoothing process runs for up to 7 iterations, with intermediate results displayed.
- **Clear Canvas:** Press **C** to clear all points and start fresh.
- **Exit:** Press **Escape** to exit the application.

---

## How to Use

1. **Add Points:** Click anywhere on the canvas to add points.
2. **Smooth Curve:** After placing points, press **Enter** to start the smoothing animation.
3. **Clear:** Press **C** to clear all points.
4. **Exit:** Press **Escape** to close the application.

---

## How It Works

- The program collects points clicked by the user.
- When smoothing is triggered, it uses **Chaikin's algorithm** to generate new points that create a smoother curve.
- The algorithm is applied iteratively, and the intermediate curves are shown in real-time.
- If the number of points is 2 or fewer, smoothing is skipped and the original polyline is displayed.

---

## Technical Details

- Built with Java Swing for the GUI.
- Uses `javax.swing.Timer` for timed animation of the smoothing steps.
- Mouse clicks capture points, and keyboard events handle control commands.
- Implements Chaikin’s corner-cutting algorithm for curve smoothing.

---

## Requirements

- Java Development Kit (JDK) 8 or higher
- Compatible with any OS that supports Java Swing (Windows, macOS, Linux)

---

## Compilation & Running

To compile and run the program from the command line:

```bash
javac Jaiking.java
java Jaiking