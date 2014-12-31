easyleed-tools
==============

Tools to manipulate images for the easyLEED processing software:

http://andim.github.io/easyleed/

Scripts
--------

1. imageconv-easyleed.sh
Bash script to select a number of frames from a larger set and process the filename for easyleed.

Run from terminal within the same folder as the original files to be processed.


ImageJ: SpectaView_to_EasyLEED
-------------------------------

ImageJ plugin to process Omicron SpectaView files and save them into
image sequences that can be opened with EasyLEED.

Installation: copy the file SpectaView_to_EasyLEED.class inside the folder "plugins" within the imagej main folder. Restart ImageJ.

Usage: Run ImageJ. Select menu Plugins, SpectaView to EasyLEED. After adding the required parameters, the SpectraView file is opened. Further processing is possible. A new panel opens up asking if you want to save for EasyLEED. 

Source: SpectaView_to_EasyLEED.java


Contact: 
Nicola Ferralis - ferralis@mit.edu

