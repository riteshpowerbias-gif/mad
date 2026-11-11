# Changes — 06_BMICalculator

## PDF Bugs & Enhancements Fixed

### 1. Replaced Legacy Support Library with AndroidX
- Replaced deprecated `import android.support.v7.app.AppCompatActivity;` with `import androidx.appcompat.app.AppCompatActivity;`.

### 2. Added Division-by-Zero and Negative Height Protection
- The original PDF implementation did not check for `height <= 0`, which caused a division-by-zero (`Infinity` / crash).
- Added an explicit validation check `if (height <= 0)` prompting the user: `"Height must be greater than 0."`.

### 3. Added Weight <= 0 Protection
- Added validation guard `if (weight <= 0)` prompting: `"Weight must be greater than 0."`.

### 4. Input Validation & Error Handling
- Added check for empty fields (`weightStr.isEmpty() || heightStr.isEmpty()`).
- Added `try/catch` block for `NumberFormatException` to safely handle non-numeric or malformed decimal input.

### 5. Added BMI Category Classification and Dynamic Color Coding
- The PDF implementation only calculated and displayed a raw floating-point number.
- Added BMI category evaluation based on standard WHO ranges:
  - **Underweight**: BMI < 18.5 (Blue `#2196F3`)
  - **Normal weight**: 18.5 <= BMI < 25.0 (Green `#4CAF50`)
  - **Overweight**: 25.0 <= BMI < 30.0 (Orange `#FF9800`)
  - **Obese**: BMI >= 30.0 (Red `#F44336`)

### 6. Updated UI Layout
- Added a dedicated `TextView` (`@+id/textViewCategory`) to `activity_main.xml` to present the categorized diagnosis to the user.
- Enhanced styling with clean vertical linear layout and proper margin spacing.

### 7. Verification of Formula
- Formula: `BMI = weight(kg) / (height(m) * height(m))`
- Verification test case:
  - Weight: 71 kg
  - Height: 1.84 m
  - $BMI = 71 / (1.84 \times 1.84) = 71 / 3.3856 \approx 20.97$
  - Result: 20.97 matches "Normal weight" (18.5 - 24.9) — verified correct.
