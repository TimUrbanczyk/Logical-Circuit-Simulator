# Logical Circuit Simulator

A simple digital logic simulator that lets you build and test circuits using basic logic gates.

## Components

### Power Sources (Switches)
- Click to place a switch in Draw Mode
- In Edit Mode, click a switch to toggle between:
  - ON (1, HIGH) - powers connected gates
  - OFF (0, LOW) - no power to connected gates

### Available Gates
- **AND Gate**
  - Outputs 1 only when both inputs receive power
  - Otherwise outputs 0
  
- **OR Gate**
  - Outputs 1 if at least one input receives power
  - Outputs 0 only when no inputs receive power
  
- **NOT Gate**
  - Flips the input: if it receives power, outputs no power
  - If it receives no power, outputs power

## How It Works

### Draw Mode (Default)
1. Click on the component you want to add (switch or gate)
2. Click anywhere on the workspace to place it
3. To connect components:
   - Click and hold on an output pin
   - Drag to an input pin
   - Release to create the connection
4. To delete:
   - Click on a component or wire to remove it

### Edit Mode
1. Press the Edit Mode button to switch modes
2. Click on switches to toggle their power state
3. Watch how the power flows through your circuit:
   - Powered connections show as highlighted
   - Gates process their inputs and update outputs instantly
4. Press Edit Mode again to return to Draw Mode

## Tips
- Start with power sources (switches)
- Connect outputs to inputs
- One output can connect to multiple inputs
- Make sure all inputs are connected
- Use Edit Mode to test your circuit's logic 