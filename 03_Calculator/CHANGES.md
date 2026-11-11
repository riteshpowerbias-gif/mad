# Changes — 03_Calculator

## PDF Bugs Fixed

### 1. Removed unused Navigation/AppBar/Binding imports
- PDF imported `Navigation`, `AppBarConfiguration`, `ActivityMainBinding`, and referenced `menu_main` — none used, all caused compile errors.
- Removed: `onCreateOptionsMenu` with `menu_main`, `onSupportNavigateUp`, ViewBinding setup.
- Simple `setContentView(R.layout.activity_main)` used instead.

### 2. Fixed `s.equals(null)` → `isEmpty()` checks
- `String.equals(null)` **always returns false** (non-null String object can never equal null).
- Replaced with `s1.isEmpty()` / `s2.isEmpty()` which correctly detects empty input.

### 3. Changed `int num1, num2` → `double num1, num2`
- PDF used `int` which truncated decimal inputs.
- Using `double` and `Double.parseDouble()` to properly handle decimal values like `3.14`.

### 4. Replaced broken margin-based ConstraintLayout with clean LinearLayout
- PDF used hard-coded constraint margins that caused widget overlap on different screen sizes.
- Replaced with a vertical `LinearLayout` with `layout_marginBottom` spacing — simpler and reliable.
- Buttons arranged in two horizontal `LinearLayout` rows using `layout_weight="1"` for equal sizing.

### 5. Added divide-by-zero and mod-by-zero guards
- `doDiv`: checks `num2 == 0` before dividing, displays `"Error: Division by zero"`.
- `doMod`: checks `num2 == 0` before mod, displays `"Error: Mod by zero"`.

### 6. Removed `onCreateOptionsMenu` and `onSupportNavigateUp`
- PDF included these methods referencing `menu_main` and Navigation component — not needed for this app.

### 7. Views initialized in `onCreate`, fields reused
- `e1`, `e2`, `t1` initialized once in `onCreate` via `findViewById`.
- `getNumbers()` reads from class-level fields (no repeated `findViewById` calls).
- `num1`, `num2` stored as class-level `double` fields, populated inside `getNumbers()`.
