# vexx

### Chain of actions

1. Any GUI action triggers a listener (key-pressed, key-typed, selection...)
2. Listener reads the data from a widget
   and modifies the corresponding (a ref) data-structure in vol.clj (volatile data)
3. Those data structures (refs) have watchers (callbacks) which updates the rest
   of the view accordingly.

### Table

+--------------------+-------------------+---------------------------+
| widget's purpose   |  vol ref          | ref's purpose             |
+--------------------+-------------------+---------------------------+
| content-data       | m-content-data    |
| tags-data          | m-tags-data       |
| ...                |                   |
+ -------------------+-------------------+---------------------------+
|                    |  m-current-path   | keep current path         |
|                    |  m-listbox-sel    | keep selected item name   |
|                    |  ...              |                           |
+ -------------------+-------------------+---------------------------+		      

#### Examples:
1. Main listbox selection change triggers the listbox....

   
   
FIXME: description

## Installation

Download from http://example.com/FIXME.

## Usage

FIXME: explanation

    $ java -jar vexx-0.1.0-standalone.jar [args]

## Options

FIXME: listing of options this app accepts.

## Examples

...

### Bugs

...

### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
