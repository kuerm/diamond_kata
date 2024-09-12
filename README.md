# diamond_kata

## Analysis

- One function that takes a character and returns a multiple line string containing all characters until the input
  character.
- The first line contains the letter 'A' and the last line contains the input character.
- The lines are centered and separated by spaces
- Characters != A-Z are invalid inputs
- The given input is called seed
- The seed can be upper or lower case
- The function should return 'Invalid Input' if the input is invalid

### Functions

- String diamond(char seed)

### Acceptance test cases

- 'A'
  Input 'A'
  Output 'A'

- 'C'
  Input 'C'
  Output

```
  A
 B B
C   C
 B B
  A
```

### Architecture

- I want to implement it IOSP way
- I will use ATDD to implement the solution