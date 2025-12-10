// -------------------------------------------------------------
// Sol LeWitt–Inspired Wall Drawing (Concentric Rounded Arches)
// Based on your reference image of Wall Drawing 579–style bands
// This version draws 3 nested "arches" with semicircular tops.
// -------------------------------------------------------------

const canvas = document.getElementById("lewitt");
const ctx = canvas.getContext("2d");

/**
 * Draw a LeWitt-style arch:
 * A rectangle with a semicircle attached at the top.
 *
 * @param {number} x - Left coordinate of the arch
 * @param {number} y - Top coordinate (0 touches the canvas top)
 * @param {number} width - Width of the arch (affects curvature)
 * @param {number} height - Straight vertical portion
 * @param {string} color - Fill color
 */
function drawRoundedArch(x, y, width, height, color) {
  const radius = width / 2; // The semicircle radius must be half the width

  ctx.fillStyle = color;
  ctx.beginPath();

  // Draw top semicircle:
  // Start from left side -> arc over to right side.
  ctx.arc(
    x + radius,     // center X
    y + radius,     // center Y
    radius,         // radius
    Math.PI,        // start angle (180°)
    0               // end angle (0°)
  );

  // Draw straight vertical sides downward
  ctx.lineTo(x + width, y + height);
  ctx.lineTo(x, y + height);

  ctx.closePath();
  ctx.fill();
}

/**
 * Draws all three concentric arches.
 * Largest → smallest, so the smaller ones appear on top.
 */
function drawLeWittArches() {
  const W = canvas.width;
  const centerX = W / 2;

  // ---------------------------------------------------------
  // Proportions chosen to visually match your reference photo:
  //
  // Blue (outer):   touches top and is the tallest + widest
  // Red (middle):   shorter and narrower
  // Yellow (inner): shortest and thinnest
  //
  // y-position offsets simulate the “stepped” vertical spacing
  // seen in the real installation.
  // ---------------------------------------------------------

  const blue = {
    width: 520,
    height: 760,
    y: 0 // touches the top of the canvas
  };

  const red = {
    width: 380,
    height: 620,
    y: 120 // sits lower than blue
  };

  const yellow = {
    width: 180,
    height: 500,
    y: 220 // sits lower than red + much thinner
  };

  // Draw in order: largest first
  const arches = [
    { ...blue, color: "#2096CE" },
    { ...red, color: "#E04967" },
    { ...yellow, color: "#F4D30C" }
  ];

  // Draw each arch centered on the canvas
  arches.forEach(a => {
    drawRoundedArch(centerX - a.width / 2, a.y, a.width, a.height, a.color);
  });
}

drawLeWittArches();
