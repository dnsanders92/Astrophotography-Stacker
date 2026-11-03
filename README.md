# Astrophotography Stacker

A Java console app that takes a folder of raw astrophotography frames, debayers them, and mean stacks the aligned result into a single 16-bit image. The image processing is written from scratch in a small package called PixelForge, with no image processing libraries.

## Results

**Raw mosaic vs debayered**

![Raw vs debayered](docs/[orion-debayer-image].png)
*Orion Nebula, a single raw frame (left) and the same frame after debayering (right).*

**Single frame vs stack**

![Andromeda single frame vs stack](docs/m31_wide.png)
*Andromeda Galaxy, a single 30 s frame (left) and the mean stack of 128 frames, 64 minutes total integration (right).*

**Noise reduction up close**

![Andromeda close up](docs/m31_close.png)
*The same region zoomed in, single frame (left) and stack (right).*

All images were auto-stretched in Siril for viewing. The saved files are linear and unstretched.

## How it works

1. Loads 16-bit single channel TIFF frames (RGGB Bayer mosaic) from a folder, one at a time.
2. Debayers each frame with bilinear interpolation and saves it as a 16-bit TIFF with a `debayered_` prefix.
3. Pauses while the frames are aligned (registered) using external software. Native alignment is planned.
4. Mean stacks the aligned `registered_` frames into a running accumulator, so memory use stays flat however many frames there are.
5. Saves the result as `stackedImage.tiff`, still linear and unstretched.

### Debayering

Each frame is an RGGB Bayer mosaic, where every pixel records only one colour. The mosaic is first split into sparse red, green and blue planes, then the missing values are filled by bilinear interpolation. Red and blue use a horizontal pass followed by a vertical pass. Green averages the neighbours that exist, so edges and corners are handled by counting neighbours.

### Stacking

Frames are loaded one at a time and summed into a single accumulator, then divided by the frame count. Only one frame is held in memory at any moment, so the number of frames is limited by disk space, not RAM. Averaging N frames reduces random noise by roughly the square root of N.

## Image types

PixelForge uses two image types instead of passing raw arrays around.

- `RawImage` holds a single channel Bayer mosaic as a 2D array of 16-bit values.
- `RGBImage` holds three colour planes, stored as `[channel][row][column]`.

Both expose `get(x, y)` (or `get(x, y, channel)`) and derive their width and height from the data, so the dimensions can't disagree with the pixels. The types also make the pipeline safer: debayering takes a `RawImage` and returns an `RGBImage`, so the compiler stops you passing an undebayered frame into something that expects colour.

## What is written in this project

- Folder and file handling
- TIFF loading and saving at 16 bits
- RGGB debayering with bilinear interpolation
- Mean stacking
- The pipeline in `Main`

Frame alignment is currently done in external software (Siril) and will be replaced by a native aligner.

## Roadmap

- Unit tests for debayering on small hand calculated arrays
- Binning
- Split stacking from file loading to keep PixelForge file free
- Multithreaded stacking
- Sigma clipping
- Star detection and native registration
- FITS support
- Image editing tools (stretch, background extraction)

## Licence

MIT
